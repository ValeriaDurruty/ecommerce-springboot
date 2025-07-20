package com.techlab.ecommerce.repository;

import com.techlab.ecommerce.entity.Pedido;
import com.techlab.ecommerce.entity.EstadoPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByEstado(EstadoPedido estado);
}