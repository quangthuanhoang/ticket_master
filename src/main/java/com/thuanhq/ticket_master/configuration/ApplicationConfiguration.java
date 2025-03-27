package com.thuanhq.ticket_master.configuration;

import java.util.HashSet;

import com.thuanhq.ticket_master.repository.RoleRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.thuanhq.ticket_master.constant.PredefinedRole;
import com.thuanhq.ticket_master.entity.User;
import com.thuanhq.ticket_master.repository.UserRepository;

@Configuration
public class ApplicationConfiguration {
    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                var roles = new HashSet<String>();
                roles.add(PredefinedRole.ADMIN_ROLE);
                User user = User.builder()
//                        .roles(roles)
                        .username("admin")
                        .password(passwordEncoder.encode("admin"))
                        .build();

                userRepository.save(user);
            }
        };
    }
}
