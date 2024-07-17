package com.jaret82.foro_hub.domain.validaciones;

import com.jaret82.foro_hub.domain.models.usuario.perfil.Perfil;
import com.jaret82.foro_hub.domain.validaciones.ivalidaciones.IValidadorObtenerPerfiles;
import com.jaret82.foro_hub.handler.NoContentException;
import org.springframework.data.domain.Page;

public class ValidarObtenerPerfiles implements IValidadorObtenerPerfiles {
    @Override
    public void validarPerfiles(Page<Perfil> perfiles) {
        if (perfiles.isEmpty()){
            throw new NoContentException("No hay perfiles para mostrar.");
        }
    }
}
