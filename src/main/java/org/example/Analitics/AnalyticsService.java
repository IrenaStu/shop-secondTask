package org.example.Analitics;

import org.example.order.Order;
import org.example.product.Product;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AnalyticsService {

    public void runAnalytics(List<Order> processedOrders) {
        System.out.println("\n=== Analytics ===");
        System.out.println("Total orders: " + processedOrders.size());

        double totalProfit = processedOrders.parallelStream()
                .mapToDouble(Order::getTotalPrice)
                .sum();
        System.out.printf("Total profit: %.2f $\n", totalProfit);

        Map<Product, Integer> productSales = processedOrders.parallelStream()
                .flatMap(order -> order.getOrderItems().entrySet().stream())
                .collect(Collectors.groupingBy(Map.Entry::getKey,
                        Collectors.summingInt(Map.Entry::getValue)));

        productSales.entrySet().stream()
                .sorted(Map.Entry.<Product, Integer>comparingByValue().reversed())
                .limit(3)
                .forEach(entry -> System.out.println(entry.getKey().getProductName() + " (" + entry.getValue() + " units)"));
    }

}
