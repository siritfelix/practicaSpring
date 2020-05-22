package com.hibernet.hibert.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "estudiantes")
public class Estudiante implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
    @Column(name = "nombre")
	private String nombre;
	
    @Column(name = "apellido")
	private String apellido;
	
    @Column(name = "email")
    private String email;
	
	@OneToMany(mappedBy ="estudiante",fetch = FetchType.LAZY,cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	private List<Inscripcion> InscripcionList;

	public void addInscripcion(Inscripcion incrip)
	{	
		InscripcionList.add(incrip);
	}
	public void delInscripcion(Inscripcion incrip)
	{	
		InscripcionList.remove(incrip);
	}
    public Estudiante() {
		InscripcionList=new ArrayList<Inscripcion>();
	}

	public Estudiante(final Integer id, final String nombre, final String apellido, final String email) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		InscripcionList=new ArrayList<Inscripcion>();
	}
  
	public Integer getId() {
		return this.id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(final String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(final String apellido) {
		this.apellido = apellido;
    }
    
	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public List<Inscripcion> getInscripcionList() {
		return InscripcionList;
	}

	public void setInscripcionList(List<Inscripcion> inscripcionList) {
		InscripcionList = inscripcionList;
	}


}