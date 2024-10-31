package model;


public class Auto {
    private int idAuto;
    private int idMarca;
    private int idModelo;
    private int idVersion;
    private int año;
    private int precio;
    private int kilometros;
    private String combustible;
    private int puertas;
    private String condicion;

    private String nombreMarca;
    private String nombreModelo;
    private String nombreVersion;
           
    public Auto() {
    }

    public Auto(int idAuto, int idMarca, int idModelo, int idVersion, int año, int precio, int kilometros, String combustible, int puertas, String condicion, String nombreMarca, String nombreModelo, String nombreVersion) {
        this.idAuto = idAuto;
        this.idMarca = idMarca;
        this.idModelo = idModelo;
        this.idVersion = idVersion;
        this.año = año;
        this.precio = precio;
        this.kilometros = kilometros;
        this.combustible = combustible;
        this.puertas = puertas;
        this.condicion = condicion;
        this.nombreMarca = nombreMarca;
        this.nombreModelo = nombreModelo;
        this.nombreVersion = nombreVersion;
    }

    public int getIdAuto() {
        return idAuto;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public int getIdModelo() {
        return idModelo;
    }

    public int getIdVersion() {
        return idVersion;
    }

    public int getAño() {
        return año;
    }

    public int getPrecio() {
        return precio;
    }

    public int getKilometros() {
        return kilometros;
    }

    public String getCombustible() {
        return combustible;
    }

    public int getPuertas() {
        return puertas;
    }

    public String getCondicion() {
        return condicion;
    }

    public String getNombreMarca() {
        return nombreMarca;
    }

    public String getNombreModelo() {
        return nombreModelo;
    }

    public String getNombreVersion() {
        return nombreVersion;
    }

    public void setIdAuto(int idAuto) {
        this.idAuto = idAuto;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public void setIdModelo(int idModelo) {
        this.idModelo = idModelo;
    }

    public void setIdVersion(int idVersion) {
        this.idVersion = idVersion;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public void setKilometros(int kilometros) {
        this.kilometros = kilometros;
    }

    public void setCombustible(String combustible) {
        this.combustible = combustible;
    }

    public void setPuertas(int puertas) {
        this.puertas = puertas;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public void setNombreMarca(String nombreMarca) {
        this.nombreMarca = nombreMarca;
    }

    public void setNombreModelo(String nombreModelo) {
        this.nombreModelo = nombreModelo;
    }

    public void setNombreVersion(String nombreVersion) {
        this.nombreVersion = nombreVersion;
    }

    
}