package com.forohub.forohub_challenge.controller;

import com.forohub.forohub_challenge.infra.errores.ValidacionDeIntegridad;
import com.forohub.forohub_challenge.usuarios.*;
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
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    //Registrar Usuarios
    @PostMapping
    @Transactional
    @Operation(summary = "Registra los usuarios en la base de datos")
    public ResponseEntity registrarUsuario(@RequestBody @Valid DatosRegistroUsuario datos, UriComponentsBuilder uriBuilder) {
        var usuario = new Usuario(datos);
        usuarioRepository.save(usuario);

        var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosDetallesUsuario(usuario));
    }

    //Listar Usuarios

    @GetMapping
    @Operation(summary = "Obtiene el listado de los usuarios")
    public ResponseEntity<Page<DatosListadoUsuario>> listarUsuarios(@PageableDefault(size = 5) Pageable paginacion) {
        var page = usuarioRepository.listarUsuarios(paginacion).map(DatosListadoUsuario::new);
        return ResponseEntity.ok(page);
    }

    //Actualizar Usuarios

    @PutMapping("/{id}")
    @Transactional
    @Operation(summary = "Actualiza los datos de un usuario existente")
    public ResponseEntity actualizarUsuario(@PathVariable Long id,@RequestBody @Valid DatosActualizarUsuario datos) {
        if (usuarioRepository.findById(id).isEmpty()){
            throw new ValidacionDeIntegridad("El usuario no fue encontrado. Verifique el id.");
        }
        var usuario = usuarioRepository.getReferenceById(datos.id());
        usuario.actualizarInformacoes(datos);

        return ResponseEntity.ok(new DatosDetallesUsuario(usuario));
    }
    //DELETE
    @DeleteMapping("/admin/{id}")
    @Transactional
    @Operation(summary = "Elimina un usuario registrado")
    public ResponseEntity eliminarUsuario(@PathVariable Long id) {
        if (usuarioRepository.findById(id).isEmpty()){
            throw new ValidacionDeIntegridad("El Usuario no fue encontrado. Verifique el id.");
        }
        usuarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    //DETALLES DE Usuarios
    @GetMapping("/{id}")
    @Operation(summary = "Obtiene los registros del usuario con ID")
    public ResponseEntity<DatosRespuestaUsuario> retornaDatosUsuario(@PathVariable Long id) {
        if (usuarioRepository.findById(id).isEmpty()){
            throw new ValidacionDeIntegridad("El Usuario no fue encontrado. Verifique el id.");
        }
        Usuario usuario = usuarioRepository.getReferenceById(id);
        var datosCurso = new DatosRespuestaUsuario(usuario.getId(), usuario.getNombre(), usuario.getLogin(), usuario.getClave(), usuario.getFechaCreacion());
        return ResponseEntity.ok(datosCurso);
    }
}
