package com.lhjundi.ordersystem.domain.model;

import java.time.LocalDateTime;
import java.util.Objects;

public final class Customer {
    private final String id;
    private final String name;
    private final String email;
    private final String document;
    private final LocalDateTime createdAt;

    public Customer(String id, String name, String email, String document) {
        validateId(id);
        validateName(name);
        validateEmail(email);
        validateDocument(document);

        this.id = id;
        this.name = name;
        this.email = email;
        this.document = document;
        this.createdAt = LocalDateTime.now();
    }

    private void validateId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer ID cannot be empty");
        }
    }

    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be empty");
        }
    }

    private void validateEmail(String email) {
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("Invalid email format");
        }
    }

    private void validateDocument(String document) {
        if (document == null || document.trim().isEmpty()) {
            throw new IllegalArgumentException("Document cannot be empty");
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDocument() {
        return document;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return STR."Customer{id='\{id}\{'\''}, name='\{name}\{'\''}, email='\{email}\{'\''}, document='\{document}\{'\''}, createdAt=\{createdAt}\{'}'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}