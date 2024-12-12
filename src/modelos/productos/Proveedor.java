/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.productos;

public class Proveedor {
    
    private int id;
    private String nombre_proveedor;
    private String contacto;
    private String telefono;
    private String correo_electronico;
    private String direccion;
    private String estado;

    public Proveedor(){
    }
    
    public Proveedor(String nombre_proveedor, String contacto, String telefono, String correo_electronico, String direccion) {
        this.nombre_proveedor = nombre_proveedor;
        this.contacto = contacto;
        this.telefono = telefono;
        this.correo_electronico = correo_electronico;
        this.direccion = direccion;
        this.estado = "Activo";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre_proveedor() {
        return nombre_proveedor;
    }

    public void setNombre_proveedor(String nombre_proveedor) {
        this.nombre_proveedor = nombre_proveedor;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
