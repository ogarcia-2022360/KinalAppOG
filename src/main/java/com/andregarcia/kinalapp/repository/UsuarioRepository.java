package com.andregarcia.kinalapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.andregarcia.kinalapp.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
