package com.aiims.hospitalManagement.security;

import com.aiims.hospitalManagement.entity.User;
import com.aiims.hospitalManagement.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;
    private final AuthUtil authUtil;
    private final HandlerExceptionResolver handlerExceptionResolver;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        try {

            log.info("Incoming request: {}", request.getRequestURI());

            String authHeader = request.getHeader("Authorization");

            // No JWT supplied
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }

            // Remove "Bearer "
            String token = authHeader.substring(7).trim();
            log.info("Authorization Header = [{}]", authHeader);
            log.info("JWT Token = [{}]", token);
            log.info("JWT Token length = {}", token.length());

            log.info("JWT token received: {}", token);

            // Empty token
            if (token.isEmpty()) {
                filterChain.doFilter(request, response);
                return;
            }

            String username = authUtil.getUsernameFromToken(token);

            if (username != null
                    && SecurityContextHolder.getContext().getAuthentication() == null) {

                User user = userRepository
                        .findByUsername(username)
                        .orElseThrow();

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                user,
                                null,
                                user.getAuthorities()
                        );

                SecurityContextHolder
                        .getContext()
                        .setAuthentication(authentication);
            }

            filterChain.doFilter(request, response);

        } catch (Exception ex) {

            log.error("JWT authentication failed", ex);

            handlerExceptionResolver.resolveException(
                    request,
                    response,
                    null,
                    ex
            );
        }
    }
}