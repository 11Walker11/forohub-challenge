package com.forohub.forohub_challenge.topicos;

import com.forohub.forohub_challenge.cursos.Curso;
import com.forohub.forohub_challenge.usuarios.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String mensaje;

    private LocalDateTime fechaCreacion;

    @Enumerated(EnumType.STRING)
    private StatusTopico status;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="usuario_id", referencedColumnName="id")
    private Usuario autor;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="curso_id", referencedColumnName="id")
    private Curso curso;


    public Topico(DatosRegistroTopico datosRegistroTopico, Usuario autor, Curso curso) {
        this.titulo = datosRegistroTopico.titulo();
        this.fechaCreacion = LocalDateTime.now();
        this.mensaje = datosRegistroTopico.mensaje();
        this.status = StatusTopico.ACTIVO;
        this.autor = autor;
        this.curso = curso;
    }

    public void actualizarDatos(DatosActualizarTopico datosActualizarTopico) {

        if (datosActualizarTopico.titulo() != null){
            this.titulo = datosActualizarTopico.titulo();
        }
        if (datosActualizarTopico.mensaje() != null){
            this.mensaje = datosActualizarTopico.mensaje();
        }
        if (datosActualizarTopico.status() != null){
            this.status = StatusTopico.valueOf(datosActualizarTopico.status());
        }
    }

    public void desactivarTopico() {
        this.status = StatusTopico.INACTIVO;
    }
}
