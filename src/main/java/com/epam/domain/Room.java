package com.epam.domain;

import com.epam.domain.enums.RoomClass;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.List;

@Data
@Builder(toBuilder = true)
public class Room {
    private int numOfGuests;
    @Singular
    private List<Booking> bookings;
    private int pricePerNight;
    private RoomClass roomClass;
}
