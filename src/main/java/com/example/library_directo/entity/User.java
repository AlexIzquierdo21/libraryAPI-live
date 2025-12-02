package com.example.library_directo.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/** Entidad que representa un usuario en el sistema de biblioteca.
 * Los usuario pueden autenticarse mediante OAuth2 (Google) o registro local.
 * Cada usuario tiene un rol que determinan sus permisos en el sistema.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    /**
     * Identificador único del usuario
     * Generado automáticamente por la BD.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Correo electrónico del usuario
     * Debe ser único en el sistema y no puede ser nulo.
     * Se utiliza como identificados principal para Login.
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * Contraseña del usuario (encriptada con BCrypt)
     * Solo para usuario con provider LOCAL (LIBRARIAN, ADMIN)
     * Usuarios con provider GOOGLE no tienen password
     * No se expone en respuestas JSON.
     */
    @Column
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    /**
     * Nombre completo del usuario.
     * Obtenido de Google OAuth2 o proporcionado en registro local.
     */
    @Column(nullable = false)
    private String name;

    /**
     * URL de la imagen de perfil del usuario.
     * Proporcionado por Google OAuth2 personalizada por el usuario.
     * Puede ser nulo.
     */
    @Column(length = 500)
    private String picture;

    /**
     * Rol del usuario en el sistema.
     * Define los permisos y acciones que puede realizar.
     * Por defecto se asigna USER.
     * Valores posibles: USER, LIBRARIAN, ADMIN.
     */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    /**
     * Proveedor de autenticación del usuario.
     * Indica si el usuario se registró mediante Google OAuth2 o Local.
     * Valores posible: GOOGLE, LOCAL.
     */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Provider provider;

    /**
     * Fecha y hora de creación del usuario.
     * Se establece automáticamente al crear el registro
     * No se puede modificar posteriormente
     */
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
}



















