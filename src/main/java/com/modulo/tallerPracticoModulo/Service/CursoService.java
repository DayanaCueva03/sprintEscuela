package com.modulo.tallerPracticoModulo.Service;

import com.modulo.tallerPracticoModulo.Entidad.Curso;
import com.modulo.tallerPracticoModulo.Repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;

    // Método para obtener todos los cursos
    public List<Curso> getAllCursos() {
        return cursoRepository.findAll();
    }

    // Método para guardar un curso
    public void saveCurso(Curso curso) {
        cursoRepository.save(curso);
    }

    // Método para obtener un curso por ID
    public Curso getCursoById(Long id) {
        return cursoRepository.findById(id).orElse(null);
    }

    // Método para eliminar un curso por ID
    public void deleteCurso(Long id) {
        cursoRepository.deleteById(id);
    }
}