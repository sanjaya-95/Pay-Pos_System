package com.example.paypos.controller;

import com.example.paypos.dto.request.ItemSaveRequestDTO;
import com.example.paypos.dto.request.RequestOrderSaveDTO;
import com.example.paypos.service.OrderService;
import com.example.paypos.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/order")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping(
            path = "/save" )

    public ResponseEntity<StandardResponse> saveItem(@RequestBody RequestOrderSaveDTO requestOrderSaveDTO ){
        String id = orderService.addOrder(requestOrderSaveDTO);


        return new ResponseEntity<StandardResponse> (
                new StandardResponse(201, id+ "Success", id ),
                HttpStatus.CREATED
        );

    }
}
