package com.andregarcia.kinalapp.controller;

import java.util.List;

import com.andregarcia.kinalapp.entity.Cliente;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.andregarcia.kinalapp.entity.DetalleVenta;
import com.andregarcia.kinalapp.service.IDetalleVentaService;

@RestController
//Las rutas en este controlador deben empezar por /codigoDetalleVenta
@RequestMapping()
public class DetalleVentaController<detalleVenta> {
    private final IDetalleVentaService detalleVentaService;
    public DetalleVentaController(IDetalleVentaService detalleVentaService){
        this.detalleVentaService = detalleVentaService;
    }

    //Responde peticiones GET
    @GetMapping
    public ResponseEntity<List<DetalleVenta>> listarDetalleVenta(){
        List<DetalleVenta> detalleVentas = detalleVentaService.listarDetalleVenta();
        return ResponseEntity.ok(detalleVentas);
    }

    //Para guardar el detalle de una venta
    @PostMapping
    public ResponseEntity<?> guardarDetalleVenta(@RequestBody DetalleVenta detalleVenta){
        try{
            DetalleVenta nuevoDetalleVenta = detalleVentaService.guardarDetalleVenta(detalleVenta);
            return new ResponseEntity<>(nuevoDetalleVenta, HttpStatus.CREATED);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //Para eliminar el detalle de una venta
    @DeleteMapping("/{codigoDetalleVenta}")
    public ResponseEntity<Void> eliminarDetalleVenta(@PathVariable Integer codigoDetalleVenta){
        try{
            if (!detalleVentaService.existeCodigoDetalleVenta(codigoDetalleVenta)){
                return ResponseEntity.notFound().build();
            }

            detalleVentaService.eliminarDetalleVenta(codigoDetalleVenta);
            return ResponseEntity.noContent().build();
        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }

    }

    //Para buscar el detalle de una venta
    @GetMapping("/{codigoDetalleVenta}")
    public ResponseEntity<DetalleVenta> buscarDetalleVenta(@PathVariable Integer codigoDetalleVenta){
        return detalleVentaService.buscarDetalleVenta(codigoDetalleVenta)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //Para actualizar el detalle de una venta
    @PutMapping("/{codigoDetalleVenta}")
    public ResponseEntity<?> actualizarDetalleVenta(@PathVariable Integer codigoDetalleVenta, @RequestBody DetalleVenta detalleVenta){
        try{
            if (!detalleVentaService.existeCodigoDetalleVenta(codigoDetalleVenta)){
                return ResponseEntity.notFound().build();
            }

            DetalleVenta detalleVentaActualizado = detalleVentaService.actualizarDetalleVenta(codigoDetalleVenta, detalleVenta);
            return ResponseEntity.ok(detalleVentaActualizado);

        }catch (IllegalArgumentException e){
            return  ResponseEntity.badRequest().body(e.getMessage());

        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
            }
        }
    }
