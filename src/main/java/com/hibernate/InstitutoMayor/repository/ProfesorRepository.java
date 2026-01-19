package com.hibernate.InstitutoMayor.repository;

import com.hibernate.InstitutoMayor.entity.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesorRepository  extends JpaRepository<Profesor, Long> {
}
