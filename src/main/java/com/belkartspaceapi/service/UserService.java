package com.belkartspaceapi.service;

import com.belkartspaceapi.model.Role;
import com.belkartspaceapi.model.User;

public interface UserService {

    void registerUser(User user, Role role);
}
