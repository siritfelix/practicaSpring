package com.hibernet.hibert.service;

import java.util.ArrayList;
import java.util.List;

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
        private static final String GUARDAR = "guardar";
        private static final String GUARDADO = "guardado";
        private static final String MODIFICADO = "modificado";
        private static final String MODIFICAR = "modificar";
        private static final String CAMBIADO = "cambiado";
        private static final String INEXISTENTE = "inexistente";
        private static final String NO_EST = "No se encontro el estudiante";
        private static final String NO_INCR = "No se encontro la incripcion";
        private static final String ERROR_OPR = "Ha ocurrido un error en la operaion";
        @Autowired
        private IEstudianteDao EstudianteDao;
        @Autowired
        private IInscripcionDao IncripcionDao;

        @Override
        @Transactional(readOnly = true)
        public List<EstudianteDTO> listarEst() {

                List<Estudiante> estudiante = (List<Estudiante>) EstudianteDao.findAll();
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
                        BeanUtils.copyProperties(EstudianteDao.findById(id).orElse(null), estudianteDTO);
                        return estudianteDTO;

                } catch (Exception e) {
                        return null;
                }
        }

        @Override
        @Transactional()
        public String guardarEst(EstudianteDTO estudianteDTO, String operacion) {
                if (GUARDAR.equals(operacion)) {
                        Estudiante estudiantedao = new Estudiante();
                        BeanUtils.copyProperties(estudianteDTO, estudiantedao);
                        try {
                                EstudianteDao.save(estudiantedao);
                                return GUARDADO;
                        } catch (Exception e) {
                                return ERROR_OPR;
                        }
                } else if (MODIFICAR.equals(operacion)) {
                        if (BuscarPorId(estudianteDTO.getId()) != null) {
                                Estudiante estudiantedao = new Estudiante();
                                BeanUtils.copyProperties(estudianteDTO, estudiantedao);
                                try {
                                        EstudianteDao.save(estudiantedao);
                                        return MODIFICADO;
                                } catch (Exception e) {
                                        return ERROR_OPR;
                                }
                        } else {
                                return INEXISTENTE;
                        }

                } else
                        return ERROR_OPR;
                
        }

        @Override
        @Transactional
        public Boolean borrarEst(Integer id) {
                Estudiante estudiante = EstudianteDao.findById(id).orElse(null);
                if (estudiante != null) {
                        List<Inscripcion> InscripcionList = estudiante.getInscripcionList();
                        InscripcionList.forEach(p -> {
                                p.setEstudiante(null);
                        });
                        estudiante.setInscripcionList(InscripcionList);
                        EstudianteDao.delete(estudiante);
                        return true;
                } else
                        return false;

        }

        @Override
        @Transactional
        public String modificarEst(Integer idEstudiante, Integer idInscripcion) {
                try {
                        Inscripcion inscripcion = IncripcionDao.findById(idInscripcion).orElse(null);
                        Estudiante estudiante = EstudianteDao.findById(idEstudiante).orElse(null);
                        if (estudiante == null)
                                return NO_EST;
                        if(inscripcion==null)
                                return NO_INCR;
                        inscripcion.setEstudiante(estudiante);
                        return CAMBIADO;

                } catch (Exception e) {
                        return ERROR_OPR;

                }
        }
}