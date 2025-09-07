package com.example.restapp.GestorFinanciero.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idRol;
    @Column(nullable = false)
    private String descripcion;

    @OneToMany(mappedBy = "usuarioRol", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Usuario> usuarioRol = new HashSet<>();
}
