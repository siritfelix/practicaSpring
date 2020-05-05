package com.hibernet.hibert.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hibernet.hibert.model.dto.InscripcionDTO;
import com.hibernet.hibert.model.entity.Estudiante;
import com.hibernet.hibert.model.entity.Inscripcion;
import com.hibernet.hibert.model.entity.Materia;
import com.hibernet.hibert.persitense.IInscripcionDao;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InscripcionServImpl implements IIncripcionServ {
    @Autowired
    private IInscripcionDao incripcionDao;
    @Autowired
    private IMateriaServ materiaSerImpl;
    @Autowired
    private IEstudianteServ estudianteservimpl;

    @Override
    public Boolean crear(InscripcionDTO inscripcionDTO) {
        if (estudianteservimpl.BuscarPorId(inscripcionDTO.getId_estudiante()) != null) {

            List<Materia> materias = new ArrayList<>();
            List<Integer> materias_id = inscripcionDTO.getId_materias();
            materias_id.forEach(p -> {
                materias.add(materiaSerImpl.buscar(p));
            });
            Estudiante estudiante = new Estudiante();
            estudianteservimpl.BuscarPorId(inscripcionDTO.getId_estudiante());
            BeanUtils.copyProperties(estudianteservimpl.BuscarPorId(inscripcionDTO.getId_estudiante()), estudiante);
            Inscripcion inscripcion = new Inscripcion(null, new Date(), estudiante, materias);
            incripcionDao.save(inscripcion);
            return true;
        } else
            return false;
    }

    @Override
    public List<InscripcionDTO> listar() {
        List<Inscripcion> inscripcionList = (List<Inscripcion>) incripcionDao.findAll();
        List<InscripcionDTO> inscripcionDTOList = new ArrayList<>();
        inscripcionList.forEach(p -> {
            InscripcionDTO inscripcionDTO = new InscripcionDTO();
            BeanUtils.copyProperties(p, inscripcionDTO);
            if(p.getEstudiante()!=null)
                inscripcionDTO.setId_estudiante(p.getEstudiante().getId());
            inscripcionDTOList.add(inscripcionDTO);

        });
        return inscripcionDTOList;
    }

    @Override
    public InscripcionDTO buscar(Integer id) {
        try {
            Inscripcion inscripcion = incripcionDao.findById(id).orElse(null);
            InscripcionDTO inscripcionDTO = new InscripcionDTO();
            BeanUtils.copyProperties(inscripcion, inscripcionDTO);
            inscripcionDTO.setId_estudiante(inscripcion.getEstudiante().getId()); 
            return inscripcionDTO;
        } catch (Exception e) {
            return null;
        }
        
    }

    @Override
    public String eliminar(Integer id) {
        try {
            Inscripcion inscripcion = incripcionDao.findById(id).orElse(null);
            incripcionDao.delete(inscripcion);
            return "eliminado";
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }

    }

}