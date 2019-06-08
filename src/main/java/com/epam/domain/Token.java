package com.epam.domain;

import com.epam.domain.enums.UserRole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Token {
    private String token;
    private long userId;
    private UserRole role;
}
