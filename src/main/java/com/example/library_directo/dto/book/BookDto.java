package com.example.library_directo.dto.book;


import com.example.library_directo.dto.category.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Dro para repsuestas de libros.
 * Contiene toda la información de un libro para mostrar al usuario.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    /**
     * Identificador único del libro
     */
    private Long id;

    /**
     * Código ISBN del libro
     */
    private String isbn;

    /**
     * Título del libro.
     */
    private String title;

    /**
     * Autor del libro.
     */
    private String author;

    /**
     * Editorial que publicó el libro.
     */
    private String publisher;

    /**
     * Año de publicación del libro.
     */
    private Integer publicationYear;

    /**
     * Descripción o sinposis del libro.
     */
    private String description;

    /**
     * URL de la imagen de portada del libro.
     */
    private String coverImage;

    /**
     * Número total de copias del libro en la biblioteca.
     */
    private Integer totalCopies;

    /**
     * Número de copias actualmente disponibles para préstamo.
     */
    private Integer availableCopies;

    /**
     * Categoría a la que pertenece el libro.
     * Incluye ID, nombre y descripción de la categoría.
     */
    private CategoryDto category;

    /**
     * Nombre del usuario que registró el libro en el sistema.
     */
    private String createdByName;
}




















