package com.hibernet.hibert.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MateriaDTO {
    private Integer id;

    private String nombre;

    private String profesor;

    private Integer horas;
    

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

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public Integer getHoras() {
        return horas;
    }

    public void setHoras(Integer horas) {
        this.horas = horas;
    }

    public MateriaDTO(Integer id, String nombre, String profesor, Integer horas) {
        this.id = id;
        this.nombre = nombre;
        this.profesor = profesor;
        this.horas = horas;
    }
    public MateriaDTO() {

    }
    

}