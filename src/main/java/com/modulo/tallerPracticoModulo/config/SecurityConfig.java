package com.modulo.tallerPracticoModulo.config;

import com.modulo.tallerPracticoModulo.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    @Lazy
    private UsuarioService usuarioService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http , Acceso acceso) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/alumnos/**","/cursos/**").hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/AlumnoCurso/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                        .requestMatchers("/**","/index").permitAll()
                        .anyRequest().authenticated()// Permitir acceso a todas las rutas sin autenticación
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .successHandler(acceso)
                        .permitAll()) // Desactiva el login
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")); // Desactiva el logout

        return http.build();
    }

    // Configuración del codificador de contraseñas
    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }




}