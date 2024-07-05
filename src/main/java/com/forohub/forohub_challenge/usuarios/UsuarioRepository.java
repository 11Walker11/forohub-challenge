package com.forohub.forohub_challenge.usuarios;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByLogin(String login);

    @Query("SELECT u FROM Usuario u ORDER BY u.fechaCreacion ASC")
    Page<Usuario> listarUsuarios(Pageable paginacion);
}

