package com.jaret82.foro_hub.domain.validaciones;

import com.jaret82.foro_hub.domain.models.usuario.perfil.DatosCrearPerfil;
import com.jaret82.foro_hub.domain.models.usuario.users.Usuario;
import com.jaret82.foro_hub.domain.validaciones.ivalidaciones.IValidarRegistroPerfiles;
import com.jaret82.foro_hub.handler.CustomNotFoundException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidarRegistroPerfiles implements IValidarRegistroPerfiles {
    @Override
    public void validarDatosEntrada(DatosCrearPerfil datos) {
        if(datos == null){
            throw new InvalidDataAccessApiUsageException("ERROR EN LOS DATOS: Esta ingresando valores nulos");

        }
    }

    @Override
    public void validarId(List<Usuario> usuarios, Long id) {
        List<Long> usuariosIds = usuarios.stream().map(Usuario::getId).toList();
        boolean idExiste = usuariosIds.contains(id);

        if (!idExiste){
            throw new CustomNotFoundException("Id no encontrado.");
        }

    }
}
