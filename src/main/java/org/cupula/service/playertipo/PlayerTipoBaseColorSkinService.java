package org.cupula.service.playertipo;

import org.cupula.model.entities.baseview.PlayerTipoBaseColorSkin;
import org.cupula.model.entities.enums.PlayerRaca;
import org.cupula.repository.playertipo.PlayerTipoBaseColorSkinRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class PlayerTipoBaseColorSkinService {

    @Inject
    PlayerTipoBaseColorSkinRepository repository;

    public List<PlayerTipoBaseColorSkin> listarTodos() {
        return repository.findAllAtivos();
    }

    public List<PlayerTipoBaseColorSkin> listarPorRaca(PlayerRaca playerRaca) {
        return repository.findByPlayerRaca(playerRaca);
    }

    public PlayerTipoBaseColorSkin buscarPorId(Long id) {
        return repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("PlayerTipoBaseColorSkin não encontrado"));
    }

    @Transactional
    public PlayerTipoBaseColorSkin criar(PlayerTipoBaseColorSkin entity) {
        entity.setDataInclusao(LocalDateTime.now());
        entity.setAtivo(true);
        repository.persist(entity);
        return entity;
    }

    @Transactional
    public PlayerTipoBaseColorSkin atualizar(Long id, PlayerTipoBaseColorSkin dados) {
        PlayerTipoBaseColorSkin entity = buscarPorId(id);
        
        entity.setPlayerRaca(dados.getPlayerRaca());
        entity.setPossibilidade(dados.getPossibilidade());
        entity.setColorMaterial(dados.getColorMaterial());
        
        return entity;
    }

    @Transactional
    public void deletar(Long id) {
        PlayerTipoBaseColorSkin entity = buscarPorId(id);
        entity.setAtivo(false);
    }
}
