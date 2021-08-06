package com.company.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Table (name = "customers")
@Entity
public class Customer {

    @Id
    private int id;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "additional_information")
    private String additionalInformation;

}
