package org.cupula.service;

import java.time.LocalDateTime;
import java.util.List;

import org.cupula.dto.items.CreateItemTipoRequest;
import org.cupula.dto.items.UpdateItemTipoRequest;
import org.cupula.model.items.ItemTipo;
import org.cupula.repository.items.ItemTipoRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class ItemTipoService {

    @Inject
    ItemTipoRepository itemTipoRepository;

    public List<ItemTipo> listarTodos() {
        return itemTipoRepository.listAll();
    }

    public List<ItemTipo> listarAtivos() {
        return itemTipoRepository.list("ativo", true);
    }

    public ItemTipo buscarPorId(Long id) {
        ItemTipo itemTipo = itemTipoRepository.findById(id);
        if (itemTipo == null) {
            throw new NotFoundException("ItemTipo não encontrado com ID: " + id);
        }
        return itemTipo;
    }

    @Transactional
    public ItemTipo criar(CreateItemTipoRequest request) {
        ItemTipo itemTipo = new ItemTipo();
        itemTipo.setNome(request.nome());
        itemTipo.setDescricao(request.descricao());
        itemTipo.setLimiteQuantidade(request.limiteQuantidade());
        itemTipo.setPesoPorQuantidade(request.pesoPorQuantidade());
        itemTipo.setAtivo(true);
        itemTipo.setDataInclusao(LocalDateTime.now());
        
        itemTipoRepository.persist(itemTipo);
        return itemTipo;
    }

    @Transactional
    public ItemTipo atualizar(Long id, UpdateItemTipoRequest request) {
        ItemTipo itemTipo = buscarPorId(id);
        
        if (request.nome() != null) {
            itemTipo.setNome(request.nome());
        }
        if (request.descricao() != null) {
            itemTipo.setDescricao(request.descricao());
        }
        if (request.limiteQuantidade() != null) {
            itemTipo.setLimiteQuantidade(request.limiteQuantidade());
        }
        if (request.pesoPorQuantidade() != null) {
            itemTipo.setPesoPorQuantidade(request.pesoPorQuantidade());
        }
        
        itemTipoRepository.persist(itemTipo);
        return itemTipo;
    }

    @Transactional
    public void inativar(Long id) {
        ItemTipo itemTipo = buscarPorId(id);
        itemTipo.setAtivo(false);
        itemTipoRepository.persist(itemTipo);
    }

    @Transactional
    public void ativar(Long id) {
        ItemTipo itemTipo = buscarPorId(id);
        itemTipo.setAtivo(true);
        itemTipoRepository.persist(itemTipo);
    }

    @Transactional
    public void deletar(Long id) {
        ItemTipo itemTipo = buscarPorId(id);
        itemTipoRepository.delete(itemTipo);
    }
}
