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
    private double precioProducto;
    @Column
    private Integer stockProducto;
    @Column
    private int estadoProducto;

    public Producto() {
    }

    public Producto(Integer codigoProducto, String nombreProducto, double precioProducto, Integer stockProducto, int estadoProducto) {
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

    public double getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(double precioProducto) {
        this.precioProducto = precioProducto;
    }

    public Integer getStockProducto() {
        return stockProducto;
    }

    public void setStockProducto(Integer stockProducto) {
        this.stockProducto = stockProducto;
    }

    public int getEstadoProducto() {
        return estadoProducto;
    }

    public void setEstadoProducto(int estadoProducto) {
        this.estadoProducto = estadoProducto;
    }
}
