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
import model.Auto;
import model.AutoDao;
import view.Pantalla;
import model.MarcaDao;
import model.ModeloDao;
import model.VersionDao;

public class AutoControlador implements ActionListener, MouseListener, KeyListener {
    private Auto auto;
    private AutoDao autoDao;
    private Pantalla panta;

    DefaultTableModel model = new DefaultTableModel();

    public AutoControlador(Auto auto, AutoDao autoDao, Pantalla panta) {
        this.auto = auto;
        this.autoDao = autoDao;
        this.panta = panta;
        
        //Botón de registrar auto
        this.panta.btn_agregar_auto.addActionListener(this);
        //Botón de modificar auto
        this.panta.btn_modificar_auto.addActionListener(this);
        //Botón de borrar auto
        this.panta.btn_borrar_modelo.addActionListener(this);
        //Botón de limpiar
        this.panta.btn_limpiar_auto.addActionListener(this);
        
        //Listado de auto
        this.panta.tb_auto_tabla.addMouseListener(this);
        
              
        listarAutos(); 
        
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == panta.btn_agregar_auto){
            //verifica si el campo título está vacío
            if(panta.txt_año.getText().equals("")){
                JOptionPane.showMessageDialog(null, "El campo año es obligatorio");
            }else{
                //Realiza el agregado
                MarcaDao marcaDao = new MarcaDao();
                int idMarca = marcaDao.buscarIdMarca(panta.cmb_marca_auto.getSelectedItem().toString());
                
                ModeloDao modeloDao = new ModeloDao();
                int idModelo = modeloDao.buscarIdModelo(panta.cmb_modelo_auto.getSelectedItem().toString());
                
                VersionDao versionDao = new VersionDao();
                int idVersion = versionDao.buscarIdVersion(panta.cmb_version_auto.getSelectedItem().toString());
                
                
                auto.setAño(Integer.parseInt(panta.txt_año.getText()));
                auto.setPrecio(Integer.parseInt(panta.txt_precio.getText()));
                auto.setKilometros(Integer.parseInt(panta.txt_kilometros.getText()));
                auto.setCombustible(panta.txt_combustible.getText());
                auto.setPuertas(Integer.parseInt(panta.txt_puertas.getText()));
                auto.setCondicion(panta.txt_condicion.getText()); 
                auto.setIdMarca(idMarca);
                auto.setIdMarca(idMarca);
                auto.setIdVersion(idVersion);
                
                if(autoDao.agregarAuto(auto)){
                    limpiarTabla();
                    limpiarCampos();
                    listarAutos();
                    JOptionPane.showMessageDialog(null, "Se agregó el auto");
                }else{
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error al agregar el auto");
                }
            }
        }else if(e.getSource() == panta.btn_modificar_auto){
            //verifica si el campo id está vacío
            if(panta.txt_id_auto.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Debe seleccionar un registro desde la tabla");
            }else{
                //Realiza la modificación
                MarcaDao autorDao = new MarcaDao();
                int idMarca = autorDao.buscarIdMarca(panta.cmb_marca_auto.getSelectedItem().toString());
                
                ModeloDao modeloDao = new ModeloDao();
                int idModelo = modeloDao.buscarIdModelo(panta.cmb_modelo_auto.getSelectedItem().toString());
                
                VersionDao versionDao = new VersionDao();
                int idVersion = versionDao.buscarIdVersion(panta.cmb_version_auto.getSelectedItem().toString());

                auto.setIdAuto(Integer.parseInt(panta.txt_id_auto.getText()));
                auto.setAño(Integer.parseInt(panta.txt_año.getText()));
                auto.setPrecio(Integer.parseInt(panta.txt_precio.getText()));
                auto.setKilometros(Integer.parseInt(panta.txt_kilometros.getText()));
                auto.setCombustible(panta.txt_combustible.getText());
                auto.setPuertas(Integer.parseInt(panta.txt_puertas.getText()));
                auto.setCondicion(panta.txt_condicion.getText());
                auto.setIdMarca(idMarca);
                auto.setIdModelo(idModelo);
                auto.setIdVersion(idVersion);
                if(autoDao.modificarAuto(auto)){
                    limpiarTabla();
                    limpiarCampos();
                    listarAutos();
                    JOptionPane.showMessageDialog(null, "Se modificó el auto");
                }else{
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error al modificar el auto");
                }
            }
        }else if(e.getSource() == panta.btn_borrar_modelo){
            //verifica si el campo id está vacío
            if(panta.txt_id_auto.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Debe seleccionar un registro desde la tabla");
            }else{
                //Realiza el borrado
                int id = Integer.parseInt(panta.txt_id_auto.getText());
                if(autoDao.borrarAuto(id)){
                    limpiarTabla();
                    limpiarCampos();
                    listarAutos();
                    JOptionPane.showMessageDialog(null, "Se eliminó el auto");
                }else{
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error al eliminar el auto");
                }
            }
        }else if(e.getSource() == panta.btn_limpiar_auto){
                limpiarTabla();
                limpiarCampos();
                listarAutos();    
                panta.btn_agregar_auto.setEnabled(true);
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
        if(e.getSource() == panta.tb_auto_tabla){
            int row = panta.tb_auto_tabla.rowAtPoint(e.getPoint());
            panta.txt_id_auto.setText(panta.tb_auto_tabla.getValueAt(row,0).toString());
            panta.txt_año.setText(panta.tb_auto_tabla.getValueAt(row,1).toString());
            panta.txt_precio.setText(panta.tb_auto_tabla.getValueAt(row,1).toString());
            panta.txt_kilometros.setText(panta.tb_auto_tabla.getValueAt(row,1).toString());
            panta.txt_combustible.setText(panta.tb_auto_tabla.getValueAt(row,1).toString());
            panta.txt_puertas.setText(panta.tb_auto_tabla.getValueAt(row,1).toString());
            panta.txt_condicion.setText(panta.tb_auto_tabla.getValueAt(row,1).toString());
            panta.cmb_marca_auto.setSelectedItem(panta.tb_auto_tabla.getValueAt(row,2).toString());
            panta.cmb_modelo_auto.setSelectedItem(panta.tb_auto_tabla.getValueAt(row,3).toString());
            panta.cmb_version_auto.setSelectedItem(panta.tb_auto_tabla.getValueAt(row,4).toString());

            //Deshabilitar
            panta.btn_agregar_auto.setEnabled(false);
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
            listarAutos();
        
    }

    //Listar todos los autos
    public void listarAutos(){
        
         List<Auto> list = autoDao.listarAuto(); 
        model = (DefaultTableModel) panta.tb_auto_tabla.getModel();
        Object[] row = new Object[5];
        limpiarTabla();
        for(int i = 0; i < list.size(); i++){
            row[0] = list.get(i).getIdAuto();
            row[1] = list.get(i).getAño();
            row[2] = list.get(i).getPrecio();
            row[3] = list.get(i).getKilometros();
            row[4] = list.get(i).getCombustible();
            row[5] = list.get(i).getPuertas();
            row[6] = list.get(i).getCondicion();
            row[7] = list.get(i).getNombreMarca();
            row[8] = list.get(i).getNombreModelo();
            row[9] = list.get(i).getNombreVersion();
            model.addRow(row);
        }
        
                
        
    }
    
    
    //Limpiar la tabla
    public void limpiarTabla(){
        for (int i = 0; i < model.getRowCount(); i++){
            model.removeRow(i);
            i = i - 1;
        }
    }
    //Limpiar los campos
    public void limpiarCampos(){
        panta.txt_id_auto.setText("");
        panta.txt_año.setText("");
        panta.txt_precio.setText("");
        panta.txt_kilometros.setText("");
        panta.txt_combustible.setText("");
        panta.txt_puertas.setText("");
        panta.txt_condicion.setText("");
        panta.txt_id_marca.setText("");
        panta.lbl_id_modelo.setText("");
        panta.txt_id_version.setText("");
    }
    
}



