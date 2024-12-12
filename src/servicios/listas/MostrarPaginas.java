/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios.listas;

import controladores.productos.CategoriaDAO;
import controladores.productos.MarcaDAO;
import controladores.productos.ProductoDAO;
import controladores.productos.ProveedorDAO;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import modelos.productos.Marca;
import modelos.productos.Producto;
import modelos.usuarios.Usuario;
import servicios.animaciones.Animaciones;
import servicios.carrito.CarritoServicios;
import static servicios.listas.ListarIterator.obtenerIterador;
import vistas.producto.VerDetalles;
import modelos.productos.Proveedor;
import modelos.productos.Categoria;

public class MostrarPaginas {

    public static void mostrarProductos(List<Producto> productos) throws InterruptedException, Exception {
        Scanner scanner = new Scanner(System.in);
        int totalProductos = productos.size();
        int pagina = 0;
        int maximaCantidad = 5;
        ProductoDAO productoDAO = new ProductoDAO();
        Usuario usuarioActual = Usuario.getUsuarioActual();
        CarritoServicios carritoServicios = new CarritoServicios();

        while (true) {
            int inicio = pagina * maximaCantidad;
            int fin = Math.min(inicio + maximaCantidad, totalProductos);

            if (inicio >= totalProductos) {
                System.out.println("No hay más productos para mostrar.");
                break;
            }

            System.out.println("\n────────────────────────────────────────");
            System.out.println("Página " + (pagina + 1) + " de " + ((totalProductos + maximaCantidad - 1) / maximaCantidad));

            for (int i = inicio; i < fin; i++) {
                Producto producto = productos.get(i);
                System.out.println((i + 1) + ". " + producto.getNombre()
                        + " - Precio: " + producto.getPrecio()
                        + " - Categoria: " + producto.getCategoria()
                        + " - Marca: " + producto.getMarca());
            }
            System.out.println("────────────────────────────────────────");

            System.out.println("\nOpciones: [N] Siguiente [P] Anterior [E] Salir");
            System.out.println("          [B] Comprar [D] Ver Detalles Productos");
            System.out.print("Indique la opcion adecuada: ");
            String opcion = scanner.nextLine().toUpperCase();

            if (opcion.equals("N") && fin < totalProductos) {
                pagina++;
            } else if (opcion.equals("P") && pagina > 0) {
                pagina--;
            } else if (opcion.equals("E")) {
                System.out.println("Saliendo del menú de productos...");
                Animaciones.cargandoAnimacion();     
                return;
            } else if (opcion.equals("B")) {
                Animaciones.cargandoAnimacion();
                System.out.println("\n────────────────────────────────────────");
                System.out.println("Ingrese el ID del producto que desea comprar:");
                int idProducto = 0;
                try {
                    idProducto = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida para la ID del producto. Debe ser un número.");
                    break;
                }

                Producto productoDetalles = productoDAO.obtenerPorId(idProducto);
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

                                boolean exito = carritoServicios.agregarProductoAlCarrito(usuarioActual.getId(), idProducto, cantidad);
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
                    System.out.println("No se encontró el producto con ID: " + idProducto);
                }

            } else if (opcion.equals("D")) {
                Animaciones.cargandoAnimacion();
                System.out.print("\nIndique el ID del producto: ");
                int idProducto = 0;
                try {
                    idProducto = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Por favor, ingrese un número válido.");
                    continue;
                }
                VerDetalles.verDetallesProductos(idProducto);
            } else {
                System.out.println("Opción inválida.");
            }

        }

    }

    public static void gestionarProductos(List<Producto> productos) throws InterruptedException, Exception {
        Scanner scanner = new Scanner(System.in);
        int totalProductos = productos.size();
        int pagina = 0;
        int maximaCantidad = 5;
        ProductoDAO productoDAO = new ProductoDAO();
        Usuario usuarioActual = Usuario.getUsuarioActual();
        CarritoServicios carritoServicios = new CarritoServicios();
        MarcaDAO marcaDAO = new MarcaDAO();
        ProveedorDAO proveedorDAO = new ProveedorDAO();
        CategoriaDAO categoriaDAO = new CategoriaDAO();

        while (true) {
            int inicio = pagina * maximaCantidad;
            int fin = Math.min(inicio + maximaCantidad, totalProductos);

            if (inicio >= totalProductos) {
                System.out.println("No hay más productos para mostrar.");
                break;
            }

            System.out.println("\n────────────────────────────────────────");
            System.out.println("Página " + (pagina + 1) + " de " + ((totalProductos + maximaCantidad - 1) / maximaCantidad));

            for (int i = inicio; i < fin; i++) {
                Producto producto = productos.get(i);
                System.out.println((i + 1) + ". " + producto.getNombre()
                        + " - Precio: " + producto.getPrecio()
                        + " - Categoria: " + producto.getCategoria()
                        + " - Marca: " + producto.getMarca());
            }
            System.out.println("────────────────────────────────────────");

            System.out.println("\nOpciones: [N] Siguiente [P] Anterior [E] Salir");
            System.out.println("          [A] Añadir Producto [M] Modificar Producto [DP] Eliminar Producto");
            System.out.println("          [D] Ver Detalles Producto");
            System.out.print("Indique la opcion adecuada: ");
            String opcion = scanner.nextLine().toUpperCase();

            switch (opcion) {
                case "N":
                    if (fin < totalProductos) {
                        pagina++;
                    } else {
                        System.out.println("No hay más páginas disponibles.");
                    }
                    break;
                case "P":
                    if (pagina > 0) {
                        pagina--;
                    } else {
                        System.out.println("Estás en la primera página.");
                    }
                    break;
                case "E":
                    System.out.println("Saliendo del menú de gestionar productos...");
                    Animaciones.cargandoAnimacion();
                    return;
                case "A":
                    boolean continuarAñadir = true;
                    while (continuarAñadir) {
                        Animaciones.cargandoAnimacion();
                        System.out.println("\n────────────────────────────────────────");
                        System.out.println("\t    AÑADIR UN PRODUCTO");
                        System.out.println("────────────────────────────────────────");

                        System.out.println("Antes de añadir un producto, considere revisar estas opciones:\n");
                        System.out.println("1. Marcas");
                        System.out.println("2. Proveedores");
                        System.out.println("3. Categorías");
                        System.out.println("4. Añadir producto");
                        System.out.println("5. Salir");
                        System.out.println("\nIndique la opción adecuada para revisar:");
                        System.out.print("O seleccione la opción para añadir un nuevo producto directamente: ");
                        String opcionGestion = scanner.nextLine().toUpperCase();

                        switch (opcionGestion) {
                            case "1":
                                Animaciones.cargandoAnimacion();
                                List<Marca> marcas = marcaDAO.listarTodos();
                                System.out.println("\n────────────────────────────────────────");
                                System.out.println("\t  MARCAS DISPONIBLES");
                                System.out.println("────────────────────────────────────────");
                                Iterator<Marca> iteradorMarcas = obtenerIterador(marcas);

                                while (iteradorMarcas.hasNext()) {
                                    Marca marca = iteradorMarcas.next();
                                    System.out.println("Nombre: " + marca.getNombre_marca());
                                    System.out.println("Descripción: " + marca.getDescripcion());
                                    System.out.println("País: " + marca.getPais());
                                    System.out.println("Estado: " + marca.getEstado());
                                    System.out.println("────────────────────────────────────────");

                                }

                                System.out.println("\nPresione cualquier tecla para regresar al menú...");
                                scanner.nextLine();

                                break;

                            case "2":
                                Animaciones.cargandoAnimacion();
                                List<Proveedor> provedores = proveedorDAO.listarTodos();
                                System.out.println("\n────────────────────────────────────────");
                                System.out.println("\t  PROVEEDORES DISPONIBLES");
                                System.out.println("────────────────────────────────────────");
                                Iterator<Proveedor> iteradorProveedor = obtenerIterador(provedores);

                                while (iteradorProveedor.hasNext()) {
                                    Proveedor proveedor = iteradorProveedor.next();
                                    System.out.println("Nombre: " + proveedor.getNombre_proveedor());
                                    System.out.println("Contacto: " + proveedor.getContacto());
                                    System.out.println("Telefono: " + proveedor.getTelefono());
                                    System.out.println("Correo electronico: " + proveedor.getCorreo_electronico());
                                    System.out.println("Direccion: " + proveedor.getDireccion());
                                    System.out.println("Estado: " + proveedor.getEstado());
                                    System.out.println("────────────────────────────────────────");
                                }

                                System.out.println("\nPresione cualquier tecla para regresar al menú...");
                                scanner.nextLine();
                                break;

                            case "3":
                                Animaciones.cargandoAnimacion();
                                List<Categoria> categorias = categoriaDAO.listarTodos();
                                System.out.println("\n────────────────────────────────────────");
                                System.out.println("\t  CATEGORIAS DISPONIBLES");
                                System.out.println("────────────────────────────────────────");
                                Iterator<Categoria> iteradorCategoria = obtenerIterador(categorias);

                                while (iteradorCategoria.hasNext()) {
                                    Categoria categoria = iteradorCategoria.next();
                                    System.out.println("Nombre: " + categoria.getNombre());
                                    System.out.println("Categoria: " + categoria.getDescripcion());
                                    System.out.println("────────────────────────────────────────");
                                }

                                System.out.println("\nPresione cualquier tecla para regresar al menú...");
                                scanner.nextLine();

                                break;
                            case "4":
                                System.out.println("Añadir un nuevo producto:");
                                try {
                                    System.out.print("Nombre del producto: ");
                                    String nombre = scanner.nextLine();

                                    System.out.print("Descripción: ");
                                    String descripcion = scanner.nextLine();

                                    System.out.print("Precio: ");
                                    double precio = Double.parseDouble(scanner.nextLine());

                                    System.out.print("Fecha de vencimiento (YYYY-MM-DD): ");
                                    java.sql.Date fechaVencimiento = java.sql.Date.valueOf(scanner.nextLine());

                                    System.out.print("Volumen de alcohol (porcentaje o N/A): ");
                                    String volumenAlcohol = scanner.nextLine();

                                    System.out.print("Categoría: ");
                                    String categoria = scanner.nextLine();

                                    System.out.print("Proveedor: ");
                                    String proveedor = scanner.nextLine();

                                    System.out.print("Marca: ");
                                    String marca = scanner.nextLine();

                                    Producto nuevoProducto = new Producto(categoria, marca, proveedor, nombre, descripcion, precio, fechaVencimiento, volumenAlcohol);

                                    boolean resultado = productoDAO.insertar(nuevoProducto);

                                    if (resultado) {
                                        System.out.println("El producto ha sido añadido exitosamente.");
                                    } else {
                                        System.out.println("No se pudo añadir el producto.");
                                    }
                                } catch (Exception e) {
                                    System.out.println("Ocurrió un error al añadir el producto: " + e.getMessage());
                                }
                                break;

                            case "5":
                                System.out.println("Saliendo del menú de añadir productos...");
                                continuarAñadir = false;
                                break;

                            default:
                                System.out.println("Opción no válida, por favor seleccione una opción válida.");
                                break;
                        }
                    }
                    break;
                case "M":
                    Animaciones.cargandoAnimacion();
                    System.out.println("\n────────────────────────────────────────");
                    System.out.println("\t    MODIFICAR UN PRODUCTO");
                    System.out.println("────────────────────────────────────────");

                    try {
                        System.out.print("Ingrese el ID del producto que desea modificar: ");
                        int idProducto = Integer.parseInt(scanner.nextLine());

                        Producto productoExistente = productoDAO.obtenerPorId(idProducto);

                        if (productoExistente == null) {
                            System.out.println("No se encontró un producto con ese ID.");
                            break;
                        }

                        System.out.println("\nDetalles actuales del producto:");
                        System.out.println("Nombre: " + productoExistente.getNombre());
                        System.out.println("Descripción: " + productoExistente.getDescripcion());
                        System.out.println("Precio: " + productoExistente.getPrecio());
                        System.out.println("Fecha de Vencimiento: " + productoExistente.getFecha_vencimiento());
                        System.out.println("Estado: " + productoExistente.getEstado());
                        System.out.println("Volumen Alcohol: " + productoExistente.getVolumenAlcohol());
                        System.out.println("Categoría: " + productoExistente.getCategoria());
                        System.out.println("Proveedor: " + productoExistente.getProveedor());
                        System.out.println("Marca: " + productoExistente.getMarca());

                        System.out.println("\nIngrese los nuevos valores (presione Enter para mantener los valores actuales):");

                        System.out.print("Nombre (" + productoExistente.getNombre() + "): ");
                        String nuevoNombre = scanner.nextLine();
                        productoExistente.setNombre(nuevoNombre.isEmpty() ? productoExistente.getNombre() : nuevoNombre);

                        System.out.print("Descripción (" + productoExistente.getDescripcion() + "): ");
                        String nuevaDescripcion = scanner.nextLine();
                        productoExistente.setDescripcion(nuevaDescripcion.isEmpty() ? productoExistente.getDescripcion() : nuevaDescripcion);

                        System.out.print("Precio (" + productoExistente.getPrecio() + "): ");
                        String nuevoPrecio = scanner.nextLine();
                        if (!nuevoPrecio.isEmpty()) {
                            productoExistente.setPrecio(Double.parseDouble(nuevoPrecio));
                        }

                        System.out.print("Fecha de vencimiento (" + productoExistente.getFecha_vencimiento() + " - YYYY-MM-DD): ");
                        String nuevaFecha = scanner.nextLine();
                        if (!nuevaFecha.isEmpty()) {
                            productoExistente.setFecha_vencimiento(java.sql.Date.valueOf(nuevaFecha));
                        }

                        System.out.print("Estado (" + productoExistente.getEstado() + "): ");
                        String nuevoEstado = scanner.nextLine();
                        productoExistente.setEstado(nuevoEstado.isEmpty() ? productoExistente.getEstado() : nuevoEstado);

                        System.out.print("Volumen Alcohol (" + productoExistente.getVolumenAlcohol() + "): ");
                        String nuevoVolumen = scanner.nextLine();
                        productoExistente.setVolumenAlcohol(nuevoVolumen.isEmpty() ? productoExistente.getVolumenAlcohol() : nuevoVolumen);

                        System.out.print("Categoría (" + productoExistente.getCategoria() + "): ");
                        String nuevaCategoria = scanner.nextLine();
                        productoExistente.setCategoria(nuevaCategoria.isEmpty() ? productoExistente.getCategoria() : nuevaCategoria);

                        System.out.print("Proveedor (" + productoExistente.getProveedor() + "): ");
                        String nuevoProveedor = scanner.nextLine();
                        productoExistente.setProveedor(nuevoProveedor.isEmpty() ? productoExistente.getProveedor() : nuevoProveedor);

                        System.out.print("Marca (" + productoExistente.getMarca() + "): ");
                        String nuevaMarca = scanner.nextLine();
                        productoExistente.setMarca(nuevaMarca.isEmpty() ? productoExistente.getMarca() : nuevaMarca);

                        boolean actualizado = productoDAO.actualizar(productoExistente);

                        if (actualizado) {
                            System.out.println("El producto se ha actualizado correctamente.");
                        } else {
                            System.out.println("Hubo un error al actualizar el producto.");
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("Error: ID o valor inválido. Por favor, ingrese datos válidos.");
                    } catch (Exception e) {
                        System.out.println("Ocurrió un error al modificar el producto: " + e.getMessage());
                    }
                    break;

                case "D":
                    Animaciones.cargandoAnimacion();
                    System.out.print("\nIndique el ID del producto: ");
                    int idProducto;
                    try {
                        idProducto = Integer.parseInt(scanner.nextLine());
                        VerDetalles.verDetallesProductos(idProducto);
                    } catch (NumberFormatException e) {
                        System.out.println("Por favor, ingrese un número válido.");
                    }
                    break;
                case "DP":
                    Animaciones.cargandoAnimacion();
                    System.out.println("\n────────────────────────────────────────");
                    System.out.println("\t    ELIMINAR UN PRODUCTO");
                    System.out.println("────────────────────────────────────────");

                    try {
                        System.out.print("Ingrese el ID del producto que desea eliminar: ");
                        int producto_id = Integer.parseInt(scanner.nextLine());

                        boolean eliminado = productoDAO.eliminar(producto_id);

                        if (eliminado) {
                            System.out.println("El producto se ha eliminado correctamente.");
                        } else {
                            System.out.println("No se pudo eliminar el producto. Verifique si el ID es correcto.");
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("Error: ID inválido. Por favor, ingrese un número válido.");
                    } catch (Exception e) {
                        System.out.println("Ocurrió un error al eliminar el producto: " + e.getMessage());
                    }
                    break;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }

        }

    }
}
