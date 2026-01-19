package com.hibernate.InstitutoMayor.service;

import com.hibernate.InstitutoMayor.dto.ProfesorDTO;
import com.hibernate.InstitutoMayor.entity.Profesor;

import java.util.List;

public interface ProfesorService {

    List<ProfesorDTO> findAll();

    ProfesorDTO findById(Long id);

    ProfesorDTO save(ProfesorDTO profesorDTO);

    ProfesorDTO update(Long id, ProfesorDTO profesorDTO);

    void deleteById(Long id);
}
