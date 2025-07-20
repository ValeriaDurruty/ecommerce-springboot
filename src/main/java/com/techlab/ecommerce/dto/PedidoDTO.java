package com.techlab.ecommerce.dto;

import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public record PedidoDTO(
        @NotEmpty
        List<LineaPedidoDTO> lineas
) {}