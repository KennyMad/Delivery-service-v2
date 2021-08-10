package com.company.models;

import lombok.Data;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Data
@Audited
@Table (name = "customers")
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "additional_information")
    private String additionalInformation;

}
