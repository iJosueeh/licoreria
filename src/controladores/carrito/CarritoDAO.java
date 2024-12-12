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
import modelos.carrito.Carrito;
import java.sql.*;
import java.util.ArrayList;
import modelos.carrito.CarritoDetalle;

public class CarritoDAO implements ICRUD<Carrito> {

    ConexionDAO cn = new ConexionDAO();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    CarritoDetallesDAO carritoDetalleDAO = new CarritoDetallesDAO();

    @Override
    public boolean insertar(Carrito tabla) throws Exception {
        String sql = "INSERT INTO Carrito (id_usuario, fecha_creacion, estado) VALUES (?, ?, ?)";
        boolean exito = false;

        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, tabla.getIdUsuario());
            ps.setDate(2, tabla.getFechaCreacion());
            ps.setString(3, tabla.getEstado());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int idGenerado = rs.getInt(1);
                    tabla.setId(idGenerado);
                    exito = true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al registrar el carrito: " + e.getMessage());
            e.printStackTrace();
        }
        return exito;
    }

    @Override
    public boolean actualizar(Carrito entidad) throws Exception {
        String sql = "UPDATE Carrito SET id_usuario = ?, fecha_creacion = ?, estado = ? WHERE id = ?";
        boolean exito = false;

        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);

            ps.setInt(1, entidad.getIdUsuario());
            ps.setDate(2, entidad.getFechaCreacion());
            ps.setString(3, entidad.getEstado());
            ps.setInt(4, entidad.getId());

            int filasAfectadas = ps.executeUpdate();
            exito = filasAfectadas > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar el carrito: " + e.getMessage());
            e.printStackTrace();
        }
        return exito;
    }

    @Override
    public boolean eliminar(int id) throws Exception {
        String sql = "DELETE FROM Carrito WHERE id = ?";
        boolean exito = false;

        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            int filasAfectadas = ps.executeUpdate();
            exito = filasAfectadas > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar el carrito: " + e.getMessage());
            e.printStackTrace();
        }
        return exito;
    }

    @Override
    public Carrito obtenerPorId(int id) throws Exception {
        String sqlCarrito = "SELECT * FROM Carrito WHERE id = ?";
        String sqlDetalles = "SELECT * FROM CarritoDetalle WHERE id_carrito = ?";
        Carrito carrito = null;

        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sqlCarrito);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                carrito = new Carrito();
                carrito.setId(rs.getInt("id"));
                carrito.setIdUsuario(rs.getInt("id_usuario"));
                carrito.setFechaCreacion(rs.getDate("fecha_creacion"));
                carrito.setEstado(rs.getString("estado"));

                List<CarritoDetalle> detalles = new ArrayList<>();
                ps = con.prepareStatement(sqlDetalles);
                ps.setInt(1, id);
                rs = ps.executeQuery();

                while (rs.next()) {
                    CarritoDetalle detalle = new CarritoDetalle();
                    detalle.setId(rs.getInt("id"));
                    detalle.setIdCarrito(rs.getInt("id_carrito"));
                    detalle.setIdProducto(rs.getInt("id_producto"));
                    detalle.setCantidad(rs.getInt("cantidad"));
                    detalle.setSubtotal(rs.getDouble("subtotal"));
                    detalles.add(detalle);
                }

                carrito.setDetalles(detalles);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener el carrito por ID: " + e.getMessage());
            e.printStackTrace();
        }
        return carrito;
    }

    @Override
    public List<Carrito> listarTodos() throws Exception {
        String sqlCarrito = "SELECT * FROM Carrito";
        String sqlDetalles = "SELECT * FROM CarritoDetalle WHERE id_carrito = ?";
        List<Carrito> carritos = new ArrayList<>();

        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sqlCarrito);
            rs = ps.executeQuery();

            while (rs.next()) {
                Carrito carrito = new Carrito();
                carrito.setId(rs.getInt("id"));
                carrito.setIdUsuario(rs.getInt("id_usuario"));
                carrito.setFechaCreacion(rs.getDate("fecha_creacion"));
                carrito.setEstado(rs.getString("estado"));

                List<CarritoDetalle> detalles = new ArrayList<>();
                PreparedStatement psDetalles = con.prepareStatement(sqlDetalles);
                psDetalles.setInt(1, carrito.getId());
                ResultSet rsDetalles = psDetalles.executeQuery();

                while (rsDetalles.next()) {
                    CarritoDetalle detalle = new CarritoDetalle();
                    detalle.setId(rsDetalles.getInt("id"));
                    detalle.setIdCarrito(rsDetalles.getInt("id_carrito"));
                    detalle.setIdProducto(rsDetalles.getInt("id_producto"));
                    detalle.setCantidad(rsDetalles.getInt("cantidad"));
                    detalle.setSubtotal(rsDetalles.getDouble("subtotal"));
                    detalles.add(detalle);
                }

                rsDetalles.close();
                psDetalles.close();

                carrito.setDetalles(detalles);
                carritos.add(carrito);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar todos los carritos: " + e.getMessage());
            e.printStackTrace();
        }
        return carritos;
    }

    public Carrito obtenerCarritoPorUsuario(int idUsuario) {
        Carrito carrito = null;
        String sql = "SELECT c.id, c.id_usuario, c.estado, c.fecha_creacion "
                + "FROM Carrito c "
                + "WHERE c.id_usuario = ? AND c.estado = 'pendiente'";

        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            rs = ps.executeQuery();

            if (rs.next()) {
                carrito = new Carrito();
                carrito.setId(rs.getInt("id"));
                carrito.setIdUsuario(rs.getInt("id_usuario"));
                carrito.setEstado(rs.getString("estado"));
                carrito.setFechaCreacion(rs.getDate("fecha_creacion"));

                List<CarritoDetalle> detalles = carritoDetalleDAO.obtenerPorCarrito(carrito.getId());
                carrito.setDetalles(detalles);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return carrito;
    }

    private List<CarritoDetalle> obtenerDetallesDelCarrito(int idCarrito) {
        List<CarritoDetalle> detalles = new ArrayList<>();
        String sql = "SELECT id, id_carrito, id_producto, cantidad, subtotal "
                + "FROM CarritoDetalle "
                + "WHERE id_carrito = ?";

        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idCarrito);
            rs = ps.executeQuery();

            while (rs.next()) {
                CarritoDetalle detalle = new CarritoDetalle();
                detalle.setId(rs.getInt("id"));
                detalle.setIdCarrito(rs.getInt("id_carrito"));
                detalle.setIdProducto(rs.getInt("id_producto"));
                detalle.setCantidad(rs.getInt("cantidad"));
                detalle.setSubtotal(rs.getDouble("subtotal"));

                detalles.add(detalle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detalles;
    }

}
