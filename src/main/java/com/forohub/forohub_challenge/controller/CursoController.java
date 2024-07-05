package com.forohub.forohub_challenge.controller;


import com.forohub.forohub_challenge.cursos.*;
import com.forohub.forohub_challenge.infra.errores.ValidacionDeIntegridad;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/cursos")
@SecurityRequirement(name = "bearer-key")
public class CursoController {

    @Autowired
    private CursoRespository cursoRespository;

    //Registrar Cursos
    @PostMapping
    @Transactional
    @Operation(summary = "Registra un nuevo curso en la base de datos")
    public ResponseEntity registrarCurso(@RequestBody @Valid DatosRegistroCurso datos, UriComponentsBuilder uriBuilder) {
        var curso = new Curso(datos);
        cursoRespository.save(curso);

        var uri = uriBuilder.path("/cursos/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosDetallesCurso(curso));
    }

    //Listar Cursos
    @GetMapping
    @Operation(summary = "Obtiene el listado de cursos")
    public ResponseEntity<Page<DatosListadoCurso>> listarCursos(@PageableDefault(size = 5) Pageable paginacion) {
        var page = cursoRespository.findByStatusActivado(paginacion).map(DatosListadoCurso::new);
        return ResponseEntity.ok(page);
    }

    //Actualizar Cursos

    @PutMapping("/{id}")
    @Transactional
    @Operation(summary = "Actualiza los datos de un curso existente")
    public ResponseEntity actualizarCurso(@PathVariable Long id,@RequestBody @Valid DatosActualizarCurso datos) {
        if (cursoRespository.findById(id).isEmpty()){
            throw new ValidacionDeIntegridad("El curso no fue encontrado. Verifique el id.");
        }
        var curso = cursoRespository.getReferenceById(datos.id());
        curso.actualizarInformacoes(datos);

        return ResponseEntity.ok(new DatosDetallesCurso(curso));
    }
    //DELETE
    @DeleteMapping("/admin/{id}")
    @Transactional
    @Operation(summary = "Elimina un curso registrado")
    public ResponseEntity eliminarCurso(@PathVariable Long id) {
        if (cursoRespository.findById(id).isEmpty()){
            throw new ValidacionDeIntegridad("El Topico no fue encontrado. Verifique el id.");
        }
        cursoRespository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    //Eliminar Cursos (DELETE LOGICO)
    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Elimina un curso registrado - inactivo")
    public ResponseEntity desactivarCurso(@PathVariable Long id) {
        if (cursoRespository.findById(id).isEmpty()){
            throw new ValidacionDeIntegridad("El Topico no fue encontrado. Verifique el id.");
        }
        Curso curso = cursoRespository.getReferenceById(id);
        curso.desactivarCurso();
        return ResponseEntity.noContent().build();
    }

    //DETALLES DE CURSOS
    @GetMapping("/{id}")
    @Operation(summary = "Obtiene los registros del curso con ID")
    public ResponseEntity<DatosRespuestaCurso> retornaDatosCurso(@PathVariable Long id) {
        if (cursoRespository.findById(id).isEmpty()){
            throw new ValidacionDeIntegridad("El Topico no fue encontrado. Verifique el id.");
        }
        Curso curso = cursoRespository.getReferenceById(id);
        var datosCurso = new DatosRespuestaCurso(curso.getId(), curso.getNombre(), curso.getDescripcion(), curso.getStatus().toString());
        return ResponseEntity.ok(datosCurso);
    }







}
