package com.techlab.ecommerce.exception;

public class ProductoNoEncontradoException extends TechlabEcommerceException {

  public ProductoNoEncontradoException(String searchTerm) {
    super(String.format("No se encontró ningún producto con el siguiente término: %s", searchTerm));
  }
}