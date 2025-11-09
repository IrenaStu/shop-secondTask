package org.example.concurency;

import lombok.RequiredArgsConstructor;
import org.example.order.OrderService;
import org.example.product.Product;
import org.example.product.ProductService;
import org.example.user.Customer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

@RequiredArgsConstructor

public class CustomerShopping implements Runnable{
  private final Customer customer;
  private final OrderService orderService;



    @Override
    public void run() {

        orderService.placeOrder(customer);

}
}
