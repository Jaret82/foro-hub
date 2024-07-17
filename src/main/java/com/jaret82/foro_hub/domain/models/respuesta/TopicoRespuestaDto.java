package com.jaret82.foro_hub.domain.models.respuesta;

public record TopicoRespuestaDto(
        long id,
        String titulo,
        String mensaje,
        String nombre,
        String fechaCreacion
){
}
