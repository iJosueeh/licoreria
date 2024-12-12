/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores.usuarios;

import controladores.interfaces.ICRUD;
import java.sql.*;
import java.util.List;
import dao.ConexionDAO;
import controladores.interfaces.IUsuarioDAO;
import modelos.usuarios.Usuario;
import javax.swing.JOptionPane;
import java.util.function.Consumer;

public class UsuarioDAO implements IUsuarioDAO, ICRUD<Usuario> {

    ConexionDAO cn = new ConexionDAO();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    @Override
    public Usuario loginAdmin(String correo_electronico, String contraseña) {
        Usuario administrador = null;
        String sql = "SELECT * FROM Usuario WHERE correo_electronico = ? AND contraseña = ? AND rol = 'administrador'";

        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, correo_electronico);
            ps.setString(2, contraseña);
            rs = ps.executeQuery();

            if (rs.next()) {
                administrador = new Usuario();
                administrador.setId(rs.getInt("id"));
                administrador.setNombre(rs.getString("nombre"));
                administrador.setNombreUsuario(rs.getString("nombre_usuario"));
                administrador.setCorreo_electronico(rs.getString("correo_electronico"));
                administrador.setContraseña(rs.getString("contraseña"));
                administrador.setTelefono(rs.getString("telefono"));
                administrador.setDireccion(rs.getString("direccion"));
                administrador.setEstado(rs.getString("estado"));
                administrador.setRol(rs.getString("rol"));

                Consumer<Usuario> registrarLogin = admin
                        -> System.out.println("Administrador encontrado en la base de datos.");
                registrarLogin.accept(administrador);
            } else {
                System.out.println("No se encontró un administrador con las credenciales proporcionadas.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return administrador;
    }

    @Override
    public Usuario loginUsuario(String correo_electronico, String password) {
        Usuario usuario = null;
        String sql = "SELECT * FROM Usuario WHERE correo_electronico = ? AND contraseña = ?";

        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, correo_electronico);
            ps.setString(2, password);
            rs = ps.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setNombreUsuario(rs.getString("nombre_usuario"));
                usuario.setCorreo_electronico(rs.getString("correo_electronico"));
                usuario.setContraseña(rs.getString("contraseña"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setDireccion(rs.getString("direccion"));
                usuario.setEstado(rs.getString("estado"));
                usuario.setRol(rs.getString("rol"));

                Consumer<Usuario> registrarLogin = u
                        -> System.out.println("Usuario encontrado en la base de Datos.");
                registrarLogin.accept(usuario);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuario;
    }

    @Override
    public Boolean registrarUsuario(Usuario usuario) {
        String sql = "INSERT INTO Usuario(nombre, nombre_usuario, correo_electronico, contraseña, telefono, direccion)"
                + " VALUES (?, ?, ?, ?, ?, ?)";

        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getNombreUsuario());
            ps.setString(3, usuario.getCorreo_electronico());
            ps.setString(4, usuario.getContraseña());
            ps.setString(5, usuario.getTelefono());
            ps.setString(6, usuario.getDireccion());

            int filasInsertadas = ps.executeUpdate();

            if (filasInsertadas > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        usuario.setId(rs.getInt(1));
                    }
                }
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error en el registro: " + e.toString());
        }
        return false;
    }

    @Override
    public boolean insertar(Usuario tabla) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean actualizar(Usuario entidad) throws Exception {
        String sql = "UPDATE Usuario SET nombre = ?, nombre_usuario = ?, correo_electronico = ?, "
                + "contraseña = ?, telefono = ?, direccion = ?, estado = ?, rol = ? WHERE id = ?";

        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);

            ps.setString(1, entidad.getNombre());
            ps.setString(2, entidad.getNombreUsuario());
            ps.setString(3, entidad.getCorreo_electronico());
            ps.setString(4, entidad.getContraseña());
            ps.setString(5, entidad.getTelefono());
            ps.setString(6, entidad.getDireccion());
            ps.setString(7, entidad.getEstado());
            ps.setString(8, entidad.getRol());
            ps.setInt(9, entidad.getId());

            int filasActualizadas = ps.executeUpdate();

            return filasActualizadas > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) throws Exception {
        String sql = "DELETE FROM Usuario WHERE id = ?";

        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            int filasEliminadas = ps.executeUpdate();

            return filasEliminadas > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Usuario obtenerPorId(int id) throws Exception {
        String sql = "SELECT * FROM Usuario WHERE id = ?";
        Usuario usuario = null;

        try {

            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setNombreUsuario(rs.getString("nombre_usuario"));
                usuario.setCorreo_electronico("correo_electronico");
                usuario.setContraseña(rs.getString("contraseña"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setDireccion(rs.getString("direccion"));
                usuario.setEstado(rs.getString("estado"));
                usuario.setRol(rs.getString("rol"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuario;
    }

    @Override
    public List<Usuario> obtenerUsuarios() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Usuario obtenerUsuarioPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Usuario> listarTodos() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
