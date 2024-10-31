package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class VersionDao {
    //Instanciar la conexión
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    public VersionDao() {
    }
    //Agregar version
    public boolean agregarVersion(Version version){
        String query = "INSERT INTO version (nombreversion idmarca idmodelo) VALUES(? ? ?)";
        try {
            con = cn.conectar();
            pst = con.prepareStatement(query);
            pst.setString(1,version.getNombreVersion());
            pst.setString(2, version.getNombreMarca());
            pst.setString(3, version.getNombreModelo());
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar el version" + e);
            return false;
        }
    }
    
    //Modificar version
    public boolean modificarVersion(Version version){
        String query = "UPDATE version SET nombreversion = ? idmarca= ?, idmodelo= ?, WHERE idversion = ?";
        try {
            con = cn.conectar();
            pst = con.prepareStatement(query);
            pst.setString(1,version.getNombreVersion());
            pst.setString(2,version.getNombreMarca());
            pst.setString(3,version.getNombreModelo());
            pst.setInt(4, version.getIdVersion());
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar el version" + e);
            return false;
        }
    }

    //Borrar version
    public boolean borrarVersion(int id){
        String query = "DELETE FROM version WHERE idversion = " + id;
        try {
            con = cn.conectar();
            pst = con.prepareStatement(query);
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al borrar el version" + e);
            return false;
        }
    }

    //Listar version
    public List listarVersion(){
        List<Version> list_versiones = new ArrayList();
        String query = "SELECT ver.*, mar.nombremarca, mod.nombremodelo FROM version as ver inner join marca as mar on ver.idmarca = mar.idmarca inner join modelo as mod on ver.idmodelo = mod.idmodelo ORDER BY nombreversion ASC";
        try {
            con = cn.conectar();
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();
            while(rs.next()){
                Version version = new Version();
                version.setIdVersion(rs.getInt("idversion"));
                version.setNombreVersion(rs.getString("nombreversion"));
                version.setNombreMarca(rs.getString("nombremarca")); 
                version.setNombreModelo(rs.getString("nombremodelo"));
                list_versiones.add(version);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return list_versiones;
    }    
    
    //Buscar id de version
    public int buscarIdVersion(String nombre){
        int id = 0;
        String query = "SELECT idversion FROM version WHERE nombreversion = '" + nombre + "'";
        try {
            con = cn.conectar();
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();
            while(rs.next()){            
                id = rs.getInt("idversion");            
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar el id de version" + e);
        }
        return id;
    }
    
}


