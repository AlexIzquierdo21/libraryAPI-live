package com.example.library_directo.repository;


import com.example.library_directo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio para gestionar operaciones de base de datos de libros.
 *Extiende JpaRepository para operaciones CRUD básicas.
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    /**
     * Busca un libro por su código ISBN.
     *
     * @param isbn código ISBN del libro
     * @return Optional conteniendo el libro si existe, vacío si no.
     */
    Optional<Book> findByIsbn(String isbn);

    /**
     * Busca todos los libros con un título específico.
     * Pueden existir múltiples libros con el mismo título.
     *
     * @param title título del libro
     * @return Lista de libros con ese título.
     */
    List<Book> findByTitle(String title);

    /**
     * Busca todos los libros de un autor específico.
     *
     * @param author nombre del autor
     * @return lista de libros del autor.
     */
    List<Book> findByAuthor(String author);

    /**
     * Verifica si existe un libro con el ISBN dado.
     * @param isbn código ISBN a verificar
     * @return true si existe, false si no.
     */
    boolean existsByIsbn(String isbn);
}
























