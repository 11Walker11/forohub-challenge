package com.forohub.forohub_challenge.topicos;

import com.forohub.forohub_challenge.cursos.Curso;

import java.time.LocalDateTime;

public record DatosDetallesTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        StatusTopico status,
        Long autorId,
        Long cursoId
) {

    public DatosDetallesTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(),topico.getFechaCreacion(),topico.getStatus(),topico.getAutor().getId(), topico.getCurso().getId());
    }
}
