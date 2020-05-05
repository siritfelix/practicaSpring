package com.hibernet.hibert.model.dto;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hibernet.hibert.model.entity.Inscripcion;
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class EstudianteDTO {

    private Integer id;
    private String nombre;
    private String apellido;
    private String email;
    
    private List<InscripcionDTO> InscripcionList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EstudianteDTO(Integer id, String nombre, String apellido, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
    }
    

    public List<InscripcionDTO> getInscripcionList() {
        return InscripcionList;
    }

    public void setInscripcionList(List<InscripcionDTO> inscripcionList) {
        InscripcionList = inscripcionList;
    }

    public EstudianteDTO() {
      //  super();
    }

}