package com.online.BookStore.service

import com.online.BookStore.Entity.Book
import com.online.BookStore.Entity.Customer
import com.online.BookStore.Entity.Order
import com.online.BookStore.Entity.OrderBook
import com.online.BookStore.dto.BookDto
import com.online.BookStore.dto.OrderBookVo
import com.online.BookStore.dto.OrderRequest
import com.online.BookStore.dto.OrderResponse
import com.online.BookStore.exception.DataNotFoundException
import com.online.BookStore.repository.BookRepository
import com.online.BookStore.repository.CustomerRepository
import com.online.BookStore.repository.OrderRepository
import com.online.BookStore.service.OrderService
import spock.lang.Specification

class OrderServiceTest extends Specification {
    CustomerRepository customerRepository = Mock(CustomerRepository)
    OrderRepository orderRepository = Mock(OrderRepository)
    BookRepository bookRepository = Mock(BookRepository)
    OrderService orderService = new OrderService(orderRepository, bookRepository, customerRepository)

    def "Testing placing order when input is valid"() {
        given:
        Customer customer = new Customer(1, "Edin", "yghkjas", "265317563")
        Date date = new Date()
        customerRepository.findByCustomerId(_) >> Optional.of(customer)
        BookDto bookDto = new BookDto(65, "JavaScript", "Axel Rauschmayer", 460, "Like it or not", new Date(), 1232.0, 2);
        List<BookDto> bookDtoList1 = new ArrayList<>(Arrays.asList(bookDto))
        OrderRequest orderRequest = new OrderRequest(1, "hgfhsdfg", "3465245", date, bookDtoList1, 1)
        orderRepository.save(_) >> new Order(1, "hgfhsdfg", "3465245", date, 2464.0,
                new ArrayList<>(Arrays.asList(new OrderBook(1, 65, 1, 2))), customer)

        when:
        def result = orderService.PlacingOrder(orderRequest)
        then:
        result == "Placed Order"

    }

    def "Testing placing order when Customer is not valid"() {
        given:
        customerRepository.findByCustomerId(_) >> Optional.empty()
        Date date = new Date();
        BookDto bookDto = new BookDto(65, "JavaScript", "Axel Rauschmayer", 460, "Like it or not", new Date(), 1232.0, 2);
        List<BookDto> bookDtoList1 = new ArrayList<>(Arrays.asList(bookDto))
        OrderRequest orderRequest = new OrderRequest(1, "hgfhsdfg", "3465245", date, bookDtoList1, 1)

        when:
        orderService.PlacingOrder(orderRequest)
        then:
        thrown(DataNotFoundException)
    }

    def "Testing OrderBookList with Given valid input"() {
        given:
        BookDto bookDto = new BookDto(65, "JavaScript", "Axel Rauschmayer", 460, "Like it or not", new Date(), 1232.0, 2);
        List<BookDto> bookDtoList1 = new ArrayList<>(Arrays.asList(bookDto))
        OrderRequest orderRequest = new OrderRequest(1, "hgfhsdfg", "3465245", new Date(), bookDtoList1, 1)
        List<OrderBook> orderBookList = new ArrayList<>(Arrays.asList(new OrderBook(1, 65, 1, 2)));
        when:
        def result = orderService.getOrderBookList(orderRequest);
        then:
        result.get(0) == new OrderBook(null, 65, null, 2)
    }

    def "Testing orderBookList when order book list is empty"() {
        given:
        OrderRequest orderRequest = new OrderRequest(1, "hgfhsdfg", "3465245", new Date(), new ArrayList<BookDto>(), 1)

        when:
        orderService.getOrderBookList(orderRequest)
        then:
        thrown(DataNotFoundException)
    }


    def "testing getOrder Details  with a valid orderId"() {
        given:
        Integer orderId = 1
        Date date = new Date()
        Customer customer = new Customer(1, "Edin", "yghkjas", "265317563")
        Order order = new Order(1, "hgfhsdfg", "3465245", date, 2464.0,
                new ArrayList<>(Arrays.asList(new OrderBook(1, 65, 1, 2))), customer)

        orderRepository.findByOrderId(orderId) >> Optional.of(order)
        bookRepository.findAllById(_) >> new ArrayList<Book>(Arrays.asList(new Book(65, "JavaScript", "Alex", 234, "hfdbsfjh",
                date, 234.0)))

        OrderResponse orderResponse = new OrderResponse(1, "hgfhsdfg", "3465245", date, 2464.0,
                new ArrayList<OrderBookVo>(Arrays.asList(new OrderBookVo(65, "JavaScript", 2))))
        when:
        def result = orderService.getOrderDetails(orderId)
        then:

        result.equals(orderResponse) == true
    }

    def "testing getOrder Details  with a invalid orderId"() {
        given:
        Integer orderId = 1
        orderRepository.findByOrderId(orderId) >> Optional.empty()
        when:
        def result = orderService.getOrderDetails(orderId)
        then:
        thrown(DataNotFoundException)
    }

    def "Testing getOrderResponseFromOrder when bookId's are valid"() {
        given:
        Date date = new Date()
        Customer customer = new Customer(1, "Edin", "yghkjas", "265317563")
        Order order = new Order(1, "hgfhsdfg", "3465245", date, 690.0,
                new ArrayList<>(Arrays.asList(new OrderBook(1, 65, 1, 2),
                        new OrderBook(2, 67, 1, 1))), customer)
        bookRepository.findAllById(_) >> (new ArrayList<Book>(
                Arrays.asList(new Book(65, "JavaScript", "Alex", 234, "hfdbsfjh", date, 234.0),
                        new Book(67, "Technology", "hdgfhd", 562, "hjsdf", date, 222.0))))
        OrderResponse orderResponse = new OrderResponse(1, "hgfhsdfg", "3465245", date, 690.0,
                new ArrayList<OrderBookVo>(Arrays.asList(new OrderBookVo(65, "JavaScript", 2),
                        new OrderBookVo(67, "Technology", 1))))
        when:
        def result = orderService.getOrderResponseFromOrder(order)
        then:
        result.equals(orderResponse) == true
    }

    def "Testing getOrderResponseFromOrder when bookId's are not valid"() {
        given:
        Date date = new Date()
        Customer customer = new Customer(1, "Edin", "yghkjas", "265317563")
        Order order = new Order(1, "hgfhsdfg", "3465245", date, 690.0,
                new ArrayList<>(Arrays.asList(new OrderBook(1, 65, 1, 2),
                        new OrderBook(2, 67, 1, 1))), customer)
        List<Book> bookList = new ArrayList<Book>()
        bookRepository.findAllById(_) >> bookList
        when:
        orderService.getOrderResponseFromOrder(order)
        then:
        thrown(DataNotFoundException)
    }

    def "test CalculatetotalPrice when inputs are valid integers"() {
        given:
        BookDto bookDto = new BookDto(65, "JavaScript", "Axel Rauschmayer", 460, "Like it or not", new Date(), 1232.0, 2);
        List<BookDto> bookDtoList1 = new ArrayList<>(Arrays.asList(bookDto))

        when:
        def result = orderService.calculateTotalPrice(bookDtoList1)
        then:
        result == 2464.0
    }

    def "test CalculatetotalPrice when price is  0"() {
        given:
        BookDto bookDto = new BookDto(65, "JavaScript", "Axel Rauschmayer", 460,
                "Like it or not", new Date(), 0.0, 2);
        List<BookDto> bookDtoList1 = new ArrayList<>(Arrays.asList(bookDto))

        when:
        def result = orderService.calculateTotalPrice(bookDtoList1)
        then:
        result == 0
    }
/*
    def "test calculatetotalPrice when price or quantity contain null"() {
        given:

        BookDto bookDto = new BookDto(65, "JavaScript", "Axel Rauschmayer", 460,
                "Like it or not", new Date(), Double.NaN, 2);
        List<BookDto> bookDtoList1 = new ArrayList<>(Arrays.asList(bookDto))
        when:
        orderService.calculateTotalPrice(bookDtoList1)
        then:
        thrown(DataNotFoundException)
    }
*/
    def "test getAllOrderDetailsOfCustomer when customerID is valid"() {
        given:
        Integer customerId = 1
        Date date = new Date()
        Customer customer = new Customer(1, "Edin", "yghkjas", "265317563")
        Order order = new Order(1, "hgfhsdfg", "3465245", date, 2464.0,
                new ArrayList<>(Arrays.asList(new OrderBook(1, 65, 1, 2))), customer)
        bookRepository.findAllById(_) >> (new ArrayList<Book>(
                Arrays.asList(new Book(65, "JavaScript", "Alex", 234, "hfdbsfjh", date, 1232.0))))

        List<Order> orderList = new ArrayList<>(Arrays.asList(order))
        OrderResponse orderResponse = new OrderResponse(1, "hgfhsdfg", "3465245", date, 2464.0,
                new ArrayList<OrderBookVo>(Arrays.asList(new OrderBookVo(65, "JavaScript", 2))))
        List<OrderResponse> orderResponseList = new ArrayList<>(Arrays.asList(orderResponse))

        orderRepository.findAllByCustomerId(customerId) >> orderList
        when:
        def result = orderService.getAllOrderDetailsOfCustomer(1)

        then:
        result.equals(orderResponseList)
    }

    def "test getAllOrderDetailsOfCustomer when customerID is returns no Order Details "() {
        given:
        Integer customerId = 1
        Date date = new Date()
        Customer customer = new Customer(1, "Edin", "yghkjas", "265317563")
        Order order = new Order(1, "hgfhsdfg", "3465245", date, 2464.0,
                new ArrayList<>(Arrays.asList(new OrderBook(1, 65, 1, 2))), customer)
        List<Order> orderList = new ArrayList<Order>()
        orderRepository.findAllByCustomerId(customerId) >> orderList
        when:
        orderService.getAllOrderDetailsOfCustomer(customerId)
        then:
        thrown(DataNotFoundException)
    }
    /*

    def "testingDeleteOrder with a valid Order id"() {
        given:
        Integer orderId = 1
        Date date = new Date()
        Customer customer = new Customer(1, "Edin", "yghkjas", "265317563")
        Order order = new Order(1, "hgfhsdfg", "3465245", date, 2464.0,
                new ArrayList<>(Arrays.asList(new OrderBook(1, 65, 1, 2))), customer)
        orderRepository.findByOrderId(orderId) >> Optional.of(order)
        orderRepository.delete(_) >> void
        when:
        def result = orderService.deleteOrder(1)

        then:
        result == "Deleted Successfully"
    }

     */


    def "testingDeleteOrder with a invalid order id"() {
        given:
        Integer orderId = 1
        orderRepository.findByOrderId(_) >> Optional.empty()
        when:
        orderService.deleteOrder(1)
        then:
        thrown(DataNotFoundException)
    }

    def "Updating Order with a valid order id and order response"() {
        given:
        Date date = new Date()
        Customer customer = new Customer(1, "Edin", "yghkjas", "265317563")
        BookDto bookDto = new BookDto(65, "JavaScript", "Axel Rauschmayer", 460, "Like it or not", date, 1232.0, 2);
        List<BookDto> bookDtoList1 = new ArrayList<>(Arrays.asList(bookDto))
        OrderRequest orderRequest = new OrderRequest(1, "hgfhsdfg", "3465245", date, bookDtoList1, 1)
        Order order = new Order(1, "hgfhsdfg", "3465245", date, 2464.0,
                new ArrayList<>(Arrays.asList(new OrderBook(1, 65, 1, 2))), customer)
        orderRepository.findByOrderId(_) >> Optional.of(order)
        customerRepository.findByCustomerId(orderRequest.getCustomerId()) >> Optional.of(customer)
        orderRepository.save(order) >> order
        when:
        def result = orderService.updatingOrder(orderRequest)
        then:
        result == "Updated your Order!!"
    }

    def "Updating Order with a invalid order id "() {

        given:

        Date date = new Date()
        Customer customer = new Customer(1, "Edin", "yghkjas", "265317563")
        BookDto bookDto = new BookDto(65, "JavaScript", "Axel Rauschmayer", 460, "Like it or not", date, 1232.0, 2);
        List<BookDto> bookDtoList1 = new ArrayList<>(Arrays.asList(bookDto))
        OrderRequest orderRequest = new OrderRequest(1, "hgfhsdfg", "3465245", date, bookDtoList1, 1)
        orderRepository.findByOrderId(_) >> Optional.empty()
        when:
        def result = orderService.updatingOrder(orderRequest)

        then:
        thrown(DataNotFoundException)

    }

    def "Updating Order with a valid order id  and customer id is no more valid or not found"() {
        given:
        Date date = new Date()
        Customer customer = new Customer(1, "Edin", "yghkjas", "265317563")
        BookDto bookDto = new BookDto(65, "JavaScript", "Axel Rauschmayer", 460, "Like it or not", date, 1232.0, 2);
        List<BookDto> bookDtoList1 = new ArrayList<>(Arrays.asList(bookDto))
        OrderRequest orderRequest = new OrderRequest(1, "hgfhsdfg", "3465245", date, bookDtoList1, 2)
        Order order = new Order(1, "hgfhsdfg", "3465245", date, 2464.0,
                new ArrayList<>(Arrays.asList(new OrderBook(1, 65, 1, 2))), customer)

        orderRepository.findByOrderId(_) >> Optional.of(order)
        customerRepository.findByCustomerId(orderRequest.getCustomerId()) >> Optional.empty()

        when:
        orderService.updatingOrder(orderRequest)
        then:
        thrown(DataNotFoundException)
    }
}
