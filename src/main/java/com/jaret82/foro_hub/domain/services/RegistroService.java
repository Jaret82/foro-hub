package com.jaret82.foro_hub.domain.services;

import com.jaret82.foro_hub.domain.models.categoria.Roles;
import com.jaret82.foro_hub.domain.models.usuario.users.DatosRegistroUsuario;
import com.jaret82.foro_hub.domain.models.usuario.users.DatosRespuestaRegistroUsuario;
import com.jaret82.foro_hub.domain.models.usuario.users.Usuario;
import com.jaret82.foro_hub.domain.models.categoria.Roles;
import com.jaret82.foro_hub.domain.repository.UsuarioRepositorio;
import com.jaret82.foro_hub.domain.services.iservices.IRegistroService;
import com.jaret82.foro_hub.domain.validaciones.ValidarRegistroUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class RegistroService implements IRegistroService {

    private final UsuarioRepositorio usuarioRepositorio;
    private final PasswordEncoder passwordEncoder;
    private final List<ValidarRegistroUsuarios> validador;

    @Autowired
    public RegistroService(UsuarioRepositorio usuarioRepositorio, PasswordEncoder passwordEncoder,
                           List<ValidarRegistroUsuarios> validador){
        this.usuarioRepositorio = usuarioRepositorio;
        this.passwordEncoder = passwordEncoder;
        this.validador = validador;
    }


    @Override
    public DatosRespuestaRegistroUsuario registroUsuario(DatosRegistroUsuario datosUsuario) {

        boolean usuarioExiste = usuarioRepositorio.existsByLogin(datosUsuario.login());
        validador.forEach(v->v.usuarioExiste(usuarioExiste));

        var passwordEncriptada = passwordEncoder.encode(datosUsuario.password());
        Roles rol = datosUsuario.rol();
        String fechaCreacion = crearFormatoFecha();
        boolean activo = true;
        Usuario usuario = usuarioRepositorio.save(new Usuario(datosUsuario,passwordEncriptada,rol,fechaCreacion,activo));

        return new DatosRespuestaRegistroUsuario(usuario.getId(), usuario.getLogin(), usuario.getRol());
    }

    public String crearFormatoFecha(){

        LocalDateTime fechaCreacion = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String fechaFormateada = fechaCreacion.format(formatter);

        return fechaFormateada;

    }
}
