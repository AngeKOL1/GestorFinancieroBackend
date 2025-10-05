package com.example.restapp.GestorFinanciero.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

//Es como los roles de las metas
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tipoMeta")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TipoMeta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idTipoMeta;
    //[Prioritario, A largo plazo, Opcional]
    @Column(nullable = false, length = 30)
    private String nombreTipoMeta;
    @Column(nullable = false, length = 100)
    private String descripcionTipoMeta;

    @OneToOne
    @JoinColumn(name = "meta_id", nullable = false)
    private Meta meta;
}
