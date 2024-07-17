package com.jaret82.foro_hub.domain.services.iservices;

import com.jaret82.foro_hub.domain.models.topico.ActualizarTopicoDto;
import com.jaret82.foro_hub.domain.models.topico.Topico;
import com.jaret82.foro_hub.domain.models.topico.TopicoDto;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITopicoService {

    public List<TopicoDto> listarTopicos();
    public Page<TopicoDto> paginarTopicos(Pageable pageable);
    public TopicoDto crearTopico(Topico topico) throws BadRequestException;

    TopicoDto listarPorId(Long id);

    TopicoDto actualizarTopico(ActualizarTopicoDto body);

    void borrarTopico(Long id);
}
