/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistas.admin;

import controladores.carrito.FacturaDAO;
import controladores.usuarios.UsuarioDAO;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import modelos.carrito.Factura;
import modelos.usuarios.Usuario;
import servicios.animaciones.Animaciones;
import static servicios.listas.ListarIterator.obtenerIterador;

/**
 *
 * @author HOME
 */
public class VerFacturas {

    public static void verFacturas() throws Exception {

        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;
        FacturaDAO facturaDAO = new FacturaDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        while (continuar) {

            System.out.println("\n────────────────────────────────────────");
            System.out.println("\t   FACTURAS ACTUALES");
            System.out.println("────────────────────────────────────────");
            List<Factura> facturas = facturaDAO.listarTodos();

            Iterator<Factura> iteradorFacturas = obtenerIterador(facturas);

            while (iteradorFacturas.hasNext()) {
                Factura factura = iteradorFacturas.next();
                Usuario usuario = usuarioDAO.obtenerPorId(factura.getId_usuario());
                System.out.println("Usuario: " + usuario.getNombreUsuario());
                System.out.println("Carrito ID: " + factura.getId_carrito());
                System.out.println("Total: " + factura.getTotal());
                System.out.println("Fecha: " + factura.getFecha());
                System.out.println("────────────────────────────────────────");

            }

            System.out.println("Presiona cualquier letra para salir del menu..");
            scanner.nextLine();
            Animaciones.cargandoAnimacion();
            continuar = false;

        }

    }

}
