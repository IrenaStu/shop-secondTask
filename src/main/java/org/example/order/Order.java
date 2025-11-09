package org.example.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.product.Product;
import org.example.user.Customer;

import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Order {
    private Long orderId;
    private Customer customer;
    private Map<Product, Integer> orderItems;
    private Double totalPrice;

    public void calculateTotalPrice() {
        this.totalPrice = orderItems.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }


    @Override
    public String toString() {
        String items = orderItems.entrySet().stream()
                .map(e -> e.getKey().getProductName() + " x" + e.getValue())
                .collect(Collectors.joining(", "));
        return "Order #" + orderId + " by " + customer +
                " | Items: [" + items + "]" +
                " | Total: $" + String.format("%.2f", totalPrice != null ? totalPrice : 0.0);
    }



}
