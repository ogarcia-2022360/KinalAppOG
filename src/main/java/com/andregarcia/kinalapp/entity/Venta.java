package com.andregarcia.kinalapp.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "ventas")

public class Venta {
@Id
@Column (name = "codigo_venta")
private int codigoVenta;
@Column
private Date fechaVenta;
@Column
private Double total;
@Column
private int estado;
@ManyToOne
@JoinColumn (name = "clientes_dpi_usuario")
    private Cliente cliente;
@JoinColumn (name = "codigo_usuario")
    private Usuario usuario;

    public Venta() {
    }

    public Venta(int codigoVenta, Date fechaVenta, Double total, int estado, Cliente cliente, Usuario usuario) {
        this.codigoVenta = codigoVenta;
        this.fechaVenta = fechaVenta;
        this.total = total;
        this.estado = estado;
        this.cliente = cliente;
        this.usuario = usuario;
    }

    public int getCodigoVenta() {
        return codigoVenta;
    }

    public void setCodigoVenta(int codigoVenta) {
        this.codigoVenta = codigoVenta;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

