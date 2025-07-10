package com.luisenrique.openbank.auth.service;

import com.luisenrique.openbank.auth.dto.AuthRequest;
import com.luisenrique.openbank.auth.dto.AuthResponse;
import com.luisenrique.openbank.auth.dto.RegisterRequest;
import com.luisenrique.openbank.security.JwtService;
import com.luisenrique.openbank.user.model.Role;
import com.luisenrique.openbank.user.model.User;
import com.luisenrique.openbank.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;

    public AuthResponse register(RegisterRequest request) {
        var user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .dni(request.getDni())
                .build();

        userRepository.save(user);

        String jwtToken = jwtService.generateToken(user);
        return new AuthResponse(jwtToken);
    }

    public AuthResponse login(AuthRequest request) {
        var authToken = new UsernamePasswordAuthenticationToken(request.getDni(), request.getPassword());
        authManager.authenticate(authToken);

        var user = userRepository.findByDni(request.getDni())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        String jwtToken = jwtService.generateToken(user);
        return new AuthResponse(jwtToken);
    }
}