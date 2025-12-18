package org.cupula.model.auth;
import java.util.List;
import java.util.Set;

import org.cupula.model.EntityClass;
import org.cupula.model.auth.enums.AuthProvider;
import org.cupula.model.auth.enums.Perfil;
import org.cupula.model.auth.pessoa.Pessoa;
import org.cupula.model.structures.Structure;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity(name = "Usuario")
public class Usuario extends EntityClass{

    private String email;

    private String senha;

    @Column(name = "nickname")
    private String nickName;

    private Boolean mudarSenha;

    @Column(name = "login_local_habilitado")
    private Boolean loginLocalHabilitado = Boolean.TRUE;

    @ElementCollection
    @CollectionTable(name = "usuario_perfil", joinColumns = @JoinColumn(name = "id_usuario", referencedColumnName = "id"))
    @Column(name = "perfil", length = 30)
    private Set<Perfil> perfis;

    // Permite login via provedores externos (Google, Facebook, Apple, Microsoft, GitHub etc.).
    @ElementCollection
    @CollectionTable(name = "usuario_login_provider", joinColumns = @JoinColumn(name = "id_usuario", referencedColumnName = "id"))
    private Set<UsuarioProvider> provedoresLogin;

    @OneToMany(mappedBy = "usuario")
    private List<Structure> baseStructures;

    @OneToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

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


    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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

    public Boolean getLoginLocalHabilitado() {
        return loginLocalHabilitado;
    }

    public void setLoginLocalHabilitado(Boolean loginLocalHabilitado) {
        this.loginLocalHabilitado = loginLocalHabilitado;
    }

    public Set<UsuarioProvider> getProvedoresLogin() {
        return provedoresLogin;
    }

    public void setProvedoresLogin(Set<UsuarioProvider> provedoresLogin) {
        this.provedoresLogin = provedoresLogin;
    }

    public boolean suportaProvider(AuthProvider provider) {
        if (provedoresLogin == null || provider == null) {
            return false;
        }
        return provedoresLogin.stream().anyMatch(p -> provider.equals(p.getProvider()));
    }
    public List<Structure> getBaseStructures() {
        return baseStructures;
    }
    public void setBaseStructures(List<Structure> baseStructures) {
        this.baseStructures = baseStructures;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

}
