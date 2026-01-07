package org.cupula.model.entities.mob;

import org.cupula.model.islands.Ilha;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity(name = "mob_posicao")
@Table(name = "mob_posicao")
public class MobPosicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // posicao atual do mob
    private Long x;
    private Long y;
    private Long z;

    @ManyToOne
    @JoinColumn(name = "ilha_id")
    private Ilha ilhaAtual;

    @OneToOne(mappedBy = "posicao")
    private Mob mob;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getX() {
        return x;
    }

    public void setX(Long x) {
        this.x = x;
    }

    public Long getY() {
        return y;
    }

    public void setY(Long y) {
        this.y = y;
    }

    public Long getZ() {
        return z;
    }

    public void setZ(Long z) {
        this.z = z;
    }

    public Ilha getIlhaAtual() {
        return ilhaAtual;
    }

    public void setIlhaAtual(Ilha ilhaAtual) {
        this.ilhaAtual = ilhaAtual;
    }

    public Mob getMob() {
        return mob;
    }

    public void setMob(Mob mob) {
        this.mob = mob;
    }
    
}
