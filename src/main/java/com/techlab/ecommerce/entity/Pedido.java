package com.techlab.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private EstadoPedido estado;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<LineaPedido> lineas = new ArrayList<>();

    public double getTotal() {
        return lineas.stream().mapToDouble(LineaPedido::getSubtotal).sum();
    }

    public void agregarLinea(LineaPedido linea) {
        linea.setPedido(this);
        lineas.add(linea);
    }
}