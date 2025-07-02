package com.luisenrique.openbank.user.repository;

import com.luisenrique.openbank.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByDni(String dni); // Necesario para autenticación
}