/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controladores.interfaces;

import java.util.List;
import modelos.productos.Proveedor;

public interface IProveedorDAO {
    public Proveedor obtenerProveedorPorId(int idProveedor);
    public Proveedor obtenerProveedorPorNombre(String nombre);
}
