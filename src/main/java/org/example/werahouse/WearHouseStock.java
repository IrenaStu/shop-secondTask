package org.example.werahouse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.product.Product;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
@Getter
@Setter
@NoArgsConstructor
public class WearHouseStock {
    private final ConcurrentMap<Product, Integer> stockMap = new ConcurrentHashMap<>();
}
