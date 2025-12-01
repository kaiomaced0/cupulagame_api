package org.cupula.repository;

import java.util.List;
import java.util.Optional;

import org.cupula.model.pvp.arenas.ArenaPvp;
import org.cupula.model.pvp.arenas.enums.ArenaPvpTipo;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ArenaPvpRepository implements PanacheRepository<ArenaPvp> {
    
    /**
     * Busca arenas por tipo
     */
    public List<ArenaPvp> findByTipo(ArenaPvpTipo tipo) {
        return find("tipo", tipo).list();
    }
    
    /**
     * Busca a arena padrão
     */
    public Optional<ArenaPvp> findPadrao() {
        return find("padrao", true).firstResultOptional();
    }
    
    /**
     * Busca arenas por nome (case insensitive)
     */
    public List<ArenaPvp> findByNome(String nome) {
        return find("LOWER(nome) LIKE LOWER(?1)", "%" + nome + "%").list();
    }
    
    /**
     * Busca arenas por dimensões específicas
     */
    public List<ArenaPvp> findByDimensoes(Long x, Long y, Long z) {
        return find("x = ?1 and y = ?2 and z = ?3", x, y, z).list();
    }
}
