package com.hibernet.hibert.web;

import java.util.ArrayList;
import java.util.List;

import com.hibernet.hibert.model.dto.EstudianteDTO;
import com.hibernet.hibert.service.EstudianteServImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
public class EstudianteController {

    @Autowired
    private EstudianteServImpl estudianteservimpl;

    @GetMapping("/listar")
    public ResponseEntity<List<EstudianteDTO>> listar() {
        return new ResponseEntity<List<EstudianteDTO>>(estudianteservimpl.listar(), HttpStatus.OK);

    }

    @GetMapping("/buscar")
    public ResponseEntity<?> buscarEstu(@RequestParam Integer id) {
        EstudianteDTO estudianteDTO = estudianteservimpl.BuscarPorId(id);

        if (estudianteDTO != null)
            return new ResponseEntity<EstudianteDTO>(estudianteservimpl.BuscarPorId(id), HttpStatus.OK);
        else
            return new ResponseEntity<>("No se encontro el estudiante", HttpStatus.NOT_FOUND);

    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardar(@RequestBody EstudianteDTO estudianteDTO) {
        String result = estudianteservimpl.Guardar(estudianteDTO, "guardar");
        if (result.equals("guardado"))
            return new ResponseEntity<EstudianteDTO>(estudianteDTO, HttpStatus.OK);
        else if (result.equals("id error"))
            return new ResponseEntity<>("Ingresar entudiante sin Id", HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>("Errror en la operacion", HttpStatus.NOT_FOUND);

    }

    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizar(@RequestBody EstudianteDTO estudianteDTO) {
        String result = estudianteservimpl.Guardar(estudianteDTO, "actualizar");
        if (result.equals("actualizado"))
            return new ResponseEntity<>("Se actualizo correctamente", HttpStatus.OK);
        else if (result.equals("inexistente"))
            return new ResponseEntity<>("No se encontro el estudiante", HttpStatus.NOT_FOUND);
        else if (result.equals("id error"))
            return new ResponseEntity<>("Debe ingresar un Id", HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @DeleteMapping("/borrar")
    public ResponseEntity<?> borrar(@RequestParam Integer id) {
        if (estudianteservimpl.Borrar(id) == true)
            return new ResponseEntity<>("Se borro exitosamente", HttpStatus.OK);
        else
            return new ResponseEntity<>("No se encontro el estudiante", HttpStatus.NOT_FOUND);

    }

    @GetMapping("/cambiar")
    public ResponseEntity<?> cambiar(@RequestParam Integer id_estudiante, @RequestParam Integer id_inscripcion) {
        String respuesta = estudianteservimpl.cambiar(id_estudiante, id_inscripcion);
        if (respuesta.equals("cambiado"))
            return new ResponseEntity<>("Se cambio", HttpStatus.OK);
        else if (respuesta.equals("estudiante"))
            return new ResponseEntity<>("No existe el estudiante!", HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>("No existe la inscripcion!", HttpStatus.NOT_FOUND);
    }
}
