package com.forohub.forohub_challenge.cursos;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarCurso(
        @NotNull
        Long id,

        String nombre,

        String descripcion,

        String status
) {
}
