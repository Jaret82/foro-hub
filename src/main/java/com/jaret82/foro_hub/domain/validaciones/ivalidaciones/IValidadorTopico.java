package com.jaret82.foro_hub.domain.validaciones.ivalidaciones;

import com.jaret82.foro_hub.domain.models.topico.TopicoDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IValidadorTopico {

    public void validarPagina(Page datos);

    public void topicoExiste(boolean titulo, boolean mensaje);

    public void idPerfilNoEncontrado(boolean id);

    void topicoIdNoexiste(boolean topicoId);

    void listaVacia(List<TopicoDto> topicos);
}

