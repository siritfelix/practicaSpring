package com.hibernet.hibert.service;

import java.util.List;

import com.hibernet.hibert.model.dto.InscripcionDTO;


public interface IIncripcionServ {
    public Boolean crear(InscripcionDTO inscripcion);
    public List<InscripcionDTO> listar();
    public InscripcionDTO buscar(Integer id);
    public String eliminar(Integer id);

}