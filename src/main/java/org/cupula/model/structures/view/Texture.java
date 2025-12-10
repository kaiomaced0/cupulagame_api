package org.cupula.model.structures.view;

import jakarta.persistence.Entity;
import org.cupula.model.EntityClass;

@Entity
public class Texture extends EntityClass {
    
    private String name;
    private String path;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }

}
