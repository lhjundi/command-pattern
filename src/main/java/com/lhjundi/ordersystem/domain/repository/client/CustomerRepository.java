package com.lhjundi.ordersystem.domain.repository.client;

import com.lhjundi.ordersystem.domain.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {
    void save(Customer customer);
    Optional<Customer> findById(String customerId);
    List<Customer> findAll();
}
