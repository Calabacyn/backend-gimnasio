package com.backend_gimnasio.backend_gimnasio.exceptions;

public class ProviderNotFoundException extends RuntimeException {

  public ProviderNotFoundException() {
    super("Proveedor no encontrado.");
  }

  public ProviderNotFoundException(Long id) {
    super("Proveedor no encontrado con id: " + id);
  }
}
