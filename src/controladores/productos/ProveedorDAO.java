/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores.productos;

import controladores.interfaces.ICRUD;
import controladores.interfaces.IProveedorDAO;
import dao.ConexionDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelos.productos.Proveedor;

public class ProveedorDAO implements ICRUD<Proveedor>, IProveedorDAO {

    ConexionDAO cn = new ConexionDAO();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    @Override
    public boolean insertar(Proveedor tabla) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean actualizar(Proveedor entidad) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean eliminar(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Proveedor obtenerPorId(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Proveedor> listarTodos() throws Exception {
        List<Proveedor> proveedores = new ArrayList<>();
        String sql = "SELECT * FROM Proveedor";

        try {

            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Proveedor proveedor = new Proveedor();
                proveedor.setId(rs.getInt("id"));
                proveedor.setNombre_proveedor(rs.getString("nombre_proveedor"));
                proveedor.setContacto(rs.getString("contacto"));
                proveedor.setTelefono(rs.getString("telefono"));
                proveedor.setCorreo_electronico(rs.getString("correo_electronico"));
                proveedor.setDireccion(rs.getString("direccion"));

                proveedores.add(proveedor);
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return proveedores;
    }

    @Override
    public Proveedor obtenerProveedorPorId(int idProveedor) {
        String sql = "SELECT * FROM Proveedor WHERE id = ?";
        Proveedor proveedor = null;

        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                proveedor = new Proveedor();

                proveedor.setId(rs.getInt("id"));
                proveedor.setNombre_proveedor(rs.getString("nombre_proveedor"));
                proveedor.setContacto(rs.getString("contacto"));
                proveedor.setTelefono(rs.getString("telefono"));
                proveedor.setTelefono(rs.getString("correo_electronico"));
                proveedor.setDireccion(rs.getString("direccion"));
                proveedor.setEstado(rs.getString("estado"));
            }
            return proveedor;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

    @Override
    public Proveedor obtenerProveedorPorNombre(String nombre) {
        String sql = "SELECT * FROM Proveedor WHERE nombre_proveedor = ?";
        Proveedor proveedor = null;

        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                proveedor.setId(rs.getInt("id"));
                proveedor.setNombre_proveedor(rs.getString("nombre_proveedor"));
                proveedor.setContacto(rs.getString("contacto"));
                proveedor.setTelefono(rs.getString("telefono"));
                proveedor.setCorreo_electronico(rs.getString("correo_electronico"));
                proveedor.setDireccion(rs.getString("direccion"));
                proveedor.setEstado(rs.getString("estado"));
            }
            return proveedor;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

}
