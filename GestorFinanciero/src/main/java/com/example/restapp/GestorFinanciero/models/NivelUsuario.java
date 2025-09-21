package com.example.restapp.GestorFinanciero.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "niveles_usuario")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class NivelUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idNivel;
    @Column(nullable = false)
    private Integer nivelActual;
    @Column(nullable = false)
    private Integer xpTotal;
    @Column(nullable = false)
    private String icono;
    @Column(nullable = false, length = 500)
    private String ventajas;
    @Column(nullable = false, length = 500)
    private String banner;

    //Un nivel puede tener varios usuarios los cuales tengan el mismo nivel
    @OneToMany(mappedBy = "nivelUsuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Usuario> usuarios = new ArrayList<>();
}
