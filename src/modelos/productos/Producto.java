/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.productos;

import java.util.Date;

/**
 *
 * @author HOME
 */
public class Producto {

    private int id;
    private String categoria;
    private String marca;
    private String proveedor;
    private String nombre;
    private String descripcion;
    private double precio;
    private Date fecha_vencimiento;
    private String estado;
    private String volumenAlcohol;

    public Producto(){
    }
    
    public Producto(String categoria, String marca, String proveedor, String nombre, String descripcion, double precio, Date fecha_vencimiento, String volumenAlcohol) {
        this.categoria = categoria;
        this.marca = marca;
        this.proveedor = proveedor;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.fecha_vencimiento = fecha_vencimiento;
        this.estado = "Disponible";
        this.volumenAlcohol = volumenAlcohol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Date getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public void setFecha_vencimiento(Date fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getVolumenAlcohol() {
        return volumenAlcohol;
    }

    public void setVolumenAlcohol(String volumenAlcohol) {
        this.volumenAlcohol = volumenAlcohol;
    }

    
    
}
