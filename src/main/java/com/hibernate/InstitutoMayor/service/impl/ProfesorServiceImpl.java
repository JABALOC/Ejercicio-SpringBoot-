package com.hibernate.InstitutoMayor.service.impl;

import com.hibernate.InstitutoMayor.dto.ProfesorDTO;
import com.hibernate.InstitutoMayor.entity.Profesor;
import com.hibernate.InstitutoMayor.exception.ResourceNotFoundException;
import com.hibernate.InstitutoMayor.repository.ProfesorRepository;
import com.hibernate.InstitutoMayor.service.ProfesorService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfesorServiceImpl implements ProfesorService {

    private static final Logger log = LoggerFactory.getLogger(ProfesorServiceImpl.class);

    private final ProfesorRepository profesorRepository;

    public ProfesorServiceImpl(ProfesorRepository profesorRepository) {
        this.profesorRepository = profesorRepository;
    }

    @Override
    @Transactional
    public List<ProfesorDTO> findAll() {
        log.info("Obteniendo todos los profesores");

        return profesorRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }


    @Override
    @Transactional
    public ProfesorDTO findById(Long id) {
        log.info("Buscando profesor por id {}", id);

        Profesor profesor = profesorRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("No se encuentra el id " + id));

        return toDTO(profesor);
    }

    @Override
    @Transactional
    public ProfesorDTO save(ProfesorDTO profesorDTO) {
        log.info("Creando nuevo profesor");

        Profesor profesor = toEntity(profesorDTO);
        Profesor guardado = profesorRepository.save(profesor);
        return toDTO(guardado);
    }

    @Override
    @Transactional
    public ProfesorDTO update(Long id, ProfesorDTO profesorDTO) {
        log.info("Buscando profesor con id {} para actualizar", + id);

        Profesor profesor = profesorRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("No se ha ncontrado profesor con id " + id));
        profesor.setName(profesorDTO.getName());
        profesor.setSurname(profesorDTO.getSurname());
        profesor.setEmail(profesor.getEmail());
        return toDTO(profesorRepository.save(profesor));


    }

    @Override
    @Transactional
    public void deleteById(Long id) {
    log.info("Profesor no encontrado");

    if (!profesorRepository.existsById(id)) {
        throw new ResourceNotFoundException("No se ha encontrado el id " + id);
    }
    profesorRepository.deleteById(id);
    }

    // Mapping

    public ProfesorDTO toDTO(Profesor profesor) {
        return new ProfesorDTO(
                profesor.getId(),
                profesor.getName(),
                profesor.getSurname(),
                profesor.getEmail()
        );
    }

    public Profesor toEntity(ProfesorDTO dto) {
        Profesor profesor = new Profesor();
        profesor.setId(dto.getId());
        profesor.setName(dto.getName());
        profesor.setSurname(dto.getSurname());
        profesor.setEmail(dto.getEmail());
        return profesor;

    }


}
