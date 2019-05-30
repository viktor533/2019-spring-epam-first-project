package com.epam.domain.enums;

import com.epam.domain.Bill;

public enum BillStatus {
    PAID, UNPAID;

    public boolean hasThisStatus(Bill bill) {
        return bill.getStatus() == this;
    }
}
