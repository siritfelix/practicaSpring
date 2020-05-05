package com.hibernet.hibert.service;

import java.util.List;

import com.hibernet.hibert.model.dto.MateriaDTO;
import com.hibernet.hibert.model.entity.Materia;

public interface IMateriaServ {
    public List<MateriaDTO> listar();
    public String crear(Materia materia);
    public Materia buscar(Integer id);
}