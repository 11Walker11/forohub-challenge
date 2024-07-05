package com.forohub.forohub_challenge.usuarios;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroUsuario(
        @Email
        @NotNull(message = "Email es obligatorio")
        String login,
        @NotBlank(message = "Clave obligatoria")
        String clave,
        @NotBlank(message = "Nombre obligatorio")
        String nombre

) {
}
