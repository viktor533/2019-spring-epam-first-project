package com.epam.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder=true)
public class Bill {
    private long id;
    private long bookingId;
    private BillStatus status;
}
