package org.cupula.model.items.baseview;

import java.util.List;

import org.cupula.model.items.ItemTipo;

import jakarta.persistence.Entity;
import org.cupula.model.EntityClass;

@Entity
public class BaseItemStructure extends EntityClass {

    private ItemTipo item;
    private Long possibilidade;
    private List<BaseItemItemStructurePart> parts;
}
