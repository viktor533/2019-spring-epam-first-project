package com.epam.service;

import static org.mockito.Mockito.when;

import com.epam.domain.Bill;
import com.epam.domain.enums.BillStatus;
import com.epam.repo.BillRepoImpl;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class BillServiceTest {

    private BillService billService;

    @Mock
    private BillRepoImpl repo;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        billService = new BillService(repo);
    }

    @Test
    public void save() {
        Bill before = Bill.builder().id(1).bookingId(2).userId(3).status(BillStatus.UNPAID).build();

        when(repo.save(before)).thenReturn(before);

        Bill after = billService.save(before);

        Assert.assertEquals(before, after);
    }

    @Test
    public void removeById() {
        Bill before = Bill.builder().id(1).userId(2).bookingId(3).status(BillStatus.PAID).build();

        when(repo.removeById(1L)).thenReturn(before);

        Bill after = billService.removeById(1L);

        Assert.assertEquals(before, after);
    }

    @Test
    public void findById() {
        Bill before = Bill.builder().id(1).userId(2).bookingId(3).status(BillStatus.UNPAID).build();

        when(repo.findById(1L)).thenReturn(before);

        Bill after = billService.findById(1L);

        Assert.assertEquals(before, after);
    }

    @Test
    public void update() {
        Bill before = Bill.builder().id(1).userId(2).bookingId(3).status(BillStatus.PAID).build();

        when(repo.update(before)).thenReturn(before);

        Bill after = billService.update(before);

        Assert.assertEquals(before, after);

    }

    @Test
    public void findAll() {
        Iterable<Bill> before = new ArrayList<>();

        when(repo.findAll()).thenReturn(before);

        Iterable<Bill> after = billService.findAll();

        Assert.assertEquals(before, after);
    }
}