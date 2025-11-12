package com.backend_gimnasio.backend_gimnasio.exceptions;

public class InvoiceNotFoundException extends RuntimeException {

  public InvoiceNotFoundException(Long id) {
    super("Invoice not found with id: " + id);
  }

  public InvoiceNotFoundException() {
    super("Invoice not found. ");
  }
}