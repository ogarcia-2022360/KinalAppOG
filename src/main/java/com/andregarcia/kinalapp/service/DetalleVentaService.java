package com.andregarcia.kinalapp.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.andregarcia.kinalapp.entity.DetalleVenta;
import com.andregarcia.kinalapp.repository.DetalleVentaRepository;

@Service
@Transactional
public class DetalleVentaService implements IDetalleVentaService {

    private final DetalleVentaRepository detalleVentaRepository;

    public DetalleVentaService(DetalleVentaRepository detalleVentaRepository){
        this.detalleVentaRepository = detalleVentaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<DetalleVenta> listarDetalleVenta(){
        return detalleVentaRepository.findAll();
    }

    @Override
    public DetalleVenta guardarDetalleVenta(DetalleVenta detalleVenta){
        if (detalleVenta.getCantidadDetalleVenta() == null || detalleVenta.getCantidadDetalleVenta() == 0) {
            detalleVenta.setCantidadDetalleVenta(1);
        }
        validarDetalleVenta(detalleVenta);
        return detalleVentaRepository.save(detalleVenta);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DetalleVenta> buscarDetalleVenta(Integer codigoDetalleVenta){
        return detalleVentaRepository.findById(codigoDetalleVenta);
    }

    @Override
    public DetalleVenta actualizarDetalleVenta(Integer codigoDetalleVenta, DetalleVenta detalleVenta){
        Optional<DetalleVenta> existente = detalleVentaRepository.findById(codigoDetalleVenta);
        if (existente.isEmpty()) {
            throw new RuntimeException("No se encontró el detalle de la venta con el código: " + codigoDetalleVenta);
        }
        detalleVenta.setCodigoDetalleVenta(codigoDetalleVenta);
        validarDetalleVenta(detalleVenta);
        return detalleVentaRepository.save(detalleVenta);
    }

    @Override
    public void eliminarDetalleVenta(Integer codigoDetalleVenta){
        Optional<DetalleVenta> existente = detalleVentaRepository.findById(codigoDetalleVenta);
        if (existente.isEmpty()) {
            throw new RuntimeException("No se encontró el detalle de la venta con el código: " + codigoDetalleVenta);
        }
        detalleVentaRepository.deleteById(codigoDetalleVenta);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existeCodigoDetalleVenta(Integer codigoDetalleVenta){
        return detalleVentaRepository.existsById(codigoDetalleVenta);
    }

    private void validarDetalleVenta(DetalleVenta detalleVenta){
        if (detalleVenta.getCodigoDetalleVenta() == null || detalleVenta.getCodigoDetalleVenta() <= 0) {
            throw new IllegalArgumentException("El código de detalle de la venta debe ser mayor a 0.");
        }
        if (detalleVenta.getCantidadDetalleVenta() == null || detalleVenta.getCantidadDetalleVenta() <= 0){
            throw new IllegalArgumentException("El detalle de la cantidad de venta es obligatorio.");
        }
        if (detalleVenta.getPrecioUnitario() == null || detalleVenta.getPrecioUnitario().compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("El precio unitario de la venta debe ser mayor a 0.");
        }
    }
}
