package com.luisenrique.openbank.user.service;

import com.luisenrique.openbank.user.model.User;
import com.luisenrique.openbank.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getAuthenticatedUser() {
        var auth = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        String dni = auth.getName(); // el username es el DNI
        return userRepository.findByDni(dni).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
}
