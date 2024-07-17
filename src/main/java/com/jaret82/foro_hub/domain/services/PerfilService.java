package com.jaret82.foro_hub.domain.services;

import com.jaret82.foro_hub.domain.models.usuario.perfil.*;
import com.jaret82.foro_hub.domain.models.usuario.users.Usuario;
import com.jaret82.foro_hub.domain.repository.PerfilRepositorio;
import com.jaret82.foro_hub.domain.repository.UsuarioRepositorio;
import com.jaret82.foro_hub.domain.services.iservices.IPerfilService;
import com.jaret82.foro_hub.domain.validaciones.ivalidaciones.IValidadorActualizacionPerfiles;
import com.jaret82.foro_hub.domain.validaciones.ivalidaciones.IValidadorObtenerPerfiles;
import com.jaret82.foro_hub.domain.validaciones.ivalidaciones.IValidarRegistroPerfiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class PerfilService implements IPerfilService {

    private final PerfilRepositorio perfilRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;
    private final List<IValidarRegistroPerfiles> validadorRegistro;
    private final List<IValidadorActualizacionPerfiles> validadorActualizacion;
    private final List<IValidadorObtenerPerfiles> validadorObtenerPerfiles;

    @Autowired
    public PerfilService(
        PerfilRepositorio perfilRepositorio,
        List<IValidarRegistroPerfiles> validadorRegistro,
        List<IValidadorActualizacionPerfiles> validadorActualizacion,
        List<IValidadorObtenerPerfiles> validadorObtenerPerfiles,
        UsuarioRepositorio usuarioRepositorio )
    {
        this.perfilRepositorio = perfilRepositorio;
        this.validadorRegistro = validadorRegistro;
        this.usuarioRepositorio = usuarioRepositorio;
        this.validadorActualizacion = validadorActualizacion;
        this.validadorObtenerPerfiles = validadorObtenerPerfiles;
    }

    @Override
    public DatosRespuestaPerfil crearPerfil(DatosCrearPerfil datos) {

        validadorRegistro.forEach(v->v.validarDatosEntrada(datos));

        boolean activo = true;

        List<Usuario> usuarios = usuarioRepositorio.findAll();

        validadorRegistro.forEach(v->v.validarId(usuarios, datos.id()));

        Optional<Usuario> usuario = usuarioRepositorio.findById(datos.id());

        Perfil perfilUsuario = new Perfil();
        perfilUsuario.setNombre(datos.nombre());
        perfilUsuario.setFechaCreacion(obtenerFechaActual());
        perfilUsuario.setFechaActualizacion(obtenerFechaActual());
        perfilUsuario.setActivo(activo);
        perfilUsuario.setUsuarioId(usuario.get());

        perfilRepositorio.save(perfilUsuario);
        Perfil perfilId = perfilRepositorio.findByNombre(perfilUsuario.getNombre());
        usuario.get().actualizarId(perfilId);

        usuarioRepositorio.save(usuario.get());

        return new DatosRespuestaPerfil(perfilUsuario.getId(),perfilUsuario.getNombre(),perfilUsuario.getFechaCreacion(),
                perfilUsuario.getFechaActualizacion());
    }

    @Override
    public DatosRespuestaActualizacionPerfil actualizarPerfil(DatosActualizarPerfil datos,Long id) {

        List<Perfil> perfiles = perfilRepositorio.findAll();
        validadorActualizacion.forEach(v->v.validarId(perfiles,id));

        String fechaActualizacion = obtenerFechaActual();

        Optional<Perfil> encontrarPerfil = perfiles.stream()
                .filter(perfil -> perfil.getId().equals(id))
                .findFirst();

        Perfil perfilEncontrado = encontrarPerfil.get();
        perfilEncontrado.actualizarDatos(fechaActualizacion,datos);

        perfilRepositorio.save(perfilEncontrado);

        return new DatosRespuestaActualizacionPerfil(perfilEncontrado.getId(),perfilEncontrado.getNombre(),
                perfilEncontrado.getFechaActualizacion());
    }

    @Override
    public DatosRespuestaPerfil obtenerPerfil(Long id) {

        List<Perfil> perfiles = perfilRepositorio.findAll();
        validadorActualizacion.forEach(v->v.validarId(perfiles,id));

        Optional<Perfil> encontrarPerfil = perfiles.stream()
                .filter(perfil -> perfil.getId().equals(id))
                .findFirst();
        Perfil perfilEncontrado = encontrarPerfil.get();

        return new DatosRespuestaPerfil(perfilEncontrado.getId(), perfilEncontrado.getNombre(),
                perfilEncontrado.getFechaCreacion(), perfilEncontrado.getFechaActualizacion());
    }

    @Override
    public Page<DatosRespuestaPerfil> paginarPerfiles(Pageable pagina) {

        Page<Perfil> perfiles = perfilRepositorio.findByActivoTrue(pagina);
        validadorObtenerPerfiles.forEach(v->v.validarPerfiles(perfiles));

        return perfiles.map(perfil -> new DatosRespuestaPerfil(perfil.getId(),
                perfil.getNombre(), perfil.getFechaCreacion(), perfil.getFechaActualizacion()));
    }

    @Override
    public void deshabilitarPerfil(Long id) {
        List<Perfil> perfiles = perfilRepositorio.findAll();
        validadorActualizacion.forEach(v->v.validarId(perfiles,id));

        Optional<Perfil> encontrarPerfil = perfiles.stream()
                .filter(perfil -> perfil.getId().equals(id))
                .findFirst();
        Perfil perfilEncontrado = encontrarPerfil.get();
        perfilEncontrado.desactivarPerfil();
        perfilRepositorio.save(perfilEncontrado);
    }

    public String obtenerFechaActual(){

        LocalDateTime fechaCreacion = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String fechaFormateada = fechaCreacion.format(formatter);

        return fechaFormateada;

    }
}
