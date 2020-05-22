package com.hibernet.hibert.web;

import java.util.List;

import com.hibernet.hibert.model.dto.MateriaDTO;
import com.hibernet.hibert.model.entity.Materia;
import com.hibernet.hibert.service.MateriaSerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/materias")
public class MateriaController {
    @Autowired
    private MateriaSerImpl materiaSerImpl;

    @GetMapping("/listar")
    public ResponseEntity<List<MateriaDTO>> listar() {
        return new ResponseEntity<List<MateriaDTO>>(materiaSerImpl.listar(), HttpStatus.OK);

    }

    @PostMapping("/crear")
    public ResponseEntity<?> crear(@RequestBody Materia materia) {
        if (materiaSerImpl.crear(materia).equals("creada"))
            return new ResponseEntity<>("Materia: creada", HttpStatus.OK);
        else
            return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
    }

}