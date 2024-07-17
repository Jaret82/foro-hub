package com.jaret82.foro_hub.domain.validaciones;

import com.jaret82.foro_hub.domain.models.respuesta.DatoRegistroRespuesta;
import com.jaret82.foro_hub.domain.models.respuesta.Respuesta;
import com.jaret82.foro_hub.domain.models.topico.Topico;
import com.jaret82.foro_hub.domain.models.usuario.perfil.Perfil;
import com.jaret82.foro_hub.domain.validaciones.ivalidaciones.IValidarRegistroRespuesta;
import com.jaret82.foro_hub.handler.NoContentException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ValidarRegistroRespuestas implements IValidarRegistroRespuesta {
    @Override
    public void existenRespuestas(Page<Respuesta> respuestas) {
        if (respuestas.isEmpty()){
            throw new NoContentException("No hay respuestas para mostrar. ");
        }
    }

    @Override
    public void existeRespuestaId(List<Respuesta> respuestas, Long id) {
        List<Long> IdRespuestas = respuestas.stream().map(Respuesta::getId).toList();

        boolean idExiste = IdRespuestas.contains(id);
        if (!idExiste){
            throw new NoContentException("El id no existe");
        }
    }

    @Override
    public void existenDatos(DatoRegistroRespuesta datos) {
        if(datos == null){
            throw new NullPointerException("No hay datos para ingresar.");
        }
    }

    @Override
    public void existeId(Long id) {
        if (id == null){
            throw new NoContentException("El Valor del id esta nulo. Por favor ingrese el id");
        }
    }

    @Override
    public void perfilExiste(Optional<Perfil> perfil) {
        if (perfil.isEmpty()){
            throw new NoContentException("El perfil no existe");
        }
    }

    @Override
    public void topicoExiste(Optional<Topico> topico) {
        if (topico.isEmpty()){
            throw new NoContentException("El topico no existe.");
        }
    }

    @Override
    public void existeRespuestaEnDb(boolean existeRespuesta) {
        if (!existeRespuesta){
            throw new NullPointerException("No existe la respuesta en la base de datos");
        }
    }

    @Override
    public void respuestaExiste(Optional<Respuesta> respuesta) {
        if (respuesta.isEmpty()){
            throw new NoContentException("No existe ninguna respuesta con ese id.");
        }
    }
}
