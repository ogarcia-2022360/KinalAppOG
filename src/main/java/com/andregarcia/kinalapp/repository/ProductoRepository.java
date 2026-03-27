package com.andregarcia.kinalapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andregarcia.kinalapp.entity.Producto;

public interface ProductoRepository extends  JpaRepository<Producto, Integer>{
}
