/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controladores.interfaces;

import java.util.List;
import modelos.usuarios.Usuario;

public interface IUsuarioDAO {
    public Usuario loginAdmin(String correo_electronico, String contraseña);
    public Usuario loginUsuario(String correo_electronico, String password);
    public Boolean registrarUsuario(Usuario usuario);
    public List<Usuario> obtenerUsuarios();
    public Usuario obtenerUsuarioPorId(int id);
}
