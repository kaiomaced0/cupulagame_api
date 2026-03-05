package org.cupula.repository.structures.basestructure;

import java.util.List;

import org.cupula.model.entities.enums.PlayerRaca;
import org.cupula.model.structures.basestructure.BaseStructure;
import org.cupula.model.structures.enums.StructureTipo;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BaseStructureRepository implements PanacheRepository<BaseStructure> {
    
    /**
     * Busca bases de estrutura por raça e tipo que estão ativas
     */
    public List<BaseStructure> findByRacaAndTipo(PlayerRaca raca, StructureTipo tipo) {
        return find("playerRaca = ?1 and structureTipo = ?2 and ativo = true", raca, tipo).list();
    }
}
