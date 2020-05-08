package com.online.BookStore.service;

import com.online.BookStore.Entity.Book;
import com.online.BookStore.Entity.Customer;
import com.online.BookStore.Entity.Order;
import com.online.BookStore.Entity.OrderBook;
import com.online.BookStore.dto.*;
import com.online.BookStore.exception.DataNotFoundException;
import com.online.BookStore.repository.BookRepository;
import com.online.BookStore.repository.CustomerRepository;
import com.online.BookStore.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public OrderService(OrderRepository orderRepository, BookRepository bookRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.bookRepository = bookRepository;
        this.customerRepository = customerRepository;
    }

    public String PlacingOrder(OrderRequest orderRequest){
        List<OrderBook> orderBookList= new ArrayList<>();
        try{
         orderBookList= getOrderBookList(orderRequest);}catch(DataNotFoundException e){
            throw new DataNotFoundException("Book ids are empty");
        }
        float totalCost= (float) calculateTotalPrice(orderRequest.getBookDtoList());
        Integer customerId=orderRequest.getCustomerId();
        Optional<Customer> customer=customerRepository.findByCustomerId(customerId);
                if(!customer.isPresent()){
                throw new DataNotFoundException("Not Found");
            }
            Customer customer1=customer.get();
        Order order= new Order(orderRequest.getOrderId(),orderRequest.getAddress(),
                orderRequest.getPhoneNo(),new Date(),totalCost,orderBookList,customer1);
        Order order1= orderRepository.save(order);
        return "Placed Order";
    }

    public double calculateTotalPrice( List<BookDto> bookDtoList){
        double total=0;
        for (int i = 0; i <bookDtoList.size() ; i++) {
            if(bookDtoList.get(i).getPrice() == null || bookDtoList.get(i).getQuantity() ==null){
                throw new DataNotFoundException("Price value is null ");
            }
            total=total+ (bookDtoList.get(i).getPrice())*(bookDtoList.get(i).getQuantity());
        }
        return total;
    }

    public OrderResponse getOrderDetails(Integer orderId) {
        Optional<Order> optionalOrder= orderRepository.findByOrderId(orderId);
        if(!optionalOrder.isPresent()){
            throw new DataNotFoundException("Not Found");
        }
        Order order=optionalOrder.get();
        return getOrderResponseFromOrder(order);
    }

    public OrderResponse getOrderResponseFromOrder(Order order){

        List<OrderBook> orderBookList=order.getBookList();
        if(orderBookList.size() == 0){
            throw new DataNotFoundException("No Book Id's found in the Order");
        }
        else{
        List<OrderBookVo> orderBookVo=new ArrayList<>(orderBookList.size());
        List<Integer> bookId=new ArrayList<>();
        for (int j = 0; j <orderBookList.size() ; j++) {
            bookId.add(orderBookList.get(j).getBookId());
        }
        try{
        Optional<List<Book>> bookListOptional = Optional.of(bookRepository.findAllById(bookId));
            if(bookListOptional.get().isEmpty() || bookListOptional.get() == null){
                throw new DataNotFoundException("Book Id Not Found");
            }
            List<Book> bookList= bookListOptional.get();

        for (int i = 0; i < bookList.size() ; i++) {
            OrderBookVo orderBookVo1=new OrderBookVo();
            orderBookVo1.setBookId(orderBookList.get(i).getBookId());
            orderBookVo1.setBookTitle(bookList.get(i).getTitle());
            orderBookVo1.setQuantity(orderBookList.get(i).getQuantity());
            orderBookVo.add(i,orderBookVo1);
        }}catch(DataNotFoundException e){
            throw new DataNotFoundException("Data Cannot be found");
        }
        return new OrderResponse(order.getOrderId(),order.getAddress(),order.getPhoneNo(),order.getCreatedDate(),
                order.getFinalPrice(),orderBookVo);
        }
    }

    public List<OrderResponse> getAllOrderDetailsOfCustomer(Integer customerId){
        Optional<List<Order>> optionalOrderList= Optional.of(orderRepository.findAllByCustomerId(customerId));
        if(optionalOrderList.get().isEmpty() || optionalOrderList.get() ==null){
            throw new DataNotFoundException("No Order Details Found");
        }
        List<Order> orderList= optionalOrderList.get();
        List<OrderResponse> orderResponseList= new ArrayList<>();
        for (int k = 0; k <orderList.size() ; k++) {
            Order order= orderList.get(k);
            OrderResponse orderResponse= getOrderResponseFromOrder(order);
            orderResponseList.add(k,orderResponse);
        }
        return orderResponseList;
    }


    public String deleteOrder(Integer orderId){

        Optional<Order> optionalOrder= orderRepository.findByOrderId(orderId);
        if(!optionalOrder.isPresent()){
            throw new DataNotFoundException("Data Cannot be found");
        }
        Order order = optionalOrder.get();
        orderRepository.delete(order);
        return "Deleted Successfully";
    }

    public String updatingOrder(OrderRequest orderRequest) {
    Integer orderId= orderRequest.getOrderId();
        Optional<Order> optionalOrder= orderRepository.findByOrderId(orderId);
        if(!optionalOrder.isPresent()){
            throw new DataNotFoundException("Data Cannot be found");
        }
        Order order= optionalOrder.get();
        order.setOrderId(orderId);
        order.setAddress(orderRequest.getAddress());
        order.setPhoneNo(orderRequest.getPhoneNo());
        order.setCreatedDate(new Date());
       order.setBookList(getOrderBookList(orderRequest));
        float totalCost= (float) calculateTotalPrice(orderRequest.getBookDtoList());
        order.setFinalPrice(totalCost);
        Optional<Customer> customer=customerRepository.findByCustomerId(orderRequest.getCustomerId());
        if(!customer.isPresent()){
            throw new DataNotFoundException("Not Found");
        }
        Customer customer1=customer.get();
        order.setCustomer(customer1);
        Order order1= orderRepository.save(order);
        return "Updated your Order!!";

    }

    public List<OrderBook> getOrderBookList(OrderRequest orderRequest){

        List<BookDto> bookList=orderRequest.getBookDtoList();
        if(bookList.size() == 0){
            throw new DataNotFoundException("No Book Id's found in the Order");
        }
        List<OrderBook> orderBookList=new ArrayList<>();
        for (int i = 0; i < bookList.size() ; i++) {
            BookDto bookDto = bookList.get(i);
            OrderBook orderBook= new OrderBook();
            orderBook.setBookId(bookDto.getBookId());
            orderBook.setQuantity(bookDto.getQuantity());
            orderBookList.add(orderBook);
        }
        return orderBookList;
    }

    /*
    public String saveOrder(OrderVO orderVO) {
        Order order= new Order(orderVO.getOrderId(),orderVO.getAddress(),orderVO.getPhoneNo(),orderVO.getCreatedDate(),orderVO.getFinalPrice());
        orderRepository.save(order);
        return "Saved Successfully";
    }

 */


}
