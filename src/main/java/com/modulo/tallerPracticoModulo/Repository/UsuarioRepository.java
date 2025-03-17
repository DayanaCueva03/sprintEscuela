package com.modulo.tallerPracticoModulo.Repository;

import com.modulo.tallerPracticoModulo.Entidad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByUsername(String username);
    boolean existsByUsername(String username);

//    Optional<Usuario> findByUsername(String username);
}