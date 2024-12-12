/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistas.cliente;

import controladores.usuarios.UsuarioDAO;
import java.util.Scanner;
import modelos.usuarios.Usuario;
import servicios.animaciones.Animaciones;
import static vistas.cliente.EditarPerfil.editarPerfil;
import static vistas.login.MenuCliente.loginCliente;

/**
 *
 * @author HOME
 */
public class VerPerfil {

    public static void verPerfil() throws InterruptedException, Exception {

        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;
        Usuario usuarioActual = Usuario.getUsuarioActual();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        
        while (continuar) {

            System.out.println("\n────────────────────────────────────────");
            System.out.println("\t   PERFIL - CLIENTE");
            System.out.println("────────────────────────────────────────");
            System.out.println("1. Nombre Completo: " + usuarioActual.getNombre());
            System.out.println("2. Nombre de Usuario: " + usuarioActual.getNombreUsuario());
            System.out.println("3. Correo Electronico: " + usuarioActual.getCorreo_electronico());
            
            if (usuarioActual.getTelefono() == null || usuarioActual.getDireccion() == null ||
                usuarioActual.getTelefono().isEmpty() || usuarioActual.getDireccion().isEmpty()) {
                
                usuarioActual.setTelefono("No proporcionado.");
                usuarioActual.setDireccion("No proporcionado.");
            }
            
            System.out.println("4. Telefono: " + usuarioActual.getTelefono());
            System.out.println("5. Direccion: " + usuarioActual.getDireccion());
            System.out.println("────────────────────────────────────────");

            System.out.println("\nOpciones: [P] Editar Perfil [D] Eliminar Cuenta [E] Salir");
            System.out.print("Indique la opcion adecuada: ");
            String opcion = scanner.nextLine().toUpperCase();

            if (opcion.equals("P")) {
                Animaciones.cargandoAnimacion();
                editarPerfil();
                break;
            } else if (opcion.equals("D")) {
                System.out.println("\n¿Estas seguro de eliminar tu cuenta?");
                System.out.println("No podrás volver a acceder a ella nuevamente.");
                System.out.print("Indique la opcion adecuada [SI/NO]: ");
                
                String opcionElimina = scanner.nextLine().toUpperCase();
                
                if (opcionElimina.equals("SI")) {
                    Animaciones.cargandoAnimacion();
                    usuarioDAO.eliminar(usuarioActual.getId());
                    System.out.println("Cuenta eliminada exitosamente.");
                    loginCliente();
                    continuar = false;
                    break;
                } else {
                    Animaciones.cargandoAnimacion();
                    System.out.println("Operacion rechazarla..");
                }
                
            } else if (opcion.equals("E")) {
                System.out.println("Saliendo del menu..");
                Animaciones.cargandoAnimacion();
                break;
            } else {
                System.out.println("Opcion no valida.");
            }
            
        }

    }

}
