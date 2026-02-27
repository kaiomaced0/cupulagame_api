package org.cupula.repository.configuracoes;

import org.cupula.model.configuracoes.BackgroundAsset;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BackgroundAssetRepository implements PanacheRepository<BackgroundAsset> {
}
