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
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().permitAll() // Permitir acceso a todas las rutas sin autenticación
                )
                .csrf(csrf -> csrf.disable()) // Desactiva CSRF temporalmente si es necesario
                .formLogin(form -> form.disable()) // Desactiva el login
                .logout(logout -> logout.disable()); // Desactiva el logout

        return http.build();
    }

    // Configuración del codificador de contraseñas
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(usuarioService); // Usa el servicio de usuario para cargar detalles del usuario
        provider.setPasswordEncoder(passwordEncoder()); // Usa el codificador de contraseñas
        return provider;
    }
}