package com.jaret82.foro_hub.controller;

import com.jaret82.foro_hub.domain.models.usuario.users.AutenticacionUsuarioDto;
import com.jaret82.foro_hub.domain.models.usuario.users.DatosRegistroUsuario;
import com.jaret82.foro_hub.domain.models.usuario.users.DatosRespuestaRegistroUsuario;
import com.jaret82.foro_hub.domain.models.usuario.users.Usuario;
import com.jaret82.foro_hub.domain.services.DatosJWTToken;
import com.jaret82.foro_hub.domain.services.RegistroService;
import com.jaret82.foro_hub.domain.services.TokenService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("auth")
public class AutenticacionController {

    private final RegistroService registroService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Autowired
    public AutenticacionController(RegistroService usuarioService,AuthenticationManager authenticationManager,
                                   TokenService tokenService){
        this.registroService = usuarioService;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/registro")
    @Transactional
    public ResponseEntity<DatosRespuestaRegistroUsuario> registroUsuario(
            @RequestBody @Valid DatosRegistroUsuario datosUsuario, UriComponentsBuilder uriBuilder){
        DatosRespuestaRegistroUsuario usuarioRegistrado =  registroService.registroUsuario(datosUsuario);
        URI url = uriBuilder.path("/registro/{id}").buildAndExpand(usuarioRegistrado.id()).toUri();
        return ResponseEntity.created(url).body(usuarioRegistrado);
    }

    @PostMapping("/login")
    public ResponseEntity autenticarUsuario(@RequestBody @Valid AutenticacionUsuarioDto datos){

        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(
                datos.login(), datos.password());
        var usuarioAutenticado = authenticationManager.authenticate((authenticationToken));
        var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return  ResponseEntity.ok(new DatosJWTToken(JWTtoken));
    }

}
