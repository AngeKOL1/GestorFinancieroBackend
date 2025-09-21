package com.example.restapp.GestorFinanciero.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.modelmapper.internal.bytebuddy.dynamic.loading.InjectionClassLoader;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="usuario_trofeo")
@EqualsAndHashCode
public class UsuarioTrofeo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer idUsuarioTrofeo;
    @Column(nullable = false)
    private LocalDate fechaObtencionTrofeo;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonManagedReference
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "trofeo_id", nullable = false)
    @JsonManagedReference
    private Trofeos trofeo;
}
