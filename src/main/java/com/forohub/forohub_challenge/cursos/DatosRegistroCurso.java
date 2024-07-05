package com.forohub.forohub_challenge.cursos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroCurso(

        @NotBlank(message = "El nombre es obligatorio")
        String nombre,

        @NotBlank(message = "El curso debe llevar una descripcion")
        String descripcion

) {
}
