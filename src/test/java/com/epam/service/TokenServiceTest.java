package com.epam.service;

import com.epam.domain.Token;
import com.epam.domain.enums.UserRole;
import com.epam.repo.Repository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

public class TokenServiceTest  {
    private TokenService tokenService;

    @Mock
    private Repository<Token, String> repository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        tokenService = new TokenService(repository);
    }

    private Token generateDemoToken() {
        return Token.builder().token("abc123").role(UserRole.USER).userId(1).build();
    }

    @Test
    public void save() {
        Token before = generateDemoToken();

        when(repository.save(before)).thenReturn(before);

        Token after = tokenService.save(before);

        Assert.assertEquals(before, after);
    }

    @Test
    public void removeById() {
        Token before = generateDemoToken();

        when(repository.removeById("abc123")).thenReturn(before);

        Token after = tokenService.removeById("abc123");

        Assert.assertEquals(before, after);
    }

    @Test
    public void findById() {
        Token before = generateDemoToken();

        when(repository.findById("abc")).thenReturn(before);

        Token after = tokenService.findById("abc");

        Assert.assertEquals(before, after);
    }

    @Test
    public void update() {
        Token before = generateDemoToken();

        when(repository.update(before)).thenReturn(before);

        Token after = tokenService.update(before);

        Assert.assertEquals(before, after);
    }

    @Test
    public void findAll() {
        Iterable<Token> before = new ArrayList<>();

        when(repository.findAll()).thenReturn(before);

        Iterable<Token> after = tokenService.findAll();

        Assert.assertEquals(before, after);
    }

    @Test
    public void saveAll() {
        Iterable<Token> before = new ArrayList<>();
        Token[] tokens = new Token[0];

        when(repository.saveAll(tokens)).thenReturn(before);

        Iterable<Token> after = tokenService.saveAll(tokens);

        Assert.assertEquals(before, after);
    }

    @Test
    public void removeAllById() {
        Iterable<Token> before = new ArrayList<>();
        String[] ids = new String[0];

        when(repository.removeAllById(ids)).thenReturn(before);

        Iterable<Token> after = tokenService.removeAllById(ids);

        Assert.assertEquals(before, after);
    }

    @Test
    public void findAllById() {
        Iterable<Token> before = new ArrayList<>();
        String[] ids = new String[0];

        when(repository.findAllById(ids)).thenReturn(before);

        Iterable<Token> after = tokenService.findAllById(ids);

        Assert.assertEquals(before, after);
    }

    @Test
    public void updateAll() {
        Iterable<Token> before = new ArrayList<>();
        Token[] tokens = new Token[0];

        when(repository.updateAll(tokens)).thenReturn(before);

        Iterable<Token> after = tokenService.updateAll(tokens);

        Assert.assertEquals(before, after);
    }
}