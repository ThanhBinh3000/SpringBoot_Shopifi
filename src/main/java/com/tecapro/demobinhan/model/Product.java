package com.tecapro.demobinhan.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private boolean status;

    @Column(nullable = false)
    private String preservation;

    @Column(nullable = false)
    private String ingredient;

    @Column(nullable = false)
    private String instructional;

    @Column(nullable = false)
    private int mass;

    @Column(nullable = false)
    private String description;

    private int saleOff;

    private Date createdDate;

    @ManyToOne
    private Category category;
}
