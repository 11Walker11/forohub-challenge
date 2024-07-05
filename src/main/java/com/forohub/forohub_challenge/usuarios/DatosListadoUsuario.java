package com.forohub.forohub_challenge.usuarios;

import java.time.LocalDateTime;

public record DatosListadoUsuario(Long id, String login, String nombre, LocalDateTime fechaCreacion) {

    public DatosListadoUsuario(Usuario usuario){
        this(usuario.getId(), usuario.getLogin(), usuario.getNombre(), usuario.getFechaCreacion());
    }
}
