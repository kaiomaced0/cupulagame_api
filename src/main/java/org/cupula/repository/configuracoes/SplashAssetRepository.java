package org.cupula.repository.configuracoes;

import org.cupula.model.configuracoes.SplashAsset;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SplashAssetRepository implements PanacheRepository<SplashAsset> {
}
