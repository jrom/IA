/*
 * Práctica de IA (Búsqueda)
 * FIB - UPC
 * Curso 2006-2007
 * Cuatrimestre de Otoño
 *
 * Daniel García Pérez
 * Sergio Vico Marfil
 *
 *
 * Paquete.java
 *
 */

package IA.Electricidad;


public class Paquete {
    private int id;
    private String company;
    private int co2;
    private int precio;
    private int kWhTotal;
    private int beneficio;
    
    /** Creates a new instance of Paquete */
    public Paquete(int id, String company, int co2, int precio, int kWhTotal, int beneficio) {
        this.id = id;
        this.company = company;
        this.co2 = co2;
        this.precio = precio;
        this.kWhTotal = kWhTotal;
        this.beneficio = beneficio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getCo2() {
        return co2;
    }

    public void setCo2(int co2) {
        this.co2 = co2;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getKWhTotal() {
        return kWhTotal;
    }

    public void setKWhTotal(int kWhTotal) {
        this.kWhTotal = kWhTotal;
    }
    
    public int getBeneficio() {
        return beneficio;
    }

    public void setBeneficio(int beneficio) {
        this.beneficio = beneficio;
    }
    
}
