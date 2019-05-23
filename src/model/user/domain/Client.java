package model.user.domain;

import model.common.domain.Role;
import model.order.domain.Order;

import java.util.ArrayList;
import java.util.List;

public class Client extends User {

    private List<Order> orders;

    public Client() {
        super();
        this.orders = new ArrayList<>();
    }

    public Client(String name, String login, long passHash) {
        super(name, login, passHash, Role.CLIENT);
        this.orders = new ArrayList<>();
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
