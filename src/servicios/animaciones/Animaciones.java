/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios.animaciones;

/**
 *
 * @author HOME
 */
public class Animaciones {

    public static void cargandoAnimacion() throws InterruptedException {
        System.out.print("Cargando");
        for (int i = 0; i < 3; i++) {
            Thread.sleep(800);
            System.out.print(".");
        }
        System.out.println();
    }

}
