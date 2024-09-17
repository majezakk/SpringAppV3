package com.example.SpringAppV3.service;

import com.example.SpringAppV3.model.OrderModel;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<OrderModel> getAllOrders();
    Optional<OrderModel> getOrderById(Long id);
    OrderModel saveOrder(OrderModel order);
    void deleteOrder(Long id);
}