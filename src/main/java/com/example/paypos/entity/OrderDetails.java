package com.example.paypos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name= "order_details")
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderDetailsId;

    private String itemName;

    private double qty;

    private double amount;


//
    @ManyToOne
    @JoinColumn(name="item_id", nullable=false)
    private Item items;

    @ManyToOne
    @JoinColumn(name="order_id", nullable=false)
    private Order orders;
}
