package com.company.models;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
public class OrderAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String house;
    private String street;
    private String city;

    public int getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getHouse() {
        return house;
    }

    public String getStreet() {
        return street;
    }

    public void setId(int id) {
        this.id = id;
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
                "id=" + id +
                ", house='" + house + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
