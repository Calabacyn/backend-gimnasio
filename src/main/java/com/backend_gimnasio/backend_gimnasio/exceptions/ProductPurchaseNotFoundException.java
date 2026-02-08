package com.backend_gimnasio.backend_gimnasio.exceptions;

public class ProductPurchaseNotFoundException extends RuntimeException {

  public ProductPurchaseNotFoundException() {
    super("Product purchase not found");
  }

  public ProductPurchaseNotFoundException(Long id) {
    super("Product purchase with ID " + id + " not found");
  }
}
