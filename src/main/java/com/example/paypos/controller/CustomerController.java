package com.example.paypos.controller;


import com.example.paypos.dto.CustomerDTO;
import com.example.paypos.dto.request.CustomerUpdateDTO;
import com.example.paypos.service.CustomerService;
import com.example.paypos.service.impl.CustomerServiceIMPL;
import com.example.paypos.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
@CrossOrigin
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/save")
    public String saveCustomer(@RequestBody CustomerDTO customerDTO){

        customerService.saveCustomer(customerDTO);
        return "saved";
    }

    @PutMapping("/update")
    public String updateCustomer(@RequestBody CustomerUpdateDTO customerUpdateDTO){
        String message = customerService.updateCustomer(customerUpdateDTO);
        return message;

    }

    @GetMapping(
            path = "/get-by-id",
            params =  "id" )

    public CustomerDTO getCustomerById(@RequestParam(value = "id") int customerId){
        CustomerDTO customerDTO = customerService.getCustomerById(customerId);
        return  customerDTO;
    }

    @DeleteMapping(
            path = "/get-by-delete-customer",
            params =  "id" )

           // path= " /delete-customer/{id}")

    //public String deleteCustomer(@PathVariable(value = "id") int customerId ){

        public String deleteCustomer(@RequestParam(value = "id") int customerId){
        String deleted = customerService.deleteCustomer(customerId);
        return deleted;

    }
//    @GetMapping(
//            path = "/get-all-customers"
//    )
//    public List<CustomerDTO> getAllCustomers(){
//        List<CustomerDTO> allCustomers = customerService.getAllCustomers();
//        return allCustomers;
//    }

    @GetMapping(
            path = "/get-all-customers"
    )
    public ResponseEntity<StandardResponse> getAllCustomers(){
        List<CustomerDTO> allCustomers = customerService.getAllCustomers();
        return new ResponseEntity<StandardResponse> (
                new StandardResponse(200, "Success", allCustomers ),
                HttpStatus.OK
        );
    }

    @GetMapping(
            path ="/get-all-customers-by-active-state/{status}"
    )

    public List<CustomerDTO> getAllCustomersByActiveState(@PathVariable(value="status") boolean activeState){
        List<CustomerDTO> allCustomers = customerService.getAllCustomersByActiveState(activeState);
        return allCustomers;
    }


}
