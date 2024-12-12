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
import java.util.ArrayList;
import java.util.List;
import modelos.carrito.CarritoDetalle;

public class CarritoDetallesDAO implements ICRUD<CarritoDetalle> {

    ConexionDAO cn = new ConexionDAO();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public CarritoDetalle obtenerPorCarritoYProducto(int idCarrito, int idProducto) throws Exception {
        CarritoDetalle carritoDetalle = null;
        String sql = "SELECT id, id_carrito, id_producto, cantidad, subtotal "
                + "FROM CarritoDetalle "
                + "WHERE id_carrito = ? AND id_producto = ?";

        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idCarrito);
            ps.setInt(2, idProducto);
            rs = ps.executeQuery();

            if (rs.next()) {
                carritoDetalle = new CarritoDetalle();
                carritoDetalle.setId(rs.getInt("id"));
                carritoDetalle.setIdCarrito(rs.getInt("id_carrito"));
                carritoDetalle.setIdProducto(rs.getInt("id_producto"));
                carritoDetalle.setCantidad(rs.getInt("cantidad"));
                carritoDetalle.setSubtotal(rs.getDouble("subtotal"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al obtener el detalle del carrito");
        }
        return carritoDetalle;
    }

    public List<CarritoDetalle> obtenerDetallesPorCarrito(int idCarrito) throws Exception {
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
                CarritoDetalle carritoDetalle = new CarritoDetalle();
                carritoDetalle.setId(rs.getInt("id"));
                carritoDetalle.setIdCarrito(rs.getInt("id_carrito"));
                carritoDetalle.setIdProducto(rs.getInt("id_producto"));
                carritoDetalle.setCantidad(rs.getInt("cantidad"));
                carritoDetalle.setSubtotal(rs.getDouble("subtotal"));

                detalles.add(carritoDetalle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detalles;
    }

    public int contarDetallesPorCarrito(int idCarrito) throws Exception {
        String sql = "SELECT COUNT(*) FROM CarritoDetalle WHERE id_carrito = ?";
        int count = 0;

        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idCarrito);

            rs = ps.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public List<CarritoDetalle> obtenerPorCarrito(int idCarrito) throws Exception {
        String sql = "SELECT * FROM CarritoDetalle WHERE id_carrito = ?";
        List<CarritoDetalle> detalles = new ArrayList<>();
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

    public boolean eliminarPorCarritoYProducto(int idCarrito, int idProducto) throws Exception {
        String sql = "DELETE FROM CarritoDetalle WHERE id_carrito = ? AND id_producto = ?";
        boolean exito = false;

        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idCarrito);
            ps.setInt(2, idProducto);

            int filasEliminadas = ps.executeUpdate();

            if (filasEliminadas > 0) {
                exito = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al eliminar el detalle del carrito");
        }
        return exito;
    }

    @Override
    public boolean insertar(CarritoDetalle tabla) throws Exception {
        String sql = "INSERT INTO CarritoDetalle (id_carrito, id_producto, cantidad, subtotal) "
                + "VALUES (?, ?, ?, ?)";
        boolean exito = false;

        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);

            ps.setInt(1, tabla.getIdCarrito());
            ps.setInt(2, tabla.getIdProducto());
            ps.setInt(3, tabla.getCantidad());
            ps.setDouble(4, tabla.getSubtotal());

            int filasInsertadas = ps.executeUpdate();

            exito = filasInsertadas > 0;
            return exito;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exito;
    }

    @Override
    public boolean actualizar(CarritoDetalle entidad) throws Exception {
        String sql = "UPDATE CarritoDetalle SET cantidad = ?, subtotal = ? WHERE id = ?";
        boolean exito = false;

        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);

            ps.setInt(1, entidad.getCantidad());
            ps.setDouble(2, entidad.getSubtotal());
            ps.setInt(3, entidad.getId());

            int filasActualizadas = ps.executeUpdate();
            exito = filasActualizadas > 0;
            return exito;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exito;
    }

    @Override
    public boolean eliminar(int id) throws Exception {
        String sql = "DELETE FROM CarritoDetalle WHERE id = ?";
        boolean exito = false;

        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            int filasEliminadas = ps.executeUpdate();
            exito = filasEliminadas > 0;
            return exito;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exito;
    }

    @Override
    public CarritoDetalle obtenerPorId(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<CarritoDetalle> listarTodos() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
