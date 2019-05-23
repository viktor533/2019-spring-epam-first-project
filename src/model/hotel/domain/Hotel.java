package model.hotel.domain;

import model.common.domain.BaseDomain;

import java.util.Objects;

public class Hotel extends BaseDomain<Long> {
    private String name;
    private String address;

    public Hotel() {

    }

    public Hotel(String name, String address) {
        this.name = name;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hotel hotel = (Hotel) o;
        return Objects.equals(name, hotel.name) &&
                Objects.equals(address, hotel.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address);
    }
}
