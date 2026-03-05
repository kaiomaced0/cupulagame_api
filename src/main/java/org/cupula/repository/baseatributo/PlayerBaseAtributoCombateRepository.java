package org.cupula.repository.baseatributo;

import java.util.List;

import org.cupula.model.baseatributo.combate.PlayerBaseAtributoCombate;
import org.cupula.model.entities.enums.PlayerRaca;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PlayerBaseAtributoCombateRepository implements PanacheRepository<PlayerBaseAtributoCombate> {
    
    public List<PlayerBaseAtributoCombate> findByRaca(PlayerRaca raca) {
        return list("raca", raca);
    }
}
