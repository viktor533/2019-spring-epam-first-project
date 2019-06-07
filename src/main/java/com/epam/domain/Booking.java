package com.epam.domain;

import com.epam.domain.enums.RoomClass;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder(toBuilder=true)
public class Booking {
    private long id;
    private RoomClass roomClass;
    private LocalDate start;
    private LocalDate end;
}
