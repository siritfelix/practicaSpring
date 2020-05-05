package com.hibernet.hibert.conversion;

import com.hibernet.hibert.model.dto.EstudianteDTO;
import com.hibernet.hibert.model.entity.Estudiante;

public class EstudianteConv {

    public EstudianteConv() {
    }

    public EstudianteDTO convToDTO(Estudiante estudiante) {
        EstudianteDTO estudianteDTO = new EstudianteDTO(estudiante.getId(), estudiante.getNombre(),
                estudiante.getApellido(), estudiante.getEmail());
        return estudianteDTO;
    }

    public Estudiante convToDao(EstudianteDTO estudiante) {
        Estudiante estudiantedao = new Estudiante(estudiante.getId(), estudiante.getNombre(), estudiante.getApellido(),
                estudiante.getEmail());
        return estudiantedao;
    }

}