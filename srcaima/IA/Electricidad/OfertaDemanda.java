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
 * OfertaDemanda.java
 *
 */

package IA.Electricidad;

import java.util.Vector;

public class OfertaDemanda {
    static private OfertaDemanda od = null;
    private Vector<Paquete> oferta = new Vector<Paquete>();
    private Vector<Consumidor> demanda = new Vector<Consumidor>();
    
    public OfertaDemanda() {
    }
    
    static public OfertaDemanda getOfertaDemanda() {
        if(od == null) {
            od = new OfertaDemanda();
        }
        return od;
    }
    
    public void addConsumidor(int id, String nom, int co2Min, int precioMax, int kWh) {
        demanda.add(id,new Consumidor(id,nom,co2Min,precioMax,kWh));
    }
    
    public void addPaquete(int id, String company, int co2, int precio, int kWhTotal, int beneficio) {
        oferta.add(id,new Paquete(id,company,co2,precio,kWhTotal,beneficio));
    }
    
    public void resetearDatos() {
        oferta.removeAllElements();
        demanda.removeAllElements();
    }
    
    public int getNumOferta() {
        return (oferta.size());
    }
    
    public int getNumDemanda() {
        return (demanda.size());
    }
    
    public String getCNom(int id) {
        return (demanda.get(id).getNom());
    }
    
    public int getCCo2Min(int id) {
        return (demanda.get(id).getCo2Min());
    }
    
    public int getCPrecioMax(int id) {
        return (demanda.get(id).getPrecioMax());
    }
    
    public int getCKWh(int id) {
        return (demanda.get(id).getKWh());
    }
    
    public String getPCompany(int id) {
        return (oferta.get(id).getCompany());
    }
    
    public int getPCo2(int id) {
        return (oferta.get(id).getCo2());
    }
    
    public int getPPrecio(int id) {
        return (oferta.get(id).getPrecio());
    }
    
    public int getPKWhTotal(int id) {
        return (oferta.get(id).getKWhTotal());
    }
    
    public int getPBeneficio(int id) {
        return (oferta.get(id).getBeneficio());
    }
    
}
