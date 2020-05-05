package com.hibernet.hibert.conversion;

import com.hibernet.hibert.model.dto.MateriaDTO;
import com.hibernet.hibert.model.entity.Materia;

import org.springframework.beans.BeanUtils;

public class MateriaConv {

    public MateriaConv() {
    }
    public MateriaDTO convToDTO(Materia materia)
    {   
        MateriaDTO materiaDTO=new MateriaDTO(null,null,null,null);
        BeanUtils.copyProperties(materia, materiaDTO);
        return materiaDTO;
    }
    public Materia convToDao(MateriaDTO materia)
    {
        Materia materiadao=new Materia(null,null,null,null);
        BeanUtils.copyProperties(materia, materiadao);
        return materiadao;
    } 


}