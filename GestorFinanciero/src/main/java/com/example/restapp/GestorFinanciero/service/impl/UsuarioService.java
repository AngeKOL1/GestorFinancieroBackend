package com.example.restapp.GestorFinanciero.service.impl;

import com.example.restapp.GestorFinanciero.models.*;
import com.example.restapp.GestorFinanciero.repo.*;
import com.example.restapp.GestorFinanciero.service.IUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UsuarioService extends GenericService<Usuario, Integer> implements IUsuarioService {

    private final UsuarioRepo usuarioRepo;
    private final RolRepo rolRepo;
    private final NivelUsuarioRepo nivelUsuarioRepo;
    private final TrofeoRepo trofeoRepo;

    @Override
    protected IGenericRepo<Usuario, Integer> getRepo() {
        return usuarioRepo;
    }

    @Override
    public Usuario save(Usuario usuario) {

        //  Asignar rol por defecto (ej: "USUARIO")
        Rol rolUsuario = rolRepo.findByNombre("USUARIO")
                .orElseThrow(() -> new RuntimeException("Rol USUARIO no encontrado"));

        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setUsuario(usuario);
        usuarioRol.setRol(rolUsuario);
        usuarioRol.setActivo(true);
        usuarioRol.setFechaAsignacion(LocalDate.now());
        usuario.getUsuarioRoles().add(usuarioRol);

        //  Asignar nivel inicial (primer nivel registrado)
        NivelUsuario nivelInicial = nivelUsuarioRepo.findFirstByOrderByIdNivelAsc()
                .orElseThrow(() -> new RuntimeException("Nivel inicial no encontrado"));
        usuario.setNivelUsuario(nivelInicial);

        //  Asignar trofeo inicial (primer trofeo registrado)
        Trofeos trofeoInicial = trofeoRepo.findFirstByOrderByIdTrofeoAsc()
                .orElseThrow(() -> new RuntimeException("Trofeo inicial no encontrado"));

        UsuarioTrofeo usuarioTrofeo = new UsuarioTrofeo();
        usuarioTrofeo.setUsuario(usuario);
        usuarioTrofeo.setTrofeo(trofeoInicial);
        usuarioTrofeo.setFechaObtencionTrofeo(LocalDate.now());
        usuario.getUsuarioTrofeo().add(usuarioTrofeo);

        //  Fechas de registro y última conexión
        usuario.setFechaRegistro(LocalDate.now());
        usuario.setUltConexion(LocalDate.now());

        //  Guardar usuario con todo asignado
        return usuarioRepo.save(usuario);
    }
}
