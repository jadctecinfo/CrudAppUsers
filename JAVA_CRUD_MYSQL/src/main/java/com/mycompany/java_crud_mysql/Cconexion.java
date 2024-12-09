/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.java_crud_mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author PAULA_MORENO
 */
public class Cconexion {
    
    private Connection conectar = null;
    
    String usuario = "root";
    String contrasena = "";
    String bd = "usuarios";
    String ip = "localhost";
    String puerto = "3306";
    
    String cadena = "jdbc:mysql://" + ip + ":" + puerto + "/" + bd;
    
    public Connection estableceConexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Asegúrate de que el driver esté en el classpath
            conectar = DriverManager.getConnection(cadena, usuario, contrasena);
            JOptionPane.showMessageDialog(null, "La conexión se ha realizado con éxito.");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error: No se encontró el driver de MySQL. " + e.getMessage());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al conectarse a la base de datos: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error inesperado: " + e.getMessage());
        }
        
        return conectar;
    }
    
    // Método para cerrar la conexión
    public void cerrarConexion() {
        if (conectar != null) {
            try {
                conectar.close();
                JOptionPane.showMessageDialog(null, "Conexión cerrada.");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar la conexión: " + e.getMessage());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error inesperado al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}