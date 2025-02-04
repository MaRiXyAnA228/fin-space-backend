package com.belkartspaceapi.service.impl;

import com.belkartspaceapi.dto.UserRegisterDTO;
import com.belkartspaceapi.model.Client;
import com.belkartspaceapi.model.Role;
import com.belkartspaceapi.model.User;
import com.belkartspaceapi.repository.ClientRepository;
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
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void registerUser(UserRegisterDTO userRegisterDTO, Role role) {
        if (userRepository.findByUsername(userRegisterDTO.username()).isPresent()) {
            throw new IllegalArgumentException("User with this login already exists");
        }

        Client client = new Client();
        client.setFirstName(userRegisterDTO.firstName());
        client.setLastName(userRegisterDTO.lastName());
        clientRepository.save(client);

        User user = new User();
        if (role == null) {
            user.setRoles(Set.of(Role.USER));
        } else {
            user.setRoles(Set.of(role));
        }

        user.setClient(client);
        user.setUsername(userRegisterDTO.username());
        user.setPassword(passwordEncoder.encode(userRegisterDTO.password()));
        user.setActive(true);

        userRepository.save(user);
    }
}
