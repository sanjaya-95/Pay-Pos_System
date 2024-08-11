package com.example.paypos.service.impl;

import com.example.paypos.dto.CustomerDTO;
import com.example.paypos.dto.request.CustomerUpdateDTO;
import com.example.paypos.entity.Customer;
import com.example.paypos.repo.CustomerRepo;
import com.example.paypos.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CustomerServiceIMPL implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public String saveCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer(
                customerDTO.getCustomerId(),
                customerDTO.getCustomerName(),
                customerDTO.getCustomerAddress(),
                customerDTO.getCustomerSalary(),
                customerDTO.getNic(),
                customerDTO.isActiveStatus()

        );
        customerRepo.save(customer);
        return "saved";
    }

    @Override
    public String updateCustomer(CustomerUpdateDTO customerUpdateDTO) {
        if(customerRepo.existsById(customerUpdateDTO.getCustomerId())){
             Customer customer= customerRepo.getById(customerUpdateDTO.getCustomerId());

             customer.setCustomerName(customerUpdateDTO.getCustomerName());
             customer.setCustomerAddress(customerUpdateDTO.getCustomerAddress());
             customer.setCustomerSalary(customerUpdateDTO.getCustomerSalary());

            customerRepo.save(customer);
            return "updated customer";
        } else{
            throw new RuntimeException("No data Found in that id");

        }

    }

    @Override
    public CustomerDTO getCustomerById(int customerId) {
        if(customerRepo.existsById(customerId)){
            Customer customer = customerRepo.getById(customerId);

            CustomerDTO customerDTO = new CustomerDTO(

                    customer.getCustomerId(),
                    customer.getCustomerName(),
                    customer.getCustomerAddress(),
                    customer.getCustomerSalary(),
                    customer.getNic(),
                    customer.isActiveStatus()

            );
            return  customerDTO;

        } else {
            throw new RuntimeException("No customer found in this id");
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> getAllCustomers = customerRepo.findAll();

        if(getAllCustomers.size() >0){
            List<CustomerDTO> customerDTOList = new ArrayList<>();

            for(Customer customer : getAllCustomers){
                CustomerDTO customerDTO = new CustomerDTO(
                        customer.getCustomerId(),
                        customer.getCustomerName(),
                        customer.getCustomerAddress(),
                        customer.getCustomerSalary(),
                        customer.getNic(),
                        customer.isActiveStatus()
                );
                customerDTOList.add(customerDTO);
            }
            return customerDTOList;
        } else {
            throw new RuntimeException("No customer found");
        }

    }

    @Override
    public List<CustomerDTO> getAllCustomersByActiveState(boolean activeState) {
        List<Customer> getAllCustomers = customerRepo.findAllByActiveStatusEquals(activeState);

        if(getAllCustomers.size() >0){
            List<CustomerDTO> customerDTOList = new ArrayList<>();

            for(Customer customer : getAllCustomers){
                CustomerDTO customerDTO = new CustomerDTO(
                        customer.getCustomerId(),
                        customer.getCustomerName(),
                        customer.getCustomerAddress(),
                        customer.getCustomerSalary(),
                        customer.getNic(),
                        customer.isActiveStatus()
                );
                customerDTOList.add(customerDTO);
            }
            return customerDTOList;
        } else {
            throw new RuntimeException("No customer found");
        }

    }



    @Override
    public String deleteCustomer(int customerId) {
        if(customerRepo.existsById(customerId)){
            customerRepo.deleteById(customerId);

            return "Deleted Successful "+customerId;

        } else {
            throw new RuntimeException("No customer found in this id");
        }

    }




}





