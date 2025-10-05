package com.example.restapp.GestorFinanciero.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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
    @Column(nullable = false, length = 50)
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

    @OneToOne(mappedBy = "meta", cascade = CascadeType.ALL, orphanRemoval = true)
    private FechaMeta fechaMeta;

    @OneToMany(mappedBy = "meta", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MetaTransaccion> metaTransaccion = new HashSet<>();

    @OneToOne(mappedBy = "meta", cascade = CascadeType.ALL, orphanRemoval = true)
    private Presupuesto presupuesto;

    @OneToOne(mappedBy = "meta", cascade = CascadeType.ALL, orphanRemoval = true)
    private TipoMeta tipoMeta;

    @OneToMany(mappedBy = "meta", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MisCategoriasMetas> misCategoriasMetas = new HashSet<>();

}
