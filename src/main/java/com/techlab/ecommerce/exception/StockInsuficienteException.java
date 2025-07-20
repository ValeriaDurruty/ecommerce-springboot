package com.techlab.ecommerce.exception;

public class StockInsuficienteException extends TechlabEcommerceException {

  public StockInsuficienteException(String producto, int disponible, int solicitado) {
    super(String.format("Stock insuficiente para '%s'. Disponible: %d, solicitado: %d", producto, disponible, solicitado));
  }
}