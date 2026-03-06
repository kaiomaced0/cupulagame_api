package org.cupula.service.playertipo;

import org.cupula.model.entities.baseview.orelha.PlayerTipoBaseOrelha;
import org.cupula.model.entities.enums.PlayerRaca;
import org.cupula.repository.playertipo.PlayerTipoBaseOrelhaRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class PlayerTipoBaseOrelhaService {

    @Inject
    PlayerTipoBaseOrelhaRepository repository;

    public List<PlayerTipoBaseOrelha> listarTodos() {
        return repository.findAllAtivos();
    }

    public List<PlayerTipoBaseOrelha> listarPorRaca(PlayerRaca playerRaca) {
        return repository.findByPlayerRaca(playerRaca);
    }

    public PlayerTipoBaseOrelha buscarPorId(Long id) {
        return repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("PlayerTipoBaseOrelha não encontrado"));
    }

    @Transactional
    public PlayerTipoBaseOrelha criar(PlayerTipoBaseOrelha entity) {
        entity.setDataInclusao(LocalDateTime.now());
        entity.setAtivo(true);
        repository.persist(entity);
        return entity;
    }

    @Transactional
    public PlayerTipoBaseOrelha atualizar(Long id, PlayerTipoBaseOrelha dados) {
        PlayerTipoBaseOrelha entity = buscarPorId(id);
        
        entity.setPlayerRaca(dados.getPlayerRaca());
        entity.setPossibilidade(dados.getPossibilidade());
        entity.setEixoXMinimo(dados.getEixoXMinimo());
        entity.setEixoXMaximo(dados.getEixoXMaximo());
        entity.setEixoYMinimo(dados.getEixoYMinimo());
        entity.setEixoYMaximo(dados.getEixoYMaximo());
        entity.setEixoZMinimo(dados.getEixoZMinimo());
        entity.setEixoZMaximo(dados.getEixoZMaximo());
        entity.setPossiveisColorMaterials(dados.getPossiveisColorMaterials());
        
        return entity;
    }

    @Transactional
    public void deletar(Long id) {
        PlayerTipoBaseOrelha entity = buscarPorId(id);
        entity.setAtivo(false);
    }
}
