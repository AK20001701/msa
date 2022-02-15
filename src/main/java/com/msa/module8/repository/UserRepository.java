package com.msa.module8.repository;

import com.msa.module8.domain.Role;
import com.msa.module8.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserRepository {

    private final PasswordEncoder passwordEncoder;

    public Optional<User> findByUsername(String username){
        User user = User.builder()
                .username("user")
                .password(passwordEncoder.encode("password"))
                .authorities(new HashSet<>(Arrays.asList(Role.USER)))
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .build();
     return Optional.of(user);
    }

}
