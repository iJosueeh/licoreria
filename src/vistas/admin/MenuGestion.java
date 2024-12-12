/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistas.admin;

import vistas.cliente.*;
import controladores.productos.ProductoDAO;
import java.util.List;
import java.util.Scanner;
import modelos.productos.Producto;
import modelos.usuarios.Usuario;
import servicios.animaciones.Animaciones;
import servicios.carrito.CarritoServicios;
import static servicios.listas.MostrarPaginas.gestionarProductos;
import static servicios.listas.MostrarPaginas.mostrarProductos;
import static vistas.admin.VerFacturas.verFacturas;
import static vistas.admin.VerReseñas.verReseñas;
import static vistas.cliente.VerPerfil.verPerfil;

public class MenuGestion {

    public static void mostrarMenuGestion() throws InterruptedException, Exception {

        Usuario usuarioActual = Usuario.getUsuarioActual();
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;
        ProductoDAO productoDAO = new ProductoDAO();
        CarritoServicios carritoServicios = new CarritoServicios();

        while (continuar) {
            System.out.println("\n────────────────────────────────────────");
            System.out.println("\t   MENU PRINCIPAL - ADMIN");
            System.out.println("────────────────────────────────────────");
            System.out.println("1. Gestionar Productos");
            System.out.println("2. Revisar Reseñas de Usuarios");
            System.out.println("3. Ver Facturas Emitidas");
            System.out.println("4. Ver Perfil");
            System.out.println("5. Cerrar Sesion");
            System.out.println("────────────────────────────────────────");
            System.out.print("Indique la opcion a elegir: ");
            int opcion = 0;

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
                continue;
            }

            switch (opcion) {
                case 1:
                    List<Producto> productos = productoDAO.listarTodos();
                    Animaciones.cargandoAnimacion();
                    gestionarProductos(productos);
                    break;
                case 2:
                    Animaciones.cargandoAnimacion();
                    verReseñas();
                    break;
                case 3:
                    Animaciones.cargandoAnimacion();
                    verFacturas();
                    break;
                case 4:
                    Animaciones.cargandoAnimacion();
                    verPerfil();
                    break;
                case 5:
                    System.out.println("Regresando al menu inicial..");
                    Animaciones.cargandoAnimacion();
                    continuar = false;
                    break;
            }

        }

    }

}
