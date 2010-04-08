/*
 * ConnectatHeuristicFunction5.java
 *
 * Created on 22 de marzo de 2006, 17:18
 */

package IA.Connectat;

import java.util.Comparator;
import java.util.Vector;
import java.util.ArrayList;

import aima.search.framework.HeuristicFunction;

public class ConnectatHeuristicFunction5 implements HeuristicFunction  
{

  public double getHeuristicValue(Object state)
  {
		ConnectatBoard CBoard=(ConnectatBoard)state;
                boolean connexions[][] = CBoard.getConnexions();
                int ncentrals = CBoard.getNCentrals();
                int nrepetidors = CBoard.getNRepetidors();
                Vector centrals = CBoard.getCentrals();
                Vector repetidors = CBoard.getRepetidors();
                int valor, valormax=0;
                for (int i=0; i<ncentrals+nrepetidors; i++) {
                    for (int j=i+1; j<ncentrals+nrepetidors; j++) {
                        if (connexions[i][j]) {
                            int xi=0, yi=0, xj=0, yj=0;
                            if (i<ncentrals) {
                                xi = ((ConnectatBoard.node)centrals.elementAt(i)).getX();
                                yi = ((ConnectatBoard.node)centrals.elementAt(i)).getY();
                            } else {
                                xi = ((ConnectatBoard.node)repetidors.elementAt(i-ncentrals)).getX();
                                yi = ((ConnectatBoard.node)repetidors.elementAt(i-ncentrals)).getY();
                            }
                            if (j<ncentrals) {
                                xj = ((ConnectatBoard.node)centrals.elementAt(j)).getX();
                                yj = ((ConnectatBoard.node)centrals.elementAt(j)).getY();
                            } else {
                                xj = ((ConnectatBoard.node)repetidors.elementAt(j-ncentrals)).getX();
                                yj = ((ConnectatBoard.node)repetidors.elementAt(j-ncentrals)).getY();
                            }
                            valor= Math.abs(xi-xj)+Math.abs(yi-yj);
                            if (valor>valormax) valormax=valor;
                        }
                    }    
                }
                
		return (CBoard.getErrorTotal()+CBoard.getNumRepetidors()*CBoard.getN()*CBoard.getM())*valormax;						
	}  
}
