package com.kvales.products.domain.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stockQuantity;
    private boolean active;

    public Product(
            Long id,
            String name,
            String description,
            BigDecimal price,
            Integer stockQuantity,
            boolean active
    ) {
        validateName(name);
        validatePrice(price);
        validateStockQuantity(stockQuantity);

        this.id = id;
        this.name = name.trim();
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.active = active;
    }

    public void updateDetails(
            String name,
            String description,
            BigDecimal price
    ) {
        validateName(name);
        validatePrice(price);

        this.name = name.trim();
        this.description = description;
        this.price = price;
    }

    public void updateStock(Integer stockQuantity) {
        validateStockQuantity(stockQuantity);
        this.stockQuantity = stockQuantity;
    }

    public void activate() {
        this.active = true;
    }

    public void deactivate() {
        this.active = false;
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                    "Product name cannot be null or blank"
            );
        }
    }

    private void validatePrice(BigDecimal price) {
        if (price == null || price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(
                    "Product price cannot be null or negative"
            );
        }
    }

    private void validateStockQuantity(Integer stockQuantity) {
        if (stockQuantity == null || stockQuantity < 0) {
            throw new IllegalArgumentException(
                    "Stock quantity cannot be null or negative"
            );
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof Product product)) {
            return false;
        }

        return id != null && Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
