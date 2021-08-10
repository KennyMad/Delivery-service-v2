package com.company.models;

import lombok.Data;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Data
@Audited
@Entity
@Table(name = "addresses")
public class OrderAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String house;
    private String street;
    private String city;

}
