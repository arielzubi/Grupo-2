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
import model.Marca;
import model.MarcaDao;
import view.Pantalla;

public class MarcaControlador implements ActionListener, MouseListener, KeyListener {
    private Marca marca;
    private MarcaDao marcaDao;
    private Pantalla panta;

    DefaultTableModel model = new DefaultTableModel();

    public MarcaControlador(Marca marca, MarcaDao marcaDao, Pantalla panta) {
        this.marca = marca;
        this.marcaDao = marcaDao;
        this.panta = panta;
        
        //Botón de registrar marca
        this.panta.btn_agregar_marca.addActionListener(this);
        //Botón de modificar marca
        this.panta.btn_modificar_marca.addActionListener(this);
        //Botón de borrar marca
        this.panta.btn_borrar_marca.addActionListener(this);
        //Botón de limpiar
        this.panta.btn_limpiar_marca.addActionListener(this);
        
        //Listado de marca
        this.panta.tb_marca_tabla.addMouseListener(this);
              
        listarMarcas(); 
        
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == panta.btn_agregar_marca){
            //verifica si el campo nombre está vacío
            if(panta.txt_nombre_marca.getText().equals("")){
                JOptionPane.showMessageDialog(null, "El campo nombre es obligatorio");
            }else{
                //Realiza el agregado
                marca.setNombreMarca(panta.txt_nombre_marca.getText());
                if(marcaDao.agregarMarca(marca)){
                    limpiarTabla();
                    limpiarCampos();
                    listarMarcas();
                    JOptionPane.showMessageDialog(null, "Se agregó el género");
                }else{
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error al agregar el género");
                }
            }
        }else if(e.getSource() == panta.btn_modificar_marca){
            //verifica si el campo id está vacío
            if(panta.txt_id_marca.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Debe seleccionar un registro desde la tabla");
            }else{
                //Realiza la modificación
                marca.setIdMarca(Integer.parseInt(panta.txt_id_marca.getText()));
                marca.setNombreMarca(panta.txt_nombre_marca.getText());
                if(marcaDao.modificarMarca(marca)){
                    limpiarTabla();
                    limpiarCampos();
                    listarMarcas();
                    JOptionPane.showMessageDialog(null, "Se modificó el género");
                }else{
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error al modificar el género");
                }
            }
        }else if(e.getSource() == panta.btn_borrar_marca){
            //verifica si el campo id está vacío
            if(panta.txt_id_marca.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Debe seleccionar un registro desde la tabla");
            }else{
                //Realiza el borrado
                int id = Integer.parseInt(panta.txt_id_marca.getText());
                if(marcaDao.borrarMarca(id)){
                    limpiarTabla();
                    limpiarCampos();
                    listarMarcas();
                    JOptionPane.showMessageDialog(null, "Se eliminó el género");
                }else{
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error al eliminar el género");
                }
            }
        }else if(e.getSource() == panta.btn_limpiar_marca){
                limpiarTabla();
                limpiarCampos();
                listarMarcas();    
                panta.btn_agregar_marca.setEnabled(true);
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
        if(e.getSource() == panta.tb_marca_tabla){
            int row = panta.tb_marca_tabla.rowAtPoint(e.getPoint());
            panta.txt_id_marca.setText(panta.tb_marca_tabla.getValueAt(row,0).toString());
            panta.txt_nombre_marca.setText(panta.tb_marca_tabla.getValueAt(row,1).toString());
            //Deshabilitar
            panta.btn_agregar_marca.setEnabled(false);
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
    }

    //Listar todos los géneros
    public void listarMarcas(){
        
        panta.cmb_marca_modelo.removeAllItems();
        panta.cmb_marca_version.removeAllItems();
        panta.cmb_marca_auto.removeAllItems();

        List<Marca> list = marcaDao.listarMarca();
        model = (DefaultTableModel) panta.tb_marca_tabla.getModel();
        Object[] row = new Object[2];
        limpiarTabla();
        for(int i = 0; i < list.size(); i++){
            row[0] = list.get(i).getIdMarca();
            row[1] = list.get(i).getNombreMarca();
            model.addRow(row);
            
            panta.cmb_marca_modelo.addItem(list.get(i).getNombreMarca());
            panta.cmb_marca_version.addItem(list.get(i).getNombreMarca());
            panta.cmb_marca_auto.addItem(list.get(i).getNombreMarca());

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
        panta.txt_id_marca.setText("");
        panta.txt_nombre_marca.setText("");
    }
    
}

