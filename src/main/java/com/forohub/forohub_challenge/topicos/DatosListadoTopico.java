package com.forohub.forohub_challenge.topicos;

import com.forohub.forohub_challenge.cursos.Curso;

import java.time.LocalDateTime;

public record DatosListadoTopico(Long id, String titulo, String mensaje,LocalDateTime fechaCreacion, String status, Long autorId, Long cursoId, String cursoNombre) {
    public DatosListadoTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(),topico.getFechaCreacion(), topico.getStatus().toString(), topico.getAutor().getId(), topico.getCurso().getId(), topico.getCurso().getNombre());

    }

}
