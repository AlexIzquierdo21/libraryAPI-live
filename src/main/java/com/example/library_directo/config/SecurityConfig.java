package com.example.library_directo.config;

import com.example.library_directo.security.JwtAuthenticationFilter;
import com.example.library_directo.service.CustomOAuth2UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configuración de seguridad de la aplicación.
 * Combina OAuth2 Login (usuarios normales) con JWT (staff).
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    /**
     * Configura la cadena de filtros de seguridad.
     * - OAuth2 para usuarios normales (Google)
     * - JWT para staff (email + password)
     *
     * @param httpSecurity objeto HttpSecurity para configurar
     * @return SecurityFilterChain configurado
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                /// Deshabilitar CSRF (no necesario para API REST)
                .csrf(csrf -> csrf.disable())

                /// Configurar manejo de excepciones para API REST
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint((request, response, authException) -> {
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            response.setContentType("application/json");
                            response.getWriter().write("{\"error\": \"Unauthorized\", \"message\": \"Authentication required\"}");
                        })
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                            response.setContentType("application/json");
                            response.getWriter().write("{\"error\": \"Forbidden\", \"message\": \"Access denied\"}");
                        })
                )

                /// Configurar reglas de autorización
                .authorizeHttpRequests(auth -> auth
                        /// Endpoints públicos (sin autenticación)
                        .requestMatchers("/", "/login", "/error",
                                "/api/auth/login", "/api/auth/register-staff").permitAll()
                        /// Resto de endpoints requieren autenticación
                        .anyRequest().authenticated()
                )

                /// Añadir filtro JWT antes del filtro de autenticación estándar
                .addFilterBefore(jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class)

                /// Habilitar OAuth2 Login con servicio personalizado
                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(customOAuth2UserService)
                        )
                )
                .build();
    }
}























