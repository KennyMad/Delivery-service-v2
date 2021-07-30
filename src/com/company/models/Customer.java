package com.company.models;

import javax.persistence.*;

@Table (name = "customers")
@Entity
public class Customer {

    @Id
    private int id;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "additional_information")
    private String additionalInformation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return customerName;
    }

    public void setName(String customerName) {
        this.customerName = customerName;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }
}
