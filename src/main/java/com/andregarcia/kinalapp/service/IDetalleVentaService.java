package com.andregarcia.kinalapp.service;

import java.util.List;
import java.util.Optional;

import com.andregarcia.kinalapp.entity.DetalleVenta;

public interface IDetalleVentaService {

    //Metodo para listar los detalles de ventas
    List<DetalleVenta> listarDetalleVenta();

    //Metodo que guarda el detalle de una venta en la base de datos
    DetalleVenta guardarDetalleVenta(DetalleVenta detalleVenta);

    //Contenedor que puede o no tener valor
    Optional<DetalleVenta> buscarDetalleVenta(Integer codigoDetalleVenta);

    //Metodo para actualizar el detalle de una venta
    DetalleVenta actualizarDetalleVenta(Integer codigoDetalleVenta, DetalleVenta detalleVenta);

    //Metodo para eliminar el datalle de una venta
    void eliminarDetalleVenta(Integer codigoDetalleVenta);
}
