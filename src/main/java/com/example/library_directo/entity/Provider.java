package com.example.library_directo.entity;

///  PROVEEDORES DE AUTENTICACIÓN SOPORTADOS POR EL SISTEMA
public enum Provider {

    /**
     * GOOGLE: Usuario autenticado mediante OAuth2 de Google
     * No tiene contraseña en el sistema.
     * Local: Usuario registrado directamente en el sistema.
     * Tiene contraseña encriptada (LIBRARIAN, ADMIN)
     */
    GOOGLE, LOCAL
}
