/*
 *
 * Práctica de IA (Búsqueda)
 * FIB - UPC
 * Curso 2006-2007
 * Cuatrimestre de Otoño
 *
 * Daniel García Pérez
 * Sergio Vico Marfil
 *
 * DistribucionGreedy.java
 *
 */

package IA.Electricidad;

import java.util.PriorityQueue;
import java.lang.Comparable;

public class DistribucionGreedy {
    
    private OfertaDemanda od;
    private Distribucion dist;
    private PriorityQueue cpp;
    private PriorityQueue cpc;
    
    /** Creates a new instance of DistribucionGreedy */
    public DistribucionGreedy() {
        od = OfertaDemanda.getOfertaDemanda();
        dist = new Distribucion();
        cpp = new PriorityQueue();
        cpc = new PriorityQueue();
    }
    
    private Distribucion inicializar(Ordenacion ord) {
        int numP = od.getNumOferta();
        int numC = od.getNumDemanda();
        NodoCola nodo;
        NodoCola paq;
        NodoCola cons;
        int orden = 0;
        
        //Metemos los paquetes en la cola de prioridad
        for (int i = 0; i < numP; i++) {
            orden = ord.getOrdValue(Ordenacion.PAQUETES, i);
            cpp.add(new NodoCola(orden, i));            
        }
        
        //Metemos los consumidores en la cola de prioridad
        for (int i = 0; i < numC; i++) {
            orden = ord.getOrdValue(Ordenacion.CONSUMIDORES, i);
            cpc.add(new NodoCola(orden, i));
        }

        //Realizamos las asignaciones (algoritmo voraz)
        while (!cpp.isEmpty() && !cpc.isEmpty()) {
            paq = (NodoCola)cpp.element();
            cons = (NodoCola)cpc.element();
            if (dist.puedeReasignar(cons.getValor(), paq.getValor())) {
                dist.reasignar(cons.getValor(), paq.getValor());
                cpc.remove();
            }else{
                cpp.remove();
            }
        }
        
        return dist;
    }
    
    public Distribucion inicializarEcologia() {
        OrdenacionEcologica ord = new OrdenacionEcologica();
        return inicializar(ord);
    }
    
    public Distribucion inicializarAhorro() {
        OrdenacionAhorro ord = new OrdenacionAhorro();
        return inicializar(ord);
    }
    
    public Distribucion inicializarEcoAhorro() {
        OrdenacionEcoAhorro ord = new OrdenacionEcoAhorro();
        return inicializar(ord);        
    }
    
    public Distribucion inicializarEcoAhorroBeneficio() {
        OrdenacionEcoAhorroBeneficio ord = new OrdenacionEcoAhorroBeneficio();
        return inicializar(ord);    
    }
    
}

class NodoCola implements Comparable{
    private int clave;
    private int valor;
    /** Creates a new instance of NodoCola */
    public NodoCola(int clave, int valor) {
        this.clave = clave;
        this.valor = valor;
    }

    public int compareTo(Object o) {
        NodoCola ent = (NodoCola) o;
        if (this.clave > ent.clave) return 1;
        if (this.clave < ent.clave) return -1;        
        return 0;
    }
    
    public void setValor(int valor) {
        this.valor = valor;        
    }
    
    public int getValor() {
        return valor;
    }
    
    public void setClave(int clave) {
        this.clave = clave;
    }
    
    public int getClave() {
        return clave;
    }
}

abstract class Ordenacion {
    
    protected OfertaDemanda od= OfertaDemanda.getOfertaDemanda();
    static final boolean PAQUETES = true;
    static final boolean CONSUMIDORES = false;
    
    public Ordenacion() {
    }
    
    abstract public int getOrdValue(boolean type, int id);    
}

class OrdenacionEcologica extends Ordenacion {
    
    public int getOrdValue(boolean type, int id) {
        int resultado = 0;
        
        if (type) {
        //PAQUETES
            resultado = od.getPCo2(id);
        }else{
        //CONSUMIDORES
            resultado = od.getCCo2Min(id);
        }
        
        return resultado;
    }
    
}

class OrdenacionAhorro extends Ordenacion {
    
    public int getOrdValue(boolean type, int id) {
        int resultado = 0;
        
        if (type) {
        //PAQUETES
            resultado = od.getPPrecio(id);
        }else{
        //CONSUMIDORES            
            resultado = od.getCPrecioMax(id);            
        }
      
        return resultado;
    }
    
}
class OrdenacionEcoAhorro extends Ordenacion {
    
    public int getOrdValue(boolean type, int id) {
        int resultado = 0;
        
        if (type) {
        //PAQUETES
            resultado =(10 - od.getPPrecio(id)) + (od.getPCo2(id) * 10);
        }else{
        //CONSUMIDORES            
            resultado =(10 - od.getCPrecioMax(id)) + (od.getCCo2Min(id) * 10);
        }
    
        return resultado;
    }
    
}
class OrdenacionEcoAhorroBeneficio extends Ordenacion {
    
    public int getOrdValue(boolean type, int id) {
        int resultado = 0;
        
        if (type) {
        //PAQUETES
            resultado = (10 - od.getPPrecio(id)) + (od.getPCo2(id) * 10) + (od.getPBeneficio(id)/5);
        }else{
        //CONSUMIDORES
            resultado = (10 - od.getCPrecioMax(id)) + (od.getCCo2Min(id) * 10);
        }
        
        return resultado;
    }
    
}
