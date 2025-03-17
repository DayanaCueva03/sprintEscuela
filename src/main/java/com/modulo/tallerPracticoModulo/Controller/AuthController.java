package com.modulo.tallerPracticoModulo.Controller;

import com.modulo.tallerPracticoModulo.Entidad.Usuario;
import com.modulo.tallerPracticoModulo.Service.UsuarioService;
import com.modulo.tallerPracticoModulo.roles.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;


    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("login", new Usuario());
        return "Auth/login";
    }


    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("register", new Usuario());
        return "Auth/register";
    }

    @PostMapping("/register/save")
    public String registro(@RequestParam(required = false)Long userId,
                           @RequestParam String username,
                           @RequestParam String email,
                           @RequestParam String password,
                           @RequestParam String confirmPassword,
                           @RequestParam(required = false) Roles rol,
                           Model model) throws Exception {

        if(!password.equals(confirmPassword)){
            model.addAttribute("error", "Las contraseñas no coinciden");
            model.addAttribute("register", new Usuario()); // Vuelve a cargar el formulario
            return "auth/register";
        }

        usuarioService.saveUser(userId,username,password,email,rol);

        return "redirect:/login";
    }






}