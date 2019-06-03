package com.epam.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Hotel {
    private long id;
    private String name;
    private String location;
    private int luxury;
    private List<Room> rooms;

    Hotel(long id, String name, String location, int luxury, List<Room> rooms) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.luxury = luxury;
        this.rooms = rooms;
    }

    public static HotelBuilder builder() {
        return new HotelBuilder();
    }

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getLocation() {
        return this.location;
    }

    public int getLuxury() {
        return this.luxury;
    }

    public List<Room> getRooms() {
        return this.rooms;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setLuxury(int luxury) {
        this.luxury = luxury;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Hotel)) return false;
        final Hotel other = (Hotel) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getId() != other.getId()) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$location = this.getLocation();
        final Object other$location = other.getLocation();
        if (this$location == null ? other$location != null : !this$location.equals(other$location)) return false;
        if (this.getLuxury() != other.getLuxury()) return false;
        final Object this$rooms = this.getRooms();
        final Object other$rooms = other.getRooms();
        if (this$rooms == null ? other$rooms != null : !this$rooms.equals(other$rooms)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Hotel;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final long $id = this.getId();
        result = result * PRIME + (int) ($id >>> 32 ^ $id);
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $location = this.getLocation();
        result = result * PRIME + ($location == null ? 43 : $location.hashCode());
        result = result * PRIME + this.getLuxury();
        final Object $rooms = this.getRooms();
        result = result * PRIME + ($rooms == null ? 43 : $rooms.hashCode());
        return result;
    }

    public String toString() {
        return "Hotel(id=" + this.getId() + ", name=" + this.getName() + ", location=" + this.getLocation() + ", luxury=" + this.getLuxury() + ", rooms=" + this.getRooms() + ")";
    }

    public HotelBuilder toBuilder() {
        return new HotelBuilder().id(this.id).name(this.name).location(this.location).luxury(this.luxury).rooms(this.rooms);
    }

    public static class HotelBuilder {
        private long id;
        private String name;
        private String location;
        private int luxury;
        private ArrayList<Room> rooms;

        HotelBuilder() {
        }

        public Hotel.HotelBuilder id(long id) {
            this.id = id;
            return this;
        }

        public Hotel.HotelBuilder name(String name) {
            this.name = name;
            return this;
        }

        public Hotel.HotelBuilder location(String location) {
            this.location = location;
            return this;
        }

        public Hotel.HotelBuilder luxury(int luxury) {
            this.luxury = luxury;
            return this;
        }

        public Hotel.HotelBuilder room(Room room) {
            if (this.rooms == null) this.rooms = new ArrayList<Room>();
            this.rooms.add(room);
            return this;
        }

        public Hotel.HotelBuilder rooms(Collection<? extends Room> rooms) {
            if (this.rooms == null) this.rooms = new ArrayList<Room>();
            this.rooms.addAll(rooms);
            return this;
        }

        public Hotel.HotelBuilder clearRooms() {
            if (this.rooms != null)
                this.rooms.clear();

            return this;
        }

        public Hotel build() {
            List<Room> rooms;
            switch (this.rooms == null ? 0 : this.rooms.size()) {
                case 0:
                    rooms = java.util.Collections.emptyList();
                    break;
                case 1:
                    rooms = java.util.Collections.singletonList(this.rooms.get(0));
                    break;
                default:
                    rooms = java.util.Collections.unmodifiableList(new ArrayList<Room>(this.rooms));
            }

            return new Hotel(id, name, location, luxury, rooms);
        }

        public String toString() {
            return "Hotel.HotelBuilder(id=" + this.id + ", name=" + this.name + ", location=" + this.location + ", luxury=" + this.luxury + ", rooms=" + this.rooms + ")";
        }
    }
}
