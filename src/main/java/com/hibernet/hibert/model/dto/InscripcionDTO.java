package com.hibernet.hibert.model.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import org.springframework.format.annotation.DateTimeFormat;
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "fecha", "id_estudiante", "lista_materias" })
public class InscripcionDTO {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("id_estudiante")
    private Integer id_estudiante;

    private List<Integer> id_materias;

    @JsonProperty("lista_materias")    
    private List<MateriaDTO> materiaList;

    @JsonProperty("fecha")
    @DateTimeFormat(pattern = "dd-MM-yyyy:HH:mm:ss")
    @JsonFormat(pattern = "dd-MM-yyyy/HH:mm:ss")
    private Date fecha;
    
    public Integer getId_estudiante() {
        return id_estudiante;
    }

    public void setId_estudiante(Integer id_estudiante) {
        this.id_estudiante = id_estudiante;
    }

    public List<Integer> getId_materias() {
        return id_materias;
    }

    public void setId_materias(List<Integer> id_materias) {
        this.id_materias = id_materias;
    }

    public InscripcionDTO(Integer id_estudiante, List<Integer> id_materias) {
        this.id_estudiante = id_estudiante;
        this.id_materias = id_materias;
    }

    public InscripcionDTO() {

    }

    public List<MateriaDTO> getMateriaList() {
        return materiaList;
    }

    public void setMateriaList(List<MateriaDTO> materiaList) {
        this.materiaList = materiaList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    @Override
    public String toString() {
        return "Id: " + this.id + " Fecha: " +this. fecha + " Materias:" +this.materiaList.get(0) + " Estudiante: "
                + this.id_estudiante;
    }

}