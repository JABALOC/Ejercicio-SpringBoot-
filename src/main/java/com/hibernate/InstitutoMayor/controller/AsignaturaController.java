package com.hibernate.InstitutoMayor.controller;

import com.hibernate.InstitutoMayor.dto.AsignaturaDTO;
import com.hibernate.InstitutoMayor.service.AsignaturaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asignaturas")
public class AsignaturaController {

    private final AsignaturaService asignaturaService;

    public AsignaturaController(AsignaturaService asignaturaService) {
        this.asignaturaService = asignaturaService;
    }

    @GetMapping("/all")
    public List<AsignaturaDTO> findAll() {
        return asignaturaService.findAll();
    }

    @GetMapping("/asignatura/{id}")
    public ResponseEntity<AsignaturaDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(asignaturaService.findById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<AsignaturaDTO> save(@RequestBody AsignaturaDTO asignaturaDTO) {
        return ResponseEntity.ok(asignaturaService.save(asignaturaDTO));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AsignaturaDTO> update(@PathVariable Long id, @RequestBody AsignaturaDTO asignaturaDTO) {
        return ResponseEntity.ok(asignaturaService.update(id, asignaturaDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        asignaturaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
