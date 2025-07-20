package com.techlab.ecommerce.dto;

import jakarta.validation.constraints.*;

public record ProductoDTO (
        @NotBlank String nombre,
        String descripcion,
        @Positive double precio,
        String categoria,
        String imagenUrl,
        @Min(0) int stock
){}