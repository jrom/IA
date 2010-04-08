/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package IA.Gasolineras;
import aima.search.framework.HeuristicFunction;

/**
 *
 * @author jesus
 */
public class HeuristicFunction_2  implements HeuristicFunction{

    public double getHeuristicValue(Object estadoParaEvaluar) {
        estado estat = (estado)estadoParaEvaluar;
        float valor=0; 
        float numer=0; 
        float deno=0; 
        
        int prioridadesNoAsignadas=0;        
        int viajesquedan=0;
        int viajeshechos=0;
        for(int i=0; i < estat.countGasolineras(); i++) {
            if (estat.getGasolinera(i)==-1)
            {prioridadesNoAsignadas=prioridadesNoAsignadas+estat.c.getPrioridad(i);
             viajesquedan++;
            }else
            {viajeshechos++;}
        }

        numer=(float)prioridadesNoAsignadas + ((float)0.2*viajeshechos +(float)0.8*viajesquedan)*(float)2;
        
        int numKmFaltan=0;        
        for(int i=0; i < estat.countCamiones(); i++) {
            numKmFaltan=numKmFaltan+estat.getNumKilometros(i);
        }
        
        deno=((float)0.2*((float)estat.getTotalKm()-(float)numKmFaltan)) + ((float)0.8*(float)numKmFaltan);
        
        valor=numer/(deno+(float)0.0001);
        
        valor=valor*1000;
        return (int)valor;

        
        
    }
}
