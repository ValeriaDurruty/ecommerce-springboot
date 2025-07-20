package com.techlab.ecommerce.service;

import com.techlab.ecommerce.dto.LineaPedidoDTO;
import com.techlab.ecommerce.dto.PedidoDTO;
import com.techlab.ecommerce.entity.*;
import com.techlab.ecommerce.exception.PedidoNoEncontradoException;
import com.techlab.ecommerce.exception.ProductoNoEncontradoException;
import com.techlab.ecommerce.exception.StockInsuficienteException;
import com.techlab.ecommerce.repository.PedidoRepository;
import com.techlab.ecommerce.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepo;
    private final ProductoRepository productoRepo;

    public PedidoService(PedidoRepository pedidoRepo, ProductoRepository productoRepo) {
        this.pedidoRepo = pedidoRepo;
        this.productoRepo = productoRepo;
    }

    public Pedido crearPedido(PedidoDTO dto) {
        Pedido pedido = new Pedido();
        pedido.setEstado(EstadoPedido.PENDIENTE);

        for (LineaPedidoDTO linea : dto.lineas()) {
            Producto producto = productoRepo.findById(linea.productoId())
                    .orElseThrow(() -> new ProductoNoEncontradoException(linea.productoId().toString()));

            if (producto.getStock() < linea.cantidad()) {
                throw new StockInsuficienteException(
                        producto.getNombre(),
                        producto.getStock(),
                        linea.cantidad()
                );
            }

            producto.setStock(producto.getStock() - linea.cantidad());
            productoRepo.save(producto);

            LineaPedido lineaPedido = LineaPedido.builder()
                    .producto(producto)
                    .cantidad(linea.cantidad())
                    .pedido(pedido)
                    .build();

            pedido.agregarLinea(lineaPedido);
        }

        return pedidoRepo.save(pedido);
    }

    public List<Pedido> listarTodos() {
        return pedidoRepo.findAll();
    }

    public Pedido cambiarEstado(Long pedidoId, EstadoPedido nuevoEstado) {
        Pedido pedido = pedidoRepo.findById(pedidoId)
                .orElseThrow(() -> new PedidoNoEncontradoException(pedidoId));

        pedido.setEstado(nuevoEstado);
        return pedidoRepo.save(pedido);
    }

    public List<Pedido> listarPorEstado(EstadoPedido estado) {
        return pedidoRepo.findByEstado(estado);
    }
}