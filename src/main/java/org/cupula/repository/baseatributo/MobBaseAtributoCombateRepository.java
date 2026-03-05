package org.cupula.repository.baseatributo;

import java.util.List;

import org.cupula.model.baseatributo.combate.MobBaseAtributoCombate;
import org.cupula.model.entities.enums.MobTipo;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MobBaseAtributoCombateRepository implements PanacheRepository<MobBaseAtributoCombate> {
    
    public List<MobBaseAtributoCombate> findByTipo(MobTipo tipo) {
        return list("tipo", tipo);
    }
}
