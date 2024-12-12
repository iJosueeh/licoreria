/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios.carrito;

import controladores.carrito.CarritoDAO;
import controladores.carrito.CarritoDetallesDAO;
import controladores.carrito.FacturaDAO;
import controladores.productos.ProductoDAO;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import modelos.carrito.CarritoDetalle;
import modelos.carrito.Carrito;
import modelos.carrito.Factura;
import modelos.productos.Producto;
import modelos.usuarios.Usuario;
import servicios.animaciones.Animaciones;

public class CarritoServicios {

    Usuario usuarioActual = Usuario.getUsuarioActual();
    ProductoDAO productoDAO = new ProductoDAO();
    CarritoDetallesDAO carritoDetallesDAO = new CarritoDetallesDAO();
    CarritoDAO carritoDAO = new CarritoDAO();
    FacturaDAO facturaDAO = new FacturaDAO();
    Scanner scanner = new Scanner(System.in);

    public boolean agregarProductoAlCarrito(int idUsuario, int idProducto, int cantidad) throws Exception {

        Carrito carrito = carritoDAO.obtenerCarritoPorUsuario(idUsuario);

        if (carrito == null) {
            carrito = new Carrito();
            carrito.setIdUsuario(idUsuario);
            carrito.setEstado("pendiente");
            carrito.setFechaCreacion(new java.sql.Date(System.currentTimeMillis()));
            carritoDAO.insertar(carrito);
        }

        CarritoDetalle detalleExistente = carritoDetallesDAO.obtenerPorCarritoYProducto(carrito.getId(), idProducto);

        if (detalleExistente != null) {
            detalleExistente.setCantidad(detalleExistente.getCantidad() + cantidad);
            detalleExistente.setSubtotal(detalleExistente.getCantidad() * productoDAO.obtenerPrecioProducto(idProducto));
            return carritoDetallesDAO.actualizar(detalleExistente);
        } else {
            CarritoDetalle nuevoDetalle = new CarritoDetalle();
            nuevoDetalle.setIdCarrito(carrito.getId());
            nuevoDetalle.setIdProducto(idProducto);
            nuevoDetalle.setCantidad(cantidad);
            nuevoDetalle.setSubtotal(cantidad * productoDAO.obtenerPrecioProducto(idProducto));
            return carritoDetallesDAO.insertar(nuevoDetalle);
        }
    }

    public void mostrarCarrito(int idUsuario) throws Exception {
        Carrito carrito = carritoDAO.obtenerCarritoPorUsuario(idUsuario);

        if (carrito == null || carrito.getDetalles().isEmpty()) {
            System.out.println("El carrito está vacío.");
            return;
        }

        Predicate<CarritoDetalle> filtroPendientes = detalle -> {
            try {
                return "pendiente".equalsIgnoreCase(carrito.getEstado());
            } catch (Exception e) {
                System.out.println("Error al verificar el estado del carrito: " + e.getMessage());
                return false;
            }
        };

        List<CarritoDetalle> detallesPendientes = carrito.getDetalles().stream()
                .filter(filtroPendientes)
                .collect(Collectors.toList());

        if (detallesPendientes.isEmpty()) {
            System.out.println("El carrito no contiene productos pendientes.");
            return;
        }

        double total = 0;
        System.out.println("\n────────────────────────────────────────");
        System.out.println("\t\t    CARRITO");
        System.out.println("────────────────────────────────────────");

        for (CarritoDetalle detalle : detallesPendientes) {
            double subtotal = detalle.getCantidad() * productoDAO.obtenerPrecioProducto(detalle.getIdProducto());
            Producto producto = productoDAO.obtenerPorId(detalle.getIdProducto());
            System.out.println("- Producto ID: " + detalle.getIdProducto()
                    + ", Nombre: " + producto.getNombre());
            System.out.println("- Cantidad: " + detalle.getCantidad());
            System.out.println("- Subtotal: $" + subtotal);
            total += subtotal;
        }
        System.out.println("\nTotal: $" + total);

        System.out.println("\n¿Deseas confirmar la compra? (SI/NO): ");
        String respuesta = scanner.nextLine().toUpperCase();

        if ("SI".equals(respuesta)) {
            Animaciones.cargandoAnimacion();
            if (realizarCompra(usuarioActual.getId())) {
                System.out.println("\n¡Compra realizada con éxito!");
            } else {
                System.out.println("Hubo un problema al procesar la compra.");
            }
        } else if ("NO".equals(respuesta)) {
            Animaciones.cargandoAnimacion();
            System.out.println("Compra cancelada.");
        } else {
            System.out.println("Opcion no valida.");
        }

    }

    public boolean actualizarCantidadProducto(int idUsuario, int idProducto, int nuevaCantidad) throws Exception {
        Carrito carrito = carritoDAO.obtenerCarritoPorUsuario(idUsuario);

        if (carrito == null) {
            System.out.println("No tienes un carrito activo.");
            return false;
        }

        CarritoDetalle detalle = carritoDetallesDAO.obtenerPorCarritoYProducto(carrito.getId(), idProducto);

        if (detalle == null) {
            System.out.println("El producto no está en el carrito.");
            return false;
        }

        detalle.setCantidad(nuevaCantidad);
        detalle.setSubtotal(nuevaCantidad * productoDAO.obtenerPrecioProducto(idProducto));
        return carritoDetallesDAO.actualizar(detalle);
    }

    public boolean eliminarProductoDelCarrito(int idUsuario, int idProducto) throws Exception {
        Carrito carrito = carritoDAO.obtenerCarritoPorUsuario(idUsuario);

        if (carrito == null) {
            System.out.println("No tienes un carrito activo.");
            return false;
        }

        boolean eliminado = carritoDetallesDAO.eliminarPorCarritoYProducto(carrito.getId(), idProducto);

        if (eliminado && carritoDetallesDAO.contarDetallesPorCarrito(carrito.getId()) == 0) {
            carrito.setEstado("pendiente");
            carritoDAO.actualizar(carrito);
        }

        return eliminado;
    }

    public boolean realizarCompra(int idUsuario) throws Exception {
        Carrito carrito = carritoDAO.obtenerCarritoPorUsuario(idUsuario);

        if (carrito == null || carrito.getDetalles().isEmpty()) {
            System.out.println("El carrito está vacío. No se puede realizar la compra.");
            return false;
        }

        double totalCompra = 0;

        for (CarritoDetalle detalle : carrito.getDetalles()) {
            double precioProducto = productoDAO.obtenerPrecioProducto(detalle.getIdProducto());
            double subtotal = detalle.getCantidad() * precioProducto;
            totalCompra += subtotal;
        }

        carrito.setEstado("Finalizado");
        carritoDAO.actualizar(carrito);

        generarFactura(idUsuario, carrito.getId(), totalCompra);

        System.out.println("Compra realizada con éxito. Total: $" + totalCompra);
        return true;
    }

    private void generarFactura(int idUsuario, int idCarrito, double total) throws Exception {
        Factura factura = new Factura();
        factura.setId_usuario(idUsuario);
        factura.setId_carrito(idCarrito);
        factura.setTotal(total);
        factura.setFecha(LocalDateTime.now());

        facturaDAO.insertar(factura);

        System.out.println("Factura generada con éxito. ID de la factura: " + factura.getId());
    }

}
