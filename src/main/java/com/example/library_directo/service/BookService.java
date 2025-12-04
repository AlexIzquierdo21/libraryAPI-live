package com.example.library_directo.service;

import com.example.library_directo.dto.book.CreateBookRequest;
import com.example.library_directo.entity.Book;
import com.example.library_directo.entity.Category;
import com.example.library_directo.entity.User;
import com.example.library_directo.repository.BookRepository;
import com.example.library_directo.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;


    /**
     * Crea un nuevo libro en el sistema.
     *
     * @param createBookRequest datos del libro a crear
     * @param currentUser usuario que registra el libro
     * @return libro creado
     * @throws RuntimeException si el ISBN ya existe o si la categoría no existe.
     */
    public Book createBook(CreateBookRequest createBookRequest, User currentUser) {
        /// Validar ISBN único
        if (bookRepository.existsByIsbn(createBookRequest.getIsbn())) {
            throw new RuntimeException("Este ISBN ya existe. Verifique el ISBN.");
        }

        ///  Verificar que la categoría existe
        Category category = categoryRepository.findById(createBookRequest.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Categoria incorrecta"));

        /// Crear libro
        Book book = Book.builder()
                .isbn(createBookRequest.getIsbn())
                .title(createBookRequest.getTitle())
                .author(createBookRequest.getAuthor())
                .publisher(createBookRequest.getPublisher())
                .publicationYear(createBookRequest.getPublicationYear())
                .description(createBookRequest.getDescription())
                .coverImage(createBookRequest.getCoverImage())
                .totalCopies(createBookRequest.getTotalCopies())
                .availableCopies(createBookRequest.getTotalCopies())
                .category(category)
                .createdBy(currentUser)
                .build();

        return bookRepository.save(book);
    }

    /**
     * Obtiene todos los libros del sistema
     *
     * @return lista de todos los libros.
     */
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    /**
     * Obtiene un libro por su ID
     *
     * @param id identificador del libro
     * @return libro encontrado
     * @throws RuntimeException si el libro no existe
     */
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Este libro no existe"));
    }

    /**
     * Actualiza un libro existente
     *
     * @param id identificador del libro a actualizar
     * @param createBookRequest nuevos datos del libro
     * @return libro actualizado
     * @throws RuntimeException si el libro no existe, si la categoría no existe
     *                          o si el nuevo ISBN ya está en uso por otro libro.
     */
    public Book updateBook(Long id, CreateBookRequest createBookRequest) {
        Book book = getBookById(id);

        ///  Verificar que la categoria existe
        Category category = categoryRepository.findById(createBookRequest.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Categoria incorrecta"));

        /// Verificar duplicado de ISBN(solo si es otro libro)
        if (bookRepository.existsByIsbn(createBookRequest.getIsbn())) {
            Book existing = bookRepository.findByIsbn(createBookRequest.getIsbn())
                    .orElseThrow();
            if(!existing.getId().equals(id)) {
                throw new RuntimeException("Ya existe un libro con este ISBN");
            }
        }

        /// Actualizar Campos
        book.setIsbn(createBookRequest.getIsbn());
        book.setTitle(createBookRequest.getTitle());
        book.setAuthor(createBookRequest.getAuthor());
        book.setPublisher(createBookRequest.getPublisher());
        book.setPublicationYear(createBookRequest.getPublicationYear());
        book.setDescription(createBookRequest.getDescription());
        book.setCoverImage(createBookRequest.getCoverImage());
        book.setTotalCopies(createBookRequest.getTotalCopies());
        book.setCategory(category);

        return bookRepository.save(book);
    }

    /**
     * Elimina un libro del sistema.
     *
     * @param id identificador del libro a eliminar.
     */
    public void deleteBook(Long id) {
        Book book = getBookById(id);
        bookRepository.deleteById(id);
    }
}



















