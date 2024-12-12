/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controladores.interfaces;

import java.util.List;

public interface ICRUD<T> {

    public boolean insertar(T tabla) throws Exception;
    boolean actualizar(T entidad) throws Exception;
    boolean eliminar(int id) throws Exception;
    T obtenerPorId(int id) throws Exception;
    List<T> listarTodos() throws Exception;
}
