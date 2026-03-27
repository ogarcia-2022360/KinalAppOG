package com.andregarcia.kinalapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.andregarcia.kinalapp.entity.DetalleVenta;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Integer> {
}
