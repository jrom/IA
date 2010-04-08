/*
 * ConnectatSuccessorFunction1.java
 *
 * Created on 4 de abril de 2006, 17:52
 */

package IA.Connectat;

import java.util.Vector;
import java.util.ArrayList;
import java.util.List;

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;
import aima.search.framework.HeuristicFunction;
/**
 *
 * @author  e7709254
 */
public class ConnectatSuccessorFunction1 implements SuccessorFunction {
    
    /** Creates a new instance of ConnectatSuccessorFunction1 */
   
        public List getSuccessors(Object estat) 
	{
		ArrayList llistaSuccessors = new ArrayList();
                
                ConnectatBoard CBoard=(ConnectatBoard) estat;
                
                boolean connexions[][] = CBoard.getConnexions();
                int graus[] = CBoard.getGrau();
                int NRepetidors = CBoard.getNRepetidors();
                int NCentrals = CBoard.getNCentrals();
                int nRepUsats = CBoard.getNumRepetidors();
                int maxNumRep = CBoard.getMaxNumRepetidors();
                int i;
                
                if (nRepUsats<maxNumRep)  { 
                    for (i=0; i<NRepetidors; i++) { //OPERADOR AFEGIR REPETIDOR ENTRE DOS NODES
                        if (graus[NCentrals+i]==0) { //si el repetidor no està usat
                            for (int u=0; u<NCentrals+NRepetidors; u++) {
                                for (int v=u+1; v<NCentrals+NRepetidors; v++) {
                                    if (connexions[u][v]&&connexions[v][u]&&i!=u&&i!=v) { 
                                        //comprovem que els nodes estiguin connectats i
                                        // que no sigui el propi node que volem afegir.
                                        //afegir el repetidor i entre els nodes u i v
                                        ConnectatBoard newCBoard = new ConnectatBoard(CBoard);
                                        newCBoard.afegirRepetidor(i,u,v);
                                        llistaSuccessors.add(
                                                new Successor("Afegit repetidor " + (NCentrals+i) + 
                                                " entre els nodes " + u + " i " +v+".",newCBoard));
                                    }
                                }
                            }

                        }
                    }
                }
                return llistaSuccessors;    
        }
}
    

