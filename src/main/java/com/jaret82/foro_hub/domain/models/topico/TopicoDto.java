package com.jaret82.foro_hub.domain.models.topico;

public record TopicoDto(

        Long id,

        String titulo,

        String mensaje,

        String autor,

        String curso,

        String fechaCreacion

) {

    public TopicoDto(Topico topico){
        this(topico.getId(), topico.getTitulo(),topico.getMensaje(),
                topico.getPerfilId().getNombre(), topico.getCurso().toString(),topico.getFechaCreacion());
    }

}
