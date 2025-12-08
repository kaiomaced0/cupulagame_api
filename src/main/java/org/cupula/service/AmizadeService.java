package org.cupula.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.cupula.dto.usuario.response.AmizadeResponse;
import org.cupula.model.auth.Usuario;
import org.cupula.model.comunity.Amizade;
import org.cupula.model.comunity.StatusAmizade;
import org.cupula.repository.auth.UsuarioRepository;
import org.cupula.repository.comunity.AmizadeRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class AmizadeService {

    @Inject
    AmizadeRepository amizadeRepository;

    @Inject
    UsuarioRepository usuarioRepository;

    /**
     * Adiciona um novo amigo (envia solicitação de amizade)
     */
    @Transactional
    public AmizadeResponse adicionarAmigo(Usuario solicitante, String emailAmigo) {
        // Buscar usuário solicitado por email
        Usuario solicitado = usuarioRepository.findByEmail(emailAmigo);
        
        if (solicitado == null) {
            throw new NotFoundException("Usuário não encontrado");
        }

        if (solicitante.getId().equals(solicitado.getId())) {
            throw new BadRequestException("Não é possível adicionar a si mesmo como amigo");
        }

        // Verificar se já existe amizade entre eles
        Amizade amizadeExistente = amizadeRepository.findBetweenUsuarios(solicitante, solicitado);
        
        if (amizadeExistente != null) {
            if (amizadeExistente.getStatus() == StatusAmizade.ACEITO) {
                throw new BadRequestException("Vocês já são amigos");
            } else if (amizadeExistente.getStatus() == StatusAmizade.PENDENTE) {
                throw new BadRequestException("Já existe uma solicitação de amizade pendente");
            } else if (amizadeExistente.getStatus() == StatusAmizade.BLOQUEADO) {
                throw new BadRequestException("Não é possível enviar solicitação para este usuário");
            }
        }

        // Criar nova solicitação
        Amizade amizade = new Amizade();
        amizade.setSolicitante(solicitante);
        amizade.setSolicitado(solicitado);
        amizade.setStatus(StatusAmizade.PENDENTE);
        amizade.setDataSolicitacao(LocalDateTime.now());
        
        amizadeRepository.persist(amizade);
        
        return AmizadeResponse.from(amizade);
    }

    /**
     * Lista todos os amigos aceitos do usuário
     */
    public List<AmizadeResponse> listarAmigos(Usuario usuario) {
        List<Amizade> amizades = amizadeRepository.findAmigosAceitos(usuario);
        return amizades.stream()
                .map(AmizadeResponse::from)
                .collect(Collectors.toList());
    }

    /**
     * Lista solicitações de amizade pendentes
     */
    public List<AmizadeResponse> listarSolicitacoesPendentes(Usuario usuario) {
        List<Amizade> solicitacoes = amizadeRepository.findSolicitacoesPendentes(usuario);
        return solicitacoes.stream()
                .map(AmizadeResponse::from)
                .collect(Collectors.toList());
    }

    /**
     * Aceitar solicitação de amizade
     */
    @Transactional
    public AmizadeResponse aceitarSolicitacao(Usuario usuario, Long amizadeId) {
        Amizade amizade = amizadeRepository.findById(amizadeId);
        
        if (amizade == null) {
            throw new NotFoundException("Solicitação não encontrada");
        }

        if (!amizade.getSolicitado().getId().equals(usuario.getId())) {
            throw new BadRequestException("Você não pode aceitar esta solicitação");
        }

        if (amizade.getStatus() != StatusAmizade.PENDENTE) {
            throw new BadRequestException("Esta solicitação já foi respondida");
        }

        amizade.setStatus(StatusAmizade.ACEITO);
        amizade.setDataResposta(LocalDateTime.now());
        
        return AmizadeResponse.from(amizade);
    }

    /**
     * Recusar solicitação de amizade
     */
    @Transactional
    public AmizadeResponse recusarSolicitacao(Usuario usuario, Long amizadeId) {
        Amizade amizade = amizadeRepository.findById(amizadeId);
        
        if (amizade == null) {
            throw new NotFoundException("Solicitação não encontrada");
        }

        if (!amizade.getSolicitado().getId().equals(usuario.getId())) {
            throw new BadRequestException("Você não pode recusar esta solicitação");
        }

        if (amizade.getStatus() != StatusAmizade.PENDENTE) {
            throw new BadRequestException("Esta solicitação já foi respondida");
        }

        amizade.setStatus(StatusAmizade.RECUSADO);
        amizade.setDataResposta(LocalDateTime.now());
        
        return AmizadeResponse.from(amizade);
    }
}
