package com.jaret82.foro_hub.domain.models.usuario.users;

import com.jaret82.foro_hub.domain.models.categoria.Roles;

public record DatosRegistroUsuario(

        Long id,

        String login,

        String password,

        Roles rol
) {

}
