package com.example.restapp.GestorFinanciero.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "metas")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Meta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMeta;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private Float montoActual;
    @Column(nullable = false)
    private Float montoObjetivo;
    @Column(nullable = false)
    private LocalDate fechaInicial;
    @Column(nullable = false)
    private LocalDate fechaFinal;
    @Column(nullable = false)
    //Puede considerarse otra tabla
    private String Estado;

    @ManyToOne
    @JoinColumn(name = "categoriaMeta_id", nullable = false)
    private CategoriaMeta categoriaMetas;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuarioMetas;

}
