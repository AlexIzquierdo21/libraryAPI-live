package com.example.library_directo.dto.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para respuestas de categorías.
 * Contiene la información completa de una categoría para mostrar al usuario.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    /**
     * Identificador único de la categoria.
     */
    private Long id;

    /**
     * Nombre de la categoría
     */
    private String name;

    /**
     * Descripción de la categoría
     */
    private String description;
}





















