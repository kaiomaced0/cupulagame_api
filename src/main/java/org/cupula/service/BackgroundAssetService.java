package org.cupula.service;

import java.util.List;
import java.util.stream.Collectors;

import org.cupula.dto.backgroundasset.request.AssignBackgroundAssetRequest;
import org.cupula.dto.backgroundasset.request.CreateBackgroundAssetRequest;
import org.cupula.dto.backgroundasset.request.UpdateBackgroundAssetRequest;
import org.cupula.dto.backgroundasset.response.BackgroundAssetChangedWebhookPayload;
import org.cupula.dto.backgroundasset.response.BackgroundAssetResponse;
import org.cupula.model.auth.Usuario;
import org.cupula.model.configuracoes.BackgroundAsset;
import org.cupula.model.documento.Documento;
import org.cupula.repository.auth.UsuarioRepository;
import org.cupula.repository.configuracoes.BackgroundAssetRepository;
import org.cupula.repository.pessoa.DocumentoRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class BackgroundAssetService {

    @Inject
    BackgroundAssetRepository backgroundAssetRepository;

    @Inject
    DocumentoRepository documentoRepository;

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    BackgroundAssetWebhookService webhookService;

    public List<BackgroundAssetResponse> listAll() {
        return backgroundAssetRepository.listAll().stream()
                .map(BackgroundAssetResponse::from)
                .collect(Collectors.toList());
    }

    public BackgroundAssetResponse findById(Long id) {
        if (id == null) {
            return null;
        }
        return BackgroundAssetResponse.from(backgroundAssetRepository.findById(id));
    }

    @Transactional
    public BackgroundAssetResponse create(CreateBackgroundAssetRequest request) {
        if (request == null || request.nome() == null || request.nome().isBlank() || request.documentoId() == null) {
            throw new IllegalArgumentException("Nome e documentoId sao obrigatorios");
        }

        Documento documento = documentoRepository.findById(request.documentoId());
        if (documento == null) {
            throw new IllegalArgumentException("Documento nao encontrado");
        }

        BackgroundAsset backgroundAsset = new BackgroundAsset();
        backgroundAsset.setNome(request.nome());
        backgroundAsset.setDocumento(documento);

        backgroundAssetRepository.persist(backgroundAsset);
        return BackgroundAssetResponse.from(backgroundAsset);
    }

    @Transactional
    public BackgroundAssetResponse update(Long id, UpdateBackgroundAssetRequest request) {
        if (id == null || request == null) {
            throw new IllegalArgumentException("Dados obrigatorios faltando");
        }

        BackgroundAsset backgroundAsset = backgroundAssetRepository.findById(id);
        if (backgroundAsset == null) {
            return null;
        }

        if (request.nome() != null && !request.nome().isBlank()) {
            backgroundAsset.setNome(request.nome());
        }

        if (request.documentoId() != null) {
            Documento documento = documentoRepository.findById(request.documentoId());
            if (documento == null) {
                throw new IllegalArgumentException("Documento nao encontrado");
            }
            backgroundAsset.setDocumento(documento);
        }

        return BackgroundAssetResponse.from(backgroundAsset);
    }

    @Transactional
    public boolean delete(Long id) {
        if (id == null) {
            return false;
        }

        BackgroundAsset backgroundAsset = backgroundAssetRepository.findById(id);
        if (backgroundAsset == null) {
            return false;
        }

        List<Usuario> usuariosComAsset = usuarioRepository.find("backGroundAsset.id", id).list();
        for (Usuario usuarioComAsset : usuariosComAsset) {
            usuarioComAsset.setBackGroundAsset(null);
        }

        backgroundAssetRepository.delete(backgroundAsset);
        return true;
    }

    public BackgroundAssetResponse getBackgroundAssetByLoggedUser(String nickName) {
        if (nickName == null) {
            return null;
        }

        Usuario usuario = usuarioRepository.findByNickName(nickName);
        if (usuario == null) {
            return null;
        }

        return BackgroundAssetResponse.from(usuario.getBackGroundAsset());
    }

    @Transactional
    public BackgroundAssetResponse assignBackgroundAssetToUser(AssignBackgroundAssetRequest request) {
        if (request == null || request.usuarioId() == null || request.backgroundAssetId() == null) {
            throw new IllegalArgumentException("usuarioId e backgroundAssetId sao obrigatorios");
        }

        Usuario usuario = usuarioRepository.findById(request.usuarioId());
        if (usuario == null) {
            throw new IllegalArgumentException("Usuario nao encontrado");
        }

        BackgroundAsset backgroundAsset = backgroundAssetRepository.findById(request.backgroundAssetId());
        if (backgroundAsset == null) {
            throw new IllegalArgumentException("BackgroundAsset nao encontrado");
        }

        usuario.setBackGroundAsset(backgroundAsset);

        webhookService.notifyBackgroundChanged(new BackgroundAssetChangedWebhookPayload(
                usuario.getId(),
                usuario.getNickName(),
                backgroundAsset.getId(),
                backgroundAsset.getNome(),
                backgroundAsset.getDocumento() == null ? null : backgroundAsset.getDocumento().getId(),
                backgroundAsset.getDocumento() == null ? null : backgroundAsset.getDocumento().getCaminho()));

        return BackgroundAssetResponse.from(backgroundAsset);
    }
}
