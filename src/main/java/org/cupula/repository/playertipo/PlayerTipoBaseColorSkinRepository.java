package org.cupula.repository.playertipo;

import org.cupula.model.entities.baseview.PlayerTipoBaseColorSkin;
import org.cupula.model.entities.enums.PlayerRaca;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class PlayerTipoBaseColorSkinRepository implements PanacheRepository<PlayerTipoBaseColorSkin> {

    public List<PlayerTipoBaseColorSkin> findByPlayerRaca(PlayerRaca playerRaca) {
        return list("playerRaca = ?1 and ativo = true", playerRaca);
    }

    public List<PlayerTipoBaseColorSkin> findAllAtivos() {
        return list("ativo = true");
    }
}
