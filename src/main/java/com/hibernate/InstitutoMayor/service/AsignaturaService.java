package com.hibernate.InstitutoMayor.service;

import com.hibernate.InstitutoMayor.dto.AsignaturaDTO;

import java.util.List;

public interface AsignaturaService {

    List<AsignaturaDTO> findAll();

    AsignaturaDTO findById(Long id);

    AsignaturaDTO save(AsignaturaDTO asignaturaDTO);

    AsignaturaDTO update(Long id, AsignaturaDTO asignaturaDTO);

    void deleteById(Long id);
}
