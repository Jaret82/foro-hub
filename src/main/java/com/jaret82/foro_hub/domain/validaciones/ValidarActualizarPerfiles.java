package com.jaret82.foro_hub.domain.validaciones;

import com.jaret82.foro_hub.domain.models.usuario.perfil.Perfil;
import com.jaret82.foro_hub.domain.validaciones.ivalidaciones.IValidadorActualizacionPerfiles;
import com.jaret82.foro_hub.handler.CustomNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidarActualizarPerfiles implements IValidadorActualizacionPerfiles {

    @Override
    public void validarId(List<Perfil> perfiles, Long id) {
        List<Long> idPerfiles = perfiles.stream().map(Perfil::getId).toList();
        boolean idExiste = idPerfiles.contains(id);
        if (!idExiste){
            throw new CustomNotFoundException("Id no encontrado.");

        }
    }


}
