package com.company.models;

public class Customer implements Cloneable{

    private int id;

    private String name;

    private String additionalInformation;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public Customer clone() throws CloneNotSupportedException{
        return (Customer) super.clone();
    }

    public Customer(){}

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", additionalInformation='" + additionalInformation + '\'' +
                '}';
    }
}
