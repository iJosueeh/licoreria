/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import javax.swing.JOptionPane;

public class ConexionDAO {

    Connection con;

    public Connection getConexion() {
        try {
            String db = "jdbc:mysql://root:hCvzwFPVcAmLgYrgLICpaQDdeuiCMvqf@autorack.proxy.rlwy.net:47979/railway";
            con = DriverManager.getConnection(db, "root", "hCvzwFPVcAmLgYrgLICpaQDdeuiCMvqf");
            return con;
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }

}
