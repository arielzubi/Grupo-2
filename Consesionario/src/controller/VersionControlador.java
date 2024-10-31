package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Version;
import model.VersionDao;
import view.Pantalla;
import model.MarcaDao;
import model.ModeloDao;

public class VersionControlador implements ActionListener, MouseListener, KeyListener {
    private Version version;
    private VersionDao versionDao;
    private Pantalla panta;

    DefaultTableModel model = new DefaultTableModel();

    public VersionControlador(Version version, VersionDao versionDao, Pantalla panta) {
        this.version = version;
        this.versionDao = versionDao;
        this.panta = panta;
        
        //Botón de registrar version
        this.panta.btn_agregar_version.addActionListener(this);
        //Botón de modificar version
        this.panta.btn_modificar_version.addActionListener(this);
        //Botón de borrar version
        this.panta.btn_borrar_version.addActionListener(this);
        //Botón de limpiar
        this.panta.btn_limpiar_version.addActionListener(this);
        
        //Listado de version
        this.panta.tb_version_tabla.addMouseListener(this);
        
        
              
        listarVersions(); 
        
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == panta.btn_agregar_version){
            //verifica si el campo título está vacío
            if(panta.txt_nombre_version.getText().equals("")){
                JOptionPane.showMessageDialog(null, "El campo título es obligatorio");
            }else{
                //Realiza el agregado
                MarcaDao marcaDao = new MarcaDao();
                int idMarca = marcaDao.buscarIdMarca(panta.cmb_marca_version.getSelectedItem().toString());
                ModeloDao modeloDao = new ModeloDao();
                int idModelo = modeloDao.buscarIdModelo(panta.cmb_modelo_version.getSelectedItem().toString());
                
                version.setNombreVersion(panta.txt_nombre_version.getText());
                version.setIdMarca(idMarca);
                version.setIdModelo(idModelo);
                
                if(versionDao.agregarVersion(version)){
                    limpiarTabla();
                    limpiarCampos();
                    listarVersions();
                    JOptionPane.showMessageDialog(null, "Se agregó el version");
                }else{
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error al agregar el version");
                }
            }
        }else if(e.getSource() == panta.btn_modificar_version){
            //verifica si el campo id está vacío
            if(panta.txt_id_version.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Debe seleccionar un registro desde la tabla");
            }else{
                //Realiza la modificación
                MarcaDao marcaDao = new MarcaDao();
                int idMarca = marcaDao.buscarIdMarca(panta.cmb_marca_version.getSelectedItem().toString());
                ModeloDao modeloDao = new ModeloDao();
                int idModelo = modeloDao.buscarIdModelo(panta.cmb_modelo_version.getSelectedItem().toString());

                version.setIdVersion(Integer.parseInt(panta.txt_id_version.getText()));
                version.setNombreVersion(panta.txt_nombre_version.getText());
                version.setIdMarca(idMarca);
                version.setIdModelo(idModelo);
                if(versionDao.modificarVersion(version)){
                    limpiarTabla();
                    limpiarCampos();
                    listarVersions();
                    JOptionPane.showMessageDialog(null, "Se modificó el version");
                }else{
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error al modificar el version");
                }
            }
        }else if(e.getSource() == panta.btn_borrar_version){
            //verifica si el campo id está vacío
            if(panta.txt_id_version.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Debe seleccionar un registro desde la tabla");
            }else{
                //Realiza el borrado
                int id = Integer.parseInt(panta.txt_id_version.getText());
                if(versionDao.borrarVersion(id)){
                    limpiarTabla();
                    limpiarCampos();
                    listarVersions();
                    JOptionPane.showMessageDialog(null, "Se eliminó el version");
                }else{
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error al eliminar el version");
                }
            }
        }else if(e.getSource() == panta.btn_limpiar_version){
                limpiarTabla();
                limpiarCampos();
                listarVersions();    
                panta.btn_agregar_version.setEnabled(true);
        }    
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getSource() == panta.tb_version_tabla){
            int row = panta.tb_version_tabla.rowAtPoint(e.getPoint());
            panta.txt_id_version.setText(panta.tb_version_tabla.getValueAt(row,0).toString());
            panta.txt_nombre_version.setText(panta.tb_version_tabla.getValueAt(row,1).toString());
            panta.cmb_marca_version.setSelectedItem(panta.tb_version_tabla.getValueAt(row,2).toString());
            panta.cmb_modelo_version.setSelectedItem(panta.tb_version_tabla.getValueAt(row,3).toString());
            

            //Deshabilitar
            panta.btn_agregar_version.setEnabled(false);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
            limpiarTabla();
            listarVersions();
        
    }

    //Listar todos los marcaes
    public void listarVersions(){
        panta.cmb_version_auto.removeAllItems();
         List<Version> list = versionDao.listarVersion(); 
        model = (DefaultTableModel) panta.tb_version_tabla.getModel();
        Object[] row = new Object[5];
        limpiarTabla();
        for(int i = 0; i < list.size(); i++){
            row[0] = list.get(i).getIdVersion();
            row[1] = list.get(i).getNombreVersion();
            row[2] = list.get(i).getNombreMarca();
            row[3] = list.get(i).getNombreModelo();
            
            model.addRow(row);
            
            panta.cmb_modelo_auto.addItem(list.get(i).getNombreModelo());
        }
       
                }
   
    //limpiar la tabla
    public void limpiarTabla(){
        for (int i = 0; i < model.getRowCount(); i++){
            model.removeRow(i);
            i = i - 1;
        }
    }
    //limpiar los campos
    public void limpiarCampos(){
        panta.txt_id_version.setText("");
        panta.txt_nombre_version.setText("");
        panta.txt_id_marca.setText("");
        panta.lbl_id_modelo.setText("");
    }
    
}



