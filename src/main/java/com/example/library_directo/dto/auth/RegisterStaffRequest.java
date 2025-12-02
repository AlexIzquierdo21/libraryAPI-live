package com.example.library_directo.dto.auth;

import com.example.library_directo.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para solicitudes de registro de staff.
 * Solo ADMIN puede registrar nuevos usuarios staff (LIBRARIAN, ADMIN).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterStaffRequest {
    /**
     * Email del usuario staff.
     */
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Email inválido")
    private String email;

    /**
     * Contraseña del nuevo usuario staff.
     */
    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, message = "La contraseña deber ser superior a 8 caracteres")
    private String password;

    /**
    Nombre del nuevo usuario staff
     */
    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    /**
     * Rol del nuevo usuario (LIBRARIAN o ADMIN)
     */
    @NotNull(message = "El rol es obligatorio")
    private Role role;
}
























