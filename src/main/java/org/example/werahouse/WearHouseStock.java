package org.example.werahouse;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
public class WearHouseStock {
    private final ConcurrentMap<Long, Integer> stockMap = new ConcurrentHashMap<>();
}
