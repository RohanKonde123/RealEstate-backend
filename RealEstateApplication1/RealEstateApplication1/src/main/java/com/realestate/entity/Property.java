package com.realestate.entity;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "properties")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    private String description;
    private Double price;
    private String location;
    
    // Transaction type: RENT, BUY, or SALE
    private String transactionType;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;
}
