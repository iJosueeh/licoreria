/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistas.producto;

import controladores.productos.ReseñasDAO;
import java.time.LocalDateTime;
import java.util.Scanner;
import modelos.productos.Reseñas;
import modelos.usuarios.Usuario;
import servicios.animaciones.Animaciones;
import static vistas.producto.VerDetalles.verDetallesProductos;

/**
 *
 * @author HOME
 */
public class CrearReseña {

    public static void nuevaReseña(int id) throws Exception {

        Usuario usuarioActual = Usuario.getUsuarioActual();
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            String comentario = "";
            int calificacion = 0;

            System.out.println("\n────────────────────────────────────────");

            while (true) {
                try {
                    System.out.print("1. Calificación (1 a 5): ");
                    calificacion = Integer.parseInt(scanner.nextLine());

                    if (calificacion < 1 || calificacion > 5) {
                        System.out.println("La calificación debe estar entre 1 y 5.");
                    } else {
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Por favor, ingrese un número válido.");
                }
            }

            System.out.print("2. Comentario: ");
            comentario = scanner.nextLine();

            while (comentario.trim().isEmpty()) {
                System.out.println("El comentario no puede estar vacío.");
                System.out.print("2. Comentario: ");
                comentario = scanner.nextLine();
            }

            Reseñas nuevaReseña = new Reseñas(usuarioActual.getId(), id, calificacion, comentario);

            LocalDateTime fecha = LocalDateTime.now();
            nuevaReseña.setFecha(fecha);

            ReseñasDAO reseñaDAO = new ReseñasDAO();
            boolean consultaExito = reseñaDAO.insertar(nuevaReseña);

            if (consultaExito) {
                Animaciones.cargandoAnimacion();
                System.out.println("¡Gracias por tu reseña, nos ayudas a mejorar!");
            } else {
                System.out.println("ERROR: Hubo un error al guardar la reseña, intente de nuevo.");
            }

            System.out.println("\n¿Quieres añadir otra reseña o regresar al menú anterior?");
            System.out.println("1. Añadir otra reseña");
            System.out.println("2. Regresar al menú principal");
            System.out.print("Indique la opción adecuada: ");

            int opcion = 0;
            while (true) {
                try {
                    opcion = Integer.parseInt(scanner.nextLine());
                    switch (opcion) {
                        case 1:
                            Animaciones.cargandoAnimacion();
                            continuar = true;
                            break;
                        case 2:
                            Animaciones.cargandoAnimacion();
                            System.out.println("Regresando al menú anterior...");
                            verDetallesProductos(id);
                            continuar = false;
                            break;
                        default:
                            System.out.println("Opción no válida, intente de nuevo.");
                            continuar = true;
                            break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Por favor, ingrese una opción válida.");
                }
            }
        }

    }

}
