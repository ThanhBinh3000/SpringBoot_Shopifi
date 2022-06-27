package com.tecapro.demobinhan.model;

import com.tecapro.demobinhan.model.auth.User;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date createDate;

    private Integer status;

    @ManyToOne
    private User user;

    @ManyToOne
    private CustomerInfo customerInfo;
}
