package com.techlab.ecommerce.exception;

public class PedidoNoEncontradoException extends TechlabEcommerceException {

  public PedidoNoEncontradoException(Long id) {
    super(String.format("No se encontró ningún pedido con ID: %d", id));
  }
}