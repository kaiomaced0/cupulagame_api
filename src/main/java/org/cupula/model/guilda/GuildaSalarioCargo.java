package org.cupula.model.guilda;

import org.cupula.model.EntityClass;
import org.cupula.model.guilda.enums.CargoGuilda;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class GuildaSalarioCargo extends EntityClass {
    
    @ManyToOne
    @JoinColumn(name = "guilda_id")
    private Guilda guilda;
    
    @Enumerated(EnumType.STRING)
    private CargoGuilda cargoGuilda;

    private Long salario;
    
    public Guilda getGuilda() {
        return guilda;
    }
    public void setGuilda(Guilda guilda) {
        this.guilda = guilda;
    }
    public CargoGuilda getCargoGuilda() {
        return cargoGuilda;
    }
    public void setCargoGuilda(CargoGuilda cargoGuilda) {
        this.cargoGuilda = cargoGuilda;
    }
    public Long getSalario() {
        return salario;
    }
    public void setSalario(Long salario) {
        this.salario = salario;
    } 

    
}
