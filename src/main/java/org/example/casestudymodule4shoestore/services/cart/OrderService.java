package org.example.casestudymodule4shoestore.services.cart;

import org.example.casestudymodule4shoestore.models.Order;
import org.example.casestudymodule4shoestore.models.OrderItem;
import org.example.casestudymodule4shoestore.repositories.cart.IOrderItemRepository;
import org.example.casestudymodule4shoestore.repositories.cart.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private IOrderRepository orderRepository;
    @Autowired
    private IOrderItemRepository orderItemRepository;

    public Order saveOrder(Order order){
        return orderRepository.save(order);
    }
    public void saveOrderItem(List<OrderItem> orderItem){
        orderItemRepository.saveAll(orderItem);
    }

}
