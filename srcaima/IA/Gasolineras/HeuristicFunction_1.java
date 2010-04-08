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
public class HeuristicFunction_1  implements HeuristicFunction{

    public double getHeuristicValue(Object estadoParaEvaluar) {
        estado estat = (estado)estadoParaEvaluar;
        float valor=0; 
        float numer=0; 
        float deno=0; 
        
        int numKmFaltan=0;        
        int prioridadesRealizadas=0;        
        for(int i=0; i < estat.countCamiones(); i++) {
            numKmFaltan=numKmFaltan+estat.getNumKilometros(i);
        }
        for(int i=0; i < estat.countGasolineras(); i++) {
            if (estat.getGasolinera(i)!=-1)
            {prioridadesRealizadas=prioridadesRealizadas+estat.c.getPrioridad(i);}
        }

        deno=((float)prioridadesRealizadas*(float)100)/
                (((float)estat.countGasolineras()*(float)3)+(float)0.0001);
        numer=((((float)80)*(float)numKmFaltan) + 
                (((float)20)*((float)estat.getTotalKm()-(float)numKmFaltan)))/
                ((float)numKmFaltan+(float)0.0001);
        valor=numer/(deno+(float)0.0001);
        
        valor=valor*1000;
        return (int)valor;
       
        
    }
}
