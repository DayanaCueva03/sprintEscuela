package com.modulo.tallerPracticoModulo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsuarioController {

    @GetMapping("/")
    public String escuela() {
        return "index";
    }

    @GetMapping("/informacion")
    public String informacion() {
        return "pages/vistaInformativa";
    }
}