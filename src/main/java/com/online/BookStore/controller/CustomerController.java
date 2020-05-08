package com.online.BookStore.controller;

import com.online.BookStore.Entity.Customer;
import com.online.BookStore.dto.CustomerVo;
import com.online.BookStore.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;


    @RequestMapping(method=RequestMethod.POST,value="/saveCustomer")
    public String saveCustomer(@RequestBody CustomerVo customerVo){
     return customerService.saveCustomer(customerVo);
    }

    @RequestMapping("/getCustomer/{customerId}")
    public CustomerVo getCustomerDetails(@PathVariable Integer customerId){
        return customerService.getCustomerDetails(customerId);
    }

    @RequestMapping(method=RequestMethod.PUT,value="/updateCustomer")
    public String updateCustomer(@RequestBody CustomerVo customerVo){
        return customerService.updateCustomer(customerVo);
    }

    @RequestMapping(method = RequestMethod.DELETE,value="/deleteCustomer/{customerId}")
    public String deleteCustomer(@PathVariable Integer customerId){
        return customerService.deleteCustomer(customerId);
    }
}
