package com.jaret82.foro_hub.domain.services.iservices;

import com.jaret82.foro_hub.domain.models.usuario.users.DatosRegistroUsuario;
import com.jaret82.foro_hub.domain.models.usuario.users.DatosRespuestaRegistroUsuario;

public interface IRegistroService {
    DatosRespuestaRegistroUsuario registroUsuario(DatosRegistroUsuario datosUsuario);
}
