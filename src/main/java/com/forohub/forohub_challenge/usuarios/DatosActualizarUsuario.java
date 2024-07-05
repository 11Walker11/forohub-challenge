package com.forohub.forohub_challenge.usuarios;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarUsuario(@NotNull Long id, String login, String clave, String nombre) {
}
