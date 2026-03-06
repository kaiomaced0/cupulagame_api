package org.cupula.repository.playertipo;

import org.cupula.model.entities.baseview.PlayerTipoCabelo;
import org.cupula.model.entities.enums.PlayerRaca;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class PlayerTipoCabeloRepository implements PanacheRepository<PlayerTipoCabelo> {

    public List<PlayerTipoCabelo> findByPlayerRaca(PlayerRaca playerRaca) {
        return list("playerRaca = ?1 and ativo = true", playerRaca);
    }

    public List<PlayerTipoCabelo> findAllAtivos() {
        return list("ativo = true");
    }
}
