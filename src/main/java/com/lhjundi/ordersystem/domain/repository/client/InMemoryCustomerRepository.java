package com.lhjundi.ordersystem.domain.repository.client;

import com.lhjundi.ordersystem.domain.model.Customer;

import java.util.*;

public class InMemoryCustomerRepository implements CustomerRepository {
    private final Map<String, Customer> customers = new HashMap<>();

    @Override
    public void save(Customer customer) {
        customers.put(customer.getId(), customer);
    }

    @Override
    public Optional<Customer> findById(String customerId) {
        return Optional.ofNullable(customers.get(customerId));
    }

    @Override
    public List<Customer> findAll() {
        return new ArrayList<>(customers.values());
    }
}
