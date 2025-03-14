package com.modulo.tallerPracticoModulo.Repository;

import com.modulo.tallerPracticoModulo.Entidad.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
}