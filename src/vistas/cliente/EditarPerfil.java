/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistas.cliente;

import controladores.usuarios.UsuarioDAO;
import java.util.Scanner;
import modelos.usuarios.Usuario;
import servicios.animaciones.Animaciones;
import servicios.paises.CodigoPais;

public class EditarPerfil {

    public static void editarPerfil() throws InterruptedException, Exception {

        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;
        Usuario usuarioActual = Usuario.getUsuarioActual();
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        while (continuar) {

            System.out.println("\n────────────────────────────────────────");
            System.out.println("\t   EDITAR PERFIL - CLIENTE");
            System.out.println("────────────────────────────────────────");
            System.out.println("¿Cual es la opcion que desea editar?:");
            System.out.println("1. Nombre Completo");
            System.out.println("2. Nombre de Usuario");
            System.out.println("3. Correo Electronico");
            System.out.println("4. Contraseña");
            System.out.println("5. Telefono");
            System.out.println("6. Direccion");
            System.out.println("7. Salir");
            System.out.println("Indique la opcion adecuada.");

            int opcion = 0;

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
                continue;
            }

            String confirmar = "";

            switch (opcion) {
                case 1:
                    Animaciones.cargandoAnimacion();
                    System.out.print("\nIndique el nuevo nombre completo: ");
                    String nombreNuevo = scanner.nextLine();

                    System.out.println("\n────────────────────────────────────────");
                    System.out.println("Valor actual: " + usuarioActual.getNombre());
                    System.out.println("Nuevo valor: " + nombreNuevo);
                    System.out.println("────────────────────────────────────────");
                    System.out.println("¿Esta de acuerdo con los cambios? [SI/NO]: ");

                    confirmar = scanner.nextLine().toUpperCase();

                    if (confirmar.equals("SI")) {
                        usuarioActual.setNombre(nombreNuevo);
                        usuarioDAO.actualizar(usuarioActual);
                        Animaciones.cargandoAnimacion();
                        System.out.println("Actualizamiento realizado con exito.");
                        break;
                    } else if (confirmar.equals("NO")) {
                        System.out.println("Volviendo al menu principal..");
                        Animaciones.cargandoAnimacion();
                    } else {
                        System.out.println("Opcion no valida.");
                    }
                    break;
                case 2:
                    Animaciones.cargandoAnimacion();
                    System.out.print("\nIndique el nuevo nombre de usuario: ");
                    String nuevoUsuario = scanner.nextLine();

                    System.out.println("\n────────────────────────────────────────");
                    System.out.println("Valor actual: " + usuarioActual.getNombreUsuario());
                    System.out.println("Nuevo valor: " + nuevoUsuario);
                    System.out.println("────────────────────────────────────────");
                    System.out.println("¿Esta de acuerdo con los cambios? [SI/NO]: ");

                    confirmar = scanner.nextLine().toUpperCase();

                    if (confirmar.equals("SI")) {
                        usuarioActual.setNombreUsuario(nuevoUsuario);
                        usuarioDAO.actualizar(usuarioActual);
                        Animaciones.cargandoAnimacion();
                        System.out.println("Actualizamiento realizado con exito.");
                    } else if (confirmar.equals("NO")) {
                        System.out.println("Volviendo al menu principal..");
                        Animaciones.cargandoAnimacion();
                    } else {
                        System.out.println("Opcion no valida.");
                    }
                    break;
                case 3:
                    Animaciones.cargandoAnimacion();
                    System.out.print("\nIndique el nuevo correo electronico: ");
                    String correoNuevo = scanner.nextLine();

                    System.out.println("\n────────────────────────────────────────");
                    System.out.println("Valor actual: " + usuarioActual.getCorreo_electronico());
                    System.out.println("Nuevo valor: " + correoNuevo);
                    System.out.println("────────────────────────────────────────");
                    System.out.println("¿Esta de acuerdo con los cambios? [SI/NO]: ");

                    confirmar = scanner.nextLine().toUpperCase();

                    if (confirmar.equals("SI")) {
                        usuarioActual.setCorreo_electronico(correoNuevo);
                        usuarioDAO.actualizar(usuarioActual);
                        Animaciones.cargandoAnimacion();
                        System.out.println("Actualizamiento realizado con exito.");
                    } else if (confirmar.equals("NO")) {
                        System.out.println("Volviendo al menu principal..");
                        Animaciones.cargandoAnimacion();
                    } else {
                        System.out.println("Opcion no valida.");
                    }
                    break;
                case 4:
                    Animaciones.cargandoAnimacion();
                    System.out.print("\nIndique la nueva contraseña: ");
                    String contraseña = scanner.nextLine();
                    System.out.println("Confirme su nueva contraseña: ");
                    String confirmarContraseña = scanner.nextLine();

                    if (contraseña.equals(confirmarContraseña)) {
                        System.out.println("\n────────────────────────────────────────");
                        System.out.println("Valor actual: " + usuarioActual.getContraseña());
                        System.out.println("Nuevo Valor: " + contraseña);
                        System.out.println("────────────────────────────────────────");
                        System.out.println("¿Esta de acuerdo con los cambios? [SI/NO]: ");

                        confirmar = scanner.nextLine().toUpperCase();

                        if (confirmar.equals("SI")) {
                            usuarioActual.setContraseña(contraseña);
                            usuarioDAO.actualizar(usuarioActual);
                            Animaciones.cargandoAnimacion();
                            System.out.println("Actualizamiento realizado con exito.");
                        } else if (confirmar.equals("NO")) {
                            System.out.println("Volviendo al menu principal..");
                            Animaciones.cargandoAnimacion();
                        } else {
                            System.out.println("Opcion no valida.");
                        }

                    } else {
                        System.out.println("Las contraseñas no coinciden.");
                        break;
                    }
                    break;
                case 5:
                    Animaciones.cargandoAnimacion();

                    System.out.print("\nIndique su pais de origen: ");
                    String pais = scanner.nextLine();

                    String codigoPais = CodigoPais.obtenerCodigo(pais);

                    System.out.print("Indique su número telefonico: ");
                    String telefono = scanner.nextLine();

                    String numeroTelefonico = codigoPais.concat(" ").concat(telefono);

                    System.out.println("\n────────────────────────────────────────");
                    System.out.println("Valor actual: " + usuarioActual.getTelefono());
                    System.out.println("Nuevo valor: " + numeroTelefonico);
                    System.out.println("────────────────────────────────────────");
                    System.out.println("¿Esta de acuerdo con los cambios? [SI/NO]: ");
                    confirmar = scanner.nextLine().toUpperCase();

                    if (confirmar.equals("SI")) {
                        usuarioActual.setTelefono(numeroTelefonico);
                        usuarioDAO.actualizar(usuarioActual);
                        Animaciones.cargandoAnimacion();
                        System.out.println("Actualizamiento realizado con exito.");
                    } else if (confirmar.equals("NO")) {
                        System.out.println("Volviendo al menu principal..");
                        Animaciones.cargandoAnimacion();
                    } else {
                        System.out.println("Opcion no valida.");
                    }
                    break;
                case 6:
                    Animaciones.cargandoAnimacion();
                    System.out.print("\nIndique su direccion actual: ");
                    String direccion = scanner.nextLine();
                    System.out.println("Indique su distrito: ");
                    String distrito = scanner.nextLine();
                    
                    String direccionCompleta = direccion.concat(", ").concat(distrito);

                    System.out.println("\n────────────────────────────────────────");
                    System.out.println("Valor actual: " + usuarioActual.getDireccion());
                    System.out.println("Nuevo valor: " + direccionCompleta);
                    System.out.println("────────────────────────────────────────");
                    System.out.println("¿Esta de acuerdo con los cambios? [SI/NO]: ");
                    confirmar = scanner.nextLine().toUpperCase();

                    if (confirmar.equals("SI")) {
                        usuarioActual.setDireccion(direccionCompleta);
                        usuarioDAO.actualizar(usuarioActual);
                        Animaciones.cargandoAnimacion();
                        System.out.println("Actualizamiento realizado con exito.");
                    } else if (confirmar.equals("NO")) {
                        System.out.println("Volviendo al menu principal..");
                        Animaciones.cargandoAnimacion();
                    } else {
                        System.out.println("Opcion no valida.");
                    }
                    break;
                case 7:
                    System.out.println("Volviendo al menú anterior...");
                    Animaciones.cargandoAnimacion();
                    continuar = false;
                    break;
                default:
                    System.out.println("Opcion no valida, intente de nuevo.");
            }

        }

    }

}
