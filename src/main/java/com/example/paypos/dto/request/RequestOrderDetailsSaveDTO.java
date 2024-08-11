package com.example.paypos.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestOrderDetailsSaveDTO {


    private String itemName;
    private double qty;
    private double amount;
    private int items;
    private int orders;
}
