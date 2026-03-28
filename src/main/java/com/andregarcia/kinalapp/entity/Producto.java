package com.andregarcia.kinalapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;

import javax.swing.*;

@Entity
@Table(name = "productos")
public class Producto {
    @Id
    @Column(name = "codigo_producto")
    private Integer codigoProducto;
    @Column
    private String nombreProducto;
    @Column
    private Double precioProducto;
    @Column
    private Integer stockProducto;
    @Column
    private Integer estadoProducto;

    public Producto() {
    }

    public Producto(Integer codigoProducto, String nombreProducto, Double precioProducto, Integer stockProducto, Integer estadoProducto) {
        this.codigoProducto = codigoProducto;
        this.nombreProducto = nombreProducto;
        this.precioProducto = precioProducto;
        this.stockProducto = stockProducto;
        this.estadoProducto = estadoProducto;
    }

    public Integer getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(Integer codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Double getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(Double precioProducto) {
        this.precioProducto = precioProducto;
    }

    public Integer getStockProducto() {
        return stockProducto;
    }

    public void setStockProducto(Integer stockProducto) {
        this.stockProducto = stockProducto;
    }

    public Integer getEstadoProducto() {
        return estadoProducto;
    }

    public void setEstadoProducto(Integer estadoProducto) {
        this.estadoProducto = estadoProducto;
    }
}
