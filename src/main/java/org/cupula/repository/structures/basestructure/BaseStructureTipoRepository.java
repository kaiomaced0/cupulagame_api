package org.cupula.repository.structures.basestructure;

import java.util.List;

import org.cupula.model.entities.enums.PlayerRaca;
import org.cupula.model.structures.basestructure.BaseStructureTipo;
import org.cupula.model.structures.enums.StructureTipo;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BaseStructureTipoRepository implements PanacheRepository<BaseStructureTipo> {
    
    /**
     * Busca configurações base de estrutura por raça e tipo de estrutura
     * Retorna apenas configurações ativas
     */
    public List<BaseStructureTipo> findByRacaAndTipo(PlayerRaca raca, StructureTipo tipo) {
        return find("playerRaca = ?1 and structureTipo = ?2 and ativo = true", raca, tipo).list();
    }
    
}
