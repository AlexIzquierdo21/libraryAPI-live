package com.example.library_directo.repository;


import com.example.library_directo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio para gestionar operaciones de base de datos de usuario.
 * Extiende JpaRepository para operaciones CRUD Básicas.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Busca un usuario por su correo electrónico.
     *
     * @param email correo electrónico del usuario
     * @return Optional conteniendo el ussuario si existe, vacío si no existe.
     */
    Optional<User> findByEmail(String email);

    /**
     * Verifica si existe un usuario con el correo electrónico dado.
     *
     * @param email correo electrónico a verificar.
     * @return true si existe y false si no.
     */
    boolean existsByEmail(String email);

}













