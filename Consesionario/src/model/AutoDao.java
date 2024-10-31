package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class AutoDao {
    //Instanciar la conexión
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    public AutoDao() {
    }
    //Agregar auto
    public boolean agregarAuto(Auto auto){
        String query = "INSERT INTO auto (año, precio kilometros,conbustible, puertas, condicion, , idmarca, idmodelo, idversion) VALUES(?,?,?,?,?,?,?,?,?,?)";
        try {
            con = cn.conectar();
            pst = con.prepareStatement(query);
            pst.setInt(1,auto.getAño());
            pst.setInt(2, auto.getPrecio());
            pst.setInt(3, auto.getKilometros());
            pst.setString(4, auto.getCombustible());
            pst.setInt(5, auto.getPuertas());
            pst.setString(6, auto.getCondicion());
            pst.setInt(7, auto.getIdMarca());
            pst.setInt(8, auto.getIdModelo());
            pst.setInt(9, auto.getIdVersion());
            pst.setInt(10, auto.getIdAuto());
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar el auto" + e);
            return false;
        }
    }
    
    //Modificar auto
    public boolean modificarAuto(Auto auto){
        String query = "UPDATE auto SET año = ?, precio= ?, kilometros = ?, combustible = ?, puertas= ?, condicion = ? idmarca = ?, idmodelo = ?, idversion = ? WHERE idauto = ?";
        try {
            con = cn.conectar();
            pst = con.prepareStatement(query);
            pst.setInt(1,auto.getAño());
            pst.setInt(2, auto.getPrecio());
            pst.setInt(3, auto.getKilometros());
            pst.setString(4, auto.getCombustible());
            pst.setInt(5, auto.getPuertas());
            pst.setString(6, auto.getCondicion());
            pst.setInt(7, auto.getIdMarca());
            pst.setInt(8, auto.getIdModelo());
            pst.setInt(9, auto.getIdVersion());
            pst.setInt(10, auto.getIdAuto());
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar el auto" + e);
            return false;
        }
    }

    //Borrar auto
    public boolean borrarAuto(int id){
        String query = "DELETE FROM auto WHERE idauto = " + id;
        try {
            con = cn.conectar();
            pst = con.prepareStatement(query);
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al borrar el auto" + e);
            return false;
        }
    }

    //Listar auto
    public List listarAuto(){
        List<Auto> list_autos = new ArrayList();
        String query = "SELECT aut.*, mar.nombremarca, mod.nombremodelo, ver.nombreversion FROM auto as aut inner join marca as mar on aut.idmarca = mar.idmarca inner join modelo as mod on aut.idmodelo = mod.idmodelo inner join version as ver on aut.idversion = ver.idversion ORDER BY año ASC";
        try {
            con = cn.conectar();
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();
            while(rs.next()){
                Auto auto = new Auto();
                auto.setIdAuto(rs.getInt("idauto"));
                auto.setAño(rs.getInt("año"));
                auto.setPrecio(rs.getInt("precio"));
                auto.setKilometros(rs.getInt("precio"));
                auto.setCombustible(rs.getString("combusitible"));
                auto.setPuertas(rs.getInt("puertas"));
                auto.setCondicion(rs.getString("condicion"));
                auto.setNombreMarca(rs.getString("nombremarca"));
                auto.setNombreModelo(rs.getString("nombremodelo"));
                auto.setNombreVersion(rs.getString("nombreversion"));
                list_autos.add(auto);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return list_autos;
    }    
    
}