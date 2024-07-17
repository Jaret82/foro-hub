package com.jaret82.foro_hub.domain.validaciones.ivalidaciones;

import com.jaret82.foro_hub.domain.models.usuario.perfil.Perfil;

import java.util.List;

public interface IValidadorActualizacionPerfiles {
    void validarId(List<Perfil> perfiles, Long id);


}
