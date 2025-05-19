package com.task_manager.rest;


import com.task_manager.service.AuthRequest;
import com.task_manager.service.AuthResponse;
import com.task_manager.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authManager, JwtUtil jwtUtil) {
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
    }

    public void setRefreshTokenCookie(HttpServletResponse response, String refreshToken) {
        ResponseCookie cookie = ResponseCookie.from("refreshToken", refreshToken)
                .httpOnly(true)            // <-- This makes it HttpOnly
                .secure(false)              // Use only on HTTPS (recommended for production)
                .path("/") // Cookie path (adjust as needed)
                .maxAge(7 * 24 * 60 * 60) // 7 days in seconds
                .sameSite("Lax")        // Prevent CSRF (you can also use "Lax")
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }

    /**
     * Authenticates the user and return a JWT token upon successful authentication
     *
     * @param request the authentication request containing username and password
     * @return A ResponseEntity containing the JWT token or an error status
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request, HttpServletResponse response) {
        try {
            logger.info("Authenticating user : {}",  request.getUsername());
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            String token = jwtUtil.generateToken(request.getUsername());

//            String refreshToken = jwtUtil.generateRefreshToken(request.getUsername());

            // Set refresh token as HttpOnly cookie
            setRefreshTokenCookie(response, token);

            return ResponseEntity.ok(new AuthResponse(token));
        } catch (AuthenticationException e) {
            logger.warn("Authentication failed for user: {}", request.getUsername());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()
                || authentication instanceof AnonymousAuthenticationToken) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String username = authentication.getName(); // Or pull from your UserDetails
        return ResponseEntity.ok(Map.of("username", username));
    }

}
