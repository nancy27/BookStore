package com.online.BookStore.repository;

import com.online.BookStore.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
    Optional<Order> findByOrderId(Integer orderId);

    List<Order> findAllByCustomerId(Integer customerId);
}
