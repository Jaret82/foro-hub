package com.jaret82.foro_hub.controller;

import com.jaret82.foro_hub.domain.models.topico.ActualizarTopicoDto;
import com.jaret82.foro_hub.domain.models.topico.Topico;
import com.jaret82.foro_hub.domain.models.topico.TopicoDto;
import com.jaret82.foro_hub.domain.repository.PerfilRepositorio;
import com.jaret82.foro_hub.domain.services.TopicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseBody
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @Autowired
    private PerfilRepositorio perfilRepositorio;

    //Ruta para obtener todos los topicos de la base de datos en una lista http://localhost:8080/topicos/lista
    @GetMapping(value = "/lista")
    @Operation(
            summary = "Obtiene los topicos registrados en la base de datos",
            description = "",
            tags = {"topico", "get"})
    public ResponseEntity<List<TopicoDto>> listadoTopicos(){
        var response = topicoService.listarTopicos();
        return ResponseEntity.ok().body(response);
    }

    //Ruta para obtener un topico de la base de datos lista http://localhost:8080/topicos/:id
    @GetMapping("/{id}")
    @Operation(
            summary = "Obtiene un topico registrado en la base de datos por un id proporcionado",
            description = "",
            tags = {"topico", "get"})
    public ResponseEntity<TopicoDto> topicoPorId(@PathVariable Long id){
        return ResponseEntity.ok(topicoService.listarPorId(id));
    }

    //Ruta para obtener los topicos de la base de datos en una pagina http://localhost:8080/topicos
    @GetMapping
    @Transactional
    @Operation(
            summary = "Obtiene las paginas de los topicos registrados en la base de datos",
            description = "",
            tags = {"topico", "get"})
    public ResponseEntity<Page<TopicoDto>> paginarTopicos(
            @PageableDefault(sort = "fechaCreacion", direction = Sort.Direction.ASC,size = 5) Pageable pagina){
        return ResponseEntity.ok(topicoService.paginarTopicos(pagina));
    }

    //Ruta para crear un nuevo topico en la base de datos  http://localhost:8080/topicos
    @PostMapping
    @Transactional
    @Operation(
            summary = "Registra un topico en la base de datos",
            description = "",
            tags = {"topico", "post"})
    public ResponseEntity<TopicoDto> crearTopico(@RequestBody @Valid Topico topico) throws BadRequestException {

        topico.setFechaCreacion(topicoService.crearFormatoFecha());
        var response =  topicoService.crearTopico(topico);

        return ResponseEntity.ok().body(response);
    }

    //Ruta para actualizar un topico de la base de datos  http://localhost:8080/topicos
    @PutMapping
    @Transactional
    @Operation(
            summary = "Actualiza un topico en la base de datos",
            description = "",
            tags = {"topico", "put"})
    public ResponseEntity<TopicoDto> actualizarTopico(@RequestBody @Valid ActualizarTopicoDto body){
        return ResponseEntity.ok(topicoService.actualizarTopico(body));
    }

    //Ruta para eliminar de forma logica un topico de la base de datos  http://localhost:8080/topicos/lista/:id
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Elimina un topico de la base de datos",
            description = "",
            tags = {"topico", "put"})
    public ResponseEntity<TopicoDto> borrarTopico(@PathVariable Long id ){
        topicoService.borrarTopico(id);
        return ResponseEntity.ok().build();
    }
}
