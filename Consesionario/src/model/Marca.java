/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author alumno
 */
public class Marca {
    private int idMarca;
    private String nombreMarca;

    public Marca() {
    }

    public Marca(int idMarca, String nombreMarca) {
        this.idMarca = idMarca;
        this.nombreMarca = nombreMarca;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public String getNombreMarca() {
        return nombreMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public void setNombreMarca(String nombreMarca) {
        this.nombreMarca = nombreMarca;
    }
    
    
}
