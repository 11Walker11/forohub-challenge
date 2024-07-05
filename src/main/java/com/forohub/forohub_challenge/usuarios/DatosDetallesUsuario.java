package com.forohub.forohub_challenge.usuarios;

import java.time.LocalDateTime;

public record DatosDetallesUsuario(Long id, String login, String clave, String nombre, LocalDateTime fechaCreacion) {

    public DatosDetallesUsuario(Usuario usuario){
        this(usuario.getId(), usuario.getLogin(), usuario.getClave(), usuario.getNombre(), usuario.getFechaCreacion());

    }
}
