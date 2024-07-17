package com.jaret82.foro_hub.domain.models.respuesta;

import com.jaret82.foro_hub.domain.models.categoria.Curso;

public record DatosRespuestaPorId(

        Long id,

        String mensaje,

        String titulo,

        Curso curso,

        String autor,

        Long topicoId,

        String fechaCreacion
) {
}
