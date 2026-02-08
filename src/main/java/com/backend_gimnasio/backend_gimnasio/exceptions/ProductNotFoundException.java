package com.backend_gimnasio.backend_gimnasio.exceptions;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException() {
        super("Product not found.");
    }

    public ProductNotFoundException(Long id) {
        super("Product with ID " + id + " not found.");
    }
}
