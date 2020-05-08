package com.online.BookStore.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.online.BookStore.Entity.Customer;
import com.online.BookStore.dto.CustomerVo;
import com.online.BookStore.exception.DataNotFoundException;
import com.online.BookStore.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    ModelMapper modelMapper = new ModelMapper();

    public String saveCustomer(CustomerVo customerVo) {
        Customer customer= modelMapper.map(customerVo,Customer.class);
        customerRepository.save(customer);
        return "Saved User Successfully";
    }

    public CustomerVo getCustomerDetails(Integer customerId){
        Optional<Customer> optionalCustomer=customerRepository.findByCustomerId(customerId);
        if(!optionalCustomer.isPresent()){throw new DataNotFoundException("Customer ID Not Found");
        }
        Customer customer= optionalCustomer.get();
        return new CustomerVo(customer.getCustomerId(),customer.getCustomerName(),customer.getAddress(),customer.getPhoneNo());
    }

    public String updateCustomer(CustomerVo customerVo) {
        Integer customerId=customerVo.getCustomerId();
        Optional<Customer> optionalCustomer=customerRepository.findByCustomerId(customerId);
        if(!optionalCustomer.isPresent()){throw new DataNotFoundException("Customer ID Not Found");
        }

        Customer customer = modelMapper.map(customerVo, Customer.class);
       customerRepository.save(customer);
       return "updated customer details successfully";
    }

    public String deleteCustomer(Integer customerId){
        Optional<Customer> optionalCustomer=customerRepository.findByCustomerId(customerId);
        if(!optionalCustomer.isPresent()){throw new DataNotFoundException("Customer ID Not Found");
        }
        Customer customer= optionalCustomer.get();
        customerRepository.delete(customer);
        return "Deleted Successfully !!";
    }
}
