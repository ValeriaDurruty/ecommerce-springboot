package com.techlab.ecommerce.service;

import com.techlab.ecommerce.dto.ProductoDTO;
import com.techlab.ecommerce.entity.Producto;
import com.techlab.ecommerce.exception.ProductoNoEncontradoException;
import com.techlab.ecommerce.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {
    private final ProductoRepository repository;

    public ProductoService(ProductoRepository repository) {
        this.repository = repository;
    }

    public List<Producto> listarTodos() {
        return repository.findAll();
    }

    public Producto buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProductoNoEncontradoException(id.toString()));
    }

    public Producto guardar(ProductoDTO dto) {
        Producto producto = Producto.builder()
                .nombre(dto.nombre())
                .descripcion(dto.descripcion())
                .precio(dto.precio())
                .categoria(dto.categoria())
                .imagenUrl(dto.imagenUrl())
                .stock(dto.stock())
                .build();

        return repository.save(producto);
    }

    public Producto actualizar(Long id, ProductoDTO dto) {
        Producto producto = repository.findById(id)
                .orElseThrow(() -> new ProductoNoEncontradoException(id.toString()));

        producto.setNombre(dto.nombre());
        producto.setDescripcion(dto.descripcion());
        producto.setPrecio(dto.precio());
        producto.setCategoria(dto.categoria());
        producto.setImagenUrl(dto.imagenUrl());
        producto.setStock(dto.stock());

        return repository.save(producto);
    }

    public boolean eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new ProductoNoEncontradoException(id.toString());
        }
        repository.deleteById(id);
        return true;
    }

    public List<Producto> buscarPorNombre(String nombre) {
        return repository.findByNombreContainingIgnoreCase(nombre);
    }

    public List<Producto> buscarPorCategoria(String categoria) {
        return repository.findByCategoriaContainingIgnoreCase(categoria);
    }
}