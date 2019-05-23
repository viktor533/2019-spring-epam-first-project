package main.java.order.domain;

import main.java.user.domain.User;
import main.java.common.domain.BaseDomain;

import java.time.LocalTime;
import java.util.Objects;

public class Order extends BaseDomain<Long> {
    private int numOfPersons;
    private User client;
    private LocalTime startDay;
    private LocalTime finishDay;

    public Order() {

    }

    public Order(int numOfPersons, User client, LocalTime startDay, LocalTime finishDay) {
        this.numOfPersons = numOfPersons;
        this.client = client;
        this.startDay = startDay;
        this.finishDay = finishDay;
    }

    public int getNumOfPersons() {
        return numOfPersons;
    }

    public void setNumOfPersons(int numOfPersons) {
        this.numOfPersons = numOfPersons;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public LocalTime getStartDay() {
        return startDay;
    }

    public void setStartDay(LocalTime startDay) {
        this.startDay = startDay;
    }

    public LocalTime getFinishDay() {
        return finishDay;
    }

    public void setFinishDay(LocalTime finishDay) {
        this.finishDay = finishDay;
    }

    @Override
    public String toString() {
        return "Order{" +
                "numOfPersons=" + numOfPersons +
                ", client=" + client +
                ", startDay=" + startDay +
                ", finishDay=" + finishDay +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return numOfPersons == order.numOfPersons &&
                Objects.equals(client, order.client) &&
                Objects.equals(startDay, order.startDay) &&
                Objects.equals(finishDay, order.finishDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numOfPersons, client, startDay, finishDay);
    }
}
