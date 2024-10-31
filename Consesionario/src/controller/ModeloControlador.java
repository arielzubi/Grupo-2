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
import model.Modelo;
import model.ModeloDao;
import view.Pantalla;
import model.MarcaDao;


public class ModeloControlador implements ActionListener, MouseListener, KeyListener {
    private Modelo modelo;
    private ModeloDao modeloDao;
    private Pantalla panta;

    DefaultTableModel model = new DefaultTableModel();

    public ModeloControlador(Modelo modelo, ModeloDao modeloDao, Pantalla panta) {
        this.modelo = modelo;
        this.modeloDao = modeloDao;
        this.panta = panta;
        
        //Botón de registrar modelo
        this.panta.btn_agregar_modelo.addActionListener(this);
        //Botón de modificar modelo
        this.panta.btn_modificar_modelo.addActionListener(this);
        //Botón de borrar modelo
        this.panta.btn_borrar_modelo.addActionListener(this);
        //Botón de limpiar
        this.panta.btn_limpiar_modelo.addActionListener(this);
        
        //Listado de modelo
        this.panta.tb_modelo_tabla.addMouseListener(this);
        
              
        listarModelos(); 
        
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == panta.btn_agregar_modelo){
            //verifica si el campo título está vacío
            if(panta.txt_nombre_modelo.getText().equals("")){
                JOptionPane.showMessageDialog(null, "El campo título es obligatorio");
            }else{
                //Realiza el agregado
                MarcaDao marcaDao = new MarcaDao();
                int idMarca = marcaDao.buscarIdMarca(panta.cmb_marca_modelo.getSelectedItem().toString());
                
                modelo.setNombreModelo(panta.txt_nombre_modelo.getText());
                modelo.setIdMarca(idMarca);
                
                if(modeloDao.agregarModelo(modelo)){
                    limpiarTabla();
                    limpiarCampos();
                    listarModelos();
                    JOptionPane.showMessageDialog(null, "Se agregó el modelo");
                }else{
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error al agregar el modelo");
                }
            }
        }else if(e.getSource() == panta.btn_modificar_modelo){
            //verifica si el campo id está vacío
            if(panta.lbl_id_modelo.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Debe seleccionar un registro desde la tabla");
            }else{
                //Realiza la modificación
                MarcaDao marcaDao = new MarcaDao();
                int idMarca = marcaDao.buscarIdMarca(panta.cmb_marca_modelo.getSelectedItem().toString());

                modelo.setIdModelo(Integer.parseInt(panta.lbl_id_modelo.getText()));
                modelo.setNombreModelo(panta.txt_nombre_modelo.getText());
                modelo.setIdMarca(idMarca);
                
                if(modeloDao.modificarModelo(modelo)){
                    limpiarTabla();
                    limpiarCampos();
                    listarModelos();
                    JOptionPane.showMessageDialog(null, "Se modificó el modelo");
                }else{
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error al modificar el modelo");
                }
            }
        }else if(e.getSource() == panta.btn_borrar_modelo){
            //verifica si el campo id está vacío
            if(panta.lbl_id_modelo.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Debe seleccionar un registro desde la tabla");
            }else{
                //Realiza el borrado
                int id = Integer.parseInt(panta.lbl_id_modelo.getText());
                if(modeloDao.borrarModelo(id)){
                    limpiarTabla();
                    limpiarCampos();
                    listarModelos();
                    JOptionPane.showMessageDialog(null, "Se eliminó el modelo");
                }else{
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error al eliminar el modelo");
                }
            }
        }else if(e.getSource() == panta.btn_limpiar_modelo){
                limpiarTabla();
                limpiarCampos();
                listarModelos();    
                panta.btn_agregar_modelo.setEnabled(true);
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
        if(e.getSource() == panta.tb_modelo_tabla){
            int row = panta.tb_modelo_tabla.rowAtPoint(e.getPoint());
            panta.lbl_id_modelo.setText(panta.tb_modelo_tabla.getValueAt(row,0).toString());
            panta.txt_nombre_modelo.setText(panta.tb_modelo_tabla.getValueAt(row,1).toString());
            panta.cmb_marca_modelo.setSelectedItem(panta.tb_modelo_tabla.getValueAt(row,2).toString());
            

            //Deshabilitar
            panta.btn_agregar_modelo.setEnabled(false);
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
            listarModelos();
        }
    

    //Listar todos los modelos
    
    
    public void listarModelos(){
        
        panta.cmb_modelo_version.removeAllItems();
        panta.cmb_modelo_auto.removeAllItems();
        
         List<Modelo> list = modeloDao.listarModelo(); 
        model = (DefaultTableModel) panta.tb_modelo_tabla.getModel();
        Object[] row = new Object[5];
        limpiarTabla();
        for(int i = 0; i < list.size(); i++){
            row[0] = list.get(i).getIdModelo();
            row[1] = list.get(i).getNombreModelo();
            row[2] = list.get(i).getNombreMarca();
            
            model.addRow(row);
            panta.cmb_modelo_version.addItem(list.get(i).getNombreModelo());
            panta.cmb_modelo_auto.addItem(list.get(i).getNombreModelo());
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
        panta.lbl_id_modelo.setText("");
        panta.txt_nombre_modelo.setText("");
        panta.txt_id_marca.setText("");
        
    }
    
}


