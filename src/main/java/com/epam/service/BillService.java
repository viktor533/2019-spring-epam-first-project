package com.epam.service;

import com.epam.domain.Bill;
import com.epam.domain.Booking;
import com.epam.domain.User;
import com.epam.repo.BillRepoImpl;
import com.epam.repo.Repository;
import com.epam.state.RepositoryState;
import lombok.SneakyThrows;

public class BillService {

    private final Repository<Bill,Long> billRepo;
    private Repository<User, Long> userRepo = RepositoryState.getUserRepositoryInstance();
    private Repository<Booking, Long> bookingRepo = RepositoryState.getBookingRepositoryInstance();


    public BillService(Repository<Bill, Long> billRepo) {
        this.billRepo = billRepo;
    }


    /**
     * Check bill and bill fields by null
     *
     * @param bill to check
     * @throws IllegalArgumentException if accepted room or room fields is null
     */
    private void checkNulls(Bill bill) throws IllegalArgumentException {
        if (bill == null) {
            throw new IllegalArgumentException("Accepted bill is null!");
        }
        if (findById(bill.getId()) != null) {
            throw new IllegalArgumentException("Bill with same id already exist!");
        }
        if (bill.getUserId() <= 0) {
            throw new IllegalArgumentException("UserId can't be less than 0");
        }
        if (userRepo.findById(bill.getUserId()) == null) {
            throw new IllegalArgumentException("UserID id is not correct: not exist!");
        }
        if (bill.getBookingId() <= 0) {
            throw new IllegalArgumentException("Booking id can't be less than 0");
        }
        if (bookingRepo.findById(bill.getBookingId()) == null) {
            throw new IllegalArgumentException("Booking id is not correct: not exist!");
        }
        if (bill.getStatus() == null) {
            throw new IllegalArgumentException("Bill must be PAID or UNPAID");
        }
    }


    /**
     * Accept bill, check by null and save it in repository
     *
     * @return saved bill
     * @throws IllegalArgumentException if accepted bill or bill fields is null
     */
    @SneakyThrows
    public Bill save(Bill bill) throws IllegalArgumentException {
        return billRepo.save(bill);
    }

    /**
     * Accepted id and try to delete bill with same id
     *
     * @return removed bill or null, if bill with same id does not exist
     * @throws IllegalArgumentException if id is null
     */
    @SneakyThrows
    public Bill removeById(Long id) throws IllegalArgumentException {
        return billRepo.removeById(id);
    }

    /**
     * Accept id and try to find bill with same id
     *
     * @return found bill or null, if bill with same id does not exist
     * @throws IllegalArgumentException if id is null
     */
    @SneakyThrows
    public Bill findById(Long id) throws IllegalArgumentException {
        return billRepo.findById(id);
    }


    /**
     * Update room
     *
     * @return updated bill or null if same bill not exist in repository
     * @throws IllegalArgumentException if accepted bill or bill fields is null
     */
    @SneakyThrows
    public Bill update(Bill bill) throws IllegalArgumentException {
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
