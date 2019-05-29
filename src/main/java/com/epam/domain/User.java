package com.epam.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

import java.util.List;

@Data
@Builder(toBuilder=true)
@NoArgsConstructor
public class User {
    private long id;
    private UserRole role;
    private String login;
    private String password;
    @Singular private List<Bill> bills;
}
