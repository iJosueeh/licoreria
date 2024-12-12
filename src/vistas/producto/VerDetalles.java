/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistas.producto;

import controladores.productos.ProductoDAO;
import controladores.productos.ReseñasDAO;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import modelos.productos.Producto;
import modelos.productos.Reseñas;
import modelos.usuarios.Usuario;
import servicios.animaciones.Animaciones;
import servicios.carrito.CarritoServicios;
import static servicios.listas.ListarIterator.obtenerIterador;
import static vistas.login.MenuCliente.loginCliente;
import static vistas.producto.CrearReseña.nuevaReseña;

public class VerDetalles {

    public static void verDetallesProductos(int id) throws Exception {

        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        Usuario usuarioActual = Usuario.getUsuarioActual();
        ProductoDAO productoDAO = new ProductoDAO();
        Producto productoDetalles = productoDAO.obtenerPorId(id);
        CarritoServicios carritoServicios = new CarritoServicios();

        while (continuar) {
            if (productoDetalles != null) {
                System.out.println("\n────────────────────────────────────────");
                System.out.println("ID: " + productoDetalles.getId());
                System.out.println("Nombre: " + productoDetalles.getNombre());
                System.out.println("Descripcion: " + productoDetalles.getDescripcion());
                System.out.println("Marca: " + productoDetalles.getMarca());
                System.out.println("Proveedor: " + productoDetalles.getProveedor());
                System.out.println("Fecha de Vencimiento: " + String.valueOf(productoDetalles.getFecha_vencimiento()));
                System.out.println("Estado: " + productoDetalles.getEstado());
                System.out.println("Nivel de Alcohol: " + productoDetalles.getVolumenAlcohol());
                System.out.println("────────────────────────────────────────");

                System.out.println("\nOpciones: [B] Comprar [E] Salir [R] Ver Reseñas");
                String opcion = scanner.nextLine().toUpperCase();

                if (opcion.equals("B")) {
                    Animaciones.cargandoAnimacion();

                    productoDetalles = productoDAO.obtenerPorId(id);
                    if (productoDetalles != null) {
                        if ("Disponible".equals(productoDetalles.getEstado())) {
                            System.out.println("Producto: " + productoDetalles.getNombre() + " - Estado: Disponible");

                            System.out.print("Ingrese la cantidad a comprar: ");
                            int cantidad = 0;
                            try {
                                cantidad = Integer.parseInt(scanner.nextLine());
                                if (cantidad > 0) {
                                    Animaciones.cargandoAnimacion();
                                    System.out.println("Procesando la compra de " + cantidad + " unidad(es)...");

                                    boolean exito = carritoServicios.agregarProductoAlCarrito(usuarioActual.getId(), id, cantidad);
                                    if (exito) {
                                        System.out.println("Producto añadido al carrito exitosamente.");
                                    } else {
                                        System.out.println("Error al añadir el producto al carrito. Intente nuevamente.");
                                    }
                                } else {
                                    System.out.println("La cantidad debe ser mayor a 0.");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Entrada inválida para la cantidad. Debe ser un número entero.");
                            }
                        } else {
                            System.out.println("El producto no está disponible para la compra.");
                        }
                    } else {
                        System.out.println("No se encontró el producto con ID: " + id);
                    }

                } else if (opcion.equals("E")) {
                    System.out.println("Regresando al menú anterior..");
                    Animaciones.cargandoAnimacion();
                    continuar = false;
                    break;
                } else if (opcion.equals("R")) {
                    Animaciones.cargandoAnimacion();
                    ReseñasDAO reseñaDAO = new ReseñasDAO();
                    System.out.println("\n────────────────────────────────────────");
                    System.out.println("1. Escribir una nueva Reseña");
                    System.out.println("2. Ver reseñas del producto");
                    System.out.println("────────────────────────────────────────");
                    System.out.print("Indique la opcion adecuada: ");

                    int reseñaOpcion = 0;

                    try {
                        reseñaOpcion = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Por favor, ingrese un número válido.");
                        continue;
                    }

                    switch (reseñaOpcion) {
                        case 1:
                            Animaciones.cargandoAnimacion();
                            nuevaReseña(id);
                            break;
                        case 2:
                            Animaciones.cargandoAnimacion();
                            List<Reseñas> reseñas = reseñaDAO.mostrarReseñasPorProducto(id);
                            if (reseñas.isEmpty()) {
                                System.out.println("No hay reseñas para este producto.");
                            } else {
                                System.out.println("\nReseñas del producto: ");
                                Iterator<Reseñas> iterador = obtenerIterador(reseñas);

                                while (iterador.hasNext()) {
                                    Reseñas reseña = iterador.next();
                                    System.out.println("Usuario: " + reseña.getNombreUsuario());
                                    System.out.println("Calificación: " + reseña.getCalificacion() + "/5");
                                    System.out.println("Comentario: " + reseña.getComentario());
                                    System.out.println("Fecha: " + reseña.getFecha());
                                    System.out.println("────────────────────────────────────────");
                                }

                                System.out.println("\n¿Desea seguir viendo mas reseñas o regresar al menu anterior?: ");
                                System.out.println("1. Seguir viendo");
                                System.out.println("2. Regresar al menu anterior.");
                                System.out.print("Indique la opcion adecuada: ");

                                int opcionReseña = 0;

                                try {
                                    opcionReseña = Integer.parseInt(scanner.nextLine());
                                } catch (NumberFormatException e) {
                                    System.out.println("Por favor, ingrese un número válido.");
                                    continue;
                                }

                                switch (opcionReseña) {
                                    case 1:
                                        break;
                                    case 2:
                                        Animaciones.cargandoAnimacion();
                                        System.out.println();
                                        continuar = false;
                                        break;
                                    default:
                                        System.out.println("Opcion no valida, vuelva a intentar");
                                        break;
                                }

                            }
                            break;
                        default:
                            System.out.println("Opcion no valida, intente de nuevo.");
                    }
                } else {
                    System.out.println("Opcion no valida o producto no disponible en estos momentos.");
                }

            }

        }

    }

}
