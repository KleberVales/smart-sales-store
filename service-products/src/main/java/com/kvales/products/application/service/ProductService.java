
package com.kvales.products.application.service;

import com.kvales.products.application.port.in.CreateProductUseCase;
import com.kvales.products.application.port.in.FindAllProductsUseCase;
import com.kvales.products.application.port.in.FindProductByIdUseCase;
import com.kvales.products.application.port.in.UpdateProductUseCase;
import com.kvales.products.application.port.out.ProductRepositoryPort;
import com.kvales.products.domain.exception.ProductNotFoundException;
import com.kvales.products.domain.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements
        CreateProductUseCase,
        FindProductByIdUseCase,
        FindAllProductsUseCase,
        UpdateProductUseCase {

    private final ProductRepositoryPort productRepositoryPort;

    public ProductService(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    @Override
    public Product createProduct(Product product) {
        return productRepositoryPort.save(product);
    }

    @Override
    public Product findProductById(Long id) {
        return productRepositoryPort.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepositoryPort.findAll();
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Product existingProduct = findProductById(id);

        existingProduct.updateDetails(
                product.getName(),
                product.getDescription(),
                product.getPrice()
        );

        existingProduct.updateStock(product.getStockQuantity());

        return productRepositoryPort.save(existingProduct);
    }
}