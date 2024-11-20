package com.lhjundi.ordersystem.domain.repository;

import java.util.List;
import java.util.Optional;

public sealed interface GenericRepository<T, K> permits InMemoryCustomerRepository, InMemoryOrderRepository {

    void save(T type);

    Optional<T> findById(K key);

    List<T> findAll();

}
