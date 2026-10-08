package com.kvales.customers.adapter.in.web;

import com.kvales.customers.application.dto.CreateCustomerRequest;
import com.kvales.customers.application.dto.CustomerResponse;
import com.kvales.customers.application.dto.UpdateCustomerRequest;
import com.kvales.customers.application.usecase.CreateCustomerUseCase;
import com.kvales.customers.application.usecase.DeleteCustomerUseCase;
import com.kvales.customers.application.usecase.FindCustomerUseCase;
import com.kvales.customers.application.usecase.UpdateCustomerUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CreateCustomerUseCase createCustomerUseCase;
    private final FindCustomerUseCase findCustomerUseCase;
    private final UpdateCustomerUseCase updateCustomerUseCase;
    private final DeleteCustomerUseCase deleteCustomerUseCase;

    @PostMapping
    public ResponseEntity<CustomerResponse> create(
            @RequestBody CreateCustomerRequest request
    ) {


        CustomerResponse response =
                createCustomerUseCase.execute(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> findById(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                findCustomerUseCase.findById(id)
        );
    }

    @GetMapping
    public ResponseEntity<CustomerResponse> findByEmail(
            @RequestParam String email
    ) {

        return ResponseEntity.ok(
                findCustomerUseCase.findByEmail(email)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> update(
            @PathVariable Long id,
            @RequestBody UpdateCustomerRequest request
    ) {

        CustomerResponse response =
                updateCustomerUseCase.execute(id, request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {

        deleteCustomerUseCase.execute(id);
    }
}
