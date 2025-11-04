package org.example.order;

import org.example.user.User;

import java.util.Map;


public class Order {
    private Long orderId;
    private User user;
    private Map<Long, OrderItem> orderItems;

}
