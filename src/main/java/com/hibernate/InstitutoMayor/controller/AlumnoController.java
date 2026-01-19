package com.hibernate.InstitutoMayor.controller;

import com.hibernate.InstitutoMayor.dto.AlumnoDTO;
import com.hibernate.InstitutoMayor.entity.Alumno;
import com.hibernate.InstitutoMayor.service.AlumnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/alumnos")
public class AlumnoController {

    private final AlumnoService alumnoService;

    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    @GetMapping("/all")
    public List<AlumnoDTO> getAlumnos() {
        return alumnoService.findAll();
    }

    @GetMapping("/alumno/{id}")
    public ResponseEntity<AlumnoDTO> getAlumno(@PathVariable Long id) {
        return ResponseEntity.ok(alumnoService.findById(id));
    }

    @GetMapping("/nombres/{name}")
    public List<AlumnoDTO> getNames(@PathVariable String name) {
        return alumnoService.findByName(name);
    }

    @PostMapping("/create")
    public ResponseEntity<AlumnoDTO> createAlumno(@RequestBody AlumnoDTO alumnoDTO) {
        AlumnoDTO creado = alumnoService.save(alumnoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AlumnoDTO> updateAlumno(@PathVariable Long id, @RequestBody AlumnoDTO alumnoDTO) {
        return ResponseEntity.ok(alumnoService.update(id, alumnoDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAlumno(@PathVariable Long id) {
        alumnoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
