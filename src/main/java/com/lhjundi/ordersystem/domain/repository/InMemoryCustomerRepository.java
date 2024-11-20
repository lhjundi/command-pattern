package com.lhjundi.ordersystem.domain.repository;

import com.lhjundi.ordersystem.domain.model.Customer;

import java.util.*;

public final class InMemoryCustomerRepository implements GenericRepository<Customer, String> {

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
