package org.cupula.repository.containers.basecontainer;

import java.util.List;

import org.cupula.model.containers.basecontainer.BaseContainerStructureUnit;
import org.cupula.model.containers.enums.ContainerTipo;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BaseContainerStructureUnitRepository implements PanacheRepository<BaseContainerStructureUnit> {
    
    public List<BaseContainerStructureUnit> findByTipo(ContainerTipo tipo) {
        return find("containerTipo = ?1 and ativo = true", tipo).list();
    }
}
