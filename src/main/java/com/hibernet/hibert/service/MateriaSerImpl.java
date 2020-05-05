package com.hibernet.hibert.service;

import java.util.ArrayList;
import java.util.List;

import com.hibernet.hibert.conversion.MateriaConv;
import com.hibernet.hibert.model.dto.MateriaDTO;
import com.hibernet.hibert.model.entity.Materia;
import com.hibernet.hibert.persitense.IMateriaDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MateriaSerImpl implements IMateriaServ {
    @Autowired
    private IMateriaDao materiaDao;
    private MateriaConv conv = new MateriaConv();

    @Override
    @Transactional(readOnly = true)
    public List<MateriaDTO> listar() {
        List<Materia> materias = (List<Materia>) materiaDao.findAll();
        List<MateriaDTO> materiaDTO = new ArrayList<>();
        materias.forEach(p -> materiaDTO.add(conv.convToDTO(p)));
        return materiaDTO;

    }

    @Override
    @Transactional
    public String crear(Materia materia) {
        try {
            materiaDao.save(materia);
            return "creada";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }

    }

    @Override
    public Materia buscar(Integer id) {       
        return  materiaDao.findById(id).orElse(null);
    }

}