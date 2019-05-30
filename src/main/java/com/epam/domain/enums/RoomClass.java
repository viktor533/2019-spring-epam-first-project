package com.epam.domain.enums;

import com.epam.domain.Room;

public enum RoomClass {
    ECONOMY, STANDART, LUX;

    public boolean hasThisClass(Room room) {
        return room.getRoomClass() == this;
    }
}
