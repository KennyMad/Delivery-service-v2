package com.company.models;

public class OrderAddress {

    private String house;
    private String street;
    private String city;

    public String getCity() {
        return city;
    }

    public String getHouse() {
        return house;
    }

    public String getStreet() {
        return street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return "OrderAddress{" +
                "house='" + house + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
