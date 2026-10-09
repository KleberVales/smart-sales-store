package com.kvales.products.application.port.in;

import com.kvales.products.domain.model.Product;

public interface CreateProductUseCase {

    Product createProduct(Product product);
}
