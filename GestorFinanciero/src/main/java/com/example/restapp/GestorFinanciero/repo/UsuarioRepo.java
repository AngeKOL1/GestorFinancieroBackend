package com.example.restapp.GestorFinanciero.repo;

import com.example.restapp.GestorFinanciero.models.Usuario;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.Optional;

public interface UsuarioRepo extends IGenericRepo<Usuario, Integer>{
    Optional<Usuario> findByCorreo(String correo);
}
