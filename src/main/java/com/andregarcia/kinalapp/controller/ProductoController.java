package com.andregarcia.kinalapp.controller;

import java.util.List;

import com.andregarcia.kinalapp.entity.Producto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.andregarcia.kinalapp.service.IProductoService;
import com.andregarcia.kinalapp.entity.Cliente;

@RestController
@RequestMapping("/productos")
public class ProductoController<producto> {

    private final IProductoService productoService;

    public ProductoController(IProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public ResponseEntity<List<Producto>> listarProductos() {
        List<Producto> productos = productoService.listarProductos();
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{codigoProducto}")
    public ResponseEntity<Producto> buscarCodigoProducto(@PathVariable Integer codigoProducto) {
        return productoService.buscarCodigoProducto(codigoProducto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> guardarProducto(@RequestBody Producto producto) {
        try {
            Producto nuevoProducto = productoService.guardarProducto(producto);
            return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{codigoProducto}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Integer codigoProducto) {
        try {
            if (!productoService.existeCodigoProducto(codigoProducto)) {
                return ResponseEntity.notFound().build();
            }
            productoService.eliminarProducto(codigoProducto);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{codigoProducto}")
    public ResponseEntity<?> actualizarProducto(@PathVariable Integer codigoProducto, @RequestBody Producto producto) {
        try {
            if (!productoService.existeCodigoProducto(codigoProducto)) {
                return ResponseEntity.notFound().build();
            }
            Producto productoActualizado = productoService.actualizarProducto(codigoProducto, producto);
            return ResponseEntity.ok(productoActualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}