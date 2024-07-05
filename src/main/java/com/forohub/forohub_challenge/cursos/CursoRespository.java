package com.forohub.forohub_challenge.cursos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface CursoRespository extends JpaRepository<Curso, Long> {
    @Query("SELECT c FROM Curso c WHERE c.status = 'ACTIVADO'")
    Page<Curso> findByStatusActivado(Pageable paginacion);

}
