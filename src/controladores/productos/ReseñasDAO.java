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
import modelos.productos.Reseñas;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author HOME
 */
public class ReseñasDAO implements ICRUD<Reseñas> {

    ConexionDAO cn = new ConexionDAO();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    @Override
    public boolean insertar(Reseñas tabla) throws Exception {
        String sql = "INSERT INTO Reseñas (id_usuario, id_producto, calificacion, comentario, fecha) "
                + "VALUES (?, ?, ?, ?, ?)";

        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, tabla.getIdUsuario());
            ps.setInt(2, tabla.getIdProducto());
            ps.setInt(3, tabla.getCalificacion());
            ps.setString(4, tabla.getComentario());

            LocalDateTime fecha = tabla.getFecha();
            if (fecha == null) {
                fecha = LocalDateTime.now();
            }

            Timestamp timestamp = Timestamp.valueOf(fecha);
            ps.setTimestamp(5, timestamp);

            int filasInsertadas = ps.executeUpdate();
            return filasInsertadas > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean actualizar(Reseñas entidad) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean eliminar(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Reseñas obtenerPorId(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Reseñas> listarTodos() throws Exception {
        List<Reseñas> reseñas = new ArrayList<>();
        String sql = "SELECT * FROM Reseñas";

        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Reseñas reseña = new Reseñas();
                reseña.setId(rs.getInt("id"));
                reseña.setIdUsuario(rs.getInt("id_usuario"));
                reseña.setIdProducto(rs.getInt("id_producto"));
                reseña.setCalificacion(rs.getInt("calificacion"));
                reseña.setComentario(rs.getString("comentario"));
                reseña.setFecha(rs.getTimestamp("fecha").toLocalDateTime());

                reseñas.add(reseña);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reseñas;
    }

    public List<Reseñas> mostrarReseñasPorProducto(int idProducto) {
        List<Reseñas> reseñas = new ArrayList<>();

        String sql = "SELECT r.id, r.id_usuario, r.id_producto, r.calificacion, r.comentario, r.fecha, u.nombre "
                + "FROM Reseñas r "
                + "INNER JOIN Usuario u ON r.id_usuario = u.id "
                + "WHERE r.id_producto = ?";

        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idProducto);
            rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int idUsuario = rs.getInt("id_usuario");
                int id_Producto = rs.getInt("id_producto");
                int calificacion = rs.getInt("calificacion");
                String comentario = rs.getString("comentario");
                Timestamp fechaTimestamp = rs.getTimestamp("fecha");
                String nombreUsuario = rs.getString("nombre");

                LocalDateTime fecha = (fechaTimestamp != null) ? fechaTimestamp.toLocalDateTime() : LocalDateTime.now();

                Reseñas reseña = new Reseñas(idUsuario, id_Producto, calificacion, comentario);
                reseña.setId(id);
                reseña.setNombreUsuario(nombreUsuario);
                reseña.setFecha(fecha);
                reseñas.add(reseña);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return reseñas;
    }

    public double obtenerPromedioCalificacion(int id) {
        String sql = "SELECT AVG(calificacion) AS promedio FROM Reseñas WHERE id_producto = ?";
        double promedio = 0.0;

        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                promedio = rs.getDouble("promedio");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return promedio;
    }

}
