package com.modulo.tallerPracticoModulo.Controller;

import com.modulo.tallerPracticoModulo.Entidad.Usuario;
import com.modulo.tallerPracticoModulo.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "Auth/register";
    }

    @PostMapping("/register/save")
    public String saveUser(@ModelAttribute("usuario") Usuario usuario) {
        usuarioService.saveUsuario(usuario);
        return "redirect:/login?success=registered";
    }


    @GetMapping("/login")
    public String login() {
        return "Auth/login";
    }
}