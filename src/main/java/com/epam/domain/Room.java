package com.epam.domain;

import com.epam.domain.enums.BillStatus;
import com.epam.domain.enums.RoomClass;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.List;

@Data
@Builder(toBuilder = true)
public class Room {
    private long id;
    private int number;
    private long hotelId;
    private int numOfGuests;
    @Singular
    private List<Bill> bills;
    private int pricePerNight;
    private RoomClass roomClass;
}
