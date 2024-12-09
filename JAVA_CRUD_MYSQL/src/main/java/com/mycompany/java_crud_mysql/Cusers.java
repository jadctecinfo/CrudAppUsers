/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.java_crud_mysql;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import com.mycompany.java_crud_mysql.Cconexion;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.TableView;

public class Cusers {
    
    private int id;
    private String nombresUser ;
    private String apellidosUser ;
    private String direccionUser ;
    private String telefonoUser ;
    private String ciudadUser ;
    private String rolUser ;

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombresUser () {
        return nombresUser ;
    }

    public void setNombresUser (String nombresUser ) {
        this.nombresUser  = nombresUser ;
    }

    public String getApellidosUser () {
        return apellidosUser ;
    }

    public void setApellidosUser (String apellidosUser ) {
        this.apellidosUser  = apellidosUser ;
    }

    public String getDireccionUser () {
        return direccionUser ;
    }

    public void setDireccionUser (String direccionUser ) {
        this.direccionUser  = direccionUser ;
    }

    public String getTelefonoUser () {
        return telefonoUser ;
    }

    public void setTelefonoUser (String telefonoUser ) {
        this.telefonoUser  = telefonoUser ;
    }

    public String getCiudadUser () {
        return ciudadUser ;
    }

    public void setCiudadUser (String ciudadUser ) {
        this.ciudadUser  = ciudadUser ;
    }

    public String getRolUser () {
        return rolUser ;
    }

    public void setRolUser (String rolUser ) {
        this.rolUser  = rolUser ;
    }
    
    public void insertUsers(JTextField paramNombres, JTextField paramApellidos, JTextField paramDireccion, JTextField paramTelefono, JTextField paramCiudad, JTextField paramRol) {
        // Asignar valores a los atributos
        setNombresUser (paramNombres.getText());
        setApellidosUser (paramApellidos.getText());
        setDireccionUser (paramDireccion.getText());
        setTelefonoUser (paramTelefono.getText());
        setCiudadUser (paramCiudad.getText());
        setRolUser (paramRol.getText());
       
        Cconexion objetoConexion = new Cconexion();
        Connection conexion = objetoConexion.estableceConexion();
        String consulta = "INSERT INTO users (Nombres, Apellidos, Direccion, Telefono, Ciudad, Rol) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = objetoConexion.estableceConexion();
             CallableStatement cs = connection.prepareCall(consulta)) {
            
            cs.setString(1, getNombresUser ());
            cs.setString(2, getApellidosUser ());
            cs.setString(3, getDireccionUser ());
            cs.setString(4, getTelefonoUser ());
            cs.setString(5, getCiudadUser ());
            cs.setString(6, getRolUser ());
            
            cs.execute();
            JOptionPane.showMessageDialog(null, "Usuario correctamente ingresado");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al ingresar el usuario: " + e.getMessage());
        }
    }
    
   public void MostrarUsers(JTable paramTablaTotalUsers) {
    
    Cconexion objetoConexion = new Cconexion();
    DefaultTableModel modelo = new DefaultTableModel(); 
    
        
    String sql = "";
    
    // Agregar columnas al modelo
    modelo.addColumn("id");
    modelo.addColumn("Nombres");
    modelo.addColumn("Apellidos"); 
    modelo.addColumn("Direccion");
    modelo.addColumn("Telefono");
    modelo.addColumn("Ciudad");
    modelo.addColumn("Rol");
    
    // Establecer el modelo en la tabla
    paramTablaTotalUsers.setModel(modelo);
    
    sql = "SELECT * FROM Users;"; 
    
    String[] datos = new String[7]; 
    Statement st;
    
    try {
        st = objetoConexion.estableceConexion().createStatement();
        ResultSet rs = st.executeQuery(sql);
        
        while (rs.next()) {
            datos[0] = rs.getString(1);
            datos[1] = rs.getString(2);
            datos[2] = rs.getString(3);
            datos[3] = rs.getString(4);
            datos[4] = rs.getString(5);
            datos[5] = rs.getString(6);
            datos[6] = rs.getString(7); 
            
            modelo.addRow(datos);
        }
        
        
        
    } catch (Exception e) {
        
        JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros, error: " + e.toString());
    }
} 
   public void seleccionarUsers(JTable paramTablaTotalUsers, JTextField paramid, JTextField paramNombres, JTextField paramApellidos,JTextField paramDireccion,JTextField paramTelefono,JTextField paramCiudad,JTextField paramRol ) {
       
       try {
           
           int fila = paramTablaTotalUsers.getSelectedRow();
           
           if (fila >=0) {
               
               paramid.setText((paramTablaTotalUsers.getValueAt(fila,0).toString()));
               paramNombres.setText((paramTablaTotalUsers.getValueAt(fila,1).toString()));       
               paramApellidos.setText((paramTablaTotalUsers.getValueAt(fila,2).toString()));
               paramDireccion.setText((paramTablaTotalUsers.getValueAt(fila,3).toString()));
               paramTelefono.setText((paramTablaTotalUsers.getValueAt(fila,4).toString()));
               paramCiudad.setText((paramTablaTotalUsers.getValueAt(fila,5).toString()));
               paramRol.setText((paramTablaTotalUsers.getValueAt(fila,6).toString()));
           }  
           
           else{
               
               JOptionPane.showMessageDialog(null,"Fila no Seleccionada");
           }
          
           
       } catch (Exception e) {
           
           JOptionPane.showMessageDialog(null,"Error de Seleccion, error:" +e.toString());
           
          
       }
                    
            
}
 
       public void ModificarUsers(JTextField paramid, JTextField paramNombres, JTextField paramApellidos, JTextField paramDireccion, JTextField paramTelefono, JTextField paramCiudad, JTextField paramRol) {
    
           int id;

    // Manejo de excepciones para la conversión de ID
    try {
        id = Integer.parseInt(paramid.getText());
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "El ID ingresado no es un número válido.");
        return; // Salir del método si el ID no es válido
    }

    // Establecer los valores
    setNombresUser (paramNombres.getText());
    setApellidosUser (paramApellidos.getText());
    setDireccionUser (paramDireccion.getText());
    setTelefonoUser (paramTelefono.getText());
    setCiudadUser (paramCiudad.getText());
    setRolUser (paramRol.getText());

    Cconexion objetoConexion = new Cconexion();
    String consulta = "UPDATE users SET Nombres = ?, Apellidos = ?, Direccion = ?, Telefono = ?, Ciudad = ?, Rol = ? WHERE id = ?;";

    try (CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta)) {
        cs.setString(1, getNombresUser ());
        cs.setString(2, getApellidosUser ());
        cs.setString(3, getDireccionUser ());
        cs.setString(4, getTelefonoUser ());
        cs.setString(5, getCiudadUser ());
        cs.setString(6, getRolUser ());
        cs.setInt(7, id); // Usar el ID que se obtuvo

        cs.execute();
        JOptionPane.showMessageDialog(null, "Modificación realizada con éxito!!!");
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "No se pudo realizar la modificación!!!, error: " + e.toString());
    }
    
    }
       
       public void EliminarUsers(JTextField paramid) {
    
           setId(Integer.parseInt(paramid.getText()));
           
           Cconexion objetoConexion = new Cconexion();
           
           String consulta = "DELETE FROM users WHERE users.id = ?;";
           
           try {
               CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
               cs.setInt(1,getId());
               
               cs.execute();
               
               JOptionPane.showMessageDialog(null, "Se Eliminó el Registro con Exito!!!");
                   
                           
           } catch (SQLException e) {
               JOptionPane.showMessageDialog(null, "Error al eliminar el Registro, error: " + e.toString());
           }
       }
  }
