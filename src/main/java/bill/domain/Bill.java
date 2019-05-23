package main.java.bill.domain;

import main.java.common.domain.BaseDomain;
import main.java.order.domain.Order;
import main.java.room.domain.Room;

import java.util.Objects;

public class Bill extends BaseDomain<Long> {
    private Order order;
    private Room room;
    private int totalPrice;

    public Bill() {

    }

    public Bill(Order order, Room room) {
        this.order = order;
        this.room = room;
        this.totalPrice = calcTotalPrice();
    }

    private int calcTotalPrice() {
        return order.getNumOfPersons() * room.getPrice();
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "java.order=" + order +
                ", java.room=" + room +
                ", totalPrice=" + totalPrice +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bill bill = (Bill) o;
        return totalPrice == bill.totalPrice &&
                Objects.equals(order, bill.order) &&
                Objects.equals(room, bill.room);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, room, totalPrice);
    }
}
