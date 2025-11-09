package org.example.product;

import lombok.Builder;
import lombok.Getter;
import org.example.werahouse.WearHouseService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Getter
public class ProductService {
  private final WearHouseService wearHouse;
  private final List<Product> productCatalog = Collections.synchronizedList(new ArrayList<>());
  private final AtomicLong productIdGenerator = new AtomicLong(1);

  public ProductService(WearHouseService warehouseService) {
    this.wearHouse = warehouseService;
  }

  public Product createProduct(String productName, double price, Integer productQuantity) {
    Product product = new Product();
    product.setProductId(productIdGenerator.incrementAndGet());
    product.setProductName(productName);
    if (productQuantity <= 0) {
      product.setStock(Stock.OUTOFSTOCK);
    }
    product.setStock(Stock.INSTOCK);
    product.setQuantity(productQuantity);
    product.setPrice(price);

    wearHouse.addProductToWarehouse(product, productQuantity);
    productCatalog.add(product);
    return product;
  }

  public List<Product> getCatalog() {
    return productCatalog;
  }
}
