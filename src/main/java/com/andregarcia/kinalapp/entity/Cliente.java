package com.andregarcia.kinalapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "clientes")

public class Cliente {
    @Id
    @Column (name = "dpi_cliente")
    private String dpiCliente;
    @Column
    private String nombreCliente;
    @Column
    private String apellidoCliente;
    @Column
    private String direccion;
    @Column
    private Integer estado;



    public Cliente() {
    }

    public Cliente(String apellidoCliente, String direccion, String dpiCliente, Integer estado, String nombreCliente) {
        this.apellidoCliente = apellidoCliente;
        this.direccion = direccion;
        this.dpiCliente = dpiCliente;
        this.estado = estado;
        this.nombreCliente = nombreCliente;
    }

    public String getApellidoCliente() {
        return apellidoCliente;
    }

    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDpiCliente() {
        return dpiCliente;
    }

    public void setDpiCliente(String dpiCliente) {
        this.dpiCliente = dpiCliente;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
}
