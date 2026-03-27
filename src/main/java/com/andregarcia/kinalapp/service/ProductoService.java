package com.andregarcia.kinalapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andregarcia.kinalapp.repository.ProductoRepository;
import com.andregarcia.kinalapp.entity.Producto;

@Service
@Transactional
public class ProductoService implements IProductoService{
    private final ProductoRepository productoRepository;
    public ProductoService(ProductoRepository productoRepository){
        this.productoRepository = productoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> listarProductos(){
        return productoRepository.findAll();
    }

    @Override
    public Producto guardarProducto(Producto producto){
        validarProducto(producto);
        if (producto.getEstadoProducto() == 0)
            producto.setEstadoProducto(1);
        return productoRepository.save(producto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Producto> buscarCodigoProducto(Integer codigoProducto){
        return productoRepository.findById(codigoProducto);
    }

    @Override
    public Producto actualizarProducto(Integer codigoProducto, Producto producto){
        if (!productoRepository.existsById(codigoProducto)){
            throw new RuntimeException("No se encontreo el Producto con el codigo: " + codigoProducto);
        }

        producto.setCodigoProducto(codigoProducto);
        validarProducto(producto);
        return productoRepository.save(producto);
    }

    @Override
    public void eliminarProducto(Integer codigoProducto){
        if (!productoRepository.existsById(codigoProducto)){
            throw new RuntimeException("No se encontro el producto con el codigo: " + codigoProducto);
        }
        productoRepository.deleteById(codigoProducto);
    }

    @Override
    @Transactional
    public boolean existeCodigoProducto(Integer codigoProducto){
        return productoRepository.existsById(codigoProducto);
    }

    private void validarProducto(Producto producto){
        if (producto.getCodigoProducto() == null || producto.getCodigoProducto() <= 0) {
            throw new IllegalArgumentException("El código del producto debe ser mayor a 0.");
        }
        if (producto.getNombreProducto() == null || producto.getNombreProducto().trim().isEmpty()){
            throw new IllegalArgumentException("El nombre del producto es obligatorio.");
        }
        if (producto.getStockProducto() == null || producto.getStockProducto() <= 0) {
            throw new IllegalArgumentException("El stock del producto debe ser mayor a 0.");
        }
    }
}
