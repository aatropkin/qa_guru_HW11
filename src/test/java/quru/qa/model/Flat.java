package quru.qa.model;

import java.util.List;
import java.util.Objects;

public class Flat {
    private int number;
    private int square;
    private String owner;
    private List<String> rooms;
    private Address address;

    public int getNumber() {
        return number;
    }

    public int getSquare() {
        return square;
    }

    public String getOwner() {
        return owner;
    }

    public List<String> getRooms() {
        return rooms;
    }

    public Address getAddress() {
        return address;
    }
}
