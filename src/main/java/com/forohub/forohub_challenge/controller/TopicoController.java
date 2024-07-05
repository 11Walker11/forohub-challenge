package com.forohub.forohub_challenge.controller;

import com.forohub.forohub_challenge.cursos.Curso;
import com.forohub.forohub_challenge.cursos.CursoRespository;
import com.forohub.forohub_challenge.infra.errores.ValidacionDeIntegridad;
import com.forohub.forohub_challenge.topicos.*;
import com.forohub.forohub_challenge.usuarios.Usuario;
import com.forohub.forohub_challenge.usuarios.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRespository cursoRespository;

    //REGISTRAR TOPICO
    @PostMapping
    @Transactional
    @Operation(summary = "Registra los topicos en la base de datos")
    public ResponseEntity<DatosDetallesTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datos, UriComponentsBuilder uriBuilder) {
        Usuario autor = usuarioRepository.findById(datos.autorId())
                .orElseThrow(() -> new ValidacionDeIntegridad("Usuario no encontrado"));
        Curso curso = cursoRespository.findById(datos.cursoId())
                .orElseThrow(() -> new ValidacionDeIntegridad("Curso no encontrado"));

        var topico = new Topico(datos, autor, curso);
        topicoRepository.save(topico);

        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosDetallesTopico(topico));
    }


    //LISTAR TOPICOS
    @GetMapping
    @Operation(summary = "Obtiene el listado de los topicos")
    public ResponseEntity<Page<DatosListadoTopico>> listarTopicos(@PageableDefault(size = 10, sort = {"titulo"}) Pageable paginacion) {
        var page = topicoRepository.listarTopicos(paginacion).map(DatosListadoTopico::new);
        return ResponseEntity.ok(page);
    }


    //ACTUALIZAR TOPICO
    @PutMapping("/{id}")
    @Transactional
    @Operation(summary = "Actualiza los datos de un topico existente")
    public ResponseEntity actualizarTopico(@PathVariable Long id,@RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {
        if (topicoRepository.findById(id).isEmpty()){
            throw new ValidacionDeIntegridad("El Topico no fue encontrado. Verifique el id.");
        }
        Topico topico = topicoRepository.getReferenceById(datosActualizarTopico.id());
        topico.actualizarDatos(datosActualizarTopico);
        return ResponseEntity.ok(new DatosRespuestaTopico(topico.getId(), topico.getTitulo(), topico.getMensaje(),
                topico.getFechaCreacion(), topico.getStatus().toString(),topico.getAutor().getId(), topico.getCurso().getId()));
    }
    //DELETE
//    @DeleteMapping("/{id}")
//    @Transactional
//    public ResponseEntity eliminarTopico(@PathVariable Long id) {
//        if (topicoRepository.findById(id).isEmpty()){
//            throw new ValidacionDeIntegridad("El Topico no fue encontrado. Verifique el id.");
//        }
//        topicoRepository.deleteById(id);
//        return ResponseEntity.noContent().build();
//    }

    // DELETE LOGICO
    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Elimina un topico registrado - inactivo")
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        if (topicoRepository.findById(id).isEmpty()){
            throw new ValidacionDeIntegridad("El Topico no fue encontrado. Verifique el id.");
        }
        Topico topico = topicoRepository.getReferenceById(id);
        topico.desactivarTopico();
        return ResponseEntity.noContent().build();
    }

    //DETALLE DE UN TOPICO
    @GetMapping("/{id}")
    @Operation(summary = "Obtiene los registros del topico con ID")
    public ResponseEntity<DatosRespuestaTopico> retornaDatosTopico(@PathVariable Long id) {
        if (topicoRepository.findById(id).isEmpty()){
            throw new ValidacionDeIntegridad("El Topico no fue encontrado. Verifique el id.");
        }
        Topico topico = topicoRepository.getReferenceById(id);
        var datosTopico = new DatosRespuestaTopico(topico.getId(), topico.getTitulo(), topico.getMensaje(),
                topico.getFechaCreacion(), topico.getStatus().toString(), topico.getAutor().getId(), topico.getCurso().getId());
        return ResponseEntity.ok(datosTopico);
    }



}
