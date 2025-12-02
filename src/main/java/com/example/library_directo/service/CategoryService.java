package com.example.library_directo.service;

import com.example.library_directo.dto.category.CreateCategoryRequest;
import com.example.library_directo.entity.Category;
import com.example.library_directo.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio para gestionar operaciones CRUD  de categorías.
 */

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    /**
     * Crea una nueva categoría en el sistema.
     * @param createCategoryRequest datos de la categoría a crear.
     * @return RuntimeException si ya existe una categoría con ese nombre.
     */
    public Category createCategory(CreateCategoryRequest createCategoryRequest) {

        /// Verificar duplicado
        if (categoryRepository.existsByName(createCategoryRequest.getName())) {
            throw new RuntimeException("Esta categoría ya existe");
        }

        ///  Crear Entity
        Category category = Category.builder()
                .name(createCategoryRequest.getName())
                .description(createCategoryRequest.getDescription())
                .build();

        /// Guardar y devolver
        return categoryRepository.save(category);
    }

    /**
     * Obtiene todas las categorías del sistema.
     *
     * @return lista de todas las categorías.
     */
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    /**
     * Obtiene una categoría por su ID.
     *
     * @param id identificador de la categoría
     * @return categoría encontrada.
     * @throws RuntimeException si la categoría no existe
     */
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Esta categoría no existe"));
    }

    /**
     * Actualiza una categoría ya existente
     *
     * @param id identificador de la categoría a actualizar.
     * @param createCategoryRequest nuevos datos de la categoría
     * @return categoría actuliazada
     * @throws RuntimeException si la categoría no existe o si el nuevo nombre ya está en uso por otra categoría.
     */

    public Category updateCategory(Long id, CreateCategoryRequest createCategoryRequest) {
        Category category =getCategoryById(id);

        /// Verificar duplicado (solo si es otra categoría)
        if (categoryRepository.existsByName(createCategoryRequest.getName())) {
            Category existing = categoryRepository.findByName(createCategoryRequest.getName())
                    .orElseThrow();
            if (!existing.getId().equals(id)){
                throw new RuntimeException("Ya existe otra categoría con ese nombre");
            }
        }

        category.setName(createCategoryRequest.getName());
        category.setDescription(createCategoryRequest.getDescription());

        return categoryRepository.save(category);
    }

    /**
     * ELimina una categoría del sistema.
     * @param id identificador de la categoría a eliminar
     * @throws  RuntimeException si la categoría no existe.
     */
    public void deleteCategory(Long id) {
        Category category = getCategoryById(id);
        categoryRepository.deleteById(id);
    }
}


















