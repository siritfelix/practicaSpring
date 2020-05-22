package com.hibernet.hibert.service;

import java.util.List;

import com.hibernet.hibert.model.dto.EstudianteDTO;


public interface IEstudianteServ {

    public List<EstudianteDTO> listarEst();

    public EstudianteDTO BuscarPorId(Integer id);

    public String guardarEst(EstudianteDTO estudianteDTO,String operarion);

    public Boolean borrarEst(Integer id);

    public String modificarEst(Integer id_estudiante,Integer id_inscripcion);


}