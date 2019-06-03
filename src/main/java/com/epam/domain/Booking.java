package com.epam.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder(toBuilder=true)
public class Booking {
    private long id;
    private long roomId;
    private LocalDate start;
    private LocalDate end;
}
