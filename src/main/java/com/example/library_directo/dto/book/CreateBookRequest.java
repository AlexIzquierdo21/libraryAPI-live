package com.example.library_directo.dto.book;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para solicitudes de creación de libros.
 * Contiene los datos necesarios para registrar un nuevo libro en el sistema.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookRequest {

    /**
     * Código ISBN del libro
     * Debe ser único y obligatorio
     */
    @NotBlank(message = "El ISBN es obligatorio")
    private String isbn;

    /**
     * Título del libro
     * Campo obligatorio
     */
    @NotBlank(message = "El título es obligatorio")
    private String title;

    /**
     * Autor del libro
     * Campo obligatorio
     */
    @NotBlank(message = "El autor es obligatorio")
    private String author;

    /**
     * Editorial que publicó el libro
     * Campo opcional
     */
    private String publisher;

    /**
     * Año de publicacción del libro
     * Campo opcional
     */
    private Integer publicationYear;

    /**
     * Descripción o sinopsis del libro
     * Campo opcional.
     */
    private String description;

    /**
     * URL de la imagen de portada del libro
     * Campo opcional.
     */
    private String coverImage;

    /**
     * Número total de copias del libro disponibles en la biblioteca.
     * Campo obligatorio. EL número de copias disponibles se inicializa automáticamente con este valor.
     */
    @NotNull(message = "EL total de copias es obligatorio")
    private Integer totalCopies;

    /**
     * ID de la categoría a la que pertenece el libro.
     * Campo obligatorio. Debe corresponder a una categoría existente.
     */
    @NotNull(message = "La categoria es obligatoria")
    private Long categoryId;
}


















