/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios.listas;

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class ListarIterator {

    public static <T> Iterator<T> obtenerIterador(List<T> lista) {
        return lista.iterator();
    }

    public static <T> void IteratorFiltro(List<T> lista, Predicate<T> filtro) {
        lista.stream()
                .filter(filtro)
                .forEach(elemento -> System.out.println(elemento));
    }

}
