package com.example.restapp.GestorFinanciero.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private double montoActual;
    @Column(nullable = false)
    private Float montoObjetivo;
    @Column(nullable = false)
    private LocalDate fechaInicial;
    @Column(nullable = false)
    private LocalDate fechaFinal;


    //Prioridad
    @ManyToOne
    @JoinColumn(name = "categoriaMeta_id", nullable = false)
    @JsonBackReference
    private CategoriaMeta categoriaMetas;

    //Prioridad
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonBackReference
    private Usuario usuarioMetas;

    //Prioridad
    @OneToOne(mappedBy = "meta", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private FechaMeta fechaMeta;


    @OneToMany(mappedBy = "meta", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<MetaTransaccion> metaTransaccion = new HashSet<>();

    @OneToOne(mappedBy = "meta", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Presupuesto presupuesto;
    //Prioridad
    @ManyToOne
    @JoinColumn(name = "tipo_meta_id", nullable = false)
    @JsonBackReference
    private TipoMeta tipoMeta;

    //Prioridad
    @ManyToOne
    @JoinColumn(name = "misCategoriaMeta_id", nullable = true)
    @JsonBackReference
    private MisCategoriasMetas misCategoriaMeta;

    @ManyToOne
    @JoinColumn(name = "estadoMeta_id", nullable = false)
    @JsonBackReference
    private EstadoMeta estadoMeta;

}
