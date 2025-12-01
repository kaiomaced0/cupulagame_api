package org.cupula.model.structures.view;

import jakarta.persistence.Entity;
import org.cupula.model.EntityClass;

@Entity
public class Texture extends EntityClass {
    
    private String name;
    private String path;
}
