package org.cupula.repository.playertipo;

import org.cupula.model.entities.baseview.orelha.PlayerTipoBaseOrelha;
import org.cupula.model.entities.enums.PlayerRaca;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class PlayerTipoBaseOrelhaRepository implements PanacheRepository<PlayerTipoBaseOrelha> {

    public List<PlayerTipoBaseOrelha> findByPlayerRaca(PlayerRaca playerRaca) {
        return list("playerRaca = ?1 and ativo = true", playerRaca);
    }

    public List<PlayerTipoBaseOrelha> findAllAtivos() {
        return list("ativo = true");
    }
}
