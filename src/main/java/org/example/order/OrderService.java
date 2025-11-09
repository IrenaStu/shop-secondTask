package org.example.order;

import lombok.RequiredArgsConstructor;
import org.example.product.Product;
import org.example.product.ProductService;
import org.example.user.Customer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

@RequiredArgsConstructor
public class OrderService {
  private final BlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();
  private final AtomicLong orderIdGenerator = new AtomicLong(1000);
  private final ProductService productService;

  public void placeOrder(Customer customer) {
    Random random = new Random();
    List<Product> catalog = productService.getCatalog();
    Map<Product, Integer> orderItems = new HashMap<>();
    int itemCount = random.nextInt(3) + 1;

    for (int i = 0; i < itemCount; i++) {
      Product product = catalog.get(random.nextInt(catalog.size()));
      int quantity = random.nextInt(3) + 1;
      orderItems.merge(product, quantity, Integer::sum);
    }

    Order order =
        Order.builder()
            .orderId(orderIdGenerator.incrementAndGet())
            .customer(customer)
            .orderItems(orderItems)
            .build();

    order.calculateTotalPrice();
    orderQueue.offer(order);
    System.out.println(customer + " placed " + order);
  }

  public BlockingQueue<Order> getOrderQueue() {
    return orderQueue;
  }
}
