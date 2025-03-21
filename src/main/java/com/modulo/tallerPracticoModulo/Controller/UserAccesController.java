package com.modulo.tallerPracticoModulo.Controller;

import com.modulo.tallerPracticoModulo.Entidad.AlumnoCursoDTO;
import com.modulo.tallerPracticoModulo.Service.AlumnoCursoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/AlumnoCurso")
public class UserAccesController {
    @Autowired
    private AlumnoCursoServices alumnoCursoServices;

    @GetMapping
    public String AlumnoCurso(Model model) {
        List<AlumnoCursoDTO> AlumnoCurso = alumnoCursoServices.obtenerAlumnosConCursos();
        model.addAttribute("AlumnoCurso", AlumnoCurso);
        return "Alumnos/alumnoCurso";
    }


}
