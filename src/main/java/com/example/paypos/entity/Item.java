package com.example.paypos.entity;


import com.example.paypos.entity.enums.MeasuringUnitType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity

@NoArgsConstructor
@AllArgsConstructor
//@Getter
//@Setter
//@ToString
@Data
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int itemId;


    private String itemName;

    @Enumerated(EnumType.STRING)
    private MeasuringUnitType measuringUnitType;

    private double balanceQty;

    private double supplierPrice;

    private double selling_price;

    private boolean activeStatus;

    @OneToMany(mappedBy="items")
    private Set<OrderDetails> orderDetails;



}

