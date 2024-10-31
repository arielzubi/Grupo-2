package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ModeloDao {
    //Instanciar la conexión
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    public ModeloDao() {
    }
    //Agregar modelo
    public boolean agregarModelo(Modelo modelo){
        String query = "INSERT INTO modelo (nombremodelo idmarca) VALUES(?, ?)";
        try {
            con = cn.conectar();
            pst = con.prepareStatement(query);
            pst.setString(1,modelo.getNombreModelo());
            pst.setString(2, modelo.getNombreMarca());
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar el modelo" + e);
            return false;
        }
    }
    
    //Modificar modelo
    public boolean modificarModelo(Modelo modelo){
        String query = "UPDATE modelo SET nombremodelo = ? idmarca= ?, WHERE idmodelo = ?";
        try {
            con = cn.conectar();
            pst = con.prepareStatement(query);
            pst.setString(1,modelo.getNombreModelo());
            pst.setString(2,modelo.getNombreMarca());
            pst.setInt(3, modelo.getIdModelo());
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar el modelo" + e);
            return false;
        }
    }

    //Borrar modelo
    public boolean borrarModelo(int id){
        String query = "DELETE FROM modelo WHERE idmodelo = " + id;
        try {
            con = cn.conectar();
            pst = con.prepareStatement(query);
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al borrar el modelo" + e);
            return false;
        }
    }

    //Listar modelo
    public List listarModelo(){
        List<Modelo> list_modelos = new ArrayList();
        String query = "SELECT mod.*, mar.nombremarca FROM modelo as mod inner join marca as mar on mod.idmarca = mar.idmarca ORDER BY nombremodelo ASC";
        try {
            con = cn.conectar();
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();
            while(rs.next()){
                Modelo modelo = new Modelo();
                modelo.setIdModelo(rs.getInt("idmodelo"));
                modelo.setNombreModelo(rs.getString("nombremodelo"));
                modelo.setNombreMarca(rs.getString("nombremarca"));              
                list_modelos.add(modelo);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return list_modelos;
    }    
    
    //Buscar id de modelo
    public int buscarIdModelo(String nombre){
        int id = 0;
        String query = "SELECT idmodelo FROM modelo WHERE nombremodelo = '" + nombre + "'";
        try {
            con = cn.conectar();
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();
            while(rs.next()){            
                id = rs.getInt("idmodelo");            
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar el id de modelo" + e);
        }
        return id;
    }
    
}


