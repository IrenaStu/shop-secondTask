package org.example.order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.product.Product;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    private long orderItemId;
    private Product product;
    private int productNumber;
    private double totalPrice;



    public void setTotalPrice(){
        totalPrice = productNumber * product.getPrice();
    }
}
