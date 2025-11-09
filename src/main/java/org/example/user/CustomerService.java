package org.example.user;

import lombok.Builder;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Builder
public class CustomerService {

    private final AtomicLong customerIdGenerator = new AtomicLong(1);
    private final List<Customer> customers = new CopyOnWriteArrayList<>();

    public Customer createCustomer(String firstName, String lastName, String email) {
        Customer customer = new Customer(
                customerIdGenerator.getAndIncrement(),
                firstName,
                lastName,
                email
        );
        customers.add(customer);
        return customer;
    }

    public List<Customer> getAllCustomers() {
        return customers;
    }

}
