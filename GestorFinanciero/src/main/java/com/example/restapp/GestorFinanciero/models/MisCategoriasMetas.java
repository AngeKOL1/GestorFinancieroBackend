package com.example.restapp.GestorFinanciero.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="miscategoriasmetas")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MisCategoriasMetas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMisCategoriasMetas;
    @Column(nullable = false, length = 40)
    private String nombre;
    @Column( length = 200)
    private String descripcion;
    @Column(nullable = false)
    //Estado de uso
    private boolean estado;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "meta_id")
    private Meta meta;
}
