package com.epam.domain;

import com.epam.domain.enums.BillStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Bill {
    private long id;
    private long bookingId;
    private BillStatus status;
}
