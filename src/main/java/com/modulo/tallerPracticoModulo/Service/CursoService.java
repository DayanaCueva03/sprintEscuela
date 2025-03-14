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

    public List<Curso> getAllCursos() {
        return cursoRepository.findAll();
    }

    public void saveCurso(Curso curso) {
        cursoRepository.save(curso);
    }

    public Curso getCursoById(Long id) {
        return cursoRepository.findById(id).orElse(null);
    }

    public void deleteCurso(Long id) {
        cursoRepository.deleteById(id);
    }
}