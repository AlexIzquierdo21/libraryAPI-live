package com.example.library_directo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller para mostrar la página de login.
 * No devuelve JSON, devuelve el nombre del template HTML.
 */
@Controller
public class LoginController {

    /**
     * Muestra la página de login con el botón de Google OAuth2.
     *
     * @return nombre del template (login.html)
     */
    @GetMapping("/")
    public String showLoginPage() {
        return "login";
    }
}
