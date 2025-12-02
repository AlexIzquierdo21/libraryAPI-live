package com.example.library_directo.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * Entidad que representa una categoría de libros en el sistema.
 * Las categorías permiten clasificar los libros (Ficción, Ciencia...)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "categories")
public class Category {

    /**
     * Identificador único de la categoría.
     * Generado automáticamente por la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre de la categoría
     * Debe ser único en el sistema y no puede ser nulo.
     */
    @Column(nullable = false, unique = true)
    private String name;

    /**
     * Descripción de la categoría.
     * Proporciona información adicional sobre el tipo de libros que incluye
     * Puede ser nulo
      */
    @Column(columnDefinition = "TEXT")
    private String description;

    /**
     * Fecha y hora de creación de la categoría.
     * Se establece automáticamente al crear el registro.
     * No se puede modificar posteriormente.
     */
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
}

























