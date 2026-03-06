package org.cupula.repository.playertipo;

import org.cupula.model.entities.baseview.orelha.TipoOrelhaColorMaterial;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class TipoOrelhaColorMaterialRepository implements PanacheRepository<TipoOrelhaColorMaterial> {

    public List<TipoOrelhaColorMaterial> findAllAtivos() {
        return list("ativo = true");
    }
}
