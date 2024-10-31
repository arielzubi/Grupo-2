package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class MarcaDao {
    //Instanciar la conexión
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    public MarcaDao() {
    }
    //Agregar marca
    public boolean agregarMarca(Marca marca){
        String query = "INSERT INTO marca (nombremarca) VALUES(?)";
        try {
            con = cn.conectar();
            pst = con.prepareStatement(query);
            pst.setString(1,marca.getNombreMarca());
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar el marca" + e);
            return false;
        }
    }
    
    //Modificar marca
    public boolean modificarMarca(Marca marca){
        String query = "UPDATE marca SET nombremarca = ? WHERE idmarca = ?";
        try {
            con = cn.conectar();
            pst = con.prepareStatement(query);
            pst.setString(1,marca.getNombreMarca());
            pst.setInt(2, marca.getIdMarca());
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar el marca" + e);
            return false;
        }
    }

    //Borrar marca
    public boolean borrarMarca(int id){
        String query = "DELETE FROM marca WHERE idmarca = " + id;
        try {
            con = cn.conectar();
            pst = con.prepareStatement(query);
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al borrar el marca" + e);
            return false;
        }
    }

    //Listar marca
    public List listarMarca(){
        List<Marca> list_marcas = new ArrayList();
        String query = "SELECT FROM marca ORDER BY nombremarca ASC";
        try {
            con = cn.conectar();
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();
            while(rs.next()){
                Marca marca = new Marca();
                marca.setIdMarca(rs.getInt("idmarca"));
                marca.setNombreMarca(rs.getString("nombremarca"));             
                list_marcas.add(marca);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return list_marcas;
    }    
    
    //Buscar id de marca
    public int buscarIdMarca(String nombre){
        int id = 0;
        String query = "SELECT idmarca FROM marca WHERE nombremarca = '" + nombre + "'";
        try {
            con = cn.conectar();
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();
            while(rs.next()){            
                id = rs.getInt("idmarca");            
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar el id de marca" + e);
        }
        return id;
    }
    
}

