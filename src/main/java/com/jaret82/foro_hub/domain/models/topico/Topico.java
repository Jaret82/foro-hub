package com.jaret82.foro_hub.domain.models.topico;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.jaret82.foro_hub.domain.models.categoria.Curso;
import com.jaret82.foro_hub.domain.models.respuesta.Respuesta;
import com.jaret82.foro_hub.domain.models.usuario.perfil.Perfil;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "Topico")
@Table(name = "topicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(unique = true, length = 150)
    private String titulo;

    @NotNull
    @Column(unique = true, length = 1500)
    private String mensaje;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "perfil_id")
    private Perfil perfilId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Curso curso;

    @Column(name = "fecha_creacion")
    private String fechaCreacion;

    @OneToMany(mappedBy = "topicoId", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Respuesta> respuestas;

    private Boolean activo;

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Perfil getPerfilId() { return perfilId; }

    public void setPerfilId(Perfil perfilId) { this.perfilId = perfilId; }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) { this.fechaCreacion = fechaCreacion;
    }

    public void actualizarDatos(ActualizarTopicoDto body) {

        if (body.titulo() != getTitulo()){
            setTitulo(body.titulo());
        }

        if (body.mensaje() != getMensaje()){
            setMensaje(body.mensaje());
        }
    }

    public void eliminarTopico() {
        setActivo(false);
    }
}
