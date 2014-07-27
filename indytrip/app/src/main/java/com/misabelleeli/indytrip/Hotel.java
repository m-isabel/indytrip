package com.misabelleeli.indytrip;

/**
 * Created by Lee on 2014-07-26.
 */
public class Hotel {
    private String name;
    private String address;

    public Hotel(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
