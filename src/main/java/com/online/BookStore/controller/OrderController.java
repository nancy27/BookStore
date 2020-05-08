package com.online.BookStore.controller;

import com.online.BookStore.Entity.Order;
import com.online.BookStore.dto.OrderRequest;
import com.online.BookStore.dto.OrderResponse;
import com.online.BookStore.dto.OrderVO;
import com.online.BookStore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;


    @RequestMapping("/getOrderDetails/{orderId}")
    public OrderResponse getOrderDetails(@PathVariable Integer orderId){
        return orderService.getOrderDetails(orderId);
    }

    @RequestMapping(method= RequestMethod.POST,value="/placeOrder")
    public String placingOrder(@RequestBody OrderRequest orderRequest){
        return  orderService.PlacingOrder(orderRequest);
    }

    @RequestMapping(method = RequestMethod.GET,value="/getAllOrders/{customerId}")
    public List<OrderResponse> getAllOrdersOnCustomerId(@PathVariable Integer customerId){
        return orderService.getAllOrderDetailsOfCustomer(customerId);
    }

    @RequestMapping(method = RequestMethod.DELETE,value="/deleteOrder/{orderId}")
    public String deleteOrder(@PathVariable Integer orderId){
        return orderService.deleteOrder(orderId);
    }

    @RequestMapping(method= RequestMethod.PUT,value="/updateOrder")
    public String updatingOrder(@RequestBody OrderRequest orderRequest){
        return  orderService.updatingOrder(orderRequest);
    }


/*
//    @RequestMapping("/get")
@RequestMapping(method= RequestMethod.POST,value="/saveOrder")
public String saveOrder(@RequestBody OrderVO orderVO){
    return  orderService.saveOrder(orderVO);
}

 */




}
