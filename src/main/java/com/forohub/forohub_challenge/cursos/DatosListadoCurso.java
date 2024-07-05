package com.forohub.forohub_challenge.cursos;

public record DatosListadoCurso(Long id, String nombre, String descripcion, String status) {

    public DatosListadoCurso(Curso curso) {
        this(curso.getId(), curso.getNombre(), curso.getDescripcion(), curso.getStatus().toString());
    }
}
