package com.hibernet.hibert.service;

import java.util.ArrayList;
import java.util.List;

import com.hibernet.hibert.conversion.EstudianteConv;
import com.hibernet.hibert.model.dto.EstudianteDTO;
import com.hibernet.hibert.model.dto.InscripcionDTO;
import com.hibernet.hibert.model.entity.Estudiante;
import com.hibernet.hibert.persitense.IEstudianteDao;
import com.hibernet.hibert.persitense.IInscripcionDao;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hibernet.hibert.model.entity.Inscripcion;

@Service
public class EstudianteServImpl implements IEstudianteServ {
        @Autowired
        private IEstudianteDao estudianteDao;
        @Autowired
        private IInscripcionDao incripcionDao;

        @Override
        @Transactional(readOnly = true)
        public List<EstudianteDTO> listar() {

                List<Estudiante> estudiante = (List<Estudiante>) estudianteDao.findAll();
                List<EstudianteDTO> estudianteDTOList = new ArrayList<EstudianteDTO>();
                estudiante.forEach(p -> {
                        EstudianteDTO estudianteDTO = new EstudianteDTO();
                        BeanUtils.copyProperties(p, estudianteDTO);
                        List<InscripcionDTO> inscripcionDTOList = new ArrayList<InscripcionDTO>();
                        List<Inscripcion> inscripcionList = p.getInscripcionList();
                        inscripcionList.forEach(q -> {
                                InscripcionDTO inscripcionDTO = new InscripcionDTO();
                                BeanUtils.copyProperties(q, inscripcionDTO);
                                inscripcionDTOList.add(inscripcionDTO);
                        });
                        estudianteDTO.setInscripcionList(inscripcionDTOList);
                        estudianteDTOList.add(estudianteDTO);
                });

                return estudianteDTOList;
        }

        @Override
        @Transactional(readOnly = true)
        public EstudianteDTO BuscarPorId(Integer id) {
                try {
                        EstudianteDTO estudianteDTO = new EstudianteDTO();
                        BeanUtils.copyProperties(estudianteDao.findById(id).orElse(null), estudianteDTO);
                        return estudianteDTO;

                } catch (Exception e) {
                        return null;
                }
        }

        @Override
        @Transactional()
        public String Guardar(EstudianteDTO estudianteDTO, String operacion) {
                if (operacion.equals("guardar")) {
                        if (estudianteDTO.getId() == null) {
                                Estudiante estudiantedao = new Estudiante();
                                BeanUtils.copyProperties(estudianteDTO, estudiantedao);
                                estudianteDao.save(estudiantedao);
                                return "guardado";
                        } else {
                                return "id error";
                        }

                } else if (operacion.equals("actualizar")) {
                        if (estudianteDTO.getId() != null) {
                                if (BuscarPorId(estudianteDTO.getId()) != null) {
                                        Estudiante estudiantedao = new Estudiante();
                                        BeanUtils.copyProperties(estudianteDTO, estudiantedao);
                                        estudianteDao.save(estudiantedao);
                                        return "actualizado";
                                } else {
                                        return "inexistente";
                                }
                        } else
                                return "id error";
                } else
                        return "error";
        }

        @Override
        @Transactional
        public Boolean Borrar(Integer id) {
                Estudiante estudiante = estudianteDao.findById(id).orElse(null);
                if (estudiante != null) {
                        List<Inscripcion> InscripcionList = estudiante.getInscripcionList();
                        InscripcionList.forEach(p -> {
                                p.setEstudiante(null);
                        });
                        estudiante.setInscripcionList(InscripcionList);
                        estudianteDao.delete(estudiante);
                        return true;
                } else
                        return false;

        }

        @Override
        @Transactional
        public String cambiar(Integer id_estudiante, Integer id_inscripcion) {
                try {

                        Inscripcion inscripcion = incripcionDao.findById(id_inscripcion).orElse(null);
                        Estudiante estudiante = estudianteDao.findById(id_estudiante).orElse(null);
                        if (estudiante == null)
                                return "estudiante";
                        inscripcion.setEstudiante(estudiante);
                        return "cambiado";

                } catch (Exception e) {
                        System.out.println("error....");
                        return "estudiante";

                }
        }
}