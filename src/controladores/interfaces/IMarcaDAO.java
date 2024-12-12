/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controladores.interfaces;

import java.util.List;
import modelos.productos.Marca;

/**
 *
 * @author HOME
 */
public interface IMarcaDAO {
    
    public List<Marca> obtenerTodosLosMarca();
    public Marca obtenerMarcaPorId(int idMarca);
    public Marca obtenerMarcaPorNombre(String nombre);
}
