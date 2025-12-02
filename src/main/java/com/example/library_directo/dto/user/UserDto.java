package com.example.library_directo.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para respuestas de usuarios.
 * Contiene información básica del usuario sin datos sensibles.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    /**
     * Identificado único del usuario.
     */
    private Long id;

    /**
     * Email del usuario.
     */
    private String email;

    /**
     * Nombre del usuario.
     */
    private String name;

    /**
     * Rol del usuario.
     */
    private String role;

    /**
     * URL foto de perfil del usuario.
     */
    private String picture;
}


























