package com.jaret82.foro_hub.domain.validaciones.ivalidaciones;

import com.jaret82.foro_hub.domain.models.usuario.perfil.DatosCrearPerfil;
import com.jaret82.foro_hub.domain.models.usuario.users.Usuario;

import java.util.List;

public interface IValidarRegistroPerfiles {
    void validarDatosEntrada(DatosCrearPerfil datos);

    void validarId(List<Usuario> usuarios, Long id);
}
