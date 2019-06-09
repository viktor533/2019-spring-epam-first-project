package com.epam.service;

import com.epam.domain.Bill;
import com.epam.repo.Repository;
import lombok.SneakyThrows;

public class BillService {

    private final Repository<Bill,Long> billRepo;

    public BillService(Repository<Bill, Long> billRepo) {
        this.billRepo = billRepo;
    }

    /**
     * Accept bill, check by null and save it in repository
     *
     * @return saved bill
     */
    @SneakyThrows
    public Bill save(Bill bill) {
        return billRepo.save(bill);
    }

    /**
     * Accepted id and try to delete bill with same id
     *
     * @return removed bill or null, if bill with same id does not exist
     */
    @SneakyThrows
    public Bill removeById(Long id) {
        return billRepo.removeById(id);
    }

    /**
     * Accept id and try to find bill with same id
     *
     * @return found bill or null, if bill with same id does not exist
     */
    @SneakyThrows
    public Bill findById(Long id)  {
        return billRepo.findById(id);
    }


    /**
     * Update room
     *
     * @return updated bill or null if same bill not exist in repository
     */
    @SneakyThrows
    public Bill update(Bill bill) {
        return billRepo.update(bill);
    }

    /**
     * @return Iterable by bills, contained in repository
     */
    @SneakyThrows
    public Iterable<Bill> findAll() {
        return billRepo.findAll();
    }
}
