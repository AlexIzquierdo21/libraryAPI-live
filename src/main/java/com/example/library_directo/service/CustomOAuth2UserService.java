package com.example.library_directo.service;

import com.example.library_directo.entity.Provider;
import com.example.library_directo.entity.Role;
import com.example.library_directo.entity.User;
import com.example.library_directo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Servicio personalizado para manejar usuarios autenticados con OAuth2.
 * Intercepta el proceso de login de Google y guarda/actualiza usuario.
 */
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        /// Obtener datos de Google
        OAuth2User oauth2User = super.loadUser(userRequest);

        /// Extraer email, nombre, foto
        String email = oauth2User.getAttribute("email");
        String name = oauth2User.getAttribute("name");
        String picture = oauth2User.getAttribute("picture");

        /// Buscar usuartio opor email
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            /// Usuario nuevo - CREAR Y GUARDAR el usuario "nuevo".
            User newUser = User.builder()
                    .email(email)
                    .name(name)
                    .picture(picture)
                    .role(Role.USER)
                    .provider(Provider.GOOGLE)
                    .build();

            userRepository.save(newUser);
        } else {
            /// Usuario existente - Actualizar datos por si cambiaron en Google
            User existingUser = userOptional.get();
            existingUser.setName(name);
            existingUser.setPicture(picture);
            userRepository.save(existingUser);
        }

        return oauth2User;
    }
}

















