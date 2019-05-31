package com.epam.domain;

import com.epam.domain.enums.UserRole;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.List;

@Data
@Builder(toBuilder = true)
public class User {
    private long id;
    private UserRole role;
    private String login;
    private String password;
    @Singular
    private List<Bill> bills;
}
