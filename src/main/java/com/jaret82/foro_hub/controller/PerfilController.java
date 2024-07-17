package com.jaret82.foro_hub.controller;

import com.jaret82.foro_hub.domain.models.usuario.perfil.DatosActualizarPerfil;
import com.jaret82.foro_hub.domain.models.usuario.perfil.DatosCrearPerfil;
import com.jaret82.foro_hub.domain.models.usuario.perfil.DatosRespuestaActualizacionPerfil;
import com.jaret82.foro_hub.domain.models.usuario.perfil.DatosRespuestaPerfil;
import com.jaret82.foro_hub.domain.services.PerfilService;
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


@RestController
@ResponseBody
@RequestMapping("/perfil")
@SecurityRequirement(name = "bearer-key")
public class PerfilController {

    private final PerfilService perfilService;

    @Autowired
    public PerfilController(PerfilService perfilService) {
        this.perfilService = perfilService;
    }


    @PostMapping
    @Transactional
    @Operation(
            summary = "Realiza el registro de un perfil asociado a un usuario la base de datos",
            description = "",
            tags = {"perfil", "post"})
    public ResponseEntity<DatosRespuestaPerfil> crearPerfil(
            @RequestBody @Valid DatosCrearPerfil datos) {
        DatosRespuestaPerfil perfilCreado = perfilService.crearPerfil(datos);
        return ResponseEntity.ok(perfilCreado);
    }


    @PutMapping("{id}")
    @Transactional
    @Operation(
            summary = "Actualiza el registro de un perfil asociado a un usuario en la base de datos",
            description = "",
            tags = {"perfil", "put"})
    public ResponseEntity<DatosRespuestaActualizacionPerfil> actualizarPerfil(
            @RequestBody @Valid DatosActualizarPerfil datos, @PathVariable Long id){

        DatosRespuestaActualizacionPerfil datosRespuesta = perfilService.actualizarPerfil(datos, id);
        return ResponseEntity.ok(datosRespuesta);

    }

    @GetMapping("{id}")
    @Operation(
            summary = "Obtiene el registro de un perfil asociado a un usuario en la base de datos",
            description = "",
            tags = {"perfil", "get"})
    public ResponseEntity<DatosRespuestaPerfil> verPerfil(@PathVariable Long id){
        DatosRespuestaPerfil perfil = perfilService.obtenerPerfil(id);
        return ResponseEntity.ok(perfil);
    }

    @GetMapping("/listaPerfiles")
    @Operation(
            summary = "Obtiene los datos de los perfiles en forma de paginación de la base de datos",
            description = "",
            tags = {"perfil", "get"})
    public ResponseEntity<Page<DatosRespuestaPerfil>> paginarPerfiles(@PageableDefault(
            size = 5, sort = "fechaActualizacion") Pageable pagina){
        Page<DatosRespuestaPerfil> respuesta = perfilService.paginarPerfiles(pagina);
        return ResponseEntity.ok(respuesta);
    }

    @DeleteMapping("{id}")
    @Operation(
            summary = "Elimina de forma logica el registro de un perfil asociado a un usuario en la base de datos",
            description = "",
            tags = {"perfil", "delete"})
    public ResponseEntity deshabilitarPerfil(@PathVariable Long id) {
        perfilService.deshabilitarPerfil(id);
        return ResponseEntity.noContent().build();
    }

}
