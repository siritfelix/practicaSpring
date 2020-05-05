package com.hibernet.hibert.service;

import java.util.List;

import com.hibernet.hibert.model.dto.EstudianteDTO;


public interface IEstudianteServ {

    public List<EstudianteDTO> listar();

    public EstudianteDTO BuscarPorId(Integer id);

    public String Guardar(EstudianteDTO estudianteDTO,String operarion);

    public Boolean Borrar(Integer id);

    public String cambiar(Integer id_estudiante,Integer id_inscripcion);


}