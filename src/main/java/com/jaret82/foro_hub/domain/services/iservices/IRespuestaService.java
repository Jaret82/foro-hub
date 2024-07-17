package com.jaret82.foro_hub.domain.services.iservices;

import com.jaret82.foro_hub.domain.models.respuesta.DatoRegistroRespuesta;
import com.jaret82.foro_hub.domain.models.respuesta.DatosRespuesta;
import com.jaret82.foro_hub.domain.models.respuesta.DatosRespuestaPorId;
import com.jaret82.foro_hub.domain.models.respuesta.DatosRespuestaRegistro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IRespuestaService {
    Page<DatosRespuesta> obtenerRespuestas(Pageable pagina);

    DatosRespuestaRegistro registrarRespuesta(DatoRegistroRespuesta datos);

    DatosRespuestaPorId obtenerRespuestaPorId(Long id);

    void eliminarRespuesta(Long id);
}
