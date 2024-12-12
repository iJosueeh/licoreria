/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistas.login;

import controladores.usuarios.UsuarioDAO;
import java.util.Scanner;
import modelos.usuarios.Usuario;
import servicios.animaciones.Animaciones;
import static servicios.validaciones.ValidarCorreo.isValidEmail;
import static vistas.admin.MenuGestion.mostrarMenuGestion;
import static vistas.cliente.MenuInicial.mostrarMenuInicial;

/**
 *
 * @author HOME
 */
public class LoginAdmin {

    public static void loginAdmin() throws InterruptedException, Exception {

        boolean continuar = true;
        Scanner scanner = new Scanner(System.in);

        while (continuar) {

            String correo_electronico = "";
            String contraseña = "";

            System.out.println("\n────────────────────────────────────────");
            System.out.println("\t      INICIAR SESION");
            System.out.println("Por favor ingresa los datos solicitados");
            System.out.println("────────────────────────────────────────");

            while (correo_electronico.isEmpty() || !isValidEmail(correo_electronico)) {
                System.out.print("1. Correo Electrónico: ");
                correo_electronico = scanner.nextLine();

                if (correo_electronico.isEmpty()) {
                    System.out.println("El correo electrónico no puede estar vacío.");
                } else if (!isValidEmail(correo_electronico)) {
                    System.out.println("Por favor, ingresa un correo electrónico válido.");
                }
            }

            while (contraseña.isEmpty()) {
                System.out.print("2. Contraseña: ");
                contraseña = scanner.nextLine();

                if (contraseña.isEmpty()) {
                    System.out.println("La contraseña no puede estar vacía.");
                }
            }

            UsuarioDAO usuarioDAO = new UsuarioDAO();

            Usuario usuarioLogueado = usuarioDAO.loginAdmin(correo_electronico, contraseña);

            if (usuarioLogueado != null) {
                Animaciones.cargandoAnimacion();
                System.out.println();
                Usuario.setUsuarioActual(usuarioLogueado);
                Usuario.setLoggedIn(true);
                System.out.println("\nInicio de Sesion completado exitosamente.");
                System.out.println("\n¡Bienvenido " + usuarioLogueado.getNombreUsuario() + "!");
                mostrarMenuGestion();
                continuar = false;
            } else {
                System.out.println("\nERROR: Inicio de Sesion no realizado exitosamente.");
                break;
            }

        }

    }

}
