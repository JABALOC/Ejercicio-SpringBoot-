package com.hibernate.InstitutoMayor.service.impl;

import com.hibernate.InstitutoMayor.dto.AlumnoDTO;
import com.hibernate.InstitutoMayor.dto.AsignaturaDTO;
import com.hibernate.InstitutoMayor.dto.ProfesorDTO;
import com.hibernate.InstitutoMayor.entity.Alumno;
import com.hibernate.InstitutoMayor.entity.Asignatura;
import com.hibernate.InstitutoMayor.exception.ResourceNotFoundException;
import com.hibernate.InstitutoMayor.repository.AsignaturaRepository;
import com.hibernate.InstitutoMayor.service.AsignaturaService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AsignaturaServiceImpl implements AsignaturaService {

    private static final Logger log = LoggerFactory.getLogger(AsignaturaServiceImpl.class);

    private final AsignaturaRepository asignaturaRepository;

    public AsignaturaServiceImpl(AsignaturaRepository asignaturaRepository) {
        this.asignaturaRepository = asignaturaRepository;
    }

    @Override
    public List<AsignaturaDTO> findAll() {
        log.info("Listado de todas las asignaturas");
        return asignaturaRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());

    }

    public AsignaturaDTO findById(Long id) {
        log.info("Buscando asignatura con id: {}", id);
        Asignatura asignatura = asignaturaRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Alumno no encontrado con id: " + id));
        return toDto(asignatura);
    }

    @Override
    @Transactional
    public AsignaturaDTO save(AsignaturaDTO asignaturaDTO) {
        log.info("Creada asignatura con nombre '{}'", asignaturaDTO.getName());
        Asignatura asignatura = toEntity(asignaturaDTO);
        Asignatura guardar = asignaturaRepository.save(asignatura);
        return toDto(guardar);
    }

    @Override
    @Transactional
    public AsignaturaDTO update(Long id, AsignaturaDTO asignaturaDTO) {
        log.info("Actualizar asignatura con id: {}", id);
        Asignatura asignatura = asignaturaRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Asignatura no encontrada con id:" + id));

        asignatura.setName(asignaturaDTO.getName());
        asignatura.setDurationHours(asignaturaDTO.getDurationHours());

        return toDto(asignaturaRepository.save(asignatura));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        log.info("Eliminando asignatura con id {}", id);

        if (!asignaturaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Asignatura no encontrada con id: " + id);
        }
        asignaturaRepository.deleteById(id);
    }

    // Mapping

    public AsignaturaDTO toDto(Asignatura asignatura) {
        return new AsignaturaDTO(
                asignatura.getId(),
                asignatura.getName(),
                asignatura.getDurationHours()
        );
    }

    public Asignatura toEntity(AsignaturaDTO dto) {
        Asignatura asignatura = new Asignatura();
        asignatura.setId(dto.getId());
        asignatura.setName(dto.getName());
        asignatura.setDurationHours(dto.getDurationHours());
        return asignatura;
    }

}
