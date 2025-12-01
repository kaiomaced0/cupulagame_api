package org.cupula.model.guilda;

import org.cupula.model.guilda.enums.CargoGuilda;

import jakarta.persistence.Entity;
import org.cupula.model.EntityClass;

@Entity
public class GuildaSalarioCargo extends EntityClass {
    private Guilda guilda;
    private CargoGuilda cargoGuilda;
    private Long salario; 
}
