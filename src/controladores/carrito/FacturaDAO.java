/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores.carrito;

import controladores.interfaces.ICRUD;
import dao.ConexionDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import modelos.carrito.Factura;
import java.sql.*;
import java.util.ArrayList;

public class FacturaDAO implements ICRUD<Factura> {

    ConexionDAO cn = new ConexionDAO();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    @Override
    public boolean insertar(Factura tabla) throws Exception {
        String sql = "INSERT INTO Factura (id_usuario, id_carrito, total, fecha) VALUES (?, ?, ?, ?)";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, tabla.getId_usuario());
            ps.setInt(2, tabla.getId_carrito());
            ps.setDouble(3, tabla.getTotal());
            ps.setTimestamp(4, Timestamp.valueOf(tabla.getFecha()));

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    tabla.setId(rs.getInt(1));
                }
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error al registrar la factura: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean actualizar(Factura entidad) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean eliminar(int id) throws Exception {
        String sql = "DELETE FROM Factura WHERE id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error al eliminar la factura: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Factura obtenerPorId(int id) throws Exception {
        String sql = "SELECT * FROM Factura WHERE id = ?";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                return new Factura(
                        rs.getInt("id_usuario"),
                        rs.getInt("id_carrito"),
                        rs.getDouble("total"),
                        rs.getTimestamp("fecha").toLocalDateTime()
                );
            }
        } catch (Exception e) {
            System.out.println("Error al obtener la factura: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Factura> listarTodos() throws Exception {
        List<Factura> facturas = new ArrayList<>();
        String sql = "SELECT * FROM Factura";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                facturas.add(new Factura(
                        rs.getInt("id_usuario"),
                        rs.getInt("id_carrito"),
                        rs.getDouble("total"),
                        rs.getTimestamp("fecha").toLocalDateTime()
                ));
            }
        } catch (Exception e) {
            System.out.println("Error al listar todas las factura: " + e.getMessage());
            e.printStackTrace();
        }
        return facturas;
    }

}
