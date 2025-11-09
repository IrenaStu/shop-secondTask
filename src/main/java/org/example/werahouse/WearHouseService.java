package org.example.werahouse;

import lombok.Getter;

import org.example.product.Product;
import org.example.product.Stock;

@Getter
public class WearHouseService {

  private final WearHouseStock wearHouseStock = new WearHouseStock();

  public void addProductToWarehouse(Product product, int quantity) {
    wearHouseStock.getStockMap().merge(product, quantity, Integer::sum);
  }

  public int getStock(Product product) {
    return wearHouseStock.getStockMap().getOrDefault(product, 0);
  }

  public void reduceStock(Product product, int quantity) {
    wearHouseStock
        .getStockMap()
        .compute(
            product,
            (p, q) -> {
              if (q == null) {
                throw new RuntimeException(
                    "Product " + p.getProductName() + " not found in warehouse");
              }
              if (q < quantity) {
                throw new RuntimeException(
                    "Not enough stock for product "
                        + p.getProductName()
                        + ": requested "
                        + quantity
                        + ", available "
                        + q);
              }

              return q - quantity;
            });
    updateProductQuantity(product);
  }

  private void updateProductQuantity(Product product) {
    Integer newQuantity = wearHouseStock.getStockMap().get(product);
    if (newQuantity != null) {
      product.setQuantity(newQuantity);
      product.setStock(newQuantity > 0 ? Stock.INSTOCK : Stock.OUTOFSTOCK);
    }
  }
}
