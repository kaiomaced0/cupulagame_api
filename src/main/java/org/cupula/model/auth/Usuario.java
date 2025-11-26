package org.cupula.model.auth;
import java.util.List;
import java.util.Set;

import org.cupula.model.EntityClass;
import org.cupula.model.auth.enums.Perfil;
import org.cupula.model.structures.Structure;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;

@Entity(name = "Usuario")
public class Usuario extends EntityClass{

    private String email;

    private String senha;

    private String login;

    private Boolean mudarSenha;

    @ElementCollection
    @CollectionTable(name = "usuario_perfil", joinColumns = @JoinColumn(name = "id_usuario", referencedColumnName = "id"))
    @Column(name = "perfil", length = 30)
    private Set<Perfil> perfis;

    private List<Structure> baseStructures;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    public Boolean getMudarSenha() {
        return mudarSenha;
    }

    public void setMudarSenha(Boolean mudarSenha) {
        this.mudarSenha = mudarSenha;
    }


    public Set<Perfil> getPerfis() {
        return perfis;
    }

    public void setPerfis(Set<Perfil> perfis) {
        this.perfis = perfis;
    }

    
}
