package com.jaret82.foro_hub.domain.models.usuario.perfil;

import com.jaret82.foro_hub.domain.models.respuesta.Respuesta;
import com.jaret82.foro_hub.domain.models.topico.Topico;
import com.jaret82.foro_hub.domain.models.usuario.users.Usuario;
import com.jaret82.foro_hub.handler.CustomNotFoundException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "Perfil")
@Table(name = "perfiles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nombre;

    @NotNull
    private String fechaCreacion;

    @OneToMany(mappedBy = "perfilId", fetch = FetchType.LAZY)
    private List<Topico> topicos;

    @OneToMany(mappedBy = "perfilId", fetch = FetchType.LAZY)
    private List<Respuesta> respuestas;

    private Boolean activo;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuarioId;

    private String fechaActualizacion;

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(String fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Usuario getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Usuario usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void desactivarPerfil(){
        setActivo(false);
    }

    public void actualizarDatos(String fechaActualizacion,DatosActualizarPerfil datos) {
        if (datos.nombre() == null || datos.nombre().equals("")){
            throw new CustomNotFoundException("El campo de Nombre se encuentra vacio");
        }
        if (datos.nombre() != getNombre()){
            setNombre(datos.nombre());
            setFechaActualizacion(fechaActualizacion);
        }
    }
}
