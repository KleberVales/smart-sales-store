package com.kvales.products.application.port.in;

import com.kvales.products.domain.model.Product;

public interface UpdateProductUseCase {

    Product updateProduct(Long id, Product product);
}