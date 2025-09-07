package com.example.restapp.GestorFinanciero.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "nivelesUsuario")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class NivelUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idNivel;
    @Column(nullable = false)
    private String nivelActual;
    @Column(nullable = false)
    private Integer xpTotal;

    //Un nivel puede tener varios usuarios los cuales tengan el mismo nivel
    @OneToMany(mappedBy = "nivelUsuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Usuario> usuarios = new ArrayList<>();
}
