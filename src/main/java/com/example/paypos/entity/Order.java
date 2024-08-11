package com.example.paypos.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name= "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderId;

    private Date date;

    private Double total;

    @ManyToOne
    @JoinColumn(name="customer_id", nullable=false)
    private Customer customers;


    @OneToMany(mappedBy="orders")
    private Set<OrderDetails> orderDetails;

    public Order(Date date, Double total, Customer customers) {
        this.date = date;
        this.total = total;
        this.customers = customers;
    }
}
