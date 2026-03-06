package org.cupula.repository.playertipo;

import org.cupula.model.entities.baseview.PlayerTipoBaseTamanho;
import org.cupula.model.entities.enums.PlayerRaca;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class PlayerTipoBaseTamanhoRepository implements PanacheRepository<PlayerTipoBaseTamanho> {

    public List<PlayerTipoBaseTamanho> findByPlayerRaca(PlayerRaca playerRaca) {
        return list("playerRaca = ?1 and ativo = true", playerRaca);
    }

    public List<PlayerTipoBaseTamanho> findAllAtivos() {
        return list("ativo = true");
    }
}
