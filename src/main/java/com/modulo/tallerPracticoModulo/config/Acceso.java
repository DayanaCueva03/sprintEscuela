package com.modulo.tallerPracticoModulo.config;

import com.modulo.tallerPracticoModulo.Entidad.Usuario;
import com.modulo.tallerPracticoModulo.Repository.UsuarioRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class Acceso implements AuthenticationSuccessHandler {


    @Autowired
    private UsuarioRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        String username = authentication.getName();
        Usuario user = userRepository.findByUsername(username);

        if(user != null){
            String rol = user.getRoles().toString();

            if("ADMIN".equals(rol)){
                response.sendRedirect("/alumnos");
            }else if("USER".equals(rol)){
                response.sendRedirect("/AlumnoCurso");
            }else{
                response.sendRedirect("/login?error=true");
            }

        }else {
            response.sendRedirect("/login?error=true");
        }


    }
}
