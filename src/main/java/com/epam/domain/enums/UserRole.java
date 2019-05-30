package com.epam.domain.enums;

import com.epam.domain.User;

public enum UserRole {
    ADMIN, USER;

    public boolean hasThisRole(User user) {
        return user.getRole() == this;
    }
}
