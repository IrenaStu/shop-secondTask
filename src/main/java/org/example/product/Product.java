package org.example.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.werahouse.WearHouseStock;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private int productId;
    private  String productName;
    private String productBrand;
    private String productSku;
    private String productCategory;
    private WearHouseStock stock;
    private double price;
}
