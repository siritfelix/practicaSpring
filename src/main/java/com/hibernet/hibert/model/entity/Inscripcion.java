package com.hibernet.hibert.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.persistence.JoinColumn;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "inscripcion")
public class Inscripcion implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd/HH:mm:ss")
    private Date fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estudiantes_idddd")
    @JsonBackReference
    private Estudiante estudiante;

    @JoinTable(name = "inscripcion_has_materias", joinColumns = @JoinColumn(name = "inscripcion_id", nullable = false), inverseJoinColumns = @JoinColumn(name = "materias_id", nullable = false))
    @ManyToMany(fetch = FetchType.LAZY)
    // @JsonBackReference
    private List<Materia> materiaList;

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

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Inscripcion(Integer id, @NotNull Date fecha, Estudiante estudiante, List<Materia> materias) {
        this.id = id;
        this.fecha = fecha;
        this.estudiante = estudiante;
        this.materiaList = materias;

    }

    public Inscripcion() {
        materiaList = new ArrayList<Materia>();
    }

    public void addMateria(Materia materia) {
        materiaList.add(materia);
    }

    public List<Materia> getMateriaList() {
        return materiaList;
    }

    public void setMateriaList(List<Materia> materiaList) {
        this.materiaList = materiaList;
    }

    @Override
    public String toString() {
        return "Id: " +this.id + " Fecha: " + this.fecha + " Materias:" + this.materiaList.get(0) + " Estudiante: "
                + this.estudiante.getId();
    }

}