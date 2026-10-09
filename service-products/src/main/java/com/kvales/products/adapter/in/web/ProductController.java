
package com.kvales.products.adapter.in.web;

import com.kvales.products.adapter.in.web.request.CreateProductRequest;
import com.kvales.products.adapter.in.web.request.UpdateProductRequest;
import com.kvales.products.adapter.in.web.response.ProductResponse;
import com.kvales.products.application.port.in.CreateProductUseCase;
import com.kvales.products.application.port.in.FindAllProductsUseCase;
import com.kvales.products.application.port.in.FindProductByIdUseCase;
import com.kvales.products.application.port.in.UpdateProductUseCase;
import com.kvales.products.domain.model.Product;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final CreateProductUseCase createProductUseCase;
    private final FindProductByIdUseCase findProductByIdUseCase;
    private final FindAllProductsUseCase findAllProductsUseCase;
    private final UpdateProductUseCase updateProductUseCase;

    public ProductController(
            CreateProductUseCase createProductUseCase,
            FindProductByIdUseCase findProductByIdUseCase,
            FindAllProductsUseCase findAllProductsUseCase,
            UpdateProductUseCase updateProductUseCase
    ) {
        this.createProductUseCase = createProductUseCase;
        this.findProductByIdUseCase = findProductByIdUseCase;
        this.findAllProductsUseCase = findAllProductsUseCase;
        this.updateProductUseCase = updateProductUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(
            @Valid @RequestBody CreateProductRequest request
    ) {
        Product product = new Product(
                null,
                request.name(),
                request.description(),
                request.price(),
                request.stockQuantity(),
                true
        );

        return ProductResponse.from(
                createProductUseCase.createProduct(product)
        );
    }

    @GetMapping("/{id}")
    public ProductResponse findProductById(@PathVariable Long id) {
        return ProductResponse.from(
                findProductByIdUseCase.findProductById(id)
        );
    }

    @GetMapping
    public List<ProductResponse> findAllProducts() {
        return findAllProductsUseCase.findAllProducts()
                .stream()
                .map(ProductResponse::from)
                .toList();
    }

    @PutMapping("/{id}")
    public ProductResponse updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody UpdateProductRequest request
    ) {
        Product product = new Product(
                id,
                request.name(),
                request.description(),
                request.price(),
                request.stockQuantity(),
                true
        );

        return ProductResponse.from(
                updateProductUseCase.updateProduct(id, product)
        );
    }
}