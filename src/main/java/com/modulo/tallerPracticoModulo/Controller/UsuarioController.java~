package com.modulo.tallerPracticoModulo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsuarioController {

    @GetMapping("/")
    public String escuela() {
        return "index";
    }

    // Ruta para la página "Sobre Nosotros"
    @GetMapping("/sobre-nosotros")
    public String sobreNosotros() {
        return "VistasPagina/AdicionalVista"; // Vista para "Sobre Nosotros"
    }

    @GetMapping("/cursosVistas")
    public String pagescursos() {
        return "VistasPagina/VistaCurso"; // Vista para "Cursos"
    }

    // Ruta para la página informativa
    @GetMapping("/informacion")
    public String informacion() {
        return "pages/vistaInformativa"; // Vista informativa
    }
}