package com.hibernate.InstitutoMayor.service;

import com.hibernate.InstitutoMayor.dto.AlumnoDTO;

import java.util.List;

public interface AlumnoService {

    List<AlumnoDTO> findAll();

    AlumnoDTO findById(Long id);

    AlumnoDTO save(AlumnoDTO alumnoDTO);

    AlumnoDTO update(Long id, AlumnoDTO alumnoDTO);

    void deleteById(Long id);

    List<AlumnoDTO> findByName(String name);

}
