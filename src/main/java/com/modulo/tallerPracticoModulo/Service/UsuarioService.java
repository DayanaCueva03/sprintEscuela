package com.modulo.tallerPracticoModulo.Service;


import com.modulo.tallerPracticoModulo.Entidad.Usuario;
import com.modulo.tallerPracticoModulo.Repository.UsuarioRepository;
import com.modulo.tallerPracticoModulo.roles.Roles;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void saveUser(Long Id, String username, String password,String email, Roles rol) {
        if(usuarioRepository.existsByUsername(username)){
            throw new IllegalStateException("El usuario ya existe");
        }

        Usuario user;
        if(Id !=null){
            user = usuarioRepository.findById(Id).orElse(new Usuario());
        }else{
            user = new Usuario();
        }

        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        if(rol == null){
            user.setRoles(Roles.USER);
        }else{
            user.setRoles(rol);
        }

        usuarioRepository.save(user);

    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = usuarioRepository.findByUsername(username);
        if(user != null){
            return new User(user.getUsername(),user.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_"+user.getRoles().toString())));
        }else{
            throw new UsernameNotFoundException("El usuario no existe");
        }
    }


}