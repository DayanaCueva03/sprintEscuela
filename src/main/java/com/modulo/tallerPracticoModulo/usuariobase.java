package com.modulo.tallerPracticoModulo;

import com.modulo.tallerPracticoModulo.Entidad.Usuario;
import com.modulo.tallerPracticoModulo.Repository.UsuarioRepository;
import com.modulo.tallerPracticoModulo.roles.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class usuariobase implements CommandLineRunner {
    @Autowired
    private UsuarioRepository usuariosRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Iniciando el DataLoader...");

        // Verificar si ya existe un usuario administrador
        if (usuariosRepository.count() == 0) {
            // Si no existe, crear el usuario administrador
            Usuario administrador = new Usuario();
            administrador.setUsername("gestor");
            administrador.setEmail("gestor@example.com");
            administrador.setPassword(passwordEncoder.encode("1234"));
            administrador.setRoles(Roles.ADMIN);
            usuariosRepository.save(administrador);

            // Imprimir un mensaje indicando que se creó el usuario
            System.out.println("Usuario administrador creado con éxito su username es: " + administrador.getUsername() + " y su password es: admin123.");
        } else {
            System.out.println("Ya existen usuarios en la base de datos.");
        }
    }
}
