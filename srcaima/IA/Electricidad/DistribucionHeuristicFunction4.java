/*
 * Pr�ctica de IA (B�squeda)
 * FIB - UPC
 * Curso 2006-2007
 * Cuatrimestre de Oto�o
 *
 * Daniel Garc�a P�rez
 * Sergio Vico Marfil
 *
 *
 * DistribucionHeuristicFunction4.java
 *
 */

package IA.Electricidad;


public class DistribucionHeuristicFunction4 extends DistribucionHeuristic {
    private OfertaDemanda od= OfertaDemanda.getOfertaDemanda();
    
    public double getHeuristicValue(Object estado) {
        Distribucion dist = (Distribucion)estado;
        double val=0;
        int paq;
        for(int i=0; i < dist.getNumConsumidores(); i++) {
            paq = dist.getPaqueteAsignado(i);
            
            if( paq == -1 ) {
                val+=1;
            }
            else {
                val += (od.getPPrecio(paq) - od.getCPrecioMax(i));
                val += ((10*(od.getCCo2Min(i) - od.getPCo2(paq)))/3.0);
                val -= od.getPBeneficio(paq)/5.0; 
            }
        }
        
        return ((int)val);
    }
    
    
}