package com.forohub.forohub_challenge.usuarios;

import com.forohub.forohub_challenge.topicos.Topico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String clave;
    private String nombre;
    private LocalDateTime fechaCreacion;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Topico> topicos;

    public Usuario(DatosRegistroUsuario datos) {
        this.login = datos.login();
        this.clave = datos.clave();
        this.nombre = datos.nombre();
        this.fechaCreacion = LocalDateTime.now();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return clave;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getClave() {
        return clave;
    }

    public void actualizarInformacoes(DatosActualizarUsuario datos) {
        if (datos.nombre() != null){
            this.nombre = datos.nombre();
        }
        if (datos.login() != null){
            this.login = datos.login();
        }
        if (datos.clave() != null){
            this.clave = datos.clave();
        }

    }

}

