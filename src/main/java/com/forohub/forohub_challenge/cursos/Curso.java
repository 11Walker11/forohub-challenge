package com.forohub.forohub_challenge.cursos;

import com.forohub.forohub_challenge.topicos.StatusTopico;
import com.forohub.forohub_challenge.usuarios.DatosRegistroUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Table(name = "cursos")
@Entity(name = "Curso")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String descripcion;

    @Enumerated(EnumType.STRING)
    private StatusCurso status;

    public Curso(DatosRegistroCurso datos) {
        this.nombre = datos.nombre();
        this.descripcion = datos.descripcion();
        this.status = StatusCurso.ACTIVADO;
    }


    public void actualizarInformacoes(DatosActualizarCurso datos) {
        if (datos.nombre() != null){
            this.nombre = datos.nombre();
        }
        if (datos.descripcion() != null){
            this.descripcion = datos.descripcion();
        }
    }

    public void desactivarCurso() {
        this.status = StatusCurso.DESACTIVADO;
    }
}
