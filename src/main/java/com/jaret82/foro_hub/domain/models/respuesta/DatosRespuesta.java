package com.jaret82.foro_hub.domain.models.respuesta;

public record DatosRespuesta(

        Long id,

        String mensaje,

        String fechaCreacion,

        TopicoRespuestaDto topicoId
) {
}
