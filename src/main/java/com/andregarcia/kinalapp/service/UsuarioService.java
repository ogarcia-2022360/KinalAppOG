package com.andregarcia.kinalapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andregarcia.kinalapp.entity.Usuario;
import com.andregarcia.kinalapp.repository.UsuarioRepository;

@Service
@Transactional
public class UsuarioService implements IUsuarioService {

    private final UsuarioRepository usuarioRepository;
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        validarUsuario(usuario);
        if (usuario.getEstado() == 0)
            usuario.setEstado(1);
        return usuarioRepository.save(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> buscarCodigoUsuario(Integer codigoUsuario) {
        return usuarioRepository.findById(codigoUsuario);
    }

    @Override
    public Usuario actualizarUsuario(Integer codigoUsuario, Usuario usuario) {
        if (!usuarioRepository.existsById(codigoUsuario)){
            throw new RuntimeException("No se encontro el usuario con el codigo");
        }
        usuario.setCodigoUsuario(codigoUsuario);
        validarUsuario(usuario);
        return usuarioRepository.save(usuario);
    }

    @Override
    public void eliminarUsuario(Integer codigoUsuario) {
        if (!usuarioRepository.existsById(codigoUsuario)){
            throw new RuntimeException("No se encontro el usuario con el codigo");
        }
        usuarioRepository.deleteById(codigoUsuario);
    }

    @Override
    @Transactional
    public boolean existeCodigoUsuario(Integer codigoUsuario){
        return usuarioRepository.existsById(codigoUsuario);
    }

    private void validarUsuario(Usuario usuario){
        if (usuario.getCodigoUsuario() <= 0) {
            throw new IllegalArgumentException("El código del usuario debe ser mayor a 0.");
        }

        if (usuario.getUsername() == null || usuario.getUsername().trim().isEmpty()){
            throw new IllegalArgumentException("El nombre del usuario es un dato obligatorio.");
        }
        if (usuario.getPassword() == null || usuario.getPassword().trim().isEmpty()){
            throw new IllegalArgumentException("La contraseña del usuario es un dato obligatorio.");
        }
    }
}
