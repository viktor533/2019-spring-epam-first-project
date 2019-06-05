package com.epam.service;

import com.epam.domain.Bill;
import com.epam.repo.BillRepoImpl;
import lombok.SneakyThrows;

public class BillService {

    private final BillRepoImpl billRepo = new BillRepoImpl();

    @SneakyThrows
    public Bill save(Bill item) throws IllegalArgumentException {
        return billRepo.save(item);
    }

    @SneakyThrows
    public Bill removeById(Long id) throws IllegalArgumentException {
        return billRepo.removeById(id);
    }

    @SneakyThrows
    public Bill findById(Long id) throws IllegalArgumentException {
        return billRepo.findById(id);
    }

    @SneakyThrows
    public Bill update(Bill item) throws IllegalArgumentException {
        return billRepo.update(item);
    }

    @SneakyThrows
    public Iterable<Bill> findAll() {
        return billRepo.findAll();
    }
}
