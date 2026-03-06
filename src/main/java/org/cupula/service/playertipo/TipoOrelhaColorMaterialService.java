package org.cupula.service.playertipo;

import org.cupula.model.entities.baseview.orelha.TipoOrelhaColorMaterial;
import org.cupula.repository.playertipo.TipoOrelhaColorMaterialRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class TipoOrelhaColorMaterialService {

    @Inject
    TipoOrelhaColorMaterialRepository repository;

    public List<TipoOrelhaColorMaterial> listarTodos() {
        return repository.findAllAtivos();
    }

    public TipoOrelhaColorMaterial buscarPorId(Long id) {
        return repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("TipoOrelhaColorMaterial não encontrado"));
    }

    @Transactional
    public TipoOrelhaColorMaterial criar(TipoOrelhaColorMaterial entity) {
        entity.setDataInclusao(LocalDateTime.now());
        entity.setAtivo(true);
        repository.persist(entity);
        return entity;
    }

    @Transactional
    public TipoOrelhaColorMaterial atualizar(Long id, TipoOrelhaColorMaterial dados) {
        TipoOrelhaColorMaterial entity = buscarPorId(id);
        
        entity.setColorMaterial(dados.getColorMaterial());
        entity.setPossibilidade(dados.getPossibilidade());
        
        return entity;
    }

    @Transactional
    public void deletar(Long id) {
        TipoOrelhaColorMaterial entity = buscarPorId(id);
        entity.setAtivo(false);
    }
}
