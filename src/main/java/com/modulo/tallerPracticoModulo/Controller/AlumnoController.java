package com.modulo.tallerPracticoModulo.Controller;

import com.modulo.tallerPracticoModulo.Entidad.Alumno;
import com.modulo.tallerPracticoModulo.Entidad.Curso;
import com.modulo.tallerPracticoModulo.Service.AlumnoService;
import com.modulo.tallerPracticoModulo.Service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/alumnos")
public class AlumnoController {

    @Autowired
    private AlumnoService alumnoService;

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public String listAlumnos(Model model) {
        model.addAttribute("alumnos", alumnoService.getAllAlumnos());
        return "alumnos/list";
    }
    @GetMapping("/new")
    public String newAlumno(Model model) {
        model.addAttribute("alumno", new Alumno());
        return "Alumnos/new";
    }
    @PostMapping("/save")
    public String saveAlumno(@ModelAttribute("alumno") Alumno alumno) {
        alumnoService.saveAlumno(alumno);
        return "redirect:/alumnos";
    }


    @GetMapping("/edit/{id}")
    public String editAlumno(@PathVariable("id") Long id, Model model) {
        model.addAttribute("alumno", alumnoService.getAlumnoById(id));
        return "alumnos/edit";
    }

    @PostMapping("/update/{id}")
    public String updateAlumno(@PathVariable("id") Long id, @ModelAttribute("alumno") Alumno alumno) {
        Alumno existingAlumno = alumnoService.getAlumnoById(id);
        existingAlumno.setNombre(alumno.getNombre());
        existingAlumno.setApellido(alumno.getApellido());
        existingAlumno.setEmail(alumno.getEmail());
        alumnoService.saveAlumno(existingAlumno);
        return "redirect:/alumnos";
    }

    @GetMapping("/delete/{id}")
    public String deleteAlumno(@PathVariable("id") Long id) {
        alumnoService.deleteAlumno(id);
        return "redirect:/alumnos";
    }

    @GetMapping("/{id}/asignar-cursos")
    public String asignarCursos(@PathVariable("id") Long id, Model model) {
        Alumno alumno = alumnoService.getAlumnoById(id);
        if (alumno == null) {
            return "redirect:/alumnos?error=notfound"; // Redirige si el alumno no existe
        }
        List<Curso> cursosDisponibles = cursoService.getAllCursos();
        model.addAttribute("alumno", alumno);
        model.addAttribute("cursosDisponibles", cursosDisponibles);
        return "Alumnos/asignar-cursos"; // Vista para asignar cursos
    }


    @PostMapping("/{id}/asignar-cursos")
    public String guardarCursosAsignados(@PathVariable("id") Long id, @RequestParam("cursos") Set<Long> cursoIds) {
        Alumno alumno = alumnoService.getAlumnoById(id);
        if (alumno == null) {
            return "redirect:/alumnos?error=notfound"; // Redirige si el alumno no existe
        }
        Set<Curso> cursos = cursoIds.stream()
                .map(cursoService::getCursoById)
                .collect(Collectors.toSet());
        alumno.setCursos(cursos);
        alumnoService.saveAlumno(alumno);
        return "redirect:/alumnos";
    }
}