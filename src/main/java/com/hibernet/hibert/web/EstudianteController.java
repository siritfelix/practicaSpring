package com.hibernet.hibert.web;

import java.util.List;
import com.hibernet.hibert.model.dto.EstudianteDTO;
import com.hibernet.hibert.service.IEstudianteServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("/estudiantes")
@CrossOrigin("*")
public class EstudianteController {

    private static final String GUARDAR = "guardar";  
    private static final String GUARDADO = "guardado";
    private static final String MODIFICADO = "modificado"; 
    private static final String MODIFICAR = "modificar"; 
    private static final String INEXISTENTE = "inexistente";
    private static final String CAMBIADO = "cambiado";
    private static final String NO_EST = "No se encontro el estudiante";
    private static final String ERROR_OPR = "Ha ocurrido un error en la operaion";
    private static final String ING_ID = "Debe ingresar el ID";
    private static final String SE_MODIFICO = "Se ha modificado correctamente";
    private static final String EST_BORRADO = "Se borro el estudiante exitosamente";
    private static final String NO_INCR = "No se encontro la incripcion";
    

    @Autowired
    private IEstudianteServ estudianteServ;

    @GetMapping("/listar")
    public ResponseEntity<List<EstudianteDTO>> listar() {

        return new ResponseEntity<List<EstudianteDTO>>(estudianteServ.listarEst(),HttpStatus.OK);

    }
    @GetMapping("/buscar")
    public ResponseEntity<?> buscarEstu(@RequestParam Integer id) {

        EstudianteDTO estudianteDTO = estudianteServ.BuscarPorId(id);

        if (estudianteDTO != null)
            return new ResponseEntity<EstudianteDTO>(estudianteServ.BuscarPorId(id), HttpStatus.OK);
        else
            return new ResponseEntity<>(NO_EST, HttpStatus.NOT_FOUND);

    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardar(@RequestBody EstudianteDTO estudianteDTO) {

        if(estudianteDTO.getId()!=null)
            estudianteDTO.setId(null);

        String result = estudianteServ.guardarEst(estudianteDTO, GUARDAR);

        if (GUARDADO.equals(result))
            return new ResponseEntity<EstudianteDTO>(estudianteDTO, HttpStatus.OK);
        else
            return new ResponseEntity<>(ERROR_OPR, HttpStatus.NOT_FOUND);

    }

    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizar(@RequestBody EstudianteDTO estudianteDTO) {
        
        if(estudianteDTO.getId()==null)
            return new ResponseEntity<>(ING_ID, HttpStatus.NOT_FOUND);
        else{
            String result = estudianteServ.guardarEst(estudianteDTO, MODIFICAR);
            if (MODIFICADO.equals(result))
                return new ResponseEntity<>(SE_MODIFICO, HttpStatus.OK);
            else if(INEXISTENTE.equals(result))
                return new ResponseEntity<>(NO_EST, HttpStatus.NOT_FOUND);
            else
                return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
       
        }
    }

    @DeleteMapping("/borrar")
    public ResponseEntity<?> borrar(@RequestParam Integer id) {

        if (estudianteServ.borrarEst(id) == true)
            return new ResponseEntity<>(EST_BORRADO, HttpStatus.OK);
        else
            return new ResponseEntity<>(NO_EST, HttpStatus.NOT_FOUND);

    }

    @GetMapping("/modificar")
    public ResponseEntity<?> modificar(@RequestParam Integer idEstudiante, @RequestParam Integer idInscripcion) {

        String result = estudianteServ.modificarEst(idEstudiante, idInscripcion);
        if (CAMBIADO.equals(result))
            return new ResponseEntity<>(CAMBIADO, HttpStatus.OK);
        else if (NO_EST.equals(result))
            return new ResponseEntity<>(NO_EST, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(NO_INCR, HttpStatus.NOT_FOUND);
    }
}
