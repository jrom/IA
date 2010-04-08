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
 * Consumidor.java
 *
 */

package IA.Electricidad;


public class Consumidor {
    private int id;
    private String nom;
    private int co2Min;
    private int precioMax;
    private int kWh;
    
    /** Creates a new instance of Consumidor */
    public Consumidor(int id, String nom, int co2Min, int precioMax, int kWh) {
        this.setId(id);
        this.setNom(nom);
        this.setCo2Min(co2Min);
        this.setPrecioMax(precioMax);
        this.setKWh(kWh);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getCo2Min() {
        return co2Min;
    }

    public void setCo2Min(int co2Min) {
        this.co2Min = co2Min;
    }

    public int getPrecioMax() {
        return precioMax;
    }

    public void setPrecioMax(int precioMax) {
        this.precioMax = precioMax;
    }

    public int getKWh() {
        return kWh;
    }

    public void setKWh(int kWh) {
        this.kWh = kWh;
    }

  
    
}
