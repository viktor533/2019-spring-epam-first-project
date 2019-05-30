package com.epam.service;

import com.epam.domain.User;
import com.epam.domain.enums.UserRole;

public interface AuthService {
    boolean register(User user);

    boolean logIn(User user);

    boolean logOut(User user);

    boolean isEnoughRights(User user, UserRole role);
}
