package com.andregarcia.kinalapp.entity;

import java.time.LocalDateTime;
import java.util.Date;
import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "ventas")

public class Venta {
@Id
@Column (name = "codigo_venta")
private Integer codigoVenta;
@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
private LocalDateTime fechaVenta;
@Column
private Double total;
@Column
private Integer estado;
@ManyToOne
@JoinColumn (name = "dpi_cliente")
    private Cliente cliente;
@ManyToOne
@JoinColumn (name = "codigo_usuario")
    private Usuario usuario;

    public Venta() {
    }

    public Venta(Integer codigoVenta, LocalDateTime fechaVenta, Double total, Integer estado, Cliente cliente, Usuario usuario) {
        this.codigoVenta = codigoVenta;
        this.fechaVenta = fechaVenta;
        this.total = total;
        this.estado = estado;
        this.cliente = cliente;
        this.usuario = usuario;
    }

    public Integer getCodigoVenta() {
        return codigoVenta;
    }

    public void setCodigoVenta(Integer codigoVenta) {
        this.codigoVenta = codigoVenta;
    }

    public LocalDateTime getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(LocalDateTime fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
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

