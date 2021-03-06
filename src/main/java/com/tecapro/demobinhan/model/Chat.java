package com.tecapro.demobinhan.model;

import com.tecapro.demobinhan.model.auth.User;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User sender;

    @ManyToOne
    private User receiver;

    private String content;

    private boolean status;

    private Date time;
}
