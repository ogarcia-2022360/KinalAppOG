package com.andregarcia.kinalapp.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andregarcia.kinalapp.entity.Venta;
import com.andregarcia.kinalapp.entity.Cliente;
import com.andregarcia.kinalapp.entity.Usuario;
import com.andregarcia.kinalapp.repository.ClienteRepository;
import com.andregarcia.kinalapp.repository.UsuarioRepository;
import com.andregarcia.kinalapp.repository.VentaRepository;

@Service
@Transactional
public class VentaService implements IVentaService {

    private final VentaRepository ventaRepository;
    private final ClienteRepository clienteRepository;
    private final UsuarioRepository usuarioRepository;

    public VentaService(VentaRepository ventaRepository,
                        ClienteRepository clienteRepository,
                        UsuarioRepository usuarioRepository) {
        this.ventaRepository = ventaRepository;
        this.clienteRepository = clienteRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Venta> listarVentas() {
        return ventaRepository.findAll();
    }

    @Override
    public Venta guardarVenta(Venta venta) {
        validarVenta(venta);

        // Validar cliente
        if (venta.getCliente() == null) {
            throw new IllegalArgumentException("Debe enviar un cliente.");
        }

        Cliente cliente = clienteRepository.findById(venta.getCliente().getDpiCliente())
                .orElseThrow(() -> new IllegalArgumentException("El cliente no existe."));

        // Validar usuario
        if (venta.getUsuario() == null) {
            throw new IllegalArgumentException("Debe enviar un usuario.");
        }

        Usuario usuario = usuarioRepository.findById(venta.getUsuario().getCodigoUsuario())
                .orElseThrow(() -> new IllegalArgumentException("El usuario no existe."));

        // Asignar entidades reales
        venta.setCliente(cliente);
        venta.setUsuario(usuario);

        // Estado por defecto
        if (venta.getEstado() == 0) {
            venta.setEstado(1);
        }

        return ventaRepository.save(venta);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Venta> buscarPorCodigoVenta(int codigoVenta) {
        return ventaRepository.findById(codigoVenta);
    }

    @Override
    public Venta actualizarVenta(int codigoVenta, Venta venta) {
        if (!ventaRepository.existsById(codigoVenta)) {
            throw new RuntimeException("La venta no se encontró con el código " + codigoVenta);
        }

        validarVenta(venta);

        // Validar y cargar cliente
        if (venta.getCliente() == null) {
            throw new IllegalArgumentException("Debe enviar un cliente.");
        }

        Cliente cliente = clienteRepository.findById(venta.getCliente().getDpiCliente())
                .orElseThrow(() -> new IllegalArgumentException("El cliente no existe."));

        // Validar y cargar usuario
        if (venta.getUsuario() == null) {
            throw new IllegalArgumentException("Debe enviar un usuario.");
        }

        Usuario usuario = usuarioRepository.findById(venta.getUsuario().getCodigoUsuario())
                .orElseThrow(() -> new IllegalArgumentException("El usuario no existe."));

        // Asignar valores
        venta.setCodigoVenta(codigoVenta);
        venta.setCliente(cliente);
        venta.setUsuario(usuario);

        return ventaRepository.save(venta);
    }

    @Override
    public void eliminarVenta(int codigoVenta) {
        if (!ventaRepository.existsById(codigoVenta)) {
            throw new RuntimeException("La venta no se encontró con el código " + codigoVenta);
        }
        ventaRepository.deleteById(codigoVenta);
    }

    @Override
    @Transactional
    public boolean existePorCodigoVenta(int codigoVenta) {
        return ventaRepository.existsById(codigoVenta);
    }

    // Validaciones internas
    private void validarVenta(Venta venta) {
        if (venta.getCodigoVenta() <= 0) {
            throw new IllegalArgumentException("El código de venta debe ser mayor a 0.");
        }

        if (venta.getFechaVenta() == null) {
            venta.setFechaVenta(LocalDateTime.now());
        }

        if (venta.getTotal() == null || venta.getTotal() <= 0) {
            throw new IllegalArgumentException("El total es obligatorio y debe ser mayor a 0.");
        }
    }
}