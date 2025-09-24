package com.example.loginseguro.config;

import com.example.loginseguro.model.Role;
import com.example.loginseguro.model.User;
import com.example.loginseguro.repo.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Set;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initUsers(UserRepository repo, PasswordEncoder encoder) {
        return args -> {
            if (repo.count() == 0) {
                String adminEmail = System.getenv().getOrDefault("ADMIN_EMAIL", "admin@example.com");
                String adminUsername = System.getenv().getOrDefault("ADMIN_USERNAME", "admin");
                String adminPass = System.getenv("ADMIN_INIT_PASSWORD");
                if (adminPass == null || adminPass.isBlank()) {
                    // Gera uma senha aleatória segura e exibe no console no primeiro start
                    byte[] buf = new byte[18];
                    new SecureRandom().nextBytes(buf);
                    adminPass = Base64.getUrlEncoder().withoutPadding().encodeToString(buf);
                    System.out.println("\n==============================");
                    System.out.println("ADMIN_INIT_PASSWORD (gerado): " + adminPass);
                    System.out.println("Altere após o primeiro login.");
                    System.out.println("==============================\n");
                }
                User admin = new User();
                admin.setUsername(adminUsername);
                admin.setEmail(adminEmail);
                admin.setPasswordHash(encoder.encode(adminPass));
                admin.setEnabled(true);
                admin.setRoles(Set.of(Role.ADMIN, Role.USER));
                repo.save(admin);
            }
        };
    }
}
