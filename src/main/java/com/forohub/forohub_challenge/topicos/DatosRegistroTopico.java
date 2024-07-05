package com.forohub.forohub_challenge.topicos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record DatosRegistroTopico(
        @NotBlank(message = "El titulo es obligatorio")
        String titulo,

        @NotBlank(message = "El mensaje es obligatorio")
        String mensaje,

        @NotNull
        Long autorId,

        @NotNull
        Long cursoId
) {
}
