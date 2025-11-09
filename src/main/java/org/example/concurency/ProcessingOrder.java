package org.example.concurency;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.order.Order;
import org.example.order.OrderService;
import org.example.product.Product;
import org.example.werahouse.WearHouseService;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@AllArgsConstructor

public class ProcessingOrder implements  Runnable {
    private final String workerName;
    private final OrderService orderService;
    private final WearHouseService warehouse;
    private final CopyOnWriteArrayList<Order> processedOrders;

    @Override
    public void run() {
        try {
            while (true) {
                Order order = orderService.getOrderQueue().take();

                StringBuilder log = new StringBuilder();
                log.append("==").append(workerName).append(" processing ").append(order).append("\n")
                        .append("Updated stock after processing:\n");

                order.getOrderItems().forEach((product, orderedQty) -> {
                    warehouse.reduceStock(product, orderedQty);
                    log.append(String.format("-> %s: ordered %d, remaining stock: %d%n",
                            product.getProductName(), orderedQty, product.getQuantity()));
                });

                processedOrders.add(order);
                log.append("===================================\n");

                System.out.print(log);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }
}
