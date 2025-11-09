package org.example.product;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private  Long productId;
    private  String productName;
    private Stock stock;
    private  volatile Integer quantity;
    private double price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return Objects.equals(productId, product.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(productId);
    }

    @Override
    public String toString() {

        return
               "{"+
                ", productId: " + productId +
                ", productName: '" + productName + '\n' +
                        "price: " + price +
                        ", " + (quantity > 0
                        ? "quantityInStock: " + quantity
                        : stock) +"}";
    }
}
