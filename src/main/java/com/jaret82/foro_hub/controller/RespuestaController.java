package com.jaret82.foro_hub.controller;

import com.jaret82.foro_hub.domain.models.respuesta.DatoRegistroRespuesta;
import com.jaret82.foro_hub.domain.models.respuesta.DatosRespuesta;
import com.jaret82.foro_hub.domain.models.respuesta.DatosRespuestaPorId;
import com.jaret82.foro_hub.domain.models.respuesta.DatosRespuestaRegistro;
import com.jaret82.foro_hub.domain.services.RespuestaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/respuestas")
@SecurityRequirement(name = "bearer-key")
public class RespuestaController {

    private final RespuestaService respuestaService;

    @Autowired
    public RespuestaController(RespuestaService respuestaService){
        this.respuestaService = respuestaService;
    }

    @GetMapping
    @Operation(
            summary = "Obtiene las respuestas registradas en la base de datos",
            description = "",
            tags = {"respuesta", "get"})
    public ResponseEntity<Page<DatosRespuesta>> paginarRespuestas(
            @PageableDefault(size = 5, sort = "fechaCreacion", direction = Sort.Direction.ASC)
            Pageable pagina)
    {
        Page<DatosRespuesta> respuesta = respuestaService.obtenerRespuestas(pagina);
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("{id}")
    @Operation(
            summary = "Obtiene la respuesta registrada en la base de datos por un id proporcionado",
            description = "",
            tags = {"respuesta", "get"})
    public ResponseEntity<DatosRespuestaPorId> obtenerRespuestaPorId(@PathVariable Long id){
        DatosRespuestaPorId respuesta = respuestaService.obtenerRespuestaPorId(id);
        return ResponseEntity.ok(respuesta);
    }

    @PostMapping
    @Transactional
    @Operation(
            summary = "Realiza el registro de una respuesta asociado a un perfil la base de datos",
            description = "",
            tags = {"respuesta", "post"})
    public ResponseEntity<DatosRespuestaRegistro> registrarRespuesta(@RequestBody @Valid DatoRegistroRespuesta datos){
        DatosRespuestaRegistro respuesta = respuestaService.registrarRespuesta(datos);
        return ResponseEntity.ok(respuesta);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarRespuesta(@PathVariable Long id){
        respuestaService.eliminarRespuesta(id);
        return ResponseEntity.noContent().build();
    }

}
