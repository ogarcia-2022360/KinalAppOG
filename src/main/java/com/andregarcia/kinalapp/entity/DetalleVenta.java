package com.andregarcia.kinalapp.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "detalleVenta")
public class DetalleVenta {
    @Id
    @Column
    private Integer codigoDetalleVenta;

    @Column
    private Integer cantidadDetalleVenta;

    @Column
    private BigDecimal precioUnitario;

    @Column
    private BigDecimal subtotal;

    @ManyToOne
    @JoinColumn(name = "Productos_codigo_producto")
    private Producto producto;
    @ManyToOne
    @JoinColumn(name = "Ventas_codigo_venta")
    private Venta venta;

    public DetalleVenta() {
    }

    public DetalleVenta(Integer codigoDetalleVenta, Integer cantidadDetalleVenta, BigDecimal precioUnitario, BigDecimal subtotal, Producto producto, Venta venta) {
        this.codigoDetalleVenta = codigoDetalleVenta;
        this.cantidadDetalleVenta = cantidadDetalleVenta;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
        this.producto = producto;
        this.venta = venta;
    }

    public Integer getCodigoDetalleVenta() {
        return codigoDetalleVenta;
    }

    public void setCodigoDetalleVenta(Integer codigoDetalleVenta) {
        this.codigoDetalleVenta = codigoDetalleVenta;
    }

    public Integer getCantidadDetalleVenta() {
        return cantidadDetalleVenta;
    }

    public void setCantidadDetalleVenta(Integer cantidadDetalleVenta) {
        this.cantidadDetalleVenta = cantidadDetalleVenta;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }
}
