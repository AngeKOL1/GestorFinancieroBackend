package com.example.restapp.GestorFinanciero.models;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="meta_transaccion")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MetaTransaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMetaTransaccion;

    @ManyToOne
    @JoinColumn(name = "meta_id", nullable = false)
    private Meta meta;

    @ManyToOne
    @JoinColumn(name = "transaccion_id", nullable = false)
    private Transaccion transaccion;



}
