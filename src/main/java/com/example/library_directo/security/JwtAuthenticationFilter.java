package com.example.library_directo.security;

import com.example.library_directo.entity.User;
import com.example.library_directo.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Filtro de autenticación JWT que intercepta todas las peticiones HTTP.
 * Se ejecutar una vez por petición  para validar Tokens JWT.
 * Solo se aplica a usuarios staff(LIBRARIAN, ADMIN) que usan JWT.
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserService userService;

    public JwtAuthenticationFilter(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        /// Extrae el header Authorization de la peticiçon
        String authHeader = request.getHeader("Authorization");

        ///  Si no hay header o no empieza con "Bearer ", continua sin autenticar
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        /// Extrae el token JWT (quitando "Bearer " que son 7 caracteres)
        String token = authHeader.substring(7);

        try {
            /// Extrae el email del token JWT
            String email = jwtUtil.extractEmail(token);

            ///  Buscar el usuario en la BD por email
            Optional<User> userOptional = userService.findByEmail(email);

            ///  Si el usuario no existe, continúa sin autenticar
            if (userOptional.isEmpty()) {
                filterChain.doFilter(request, response);
                return;
            }
            User user = userOptional.get();

            ///  Valida el token(verifica firma y expiración)
            boolean isValid = jwtUtil.validateToken(token, email);

            if (!isValid) {
                filterChain.doFilter(request, response);
                return;
            }

            /// Crear lista de autoridades en el rol del usuario
            List<GrantedAuthority> authorities = List.of(
                    new SimpleGrantedAuthority("ROLE" + user.getRole().name())
            );

            ///  Crea el objeto de autenticación de Spring Security
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(
                            user.getEmail(),
                            null,
                            authorities
                    );

            /// Setea el usuario autenticado en el contexto de seguridad
            SecurityContextHolder.getContext().setAuthentication(authToken);

            /// Continúa con la cadena de filtros
            filterChain.doFilter(request, response);

        } catch (Exception e) {
            ///  Si hay algún error (token inválido, expirado...) continúa sin autenticar
            filterChain.doFilter(request, response);
        }
    }
}




















