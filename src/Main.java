/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.Scanner;
import servicios.animaciones.Animaciones;
import static vistas.login.LoginAdmin.loginAdmin;
import vistas.login.MenuCliente;
import static vistas.login.MenuCliente.loginCliente;

public class Main {

    public static void main(String[] args) throws InterruptedException, Exception {

        MenuCliente loginCliente = new MenuCliente();

        boolean continuar = true;
        Scanner scanner = new Scanner(System.in);

        while (continuar) {
            System.out.println("\n────────────────────────────────────────");
            System.out.println("\t      LICORERIA");
            System.out.println("Seleccione la manera de iniciar sesion\n");
            System.out.println("1. Administrador");
            System.out.println("2. Cliente");
            System.out.println("3. Salir\n");
            System.out.println("Indique la opcion adecuada:");
            System.out.println("────────────────────────────────────────");

            int opcion = 0;

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
                continue;
            }

            switch (opcion) {
                case 1:
                    Animaciones.cargandoAnimacion();
                    System.out.println();
                    loginAdmin();
                    break;
                case 2:
                    Animaciones.cargandoAnimacion();
                    System.out.println();
                    loginCliente();
                    break;
                case 3:
                    Animaciones.cargandoAnimacion();
                    System.out.println();
                    System.out.println("Programa apagado, vuelva pronto!");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opcion no valida, vuelva a intentar");
                    break;
            }

        }

    }

}
