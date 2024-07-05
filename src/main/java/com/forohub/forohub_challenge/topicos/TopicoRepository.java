package com.forohub.forohub_challenge.topicos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico,Long> {
    @Query("SELECT t FROM Topico t ORDER BY t.fechaCreacion ASC")
    Page<Topico> listarTopicos(Pageable paginacion);
}
