package com.example.library_directo.controller;

import com.example.library_directo.dto.book.BookDto;
import com.example.library_directo.dto.book.CreateBookRequest;
import com.example.library_directo.dto.category.CategoryDto;
import com.example.library_directo.entity.Book;
import com.example.library_directo.entity.Category;
import com.example.library_directo.entity.User;
import com.example.library_directo.repository.UserRepository;
import com.example.library_directo.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestionar libros.
 * Proporciona endpoints para operaciones CRUD sobre libros.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;
    private final UserRepository userRepository;

    /**
     * Obtiene todos los libros del sistema
     *
     * @return lista de libros en formato DTO.
     */
    @GetMapping
    public List<BookDto> getAllBook() {
        return bookService.getAllBooks()
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    /**
     * Obtiene un libro específico por su ID
     *
     * @param id identificador del libro
     * @return libro en formato DTO.
     * @throws RuntimeException si el libro no existe.
     */
    @GetMapping("/{id}")
    public BookDto getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        return convertToDto(book);
    }

    /**
     * Crea un nuevo libro en el sistema
     * Solo accesible por usuarios con rol ADMIN o LIBRARIAN
     * Soporta autenticación OAuth2 y JWT
     *
     * @param createBookRequest datos del libro a crear
     * @return libro creado en formato DTO con status 201 CREATED
     * @throws RuntimeException si el ISBN ya existe, la categoría no existe,
     *                          o el usuario no está autenticado
     */
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN')")
    public ResponseEntity<BookDto> createBook(
            @Valid @RequestBody CreateBookRequest createBookRequest
            ) {
        ///  Obtener el email del usuario autenticado (funciona con OAuth2 y JWT)
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User currentUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Book book = bookService.createBook(createBookRequest, currentUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDto(book));
    }

    /**
     * Actualiza un libro existente.
     * Solo accesible por usuarios con rol ADMIN o LIBRARIAN.
     *
     * @param id identificador del libro a actualizar
     * @param createBookRequest nuevos datos del libro
     * @return libro actualizado en formato DTO
     * @throws RuntimeException si el libro no existe, si la categoría no existe,
     *                          o si el nuevo ISBN ya está en uso por otro libro
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN')")
    public ResponseEntity<BookDto> updateBook(
            @PathVariable Long id,
            @Valid @RequestBody CreateBookRequest createBookRequest
    ) {
        Book book = bookService.updateBook(id, createBookRequest);
        return ResponseEntity.ok(convertToDto(book));
    }

    /**
     * Elimina un libro del sistema.
     * Solo accesible por usuarios con rol ADMIN o LIBRARIAN.
     *
     * @param id identificador del libro a eliminar
     * @return respuesta sin contenido con status 204 NO CONTENT
     * @throws RuntimeException si el libro no existe
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN')")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Convierte una entidad Book a BookDto.
     *
     * @param book entidad a convertir
     * @return DTO con los datos del libro.
     */
    private BookDto convertToDto(Book book) {
        return new BookDto(
                book.getId(),
                book.getIsbn(),
                book.getTitle(),
                book.getAuthor(),
                book.getPublisher(),
                book.getPublicationYear(),
                book.getDescription(),
                book.getCoverImage(),
                book.getTotalCopies(),
                book.getAvailableCopies(),
                convertCategoryToDto(book.getCategory()),
                book.getCreatedBy().getName()
        );
    }

    /**
     * Convierte una entidad Category a CategoryDto
     * @param category entidad a convertir
     * @return DTO con los datos de la categoría.
     */
    private CategoryDto convertCategoryToDto(Category category) {
        return new CategoryDto(
                category.getId(),
                category.getName(),
                category.getDescription()
        );
    }
}





















