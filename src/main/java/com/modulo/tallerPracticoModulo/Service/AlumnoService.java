package com.modulo.tallerPracticoModulo.Service;

import com.modulo.tallerPracticoModulo.Entidad.Alumno;
import com.modulo.tallerPracticoModulo.Repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumnoService {

    @Autowired
    private AlumnoRepository alumnoRepository;

    public List<Alumno> getAllAlumnos() {
        return alumnoRepository.findAll();
    }

    public void saveAlumno(Alumno alumno) {
        alumnoRepository.save(alumno);
    }

    public Alumno getAlumnoById(Long id) {
        return alumnoRepository.findById(id).orElse(null);
    }

    public void deleteAlumno(Long id) {
        alumnoRepository.deleteById(id);
    }
}