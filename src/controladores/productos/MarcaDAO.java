/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores.productos;

import controladores.interfaces.ICRUD;
import controladores.interfaces.IMarcaDAO;
import dao.ConexionDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelos.productos.Marca;

/**
 *
 * @author HOME
 */
public class MarcaDAO implements ICRUD<Marca>, IMarcaDAO {

    ConexionDAO cn = new ConexionDAO();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    @Override
    public boolean insertar(Marca tabla) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean actualizar(Marca entidad) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean eliminar(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Marca obtenerPorId(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Marca> listarTodos() throws Exception {
        List<Marca> marcas = new ArrayList<>();

        String sql = "SELECT * FROM Marca";

        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Marca marca = new Marca();
                marca.setId(rs.getInt("id"));
                marca.setNombre_marca(rs.getString("nombre_marca"));
                marca.setDescripcion(rs.getString("descripcion"));
                marca.setPais(rs.getString("pais_origen"));
                marca.setEstado(rs.getString("estado"));

                marcas.add(marca);
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return marcas;
    }

    @Override
    public List<Marca> obtenerTodosLosMarca() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Marca obtenerMarcaPorId(int idMarca) {
        String sql = "SELECT * FROM Marca WHERE id = ?";
        Marca marca = null;

        try {

            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                marca = new Marca();

                marca.setId(rs.getInt("id"));
                marca.setNombre_marca(rs.getString("nombre_marca"));
                marca.setDescripcion(rs.getString("descripcion"));
                marca.setPais(rs.getString("pais_origen"));
                marca.setEstado(rs.getString("estado"));
            }
            return marca;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

    @Override
    public Marca obtenerMarcaPorNombre(String nombre) {
        String sql = "SELECT * FROM Marca WHERE nombre_marca = ?";
        Marca marca = null;

        try {

            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                marca = new Marca();

                marca.setId(rs.getInt("id"));
                marca.setNombre_marca(rs.getString("nombre_marca"));
                marca.setDescripcion(rs.getString("descripcion"));
                marca.setPais(rs.getString("pais_origen"));
                marca.setEstado(rs.getString("estado"));
            }
            return marca;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

}
