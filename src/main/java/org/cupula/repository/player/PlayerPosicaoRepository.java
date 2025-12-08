package org.cupula.repository.player;

import org.cupula.model.entities.player.PlayerPosicao;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PlayerPosicaoRepository implements PanacheRepository<PlayerPosicao> {
}
