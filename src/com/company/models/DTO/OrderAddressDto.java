package com.company.models.DTO;

public class OrderAddressDto {

    private int id;

    private String house;
    private String street;
    private String city;

    public OrderAddressDto(){}

    public OrderAddressDto(String house, String street, String city) {
        this.house = house;
        this.street = street;
        this.city = city;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "OrderAddressDto{" +
                "id=" + id +
                ", house='" + house + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
