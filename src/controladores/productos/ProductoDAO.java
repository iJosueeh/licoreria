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
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import modelos.productos.Categoria;
import modelos.productos.Marca;
import modelos.productos.Producto;
import modelos.productos.Proveedor;
import servicios.animaciones.Animaciones;

public class ProductoDAO implements ICRUD<Producto> {

    ConexionDAO cn = new ConexionDAO();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    @Override
    public boolean insertar(Producto producto) throws Exception {
        String sql = "INSERT INTO Producto (id_categoria, id_marca, id_proveedor, nombre, descripcion, precio, fecha_vencimiento, estado, volumen_alcohol) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);

            CategoriaDAO categoriaDAO = new CategoriaDAO();
            ProveedorDAO proveedorDAO = new ProveedorDAO();
            MarcaDAO marcaDAO = new MarcaDAO();

            Categoria categoria = categoriaDAO.obtenerCategoriaPorNombre(producto.getCategoria());
            Proveedor proveedor = proveedorDAO.obtenerProveedorPorNombre(producto.getProveedor());
            Marca marca = marcaDAO.obtenerMarcaPorNombre(producto.getMarca());

            Predicate<Categoria> validarCategoria = c -> c != null && c.getId() > 0;
            Predicate<Proveedor> validarProveedor = p -> p != null && p.getId() > 0;
            Predicate<Marca> validarMarca = m -> m != null && m.getId() > 0;

            if (!validarCategoria.test(categoria)) {
                System.out.println("La categoría no es válida.");
                return false;
            }
            if (!validarProveedor.test(proveedor)) {
                System.out.println("El proveedor no es válido.");
                return false;
            }
            if (!validarMarca.test(marca)) {
                System.out.println("La marca no es válida.");
                return false;
            }

            Consumer<Producto> logProducto = p
                    -> System.out.println("Preparando para registrar el producto: " + p.getNombre());
            logProducto.accept(producto);

            Animaciones.cargandoAnimacion();

            ps.setInt(1, categoria.getId());
            ps.setInt(2, marca.getId());
            ps.setInt(3, proveedor.getId());
            ps.setString(4, producto.getNombre());
            ps.setString(5, producto.getDescripcion());
            ps.setDouble(6, producto.getPrecio());
            ps.setDate(7, (java.sql.Date) producto.getFecha_vencimiento());
            ps.setString(8, producto.getEstado());
            ps.setString(9, producto.getVolumenAlcohol());

            int filasInsertadas = ps.executeUpdate();

            if (filasInsertadas > 0) {
                System.out.println("\nProducto registrado exitosamente.");
            } else {
                System.out.println("No se pudo registrar el producto.");
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return false;
    }

    @Override
    public boolean actualizar(Producto pntidad) throws Exception {
        String sql = "UPDATE Producto SET id_categoria = ?, id_marca = ?, id_proveedor = ?, nombre = ?, "
                + "descripcion = ?, precio = ?, fecha_vencimiento = ?, estado = ?, volumen_alcohol = ? "
                + "WHERE id = ?";

        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);

            CategoriaDAO categoriaDAO = new CategoriaDAO();
            ProveedorDAO proveedorDAO = new ProveedorDAO();
            MarcaDAO marcaDAO = new MarcaDAO();

            Categoria categoria = categoriaDAO.obtenerCategoriaPorNombre(pntidad.getCategoria());
            Proveedor proveedor = proveedorDAO.obtenerProveedorPorNombre(pntidad.getProveedor());
            Marca marca = marcaDAO.obtenerMarcaPorNombre(pntidad.getMarca());

            ps.setInt(1, categoria.getId());
            ps.setInt(2, marca.getId());
            ps.setInt(3, proveedor.getId());
            ps.setString(4, pntidad.getNombre());
            ps.setString(5, pntidad.getDescripcion());
            ps.setDouble(6, pntidad.getPrecio());
            ps.setDate(7, new java.sql.Date(pntidad.getFecha_vencimiento().getTime()));
            ps.setString(8, pntidad.getEstado());
            ps.setString(9, pntidad.getVolumenAlcohol());
            ps.setInt(10, pntidad.getId());

            int filasActualizadas = ps.executeUpdate();

            if (filasActualizadas > 0) {
                System.out.println("Producto actualizado exitosamente.");
                return true;
            } else {
                System.out.println("No se pudo actualizar el producto. Verifica el ID.");
            }

        } catch (Exception e) {
            System.out.println("Error al actualizar el producto: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean eliminar(int id) throws Exception {
        String sql = "DELETE FROM Producto WHERE id = ?";

        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int filasAfectadas = ps.executeUpdate();

            return filasAfectadas > 0;

        } catch (Exception e) {
            System.out.println("Error al eliminar el producto: " + e.getMessage());
        } 
        return false;
    }

    @Override
    public Producto obtenerPorId(int id) throws Exception {
        String sql = "SELECT p.id, c.nombre_categoria AS categoria, m.nombre_marca AS marca, pr.nombre_proveedor AS proveedor, "
                + "p.nombre, p.descripcion, p.precio, p.fecha_vencimiento, p.estado, p.volumen_alcohol "
                + "FROM Producto p "
                + "INNER JOIN Categoria c ON p.id_categoria = c.id "
                + "INNER JOIN Marca m ON p.id_marca = m.id "
                + "INNER JOIN Proveedor pr ON p.id_proveedor = pr.id "
                + "WHERE p.id = ?";

        Producto producto = null;

        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                producto = new Producto();
                producto.setId(rs.getInt("id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setFecha_vencimiento(rs.getDate("fecha_vencimiento"));
                producto.setEstado(rs.getString("estado"));
                producto.setVolumenAlcohol(rs.getString("volumen_alcohol"));

                producto.setCategoria(rs.getString("categoria"));
                producto.setMarca(rs.getString("marca"));
                producto.setProveedor(rs.getString("proveedor"));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return producto;
    }

    @Override
    public List<Producto> listarTodos() throws Exception {
        List<Producto> listaProductos = new ArrayList<>();

        String sql = "SELECT p.id, c.nombre_categoria AS categoria, m.nombre_marca AS marca, pr.nombre_proveedor AS proveedor, "
                + "p.nombre, p.descripcion, p.precio, p.fecha_vencimiento, p.estado, p.volumen_alcohol "
                + "FROM Producto p "
                + "INNER JOIN Categoria c ON p.id_categoria = c.id "
                + "INNER JOIN Marca m ON p.id_marca = m.id "
                + "INNER JOIN Proveedor pr ON p.id_proveedor = pr.id ";

        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId(rs.getInt("id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setFecha_vencimiento(rs.getDate("fecha_vencimiento"));
                producto.setEstado(rs.getString("estado"));
                producto.setVolumenAlcohol(rs.getString("volumen_alcohol"));

                producto.setCategoria(rs.getString("categoria"));
                producto.setMarca(rs.getString("marca"));
                producto.setProveedor(rs.getString("proveedor"));

                listaProductos.add(producto);
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return listaProductos;
    }

    public double obtenerPrecioProducto(int idProducto) throws Exception {
        String sql = "SELECT precio FROM Producto WHERE id = ?";
        double precio = 0.0;

        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idProducto);
            rs = ps.executeQuery();

            if (rs.next()) {
                precio = rs.getDouble("precio"); // Se obtiene el precio del producto
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return precio;
    }

}
