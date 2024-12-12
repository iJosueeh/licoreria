/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistas.admin;

import controladores.productos.ProductoDAO;
import controladores.productos.ReseñasDAO;
import controladores.usuarios.UsuarioDAO;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import modelos.productos.Producto;
import modelos.productos.Reseñas;
import modelos.usuarios.Usuario;
import servicios.animaciones.Animaciones;
import static servicios.listas.ListarIterator.obtenerIterador;

public class VerReseñas {

    public static void verReseñas() throws Exception {

        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;
        ReseñasDAO reseñasDAO = new ReseñasDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        ProductoDAO productoDAO = new ProductoDAO();

        while (continuar) {

            System.out.println("\n────────────────────────────────────────");
            System.out.println("\t   RESEÑAS ACTUALES");
            System.out.println("────────────────────────────────────────");
            List<Reseñas> reseñas = reseñasDAO.listarTodos();
            Iterator<Reseñas> iteradorReseñas = obtenerIterador(reseñas);

            while (iteradorReseñas.hasNext()) {
                Reseñas reseña = iteradorReseñas.next();
                Usuario usuario = usuarioDAO.obtenerPorId(reseña.getIdUsuario());
                Producto producto = productoDAO.obtenerPorId(reseña.getIdProducto());
                System.out.println("ID: " + reseña.getId());
                System.out.println("Usuario: " + usuario.getNombreUsuario());
                System.out.println("Producto: " + producto.getNombre());
                System.out.println("Calificacion: " + reseña.getCalificacion());
                System.out.println("Comentario: " + reseña.getComentario());
                System.out.println("────────────────────────────────────────");
            }
            
            System.out.println("Presiona cualquier letra para salir del menu..");
            scanner.nextLine();
            Animaciones.cargandoAnimacion();
            continuar = false;
        }

    }

}
