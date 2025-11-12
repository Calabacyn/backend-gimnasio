package com.backend_gimnasio.backend_gimnasio.exceptions;



public class ProductCategoryNotFoundException extends RuntimeException {

    public ProductCategoryNotFoundException() {
        super("Product category not found.");
    }

    public ProductCategoryNotFoundException(Long id) {
        super("Product category not found with ID: " + id);
    }
}
