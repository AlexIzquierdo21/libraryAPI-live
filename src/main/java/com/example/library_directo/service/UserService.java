package com.example.library_directo.service;


import com.example.library_directo.entity.User;
import com.example.library_directo.repository.UserRepository;
import com.example.library_directo.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Servicio para gestionar operaciones de usuarios
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private final JwtUtil jwtUtil;

    /**
     * Busca un usuario por su email
     * @param email email del usuario
     * @return Optional con el usuario si existe.
     */
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Verifica si existe un usuario con el email proporcionado.
     *
     * @param email email a verificar.
     * @return true si existe.
     */
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    /**
     * Guarda un usuario en la base de datos
     * @param user usuario a guardar
     * @return usuario guardado
     */
    public User save(User user) {
        return userRepository.save(user);
    }

}



























