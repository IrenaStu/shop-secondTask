package org.example.shop;

import org.example.product.Product;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Shop {
    private Long shopId;
    private String shopName;
    private final ConcurrentMap<Long, Product> productCatalog = new ConcurrentHashMap<>();


}
