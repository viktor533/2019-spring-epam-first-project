package com.epam.repo;

import com.epam.domain.Bill;
import com.epam.domain.enums.BillStatus;
import org.junit.Assert;
import org.junit.Test;

public class BillRepoImplTest {

    @Test(expected = IllegalArgumentException.class)
    public void save_NullableBill() {

        // arrange
        BillRepoImpl repo = new BillRepoImpl();
        Bill bill = null;

        // act
        // assert
        bill = repo.save(bill);

    }

    @Test
    public void save_AllValuesOneAndPaidBill() {
        // arrange
        BillRepoImpl repo = new BillRepoImpl();

        // act
        Bill bill = Bill.builder().id(1L).bookingId(1L).userId(1L).status(BillStatus.PAID).build();

        // assert
        Assert.assertEquals(
            Bill.builder().id(1L).bookingId(1L).userId(1L).status(BillStatus.PAID).build(), bill);
    }

    @Test
    public void removeById() {

    }

    @Test
    public void findById() {
    }

    @Test
    public void update() {
    }

    @Test
    public void findAll() {
    }
}