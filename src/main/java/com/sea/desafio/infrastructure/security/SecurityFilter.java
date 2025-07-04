package com.sea.desafio.infrastructure.security;

import com.sea.desafio.infrastructure.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain
    ) throws ServletException, IOException {

        var token = this.recoverToken(request);

        if (token != null) {
            var email = tokenService.validateToken(token);
            UserDetails userDetails = usuarioRepository.findByEmail(email);

            var authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities()
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer", "");
    }
}
