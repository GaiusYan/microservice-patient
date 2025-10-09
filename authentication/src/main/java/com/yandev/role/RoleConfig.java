package com.yandev.role;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class RoleConfig {

    final List<String> roleNames = List.of("ROLE_ADMIN", "ROLE_PATIENT", "ROLE_DOCTOR", "ROLE_RECEPTIONIST");

    @Bean
    public CommandLineRunner commandLineRunner(RoleService roleService) {
        return args -> {
            for (String roleName : roleNames) {
                roleService.createRole(Role
                        .builder()
                        .name(roleName)
                        .build());
            }
        };
    }
}
