
package com.kvales.products.application.port.in;

import com.kvales.products.domain.model.Product;

import java.util.List;

public interface FindAllProductsUseCase {

    List<Product> findAllProducts();
}