package com.jaret82.foro_hub.domain.validaciones;

import com.jaret82.foro_hub.domain.validaciones.ivalidaciones.IValidarRegistroUsuarios;
import org.springframework.stereotype.Component;

@Component
public class ValidarRegistroUsuarios implements IValidarRegistroUsuarios {

    @Override
    public void usuarioExiste(boolean usuarioExiste) {
        if (usuarioExiste){
            throw new IllegalArgumentException("ERROR USERNAME: El nombre de usuario ingresado ya existe");
        }
    }


}
