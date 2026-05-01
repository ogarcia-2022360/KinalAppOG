package com.andregarcia.kinalapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VistasController {

    @GetMapping("/entidades")
    public String entidades() {
        return "entidades";
    }

    @GetMapping("/clientesVista")
    public String clientes() {
        return "clientes";
    }

    @GetMapping("/usuariosVista")
    public String usuarios() {
        return "usuarios";
    }

    @GetMapping("/productosVista")
    public String productos() {
        return "productos";
    }

    @GetMapping("/ventasVista")
    public String ventas() {
        return "ventas";
    }
}