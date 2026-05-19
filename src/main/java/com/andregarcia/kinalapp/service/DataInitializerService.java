package com.andregarcia.kinalapp.service;

import com.andregarcia.kinalapp.entity.*;
import com.andregarcia.kinalapp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class DataInitializerService implements CommandLineRunner {

    @Autowired private ClienteRepository clienteRepository;
    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private ProductoRepository productoRepository;
    @Autowired private VentaRepository ventaRepository;
    @Autowired private DetalleVentaRepository detalleVentaRepository;

    private final Random random = new Random();

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        // 1. CLIENTES (Ya lo tenías, pero aquí está integrado)
        if (clienteRepository.count() == 0) {
            List<Cliente> clientes = new ArrayList<>();
            for (int i = 1; i <= 1000; i++) {
                String dpi = String.format("3000%09d", i);
                clientes.add(new Cliente("Apellido_" + i, "Dirección_" + i, dpi, 1, "Nombre_" + i));
            }
            clienteRepository.saveAll(clientes);
        }

        // 2. USUARIOS
        if (usuarioRepository.count() == 0) {
            List<Usuario> usuarios = new ArrayList<>();
            for (int i = 1; i <= 1000; i++) {
                usuarios.add(new Usuario(i, "user" + i, "pass" + i, "user" + i + "@kinal.edu.gt", (i % 2 == 0 ? "ADMIN" : "USER"), 1));
            }
            usuarioRepository.saveAll(usuarios);
        }

        // 3. PRODUCTOS
        if (productoRepository.count() == 0) {
            List<Producto> productos = new ArrayList<>();
            for (int i = 1; i <= 1000; i++) {
                double precioAleatorio = 10.0 + (500.0 * random.nextDouble());
                productos.add(new Producto(i, "Producto " + i, precioAleatorio, random.nextInt(100) + 1, 1));
            }
            productoRepository.saveAll(productos);
        }

        // 4. VENTAS
        if (ventaRepository.count() == 0) {
            List<Cliente> listaClientes = clienteRepository.findAll();
            List<Usuario> listaUsuarios = usuarioRepository.findAll();
            List<Venta> ventas = new ArrayList<>();

            for (int i = 1; i <= 1000; i++) {
                Venta v = new Venta();
                v.setCodigoVenta(i);
                v.setFechaVenta(LocalDateTime.now().minusDays(random.nextInt(30)));
                v.setTotal(0.0); // Se actualiza al crear detalles o queda como base
                v.setEstado(1);
                v.setCliente(listaClientes.get(random.nextInt(listaClientes.size())));
                v.setUsuario(listaUsuarios.get(random.nextInt(listaUsuarios.size())));
                ventas.add(v);
            }
            ventaRepository.saveAll(ventas);
        }

        // 5. DETALLE VENTA
        if (detalleVentaRepository.count() == 0) {
            List<Venta> listaVentas = ventaRepository.findAll();
            List<Producto> listaProductos = productoRepository.findAll();
            List<DetalleVenta> detalles = new ArrayList<>();

            for (int i = 1; i <= 1000; i++) {
                DetalleVenta dv = new DetalleVenta();
                dv.setCodigoDetalleVenta(i);

                // Asignar Producto y extraer datos según tus nombres de atributos
                Producto p = listaProductos.get(random.nextInt(listaProductos.size()));
                dv.setProducto(p);

                int cantidad = random.nextInt(10) + 1;
                dv.setCantidadDetalleVenta(cantidad);

                // Convertir Double de Producto a BigDecimal de DetalleVenta
                BigDecimal precio = BigDecimal.valueOf(p.getPrecioProducto());
                dv.setPrecioUnitario(precio);
                dv.setSubtotal(precio.multiply(new BigDecimal(cantidad)));

                // Asignar Venta
                dv.setVenta(listaVentas.get(random.nextInt(listaVentas.size())));

                detalles.add(dv);
            }
            detalleVentaRepository.saveAll(detalles);
        }
    }
}