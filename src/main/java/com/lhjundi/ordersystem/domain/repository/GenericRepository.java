package com.lhjundi.ordersystem.domain.repository;

import java.util.List;
import java.util.Optional;

public interface GenericRepository <T, K>{

    void save(T type);

    Optional<T> findById(K key);

    List<T> findAll();

}
