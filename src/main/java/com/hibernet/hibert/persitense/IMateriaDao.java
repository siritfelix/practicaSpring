package com.hibernet.hibert.persitense;

import com.hibernet.hibert.model.entity.Materia;

import org.springframework.data.repository.CrudRepository;

public interface IMateriaDao extends CrudRepository<Materia,Integer>  {

}