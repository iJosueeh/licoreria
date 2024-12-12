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

/**
 *
 * @author HOME
 */
public class RegistarUsuario {

    public static void registrarUsuario() throws InterruptedException {

        boolean continuar = true;
        Scanner scanner = new Scanner(System.in);

        String nombreCompleto = "";
        String nombreUsuario = "";
        String correoElectronico = "";
        String contraseña = "";
        String confirmarContraseña = "";

        while (continuar) {
            System.out.println("\n────────────────────────────────────────");
            System.out.println("\t      REGISTRARSE");
            System.out.println("Por favor ingresa los datos solicitados");
            System.out.println("────────────────────────────────────────");

            // Validación del nombre completo
            while (nombreCompleto.isEmpty()) {
                System.out.print("1. Nombre Completo: ");
                nombreCompleto = scanner.nextLine();
                if (nombreCompleto.isEmpty()) {
                    System.out.println("El nombre completo no puede estar vacío.");
                }
            }

            // Validación del nombre de usuario
            while (nombreUsuario.isEmpty()) {
                System.out.print("2. Nombre de Usuario: ");
                nombreUsuario = scanner.nextLine();
                if (nombreUsuario.isEmpty()) {
                    System.out.println("El nombre de usuario no puede estar vacío.");
                }
            }

            // Validación del correo electrónico
            while (correoElectronico.isEmpty() || !isValidEmail(correoElectronico)) {
                System.out.print("3. Correo Electrónico: ");
                correoElectronico = scanner.nextLine();
                if (correoElectronico.isEmpty()) {
                    System.out.println("El correo electrónico no puede estar vacío.");
                } else if (!isValidEmail(correoElectronico)) {
                    System.out.println("Por favor, ingresa un correo electrónico válido.");
                }
            }

            // Validación de la contraseña
            while (contraseña.isEmpty()) {
                System.out.print("4. Contraseña: ");
                contraseña = scanner.nextLine();
                if (contraseña.isEmpty()) {
                    System.out.println("La contraseña no puede estar vacía.");
                }
            }

            // Validación de la confirmación de contraseña
            while (confirmarContraseña.isEmpty() || !confirmarContraseña.equals(contraseña)) {
                System.out.print("5. Confirmar contraseña: ");
                confirmarContraseña = scanner.nextLine();
                if (confirmarContraseña.isEmpty()) {
                    System.out.println("La confirmación de la contraseña no puede estar vacía.");
                } else if (!confirmarContraseña.equals(contraseña)) {
                    System.out.println("Las contraseñas no coinciden. Intenta de nuevo.");
                }
            }

            // Crear un nuevo usuario y registrar
            Usuario nuevoUsuario = new Usuario(nombreCompleto, nombreUsuario, correoElectronico, confirmarContraseña);

            if (nuevoUsuario != null) {
                UsuarioDAO usuarioDAO = new UsuarioDAO();

                boolean exitoRegistro = usuarioDAO.registrarUsuario(nuevoUsuario);

                if (exitoRegistro) {
                    Animaciones.cargandoAnimacion();
                    System.out.println();
                    Usuario.setUsuarioActual(nuevoUsuario);
                    Usuario.setLoggedIn(true);
                    System.out.println("\nRegistro completado exitosamente.");
                    continuar = false;
                } else {
                    System.out.println("\nERROR: Registro no realizado exitosamente.");
                }
            }
        }
    }
}
