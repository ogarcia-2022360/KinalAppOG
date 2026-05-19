package com.andregarcia.kinalapp.controller;

import java.util.List;

import com.andregarcia.kinalapp.service.IUsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.andregarcia.kinalapp.entity.Usuario;

@RestController
//Todas las rutas en este controlador deben empezar por /usuarios
@RequestMapping("/usuarios")
public class UsuarioController<usuario> {

    private final IUsuarioService usuarioService;

    public UsuarioController(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    //Para peticiones GET:
    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{codigoUsuario}")
    public ResponseEntity<Usuario> buscarCodigoCliente(@PathVariable Integer codigoUsuario) {
        return usuarioService.buscarCodigoUsuario(codigoUsuario)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> guardarUsuario(@RequestBody Usuario usuario) {
        try {
            Usuario nuevoUsuario = usuarioService.guardarUsuario(usuario);
            return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //Para eliminar un Usuario
    @DeleteMapping("/{codigoUsuario}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Integer codigoUsuario) {
        try {
            if (!usuarioService.existeCodigoUsuario(codigoUsuario)) {
                return ResponseEntity.notFound().build();
            }
            usuarioService.eliminarUsuario(codigoUsuario);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{codigoUsuario}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable Integer codigoUsuario, @RequestBody Usuario usuario) {
        try {
            if (!usuarioService.existeCodigoUsuario(codigoUsuario)) {
                return ResponseEntity.notFound().build();
            }

            Usuario usuarioActualizado = usuarioService.actualizarUsuario(codigoUsuario, usuario);
            return ResponseEntity.ok(usuarioActualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}