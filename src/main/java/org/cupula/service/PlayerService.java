package org.cupula.service;

import java.util.List;
import java.util.stream.Collectors;

import org.cupula.dto.player.request.CriarPlayerRequest;
import org.cupula.dto.player.response.PlayerResponse;
import org.cupula.model.auth.Usuario;
import org.cupula.model.comunity.VisibilidadePerfil;
import org.cupula.model.entities.baseview.PlayerTipoCabelo;
import org.cupula.model.entities.player.Player;
import org.cupula.model.entities.player.PlayerPosicao;
import org.cupula.model.entities.player.PlayerStatus;
import org.cupula.repository.entities.baseview.PlayerTipoCabeloRepository;
import org.cupula.repository.player.PlayerPosicaoRepository;
import org.cupula.repository.player.PlayerRepository;
import org.cupula.repository.player.PlayerStatusRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PlayerService {

    @Inject
    PlayerRepository playerRepository;

    @Inject
    PlayerStatusRepository playerStatusRepository;

    @Inject
    PlayerPosicaoRepository playerPosicaoRepository;

    @Inject
    PlayerTipoCabeloRepository playerTipoCabeloRepository;

    /**
     * Cria um novo player para o usuário logado
     */
    @Transactional
    public PlayerResponse criarPlayer(Usuario usuario, CriarPlayerRequest request) {
        // Criar PlayerPosicao
        PlayerPosicao posicao = new PlayerPosicao();
        posicao.setX(0L);
        posicao.setY(10L); // Spawna a 10 unidades de altura
        posicao.setZ(0L);
        playerPosicaoRepository.persist(posicao);

        // Criar PlayerStatus
        PlayerStatus status = new PlayerStatus();
        status.setPosicao(posicao);
        playerStatusRepository.persist(status);
        
        // Criar Player
        Player player = new Player();
        player.setUsuario(usuario);
        player.setVisibilidade(VisibilidadePerfil.AMIGOS);
        player.setRaca(request.raca());
        player.setTamanhoX(request.tamanhoX());
        player.setTamanhoY(request.tamanhoY());
        player.setTamanhoZ(request.tamanhoZ());
        
        if (request.tipoCabeloId() != null) {
            PlayerTipoCabelo tipoCabelo = playerTipoCabeloRepository.findById(request.tipoCabeloId());
            player.setTipoCabelo(tipoCabelo);
        }
        
        player.setCorPele(request.corPele());
        player.setTamanhoXOrelha(request.tamanhoXOrelha());
        player.setTamanhoYOrelha(request.tamanhoYOrelha());
        player.setTamanhoZOrelha(request.tamanhoZOrelha());
        player.setCorOrelha(request.corOrelha());
        player.setStatus(status);
        
        playerRepository.persist(player);
        
        return PlayerResponse.from(player);
    }

    /**
     * Lista todos os players do usuário logado
     */
    public List<PlayerResponse> listarPlayersDoUsuario(Usuario usuario) {
        List<Player> players = playerRepository.findByUsuario(usuario);
        return players.stream()
                .map(PlayerResponse::from)
                .collect(Collectors.toList());
    }

    /**
     * Busca um player por ID
     */
    public PlayerResponse buscarPorId(Long id) {
        Player player = playerRepository.findById(id);
        return player != null ? PlayerResponse.from(player) : null;
    }
}
