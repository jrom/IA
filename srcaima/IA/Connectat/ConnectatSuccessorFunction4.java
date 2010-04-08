/*
 * ConnectatSuccessorFunction4.java
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
public class ConnectatSuccessorFunction4 implements SuccessorFunction {
    
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
                                    if (connexions[u][v]&&connexions[v][u]&&i!=u&&i!=v) { //comprovem que els nodes estiguin connectats i que no sigui el propi node que volem afegir
                                        //afegir el repetidor i entre els nodes u i v
                                        ConnectatBoard newCBoard = new ConnectatBoard(CBoard);
                                        newCBoard.afegirRepetidor(i,u,v);
                                        llistaSuccessors.add(new Successor("Afegit repetidor " + (NCentrals+i) + " entre els nodes " + u + " i " +v+".",newCBoard));
                                    }
                                }
                            }
                            
                            for (int u=0; u<NCentrals+NRepetidors; u++) {
                                for (int v=0; v<NCentrals+NRepetidors; v++) {
                                    for (int w=0; w<NCentrals+NRepetidors; w++) {
                                        if (connexions[u][v]&&connexions[v][w]&&i!=u&&i!=v&&u!=w&&i!=w) { //comprovem que els nodes estiguin connectats i que no sigui el propi node que volem afegir
                                            //afegir el repetidor i entre els nodes u i v
                                            ConnectatBoard newCBoard = new ConnectatBoard(CBoard);
                                            newCBoard.afegirRepetidor(i,u,v,w);
                                            llistaSuccessors.add(new Successor("Afegit repetidor " + (NCentrals+i) + " entre els nodes " + u + " , " +v+ " i " +w+".",newCBoard));
                                        }
                                    }
                                }
                            }
                            
                            for (int u=0; u<NCentrals+NRepetidors; u++) {
                                for (int v=0; v<NCentrals+NRepetidors; v++) {
                                    for (int w=0; w<NCentrals+NRepetidors; w++) {
                                        for (int s=0; s<NCentrals+NRepetidors; s++) {
                                            if (connexions[u][v]&&connexions[v][w]&&connexions[w][s] && i!=u && i!=v && i!=w && i!=s && u!=w && v!=s) { //comprovem que els nodes estiguin connectats i que no sigui el propi node que volem afegir
                                                //afegir el repetidor i entre els nodes u i v
                                                ConnectatBoard newCBoard = new ConnectatBoard(CBoard);
                                                newCBoard.afegirRepetidor(i,u,v,w,s);
                                                llistaSuccessors.add(new Successor("Afegit repetidor " + (NCentrals+i) + " entre els nodes " + u + " , " +v+ " , " +w+ " i " +s+".",newCBoard));
                                            }
                                        }
                                    }
                                }
                            }
                            
                            for (int u=0; u<NCentrals+NRepetidors; u++) {
                                for (int v=0; v<NCentrals+NRepetidors; v++) {
                                    for (int w=0; w<NCentrals+NRepetidors; w++) {
                                        for (int s=0; s<NCentrals+NRepetidors; s++) {
                                            for (int t=0; t<NCentrals+NRepetidors; t++) {
                                                if (connexions[u][v]&&connexions[v][w]&&connexions[w][s]&&connexions[s][t] && i!=u && i!=v && i!=w && i!=s && i!=t && u!=w && v!=s && w!=t) { //comprovem que els nodes estiguin connectats i que no sigui el propi node que volem afegir
                                                    //afegir el repetidor i entre els nodes u i v
                                                    ConnectatBoard newCBoard = new ConnectatBoard(CBoard);
                                                    newCBoard.afegirRepetidor(i,u,v,w,s,t);
                                                    llistaSuccessors.add(new Successor("Afegit repetidor " + (NCentrals+i) + " entre els nodes " + u + " , " +v+ " , " +w+" , " +s+ " i " +t+".",newCBoard));
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                        }
                    }
                }
                
                if (nRepUsats>0)  { 
                    for (i=0; i<NRepetidors; i++) {
                        if (graus[NCentrals+i]>0) { //si el repetidor esta usat
                            ConnectatBoard newCBoard = new ConnectatBoard(CBoard);
                            newCBoard.treureRepetidor(i);
                            llistaSuccessors.add(new Successor("Tret el repetidor " + (NCentrals+i) +".",newCBoard));
                        }
                    }
                }
                
                return llistaSuccessors;    
        }
}