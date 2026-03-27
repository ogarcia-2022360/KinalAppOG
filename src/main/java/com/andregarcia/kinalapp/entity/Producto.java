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
    private Spring nombreProducto;
    @Column
    private double precioProducto;
    @Column
    private int stockProducto;
    @Column
    private int estadoProducto;

    public Producto() {
    }

    public Producto(Integer codigoProducto, Spring nombreProducto, double precioProducto, int stockProducto, int estadoProducto) {
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

    public Spring getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(Spring nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public double getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(double precioProducto) {
        this.precioProducto = precioProducto;
    }

    public int getStockProducto() {
        return stockProducto;
    }

    public void setStockProducto(int stockProducto) {
        this.stockProducto = stockProducto;
    }

    public int getEstadoProducto() {
        return estadoProducto;
    }

    public void setEstadoProducto(int estadoProducto) {
        this.estadoProducto = estadoProducto;
    }
}
