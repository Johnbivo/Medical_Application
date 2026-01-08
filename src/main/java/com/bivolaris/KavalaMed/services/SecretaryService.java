package com.bivolaris.KavalaMed.services;

import com.bivolaris.KavalaMed.dtos.SecretaryRegisterRequest;
import com.bivolaris.KavalaMed.entities.Secretary;
import com.bivolaris.KavalaMed.entities.User;
import com.bivolaris.KavalaMed.mappers.SecretaryRegisterRequestMapper;
import com.bivolaris.KavalaMed.repositories.SecretaryRepository;
import com.bivolaris.KavalaMed.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SecretaryService {

    private final SecretaryRepository secretaryRepository;
    private final UserRepository userRepository;
    private final SecretaryRegisterRequestMapper secretaryRegisterRequestMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public boolean registerSecretary(SecretaryRegisterRequest request) {
        try {
            if (userRepository.existsByUsername(request.getUsername())) {
                throw new RuntimeException("Username already exists");
            }
            if (!request.getPassword().equals(request.getConfirmPassword())) {
                throw new RuntimeException("Passwords do not match");
            }

            User user = secretaryRegisterRequestMapper.toUser(request);
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setRole(User.Role.Secretary);
            user.setActivated(true);
            User savedUser = userRepository.save(user);

            Secretary secretary = secretaryRegisterRequestMapper.toSecretary(request);
            secretary.setUser(savedUser);
            secretaryRepository.save(secretary);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Failed to register secretary: " + e.getMessage());
        }
    }
}
