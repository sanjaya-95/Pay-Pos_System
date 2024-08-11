package com.example.paypos.dto.response;


import com.example.paypos.entity.enums.MeasuringUnitType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemGetResponseDTO {

    private int itemId;
    private String itemName;
    private double balanceQty;
    private double supplierPrice;
    private double selling_price;
    private boolean activeStatus;
}
