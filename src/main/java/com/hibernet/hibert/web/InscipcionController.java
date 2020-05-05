package com.hibernet.hibert.web;

import java.util.List;
import com.hibernet.hibert.model.dto.InscripcionDTO;
import com.hibernet.hibert.service.InscripcionServImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("/inscripcion")
public class InscipcionController {

    @Autowired
    private InscripcionServImpl inscripcionServImpl;

    @GetMapping("/listar")
    public ResponseEntity<List<InscripcionDTO>> listar() {
        return new ResponseEntity<List<InscripcionDTO>>(inscripcionServImpl.listar(), HttpStatus.OK);

    }

    @PostMapping("/crear")
    public ResponseEntity<String> crear(@RequestBody InscripcionDTO inscripcionDTO) {
        if (inscripcionServImpl.crear(inscripcionDTO) == true)
            return new ResponseEntity<>("Creada", HttpStatus.OK);
        else
            return new ResponseEntity<>("No existe el estudiante", HttpStatus.OK);

    }

    @GetMapping("/buscar")
    public ResponseEntity<?> buscar(@RequestParam Integer id) {
        InscripcionDTO inscripcionDTO = inscripcionServImpl.buscar(id);
        if (inscripcionDTO != null)
            return new ResponseEntity<InscripcionDTO>(inscripcionDTO, HttpStatus.OK);
        else
            return new ResponseEntity<String>("No existe id de inscripcion!", HttpStatus.NOT_FOUND);

    }

    @GetMapping("/eliminar")
    public ResponseEntity<String> eliminar(@RequestParam Integer id) {
        if (inscripcionServImpl.buscar(id) != null) {
            inscripcionServImpl.eliminar(id);
            return new ResponseEntity<String>("Inscripcion Eliminada con exito!", HttpStatus.OK);
        } else
            return new ResponseEntity<String>("No existe id de inscripcion!", HttpStatus.NOT_FOUND);

    }

}