package com.techlab.ecommerce.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record LineaPedidoDTO(
        @NotNull Long productoId,
        @Min(1) int cantidad
) {
}