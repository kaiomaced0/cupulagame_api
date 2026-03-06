package org.cupula.service.playertipo;

import org.cupula.model.entities.baseview.PlayerTipoBaseTamanho;
import org.cupula.model.entities.enums.PlayerRaca;
import org.cupula.repository.playertipo.PlayerTipoBaseTamanhoRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class PlayerTipoBaseTamanhoService {

    @Inject
    PlayerTipoBaseTamanhoRepository repository;

    public List<PlayerTipoBaseTamanho> listarTodos() {
        return repository.findAllAtivos();
    }

    public List<PlayerTipoBaseTamanho> listarPorRaca(PlayerRaca playerRaca) {
        return repository.findByPlayerRaca(playerRaca);
    }

    public PlayerTipoBaseTamanho buscarPorId(Long id) {
        return repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("PlayerTipoBaseTamanho não encontrado"));
    }

    @Transactional
    public PlayerTipoBaseTamanho criar(PlayerTipoBaseTamanho entity) {
        entity.setDataInclusao(LocalDateTime.now());
        entity.setAtivo(true);
        repository.persist(entity);
        return entity;
    }

    @Transactional
    public PlayerTipoBaseTamanho atualizar(Long id, PlayerTipoBaseTamanho dados) {
        PlayerTipoBaseTamanho entity = buscarPorId(id);
        
        entity.setPlayerRaca(dados.getPlayerRaca());
        entity.setPossibilidade(dados.getPossibilidade());
        entity.setEixoXMinimo(dados.getEixoXMinimo());
        entity.setEixoXMaximo(dados.getEixoXMaximo());
        entity.setEixoYMinimo(dados.getEixoYMinimo());
        entity.setEixoYMaximo(dados.getEixoYMaximo());
        entity.setEixoZMinimo(dados.getEixoZMinimo());
        entity.setEixoZMaximo(dados.getEixoZMaximo());
        
        return entity;
    }

    @Transactional
    public void deletar(Long id) {
        PlayerTipoBaseTamanho entity = buscarPorId(id);
        entity.setAtivo(false);
    }
}
