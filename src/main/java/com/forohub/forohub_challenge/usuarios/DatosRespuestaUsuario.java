package com.forohub.forohub_challenge.usuarios;

import java.time.LocalDateTime;

public record DatosRespuestaUsuario(Long id, String login, String clave, String nombre, LocalDateTime fechaCreacion) {
}
