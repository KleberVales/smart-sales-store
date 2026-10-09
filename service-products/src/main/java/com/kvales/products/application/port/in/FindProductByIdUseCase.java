
package com.kvales.products.application.port.in;

import com.kvales.products.domain.model.Product;

public interface FindProductByIdUseCase {

    Product findProductById(Long id);
}