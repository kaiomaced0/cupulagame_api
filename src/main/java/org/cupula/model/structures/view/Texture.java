package org.cupula.model.structures.view;

import org.cupula.model.EntityClass;

import jakarta.persistence.Entity;

@Entity(name = "texture")
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
