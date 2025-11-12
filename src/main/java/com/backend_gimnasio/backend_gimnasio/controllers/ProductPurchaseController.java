package com.backend_gimnasio.backend_gimnasio.controllers;

import com.backend_gimnasio.backend_gimnasio.exceptions.ProductPurchaseNotFoundException;
import com.backend_gimnasio.backend_gimnasio.model.dtos.ProductPurchaseDTO;
import com.backend_gimnasio.backend_gimnasio.services.interfaces.IProductPurchaseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-purchases")
public class ProductPurchaseController {

    private final IProductPurchaseService productPurchaseService;

    public ProductPurchaseController(IProductPurchaseService productPurchaseService) {
        this.productPurchaseService = productPurchaseService;
    }

    @GetMapping
    public List<ProductPurchaseDTO> getAll() {
        return productPurchaseService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductPurchaseDTO> getById(@PathVariable Long id) {
        ProductPurchaseDTO productPurchase = productPurchaseService.getById(id)
                .orElseThrow(() -> new ProductPurchaseNotFoundException(id));
        return ResponseEntity.ok(productPurchase);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid ProductPurchaseDTO productPurchase) {
        productPurchaseService.create(productPurchase);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Valid ProductPurchaseDTO productPurchase) {
        productPurchaseService.update(productPurchase);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        productPurchaseService.delete(id);
    }
}
