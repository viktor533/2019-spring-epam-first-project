package com.epam.service;

import com.epam.domain.Token;
import com.epam.repo.Repository;

public class TokenService extends BaseService<Token, String> {
    public TokenService(Repository<Token, String> repository) {
        super(repository);
    }
}
