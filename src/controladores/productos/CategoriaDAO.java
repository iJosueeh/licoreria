/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores.productos;

import controladores.interfaces.ICRUD;
import dao.ConexionDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import modelos.productos.Categoria;
import controladores.interfaces.ICategoriaDAO;
import java.util.ArrayList;

/**
 *
 * @author HOME
 */
public class CategoriaDAO implements ICRUD<Categoria>, ICategoriaDAO {

    ConexionDAO cn = new ConexionDAO();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    @Override
    public boolean insertar(Categoria tabla) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean actualizar(Categoria entidad) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean eliminar(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Categoria obtenerPorId(int id) throws Exception {
        String sql = "SELECT * FROM Categoria WHERE id = ?";
        Categoria categoria = null;

        try {

            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                categoria = new Categoria();

                categoria.setId(rs.getInt("id"));
                categoria.setNombre(rs.getString("nombre_categoria"));
                categoria.setDescripcion(rs.getString("descripcion"));
            }
            return categoria;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

    @Override
    public List<Categoria> listarTodos() throws Exception {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT * FROM Categoria";

        try {

            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("id"));
                categoria.setNombre(rs.getString("nombre_categoria"));
                categoria.setDescripcion(rs.getString("descripcion"));
                categorias.add(categoria);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return categorias;
    }

    @Override
    public Categoria obtenerCategoriaPorId(int idCategoria) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Categoria obtenerCategoriaPorNombre(String nombre) {

        String sql = "SELECT * FROM Categoria WHERE nombre_categoria = ?";
        Categoria categoria = null;

        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                categoria = new Categoria();
                categoria.setId(rs.getInt("id"));
                categoria.setNombre(rs.getString("nombre_categoria"));
                categoria.setDescripcion(rs.getString("descripcion"));

            }
            return categoria;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

}
