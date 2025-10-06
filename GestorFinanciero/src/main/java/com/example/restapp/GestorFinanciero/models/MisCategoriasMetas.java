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

    @OneToMany(mappedBy = "misCategoriaMeta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Meta> metas = new ArrayList<>();
}
