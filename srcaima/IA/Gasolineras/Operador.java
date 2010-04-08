/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package IA.Gasolineras;
import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jesus
 */
public class Operador implements SuccessorFunction{

    public List getSuccessors(Object estadoActual) {
        ArrayList retVal= new ArrayList();
        
        estado estat=(estado) estadoActual;
        estado aux;
        
        for (int j = 0; j < estat.countGasolineras()-1; j++) {
            for (int i = j+1; i < estat.countGasolineras(); i++) {
            if (estat.puedeCambiar(j, i)) {
                aux = new estado(estat);                    
                aux.Cambiar(j,i);
                retVal.add(new Successor("Cambio de la Gasolinera=" + String.valueOf(j) +
                        " con la Gasolinera=" + String.valueOf(i)+"\n", aux));
                }
            }
        }
        for (int j = 0; j < estat.countCamiones(); j++) {
            for (int i = 0; i < estat.countGasolineras(); i++) {
            if (estat.puedeAfegir(j, i)) {
                aux = new estado(estat);                    
                aux.Afegir(j,i);
                retVal.add(new Successor("Agregar el Camión=" + String.valueOf(j) + 
                        " a la Gasolinera=" + String.valueOf(i)+"\n", aux));
                }
            }
        }
        for (int i = 0; i < estat.countGasolineras(); i++) {
            if (estat.puedeBorrar(i)) {
                aux = new estado(estat);                     
                aux.Borrar(i);
                retVal.add(new Successor("No enviar camión a la Gasolinera=" + 
                        String.valueOf(i)+"\n", aux));
                }
        }
        
        return retVal;
    }
}
