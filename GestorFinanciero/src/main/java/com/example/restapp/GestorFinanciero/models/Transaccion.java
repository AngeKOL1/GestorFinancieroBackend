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
@Table(name = "transacciones")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Transaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTransacción;
    @Column(nullable = false)
    private Float Monto;
    @Column(nullable = false)
    private LocalDate fechaTransaccion;
    @Column(nullable = false,  length = 300 )
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuarioTransacciones;

    @OneToMany(mappedBy = "transaccion", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MetaTransaccion> metaTransaccion = new HashSet<>();
}
