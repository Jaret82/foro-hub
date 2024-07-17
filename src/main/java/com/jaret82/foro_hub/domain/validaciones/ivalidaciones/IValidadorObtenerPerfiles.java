package com.jaret82.foro_hub.domain.validaciones.ivalidaciones;

import com.jaret82.foro_hub.domain.models.usuario.perfil.Perfil;
import org.springframework.data.domain.Page;

public interface IValidadorObtenerPerfiles {
    void validarPerfiles(Page<Perfil> perfiles);
}
