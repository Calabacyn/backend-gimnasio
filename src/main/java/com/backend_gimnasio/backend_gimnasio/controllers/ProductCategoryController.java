package com.backend_gimnasio.backend_gimnasio.controllers;

import com.backend_gimnasio.backend_gimnasio.exceptions.ProductCategoryNotFoundException;
import com.backend_gimnasio.backend_gimnasio.model.dtos.ProductCategoryDTO;
import com.backend_gimnasio.backend_gimnasio.services.interfaces.IProductCategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-categories")
public class ProductCategoryController {

    private final IProductCategoryService productCategoryService;

    public ProductCategoryController(IProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @GetMapping
    public List<ProductCategoryDTO> getAll() {
        return productCategoryService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductCategoryDTO> getBy(@PathVariable Long id) {
        ProductCategoryDTO category = productCategoryService.getBy(id)
                .orElseThrow(() -> new ProductCategoryNotFoundException(id));
        return ResponseEntity.ok(category);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid ProductCategoryDTO category) {
        productCategoryService.create(category);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Valid ProductCategoryDTO category) {
        productCategoryService.update(category);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        productCategoryService.delete(id);
    }
}
