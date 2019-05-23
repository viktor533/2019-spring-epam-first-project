package main.java.room.domain;

import main.java.common.domain.RoomClass;
import main.java.common.domain.BaseDomain;
import main.java.roomMonitor.domain.RoomMonitor;

import java.util.Objects;

public class Room extends BaseDomain<Long> {
    // private Hotel java.hotel; If update to many hotels
    private int numOfPlaces;
    private int price;
    private String number;
    private RoomClass roomClass;
    private RoomMonitor roomMonitor;

    public Room() {
    }

    public Room(String number, int numOfPlaces, int price, RoomClass roomClass, RoomMonitor roomMonitor) {
        this.number = number;
        this.numOfPlaces = numOfPlaces;
        this.price = price;
        this.roomClass = roomClass;
        this.roomMonitor = roomMonitor;
    }

    public int getNumOfPlaces() {
        return numOfPlaces;
    }

    public void setNumOfPlaces(int numOfPlaces) {
        this.numOfPlaces = numOfPlaces;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public RoomClass getRoomClass() {
        return roomClass;
    }

    public void setRoomClass(RoomClass roomClass) {
        this.roomClass = roomClass;
    }

    public RoomMonitor getRoomMonitor() {
        return roomMonitor;
    }

    public void setRoomMonitor(RoomMonitor roomMonitor) {
        this.roomMonitor = roomMonitor;
    }

    @Override
    public String toString() {
        return "Room{" +
                "numOfPlaces=" + numOfPlaces +
                ", price=" + price +
                ", number='" + number + '\'' +
                ", roomClass=" + roomClass +
                ", java.roomMonitor=" + roomMonitor +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return numOfPlaces == room.numOfPlaces &&
                price == room.price &&
                Objects.equals(number, room.number) &&
                roomClass == room.roomClass &&
                Objects.equals(roomMonitor, room.roomMonitor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numOfPlaces, price, number, roomClass, roomMonitor);
    }
}
