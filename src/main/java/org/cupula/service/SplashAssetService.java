package org.cupula.service;

import java.util.List;
import java.util.stream.Collectors;

import org.cupula.dto.splashasset.request.CreateSplashAssetRequest;
import org.cupula.dto.splashasset.request.UpdateSplashAssetRequest;
import org.cupula.dto.splashasset.response.SplashAssetResponse;
import org.cupula.model.auth.Usuario;
import org.cupula.model.configuracoes.SplashAsset;
import org.cupula.model.documento.Documento;
import org.cupula.model.entities.player.Player;
import org.cupula.model.entities.player.PlayerStatus;
import org.cupula.repository.auth.UsuarioRepository;
import org.cupula.repository.configuracoes.SplashAssetRepository;
import org.cupula.repository.pessoa.DocumentoRepository;
import org.cupula.repository.player.PlayerRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class SplashAssetService {

    @Inject
    SplashAssetRepository splashAssetRepository;

    @Inject
    DocumentoRepository documentoRepository;

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    PlayerRepository playerRepository;

    public List<SplashAssetResponse> listAll() {
        return splashAssetRepository.listAll().stream()
                .map(SplashAssetResponse::from)
                .collect(Collectors.toList());
    }

    public SplashAssetResponse findById(Long id) {
        if (id == null) {
            return null;
        }
        return SplashAssetResponse.from(splashAssetRepository.findById(id));
    }

    @Transactional
    public SplashAssetResponse create(CreateSplashAssetRequest request) {
        if (request == null || request.nome() == null || request.nome().isBlank() || request.documentoId() == null) {
            throw new IllegalArgumentException("Nome e documentoId sao obrigatorios");
        }

        Documento documento = documentoRepository.findById(request.documentoId());
        if (documento == null) {
            throw new IllegalArgumentException("Documento nao encontrado");
        }

        SplashAsset splashAsset = new SplashAsset();
        splashAsset.setNome(request.nome());
        splashAsset.setDocumento(documento);

        splashAssetRepository.persist(splashAsset);
        return SplashAssetResponse.from(splashAsset);
    }

    @Transactional
    public SplashAssetResponse update(Long id, UpdateSplashAssetRequest request) {
        if (id == null || request == null) {
            throw new IllegalArgumentException("Dados obrigatorios faltando");
        }

        SplashAsset splashAsset = splashAssetRepository.findById(id);
        if (splashAsset == null) {
            return null;
        }

        if (request.nome() != null && !request.nome().isBlank()) {
            splashAsset.setNome(request.nome());
        }

        if (request.documentoId() != null) {
            Documento documento = documentoRepository.findById(request.documentoId());
            if (documento == null) {
                throw new IllegalArgumentException("Documento nao encontrado");
            }
            splashAsset.setDocumento(documento);
        }

        return SplashAssetResponse.from(splashAsset);
    }

    @Transactional
    public boolean delete(Long id) {
        if (id == null) {
            return false;
        }

        SplashAsset splashAsset = splashAssetRepository.findById(id);
        if (splashAsset == null) {
            return false;
        }

        splashAssetRepository.delete(splashAsset);
        return true;
    }

    public SplashAssetResponse resolveByUsuarioId(Long usuarioId, Long playerIdLogado) {
        if (usuarioId == null) {
            return SplashAssetResponse.from(getFallbackSplashAsset());
        }

        Usuario usuario = usuarioRepository.findById(usuarioId);
        if (usuario == null) {
            return SplashAssetResponse.from(getFallbackSplashAsset());
        }

        if (playerIdLogado != null) {
            return SplashAssetResponse.from(resolveByRules(usuario, playerIdLogado));
        }

        List<Player> playersDoUsuario = playerRepository.findByUsuario(usuario);
        if (playersDoUsuario == null || playersDoUsuario.isEmpty()) {
            return SplashAssetResponse.from(getSplashByIdOrFallback(1L));
        }

        Player player = playersDoUsuario.getFirst();
        return SplashAssetResponse.from(resolveByPlayerNivel(player));
    }

    public SplashAssetResponse resolveByLoggedUser(String nickName, Long playerIdLogado) {
        if (nickName == null || nickName.isBlank()) {
            return SplashAssetResponse.from(getFallbackSplashAsset());
        }

        Usuario usuario = usuarioRepository.findByNickName(nickName);
        if (usuario == null) {
            return SplashAssetResponse.from(getFallbackSplashAsset());
        }

        return SplashAssetResponse.from(resolveByRules(usuario, playerIdLogado));
    }

    private SplashAsset resolveByRules(Usuario usuario, Long playerIdLogado) {
        if (usuario == null || playerIdLogado == null) {
            return getSplashByIdOrFallback(1L);
        }

        Player player = playerRepository.findById(playerIdLogado);
        if (player == null || player.getUsuario() == null || !player.getUsuario().getId().equals(usuario.getId())) {
            return getSplashByIdOrFallback(1L);
        }

        return resolveByPlayerNivel(player);
    }

    private SplashAsset resolveByPlayerNivel(Player player) {
        if (player == null) {
            return getSplashByIdOrFallback(1L);
        }

        PlayerStatus status = player.getStatus();
        Long nivel = status == null || status.getNivelAtual() == null ? 1L : status.getNivelAtual();

        if (nivel < 10L) {
            return getSplashByIdOrFallback(2L);
        }
        if (nivel < 30L) {
            return getSplashByIdOrFallback(3L);
        }
        if (nivel < 50L) {
            return getSplashByIdOrFallback(4L);
        }

        return getSplashByIdOrFallback(5L);
    }

    private SplashAsset getSplashByIdOrFallback(Long id) {
        SplashAsset splash = splashAssetRepository.findById(id);
        if (splash != null) {
            return splash;
        }

        return getFallbackSplashAsset();
    }

    private SplashAsset getFallbackSplashAsset() {
        SplashAsset splashUm = splashAssetRepository.findById(1L);
        if (splashUm != null) {
            return splashUm;
        }

        return splashAssetRepository.findAll().firstResult();
    }
}
