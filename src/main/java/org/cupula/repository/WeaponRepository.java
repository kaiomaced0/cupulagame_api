package org.cupula.repository;

import org.cupula.model.items.tipositem.Weapon;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class WeaponRepository implements PanacheRepository<Weapon> {   
    
}
