package model.user.domain;

import model.common.domain.BaseDomain;
import model.common.domain.Role;
import model.order.domain.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User extends BaseDomain<Long> {
    private String name;
    private String login;
    private long passHash;
    private Role role;
    private List<Order> orders;

    public User() {

    }

    public User(String name, String login, long passHash, Role role) {
        this.name = name;
        this.login = login;
        this.passHash = passHash;
        this.role = role;
        if (Role.CLIENT.equals(role)) {
            this.orders = new ArrayList<>();
        } else {
            this.orders = null;
        }

    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public long getPassHash() {
        return passHash;
    }

    public void setPassHash(long passHash) {
        this.passHash = passHash;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", passHash=" + passHash +
                ", role=" + role +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return passHash == user.passHash &&
                Objects.equals(name, user.name) &&
                Objects.equals(login, user.login) &&
                role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, login, passHash, role);
    }
}
