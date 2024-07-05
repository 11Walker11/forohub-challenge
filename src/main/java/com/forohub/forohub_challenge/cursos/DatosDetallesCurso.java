package com.forohub.forohub_challenge.cursos;

public record DatosDetallesCurso(

        Long id,

        String nombre,

        String descripcion,

        StatusCurso status
) {
    public DatosDetallesCurso(Curso curso) {
        this(curso.getId(), curso.getNombre(), curso.getDescripcion(), curso.getStatus());
    }
}
