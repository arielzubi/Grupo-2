package model;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class Conexion {

//Atributos    
     String ip="localhost";
     String puerto="3306";
     String usuario = "root";
     String pass = "root";
     String bd="concesionario";
     String cadena = "jdbc:mysql://"+ip+":"+puerto+"/"+bd;
     Connection con = null;

     
//Constructor vacío     
     public Connection conectar()	{
        try {
               Class.forName("com.mysql.cj.jdbc.Driver");
               con = DriverManager.getConnection(cadena,usuario,pass);
//               JOptionPane.showMessageDialog(null,"Se conectó correctamente a la base de datos");
        } catch (Exception e) {
               JOptionPane.showMessageDialog(null,"No se conectó a la base de datos, error: " + e.getMessage());
        }
        return con;
     }

}

