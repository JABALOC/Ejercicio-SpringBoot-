package com.hibernate.InstitutoMayor.service.impl;

import com.hibernate.InstitutoMayor.dto.AlumnoDTO;
import com.hibernate.InstitutoMayor.entity.Alumno;
import com.hibernate.InstitutoMayor.exception.ResourceNotFoundException;
import com.hibernate.InstitutoMayor.repository.AlumnoRepository;
import com.hibernate.InstitutoMayor.service.AlumnoService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlumnoServiceImpl implements AlumnoService {

    private static final Logger log = LoggerFactory.getLogger(AlumnoServiceImpl.class);

    private final AlumnoRepository alumnoRepository;

    public AlumnoServiceImpl(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    // ----------------READ----------------

    @Override
    @Transactional
    public List<AlumnoDTO> findAll() {
        log.info("Obteniendo todos los alumnos");

        return alumnoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public AlumnoDTO findById(Long id) {
        log.info("Buscando alumno con id {}", id);

        Alumno alumno = alumnoRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Alumno no encontrado con id " + id));

        return toDTO(alumno);
    }

    @Override
    @Transactional
    public List<AlumnoDTO> findByName(String name) {
        log.info("Buscando alumno por nombre");

        return alumnoRepository.findByName(name)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // ----------------WRITE----------------

    @Override
    @Transactional
    public AlumnoDTO save(AlumnoDTO alumnoDTO) {
        log.info("Creando alumno '{}'", alumnoDTO.getName());

        Alumno alumno = toEntity(alumnoDTO);
        Alumno guardado = alumnoRepository.save(alumno);

        return toDTO(guardado);
    }

    @Override
    @Transactional
    public AlumnoDTO update(Long id, AlumnoDTO alumnoDTO) {
        Alumno alumno = alumnoRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Alumno no encontrado con id " + id));

        alumno.setName(alumnoDTO.getName());
        alumno.setSurname(alumnoDTO.getSurname());
        alumno.setAge(alumnoDTO.getAge());
        alumno.setEmail(alumnoDTO.getEmail());

        return toDTO(alumnoRepository.save(alumno));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (!alumnoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Alumno no encontrado con id " + id);
        }
        alumnoRepository.deleteById(id);
    }

    // ----------------MAPPERS----------------

    private AlumnoDTO toDTO(Alumno alumno) {
        return new AlumnoDTO(
                alumno.getId(),
                alumno.getName(),
                alumno.getSurname(),
                alumno.getAge(),
                alumno.getEmail()
        );
    }

    private Alumno toEntity(AlumnoDTO dto) {
        Alumno alumno = new Alumno();
        alumno.setId(dto.getId());
        alumno.setName(dto.getName());
        alumno.setSurname(dto.getSurname());
        alumno.setAge(dto.getAge());
        alumno.setEmail(dto.getEmail());
        return alumno;
    }

}
