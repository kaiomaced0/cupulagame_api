package org.cupula.model.entities;

import java.util.List;

import org.cupula.model.containers.Container;

import jakarta.persistence.Entity;
import org.cupula.model.EntityClass;

@Entity
public class Mob extends EntityClass {
    
    private List<Container> containers;
}
