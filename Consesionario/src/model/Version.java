/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author alumno
 */
public class Version {
    private int idVersion;
    private String nombreVersion;
    private int idMarca;
    private int idModelo;
    
    private String nombreMarca;
    private String nombreModelo;

    public Version() {
    }

    public Version(int idVersion, String nombreVersion, int idMarca, int idModelo, String nombreMarca, String nombreModelo) {
        this.idVersion = idVersion;
        this.nombreVersion = nombreVersion;
        this.idMarca = idMarca;
        this.idModelo = idModelo;
        this.nombreMarca = nombreMarca;
        this.nombreModelo = nombreModelo;
    }

    public int getIdVersion() {
        return idVersion;
    }

    public String getNombreVersion() {
        return nombreVersion;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public int getIdModelo() {
        return idModelo;
    }

    public String getNombreMarca() {
        return nombreMarca;
    }

    public String getNombreModelo() {
        return nombreModelo;
    }

    public void setIdVersion(int idVersion) {
        this.idVersion = idVersion;
    }

    public void setNombreVersion(String nombreVersion) {
        this.nombreVersion = nombreVersion;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public void setIdModelo(int idModelo) {
        this.idModelo = idModelo;
    }

    public void setNombreMarca(String nombreMarca) {
        this.nombreMarca = nombreMarca;
    }

    public void setNombreModelo(String nombreModelo) {
        this.nombreModelo = nombreModelo;
    }
    
    
}