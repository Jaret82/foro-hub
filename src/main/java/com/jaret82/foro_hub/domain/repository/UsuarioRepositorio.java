package com.jaret82.foro_hub.domain.repository;

import com.jaret82.foro_hub.domain.models.usuario.users.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

    UserDetails findByLogin(String subject);
    boolean existsByLogin(String login);

}
