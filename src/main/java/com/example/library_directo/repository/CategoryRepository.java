package com.example.library_directo.repository;

import com.example.library_directo.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio para gestionar operaciones de base de datos de categorías.
 * Extienda JpaRepository para operaciones CRUD básicas.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    /**
     * Busca una categoría por su nombre.
     *
     * @param name nombre de la categoría.
     * @return Optional conteniendo la categoría si existe, vacío si no existe.
     */
    Optional<Category> findByName(String name);

    /**
     * Verifica si existe una categoría con el  nombre dado.
     *
     * @param name nombre de la categoría a verificar
     * @return true si existe, false si no.
     */
    boolean existsByName(String name);
}















