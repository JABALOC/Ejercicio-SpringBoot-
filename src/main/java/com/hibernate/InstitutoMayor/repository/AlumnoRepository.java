package com.hibernate.InstitutoMayor.repository;

import com.hibernate.InstitutoMayor.entity.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlumnoRepository  extends JpaRepository<Alumno, Long> {

    List<Alumno> findByName(String name);
}
