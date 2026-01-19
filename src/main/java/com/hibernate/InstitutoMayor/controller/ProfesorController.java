package com.hibernate.InstitutoMayor.controller;

import com.hibernate.InstitutoMayor.dto.ProfesorDTO;
import com.hibernate.InstitutoMayor.service.ProfesorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profesores")
public class ProfesorController {

    private final ProfesorService profesorService;

    public ProfesorController(ProfesorService profesorService) {
        this.profesorService = profesorService;
    }

    @GetMapping("/all")
    public List<ProfesorDTO> findAll() {
        return profesorService.findAll();
    }

    @GetMapping("/profesor/{id}")
    public ResponseEntity<ProfesorDTO> findById(@PathVariable Long id) {
        ProfesorDTO profesor = profesorService.findById(id);
        return ResponseEntity.ok(profesor);
    }

    @PostMapping("/create")
    public ProfesorDTO create(@RequestBody ProfesorDTO profesorDTO) {
        return profesorService.save(profesorDTO);
    }

    @PutMapping("/update/{id}")
    public ProfesorDTO update(@PathVariable Long id, @RequestBody ProfesorDTO profesorDTO) {
        ProfesorDTO guardar = profesorService.update(id, profesorDTO);
        return profesorService.save(guardar);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        profesorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
