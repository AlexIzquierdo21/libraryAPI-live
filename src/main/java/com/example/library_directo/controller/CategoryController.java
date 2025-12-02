package com.example.library_directo.controller;

import com.example.library_directo.dto.category.CategoryDto;
import com.example.library_directo.dto.category.CreateCategoryRequest;
import com.example.library_directo.entity.Category;
import com.example.library_directo.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestionar categorías de libros.
 * Proporciona endpoints para operaciones CRUD sobre categorías.
 */
@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    /**
     * Obtiene todas las categorías del sistema.
     *
     * @return lista de categorías en formato DTO.
     */
    @GetMapping
    public List<CategoryDto> getAllCategories() {
        return categoryService.getAllCategories()
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    /**
     * Obtiene una categoría específica por su ID.
     *
     * @param id identificador de la categoría
     * @return categoría en formato DTO
     * @throws  RuntimeException si la categoría no existe.
     */
    @GetMapping("/{id}")
    public CategoryDto getCategoryById(@PathVariable Long id) {
        Category category = categoryService.getCategoryById(id);
        return convertToDto(category);
    }

    /**
     * Crea una nueva categoría en el sistema.
     * Solo accesible por usuarios con rol ADMIN.
     *
     * @param createCategoryRequest datos de la categoría a crear
     * @return categoría creada en formato DTO con status 201 CREATED
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> createCategory(
            @Valid @RequestBody CreateCategoryRequest createCategoryRequest
            ) {
        Category category = categoryService.createCategory(createCategoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDto(category));
    }

    /**
     * Actualiza una categoria existente.
     * Solo accesible por usuarios con rol ADMIN.
     *
     * @param id identificador de la categoria a actualizar
     * @param createCategoryRequest nuevos datos de la categoría
     * @return categoría actualizada en formato DTO.
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> updateCategory(
            @PathVariable Long id,
            @Valid @RequestBody CreateCategoryRequest createCategoryRequest
    ) {
        Category category = categoryService.updateCategory(id, createCategoryRequest);
        return ResponseEntity.ok(convertToDto(category));
    }

    /**
     * Elimina una categoria del sistema.
     * Solo accesible por usuario con rol ADMIN.
     *
     * @param id identificador de la categoría a eliminar
     * @return respuesta sin contenido con status 204 NO CONTENT
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    private CategoryDto convertToDto(Category category) {
        return new CategoryDto(
                category.getId(),
                category.getName(),
                category.getDescription()
        );
    }
}





























