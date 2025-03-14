package com.modulo.tallerPracticoModulo.Controller;

import com.modulo.tallerPracticoModulo.Entidad.Curso;
import com.modulo.tallerPracticoModulo.Service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public String listCursos(Model model) {
        model.addAttribute("cursos", cursoService.getAllCursos());
        return "cursos/listcurso";
    }

    @GetMapping("/newcurso")
    public String newCurso(Model model) {
        model.addAttribute("curso", new Curso());
        return "cursos/newcurso";
    }

    @PostMapping("/save")
    public String saveCurso(@ModelAttribute("curso") Curso curso) {
        cursoService.saveCurso(curso);
        return "redirect:/cursos";
    }

    @GetMapping("/edit/{id}")
    public String editCurso(@PathVariable("id") Long id, Model model) {
        Curso curso = cursoService.getCursoById(id);
        if (curso == null) {

            return "redirect:/cursos?error=notfound";
        }
        model.addAttribute("curso", curso);
        return "cursos/editcurso";
    }

    @PostMapping("/update/{id}")
    public String updateCurso(@PathVariable("id") Long id, @ModelAttribute("curso") Curso curso) {
        Curso existingCurso = cursoService.getCursoById(id);
        if (existingCurso != null) {
            existingCurso.setNombre(curso.getNombre());
            existingCurso.setDescripcion(curso.getDescripcion());
            cursoService.saveCurso(existingCurso);
        }
        return "redirect:/cursos";
    }


    @GetMapping("/delete/{id}")
    public String deleteCurso(@PathVariable("id") Long id) {
        cursoService.deleteCurso(id);
        return "redirect:/cursos";
    }
}