package com.andregarcia.kinalapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.andregarcia.kinalapp.entity.Producto;

public interface IProductoService {
    //Metodo que lista todos los Productos
    List<Producto> listarProductos();

    //Metodo que guarda un producto en la DB
    Producto guardarProducto(Producto producto);

    //Contenedor que puede o no tener valor
    Optional<Producto> buscarCodigoProducto(Integer codigoProducto);

    //Metodo que actualiza un producto
    Producto actualizarProducto(Integer codigoProducto, Producto producto);

    //Metodo para eliminar un Producto
    void eliminarProducto(Integer codigoProducto);

    //Metodo que retorna "true" si existe y "false" si no existe
    boolean existeCodigoProducto(Integer codigoProducto);
}
