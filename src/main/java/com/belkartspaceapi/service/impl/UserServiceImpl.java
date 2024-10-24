package com.belkartspaceapi.service.impl;

import com.belkartspaceapi.model.Role;
import com.belkartspaceapi.model.User;
import com.belkartspaceapi.repository.UserRepository;
import com.belkartspaceapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void registerUser(User user, Role role) {
        if (userRepository.findAllByUsername(user.getUsername()).isPresent()) {
            throw new IllegalArgumentException("User with this login already exists");
        }
        if(role == null){
            user.setRoles(Set.of(Role.USER));
        }else{
            user.setRoles(Set.of(role));
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        userRepository.save(user);
    }
}
