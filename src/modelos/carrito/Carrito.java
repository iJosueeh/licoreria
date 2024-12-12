/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.carrito;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Carrito {

    private int id;
    private int idUsuario;
    private String estado;
    private java.sql.Date fechaCreacion;
    private List<CarritoDetalle> detalles;

    public Carrito() {
        this.detalles = new ArrayList<>();
    }

    public Carrito(int idUsuario, String estado, Date fechaCreacion) {
        this.idUsuario = idUsuario;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.detalles = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public List<CarritoDetalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<CarritoDetalle> detalles) {
        this.detalles = detalles;
    }

    public void agregarProductoAlCarrito(CarritoDetalle detalle) {
        if (this.detalles == null) {
            this.detalles = new ArrayList<>();
        }
        this.detalles.add(detalle);
    }

}
