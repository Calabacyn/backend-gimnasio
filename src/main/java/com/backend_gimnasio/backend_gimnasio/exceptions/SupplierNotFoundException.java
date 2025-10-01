package com.backend_gimnasio.backend_gimnasio.exceptions;

public class SupplierNotFoundException extends RuntimeException {
    public SupplierNotFoundException(Long id) {
        super("Supplier not found with ID: " + id);
    }
}
