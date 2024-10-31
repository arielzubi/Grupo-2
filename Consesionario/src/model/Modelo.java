package model;

public class Modelo {
    private int idModelo;
     private String nombreModelo;
    private int idMarca;
   
    
    private String nombreMarca;

    public Modelo() {
    }

    public Modelo(int idModelo, int idMarca, String nombreModelo, String nombreMarca) {
        this.idModelo = idModelo;
        this.idMarca = idMarca;
        this.nombreModelo = nombreModelo;
        this.nombreMarca = nombreMarca;
    }

    public int getIdModelo() {
        return idModelo;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public String getNombreModelo() {
        return nombreModelo;
    }

    public String getNombreMarca() {
        return nombreMarca;
    }

    public void setIdModelo(int idModelo) {
        this.idModelo = idModelo;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public void setNombreModelo(String nombreModelo) {
        this.nombreModelo = nombreModelo;
    }

    public void setNombreMarca(String nombreMarca) {
        this.nombreMarca = nombreMarca;
    }

    
}