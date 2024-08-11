package com.example.paypos.dto.request;

import com.example.paypos.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestOrderSaveDTO {

    private int customers;
    private Date date;
    private Double total;
    private List<RequestOrderDetailsSaveDTO> orderDetails;

}
