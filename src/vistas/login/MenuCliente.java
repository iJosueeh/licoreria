/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistas.login;

import static vistas.login.LoginUsuario.loginUsuario;
import static vistas.login.RegistarUsuario.registrarUsuario;
import java.util.Scanner;
import servicios.animaciones.Animaciones;

/**
 *
 * @author HOME
 */
public class MenuCliente {

    public static void loginCliente() throws InterruptedException, Exception {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        do {

            System.out.println("\n────────────────────────────────────────");
            System.out.println("\t      INICIAR SESION");
            System.out.println("¿Cuentas con una cuenta o deseas registrarte?\n");
            System.out.println("1. Ya tengo cuenta");
            System.out.println("2. Registrar una nueva cuenta");
            System.out.println("3. Salir");
            System.out.println("────────────────────────────────────────");
            System.out.print("Porfavor, indique la opcion a elegir: ");

            int opcionMenu = 0;
            try {
                opcionMenu = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
                continue;
            }

            switch (opcionMenu) {
                case 1:
                    Animaciones.cargandoAnimacion();
                    System.out.println();
                    loginUsuario();
                    break;
                case 2:
                    Animaciones.cargandoAnimacion();
                    System.out.println();
                    registrarUsuario();
                    break;
                case 3:
                    Animaciones.cargandoAnimacion();
                    System.out.println();
                    continuar = false;
                    break;
            }

        } while (continuar);

    }

}
