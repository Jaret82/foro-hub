package com.jaret82.foro_hub.domain.repository;

import com.jaret82.foro_hub.domain.models.topico.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicoRepositorio extends JpaRepository<Topico, Long> {
    boolean existsByTitulo(String titulo);

    boolean existsByMensaje(String mensaje);

    Page<Topico> findByActivoTrue(Pageable pageable);
}
