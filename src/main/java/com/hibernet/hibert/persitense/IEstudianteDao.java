package com.hibernet.hibert.persitense;
import com.hibernet.hibert.model.entity.Estudiante;
import org.springframework.data.repository.CrudRepository;

public interface IEstudianteDao extends CrudRepository<Estudiante,Integer> {

}