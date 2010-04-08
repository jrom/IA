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
 * Distribucion.java
 *
 */

package IA.Electricidad;

import java.util.*;

public class Distribucion {
    private OfertaDemanda od;
    private int cAsignaciones [];
    private int pKWHVendido [];
    
    
    public Distribucion() {
        od = OfertaDemanda.getOfertaDemanda();
        cAsignaciones = new int[od.getNumDemanda()];
        for (int i = 0; i < cAsignaciones.length; i++) {
            cAsignaciones[i] = -1;
        }
        pKWHVendido = new int[od.getNumOferta()];
    }
    
    public Distribucion(Distribucion d) {
        od = OfertaDemanda.getOfertaDemanda();
        cAsignaciones = d.cAsignaciones.clone();
        pKWHVendido = d.pKWHVendido.clone();
    }
    
    //OPERADORES
    
    public void reasignar(int idConsumidor, int idPaquete) {
        //miramos que paquete tenia antes
        int ant = cAsignaciones[idConsumidor];
        
        //asignamos el paquete al cliente
        cAsignaciones[idConsumidor] = idPaquete;
        
        //recalculamos los KWH vendidos de los dos paquetes
        if (ant != -1) {
            pKWHVendido[ant] -= od.getCKWh(idConsumidor);
        }
        pKWHVendido[idPaquete] += od.getCKWh(idConsumidor);
    }
    
    public boolean puedeReasignar( int idConsumidor, int idPaquete) {
        //El paquete ya está asignado a este consumidor
        if( cAsignaciones[idConsumidor] == idPaquete ) return false;
        
        //No queda suficiente energia
        if( pKWHVendido[idPaquete] + od.getCKWh(idConsumidor) > od.getPKWhTotal(idPaquete)) return false;
        
        //El consumidor no puede pagar el paquete
        if( od.getCPrecioMax(idConsumidor) < od.getPPrecio(idPaquete)) return false;
        
        //El paquete es demasiado contaminante
        if( od.getCCo2Min(idConsumidor) > od.getPCo2(idPaquete)) return false;
        
        return true;
    }
    
    
    public int getPaqueteAsignado(int idConsumidor) {
        return (cAsignaciones[idConsumidor] );
    }
    
    
    //FUNCIONES AUXILIARES
    
    public String toString() {
        
        String s[] = new String[od.getNumOferta()+1];
        int i;
        for (i = 0; i < od.getNumOferta(); i++) {
            s[i] = new String("\nPaquete: "+i+"\n");
        }
        s[od.getNumOferta()] = new String("\nSin asignacion\n");
        
        for (i = 0; i < od.getNumDemanda(); i++) {
            if(cAsignaciones[i] < 0) {
                s[od.getNumOferta()] += "  Id="  + String.valueOf(i) + " Nombre: " + od.getCNom(i) + "\n";
            }else {
                s[cAsignaciones[i]] += "  Id="  + String.valueOf(i) + " Nombre: " + od.getCNom(i) + "\n";
            }
        }
        
        String ret ="";
        
        for (i = 0; i <= od.getNumOferta(); i++) {
            ret += s[i];
        }
        return ret;
    }
    
    public boolean equals(Object o) {
        //Casteamos la entrada a Distribución
        Distribucion dist = (Distribucion) o;
        
        //La dirección de memoria del objeto de Datos es la misma
        if (dist.od != this.od) return false;
        
        //La cantidad de consumidores y paquetes es la misma
        if (this.cAsignaciones.length != dist.cAsignaciones.length) return false;
        if (this.pKWHVendido.length != dist.pKWHVendido.length) return false;
        
        //Para cada consumidor el paquete asignado es el mismo
        for (int i = 0; i < this.cAsignaciones.length; i++) {
            if (this.cAsignaciones[i] != dist.cAsignaciones[i]) return false;
        }
        
        //Para cada paquete la cantidad de kWh vendida es la misma
        for (int i = 0; i < this.pKWHVendido.length; i++) {
            if (this.pKWHVendido[i] != dist.pKWHVendido[i]) return false;
        }
        
        return true;
    }
    
    public int getNumPaquetes() {
        return od.getNumOferta();
    }
    
    public int getNumConsumidores() {
        return od.getNumDemanda();
    }
}
