package org.example;

import org.example.Analitics.AnalyticsService;
import org.example.concurency.CustomerShopping;
import org.example.concurency.ProcessingOrder;
import org.example.order.Order;
import org.example.order.OrderService;
import org.example.product.ProductService;
import org.example.user.Customer;
import org.example.user.CustomerService;
import org.example.werahouse.WearHouseService;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
  public static void main(String[] args) throws InterruptedException {
    WearHouseService warehouse = new WearHouseService();
    ProductService productService = new ProductService(warehouse);
    CustomerService customerService = CustomerService.builder().build();
    OrderService orderService = new OrderService(productService);

    productService.createProduct("Laptop", 1200.00, 400);
    productService.createProduct("Mouse", 50.00, 500);
    productService.createProduct("Keyboard", 100.00, 80);
    productService.createProduct("Headphones", 150, 800);
    productService.createProduct("Tv", 1500.00, 160);
    productService.createProduct("Mixer", 80.00, 300);
    productService.createProduct("Blender", 160.00, 200);
    productService.createProduct("Smartphones", 1900.00, 350);
    System.out.println("Productcatalog: " + productService.getProductCatalog());
    System.out.println("                  ");

    Customer irena = customerService.createCustomer("Irena", "Sturua", "Irena@example.com");
    Customer nino = customerService.createCustomer("Nino", "Ordenidze", "nino@example.com");
    Customer gegi = customerService.createCustomer("Gegi", "Salukvadze", "Gegi@example.com");
    Customer maria = customerService.createCustomer("Maria", "Abirjania", "maria@example.com");
    Customer ksenya = customerService.createCustomer("Ksenya", "Fodorova", "Ksena@example.com");

    List<Customer> customers = customerService.getAllCustomers();
    CopyOnWriteArrayList<Order> processedOrders = new CopyOnWriteArrayList<>();
    ExecutorService customerPool = Executors.newFixedThreadPool(5);
    ExecutorService orderPool = Executors.newFixedThreadPool(3);

    // Submit customer tasks
    for (Customer customer : customers) {
      customerPool.submit(new CustomerShopping(customer, orderService));
    }

    // Submit worker tasks
    for (int i = 1; i <= 3; i++) {
      orderPool.submit(
          new ProcessingOrder("Worker-" + i, orderService, warehouse, processedOrders));
    }

    // Shutdown customer pool after tasks
    customerPool.shutdown();
    customerPool.awaitTermination(5, TimeUnit.SECONDS);

    // Let workers finish processing
    Thread.sleep(3000);
    orderPool.shutdownNow();

    // Run analytics
    new AnalyticsService().runAnalytics(processedOrders);
  }
}
