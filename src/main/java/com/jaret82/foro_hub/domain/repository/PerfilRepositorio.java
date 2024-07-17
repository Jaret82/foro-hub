package com.jaret82.foro_hub.domain.repository;

import com.jaret82.foro_hub.domain.models.usuario.perfil.Perfil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilRepositorio extends JpaRepository<Perfil, Long> {
    Perfil findByNombre(String nombre);
    Page<Perfil> findByActivoTrue(Pageable pagina);
}
