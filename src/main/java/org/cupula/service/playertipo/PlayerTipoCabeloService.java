package org.cupula.service.playertipo;

import org.cupula.model.entities.baseview.PlayerTipoCabelo;
import org.cupula.model.entities.enums.PlayerRaca;
import org.cupula.repository.playertipo.PlayerTipoCabeloRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class PlayerTipoCabeloService {

    @Inject
    PlayerTipoCabeloRepository repository;

    public List<PlayerTipoCabelo> listarTodos() {
        return repository.findAllAtivos();
    }

    public List<PlayerTipoCabelo> listarPorRaca(PlayerRaca playerRaca) {
        return repository.findByPlayerRaca(playerRaca);
    }

    public PlayerTipoCabelo buscarPorId(Long id) {
        return repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("PlayerTipoCabelo não encontrado"));
    }

    @Transactional
    public PlayerTipoCabelo criar(PlayerTipoCabelo entity) {
        entity.setDataInclusao(LocalDateTime.now());
        entity.setAtivo(true);
        repository.persist(entity);
        return entity;
    }

    @Transactional
    public PlayerTipoCabelo atualizar(Long id, PlayerTipoCabelo dados) {
        PlayerTipoCabelo entity = buscarPorId(id);
        
        entity.setNome(dados.getNome());
        entity.setPath(dados.getPath());
        entity.setPossibilidade(dados.getPossibilidade());
        entity.setPlayerRaca(dados.getPlayerRaca());
        entity.setEstrutura(dados.getEstrutura());
        
        return entity;
    }

    @Transactional
    public void deletar(Long id) {
        PlayerTipoCabelo entity = buscarPorId(id);
        entity.setAtivo(false);
    }
}
