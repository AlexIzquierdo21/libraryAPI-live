package com.example.library_directo.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * Entidad que representa un libro en el sistema de biblioteca.
 * Incluye información básica del libro, disponibilidad y relaciones con categoría y usuarios
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "books")
public class Book {

    /**
     * Identificador único del libro.
     * Generado automáticamente por la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Código ISBN del libro.
     * Debe ser único en el sistema y tener máximo 13 caracteres.
     */
    @Column(nullable = false, unique = true, length = 13)
    private String isbn;

    /**
     * Título del libro
     * No puede ser nulo.
     */
    @Column(nullable = false)
    private String title;

    /**
     * Autor del libro
     * No puede ser nulo.
     */
    @Column(nullable = false)
    private String author;

    /**
     * Editorial que publicó el libro
     * Puede ser nulo.
     */
    @Column
    private String publisher;

    /**
     * Año de publicación del libro
     * Puede ser nulo.
     */
    @Column
    private Integer publicationYear;

    /**
     * Descripción o sinopsis del libro.
     * Puede ser nulo.
     */
    @Column
    private String description;

    /**
     * URL de la imagen de portada del libro
     * Máximo 500 caracteres, puede ser nulo.
     */
    @Column(length = 500)
    private String coverImage;

    /**
     * Número total de copias del libro disponibles en la biblioteca.
     * No puede ser nulo.
     */
    @Column(nullable = false)
    private Integer totalCopies;

    /**
     * Número de copias actualmente disponibles para préstamo.
     * No puede ser nulo y se actualiza con préstamos/devoluciones.
     */
    @Column(nullable = false)
    private Integer availableCopies;

    /**
     * Usuario que registró el libro en el sistema.
     * Relación Many-to-One con User.
     */
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    /**
     * Usuario que registró el libro en el sistema
     * Relación Many-to-One con User.
     */
    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

    /**
     * Fecha y hora registro del libro.
     * Se establece automáticamente al crear el registro.
     */
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    /**
     * Fecha y hora de la última actualización del libro.
     * Se actualiza automáticamente al modificar el registro.
     */
    @UpdateTimestamp
    @Column
    private LocalDateTime updatedAt;
}























