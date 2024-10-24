package com.belkartspaceapi.service;

import com.belkartspaceapi.dto.UserRegisterDTO;
import com.belkartspaceapi.model.Role;

public interface UserService {

    void registerUser(UserRegisterDTO userRegisterDTO, Role role);
}
