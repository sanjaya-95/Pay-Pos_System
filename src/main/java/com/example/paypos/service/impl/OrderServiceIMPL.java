package com.example.paypos.service.impl;

import com.example.paypos.dto.request.RequestOrderSaveDTO;
import com.example.paypos.dto.response.ItemGetResponseDTO;
import com.example.paypos.entity.Order;
import com.example.paypos.entity.OrderDetails;
import com.example.paypos.repo.CustomerRepo;
import com.example.paypos.repo.ItemRepo;
import com.example.paypos.repo.OrderDetailRepo;
import com.example.paypos.repo.OrderRepo;
import com.example.paypos.service.OrderService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@Transactional
public class OrderServiceIMPL implements OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private OrderDetailRepo orderDetailRepo;

    @Autowired
    private ItemRepo itemRepo;

    @Override
    @Transactional
    public String addOrder(RequestOrderSaveDTO requestOrderSaveDTO) {
        Order order = new Order(
                requestOrderSaveDTO.getDate(),
                requestOrderSaveDTO.getTotal(),
                customerRepo.getById(requestOrderSaveDTO.getCustomers())

        );
        orderRepo.save(order);

        if(orderRepo.existsById(order.getOrderId())){

            List<OrderDetails> orderDetails = modelMapper.
                    map(requestOrderSaveDTO.getOrderDetails(), new TypeToken<List<OrderDetails>>() {

            }.getType());

            for(int i=0; i<orderDetails.size(); i++){
                orderDetails.get(i).setOrders(order);
                orderDetails.get(i).setItems(itemRepo.getById(requestOrderSaveDTO.getOrderDetails().get(i).getItems()));

            }
           if(orderDetails.size()>0){
               orderDetailRepo.saveAll(orderDetails);
           }
            return "saved";
        }
        return  null;
    }

}
