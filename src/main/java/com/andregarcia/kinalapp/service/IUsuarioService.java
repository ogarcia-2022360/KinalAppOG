package com.andregarcia.kinalapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.andregarcia.kinalapp.entity.Usuario;

public interface IUsuarioService {

    //Metodo que devuelve una lista de todos los Usuarios
    List<Usuario> listarUsuarios();

    //Metodo que guarda un Usuario en la DB
    Usuario guardarUsuario(Usuario usuario);

    //Contener que puede o no tener valor
    Optional<Usuario> buscarCodigoUsuario(Integer codigoUsuario);

    //Metodo que actualiza un Usuario
    Usuario actualizarUsuario(Integer codigoUsuario, Usuario usuario);

    //Metodo que elimina un Usuario
    void eliminarUsuario(Integer codigoUsuario);

    //Retorna "true" si existe y "false" si no existe
    boolean existeCodigoUsuario(Integer codigoUsuario);
}
