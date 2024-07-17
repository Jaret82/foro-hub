package com.jaret82.foro_hub.domain.validaciones;

import com.jaret82.foro_hub.domain.models.topico.TopicoDto;
import com.jaret82.foro_hub.domain.validaciones.ivalidaciones.IValidadorTopico;
import com.jaret82.foro_hub.handler.NoContentException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidarDatosTopicos implements IValidadorTopico {


    @Override
    public void validarPagina(Page datos) {
        if (datos.getContent().isEmpty()){
            throw new NoContentException("No hay topicos para mostrar");
        }

    }

    @Override
    public void topicoExiste(boolean titulo, boolean mensaje) {
        if (titulo ) {
            throw new NoContentException("El titulo ya existe!");
        }
        if (mensaje) {
            throw new NoContentException("El mensaje ya exisite!");
        }
    }

    @Override
    public void idPerfilNoEncontrado(boolean id) {
        if (!id){
            throw new NoContentException("ERROR ID: Id del perfil no encontrado.");
        }
    }

    @Override
    public void topicoIdNoexiste(boolean topicoId) {
        if (!topicoId){
            throw new NoContentException("ERROR ID: Id del topico no encontrado.");
        }
    }

    @Override
    public void listaVacia(List<TopicoDto> topicos) {
        if (topicos.isEmpty()){
            throw new NoContentException("No hay topicos para mostrar");
        }
    }
}
