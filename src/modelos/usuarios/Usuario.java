/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.usuarios;

public class Usuario {

    private int id;
    private String nombre;
    private String nombreUsuario;
    private String correo_electronico;
    private String contraseña;
    private String telefono;
    private String direccion;
    private String estado;
    private String rol;
    private static boolean loggedIn = false;
    private static Usuario usuarioActual;

    public Usuario() {
    }

    public Usuario(String nombre, String nombreUsuario, String correo_electronico, String contraseña) {
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.correo_electronico = correo_electronico;
        this.contraseña = contraseña;
    }

    public Usuario(String nombre, String nombreUsuario, String correo_electronico, String contraseña, String telefono, String direccion) {
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.correo_electronico = correo_electronico;
        this.contraseña = contraseña;
        this.telefono = telefono;
        this.direccion = direccion;
        this.estado = "Activo";
        this.rol = "Usuario";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }

    public String getContraseña() {
        return contraseña;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public static boolean isLoggedIn() {
        return loggedIn;
    }

    public static void setLoggedIn(boolean loggedIn) {
        Usuario.loggedIn = loggedIn;
    }

    public static Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public static void setUsuarioActual(Usuario usuarioActual) {
        Usuario.usuarioActual = usuarioActual;
    }

}
