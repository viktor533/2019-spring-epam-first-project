package com.epam.service;

import static org.mockito.Mockito.when;

import com.epam.domain.Bill;
import com.epam.domain.User;
import com.epam.domain.enums.BillStatus;
import com.epam.domain.enums.UserRole;
import com.epam.repo.UserRepoImpl;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UserServiceTest {

    private UserService userService;

    private static final Bill BILL = Bill.builder().id(1).userId(2).bookingId(3)
        .status(BillStatus.PAID).build();

    @Mock
    private UserRepoImpl repo;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new UserService(repo);
    }


    @Test
    public void save() {
        User before = User.builder().id(1).login("lala").password("132").role(UserRole.USER)
            .bill(BILL).build();

        when(repo.save(before)).thenReturn(before);

        User after = userService.save(before);

        Assert.assertEquals(before, after);
    }

    @Test
    public void removeById() {
        User before = User.builder().id(1).login("lala").password("132").role(UserRole.USER)
            .bill(BILL).build();
        when(repo.removeById(1L)).thenReturn(before);

        User after = userService.removeById(1L);

        Assert.assertEquals(before, after);
    }

    @Test
    public void findById() {
        User before = User.builder().id(1).login("lala").password("132").role(UserRole.USER)
            .bill(BILL).build();
        when(repo.findById(1L)).thenReturn(before);

        User after = userService.findById(1L);

        Assert.assertEquals(before, after);
    }

    @Test
    public void update() {
        User before = User.builder().id(1).login("lala").password("132").role(UserRole.USER)
            .bill(BILL).build();

        when(repo.update(before)).thenReturn(before);

        User after = userService.update(before);

        Assert.assertEquals(before, after);

    }

    @Test
    public void findAll() {
        Iterable<User> before = new ArrayList<>();

        when(repo.findAll()).thenReturn(before);

        Iterable<User> after = userService.findAll();

        Assert.assertEquals(before, after);
    }
}