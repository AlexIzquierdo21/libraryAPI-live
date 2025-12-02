package com.example.library_directo.dto.category;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para solicitudes de creación de categorías.
 * Contiene los datos necesarios para registrar una nueva categoría de libros.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCategoryRequest {

    /**
     * Nombre de la categoría.
     * Campo obligatorio y debe ser único en el sistema.
     */
    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    /**
     * Descripción de la categoría.
     * Campo opcional que proporciona información adicional sobre la categoría.
     */
    private String description;
}





















