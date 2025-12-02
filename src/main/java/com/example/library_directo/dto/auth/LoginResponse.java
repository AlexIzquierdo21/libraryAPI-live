package com.example.library_directo.dto.auth;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para respuestas de login de staff
 * Contiene el token JWT y datos básicos del usuario autenticado.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

    /**
     * Token JWT generado para autenticación
     */
    private String token;

    /**
     * Email del usuario autenticado.
     */
    private String email;

    /**
     * Nombre del usuario autenticado.
     */
    private String name;

    /**
     * Role del usuario (LIBRARIAN, ADMIN)
     */
    private String role;
}
























