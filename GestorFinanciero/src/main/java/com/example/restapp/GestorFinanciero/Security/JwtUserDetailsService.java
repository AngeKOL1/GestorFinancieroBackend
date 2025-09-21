package com.example.restapp.GestorFinanciero.Security;

import com.example.restapp.GestorFinanciero.models.Rol;
import com.example.restapp.GestorFinanciero.models.Usuario;
import com.example.restapp.GestorFinanciero.repo.UsuarioRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UsuarioRepo repo;

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Usuario user = repo.findByCorreo(correo)
                .orElseThrow(() -> new UsernameNotFoundException("Correo not found: " + correo));

        List<GrantedAuthority> authorities = new ArrayList<>();
        user.getUsuarioRoles().forEach(usuarioRol -> {
            Rol rol = usuarioRol.getRol();
            authorities.add(new SimpleGrantedAuthority("ROLE_" + rol.getNombre()));
        });

        return new org.springframework.security.core.userdetails.User(
                user.getCorreo(),
                user.getContrasena(),
                authorities
        );
    }


}

