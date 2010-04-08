/*
 * ConnectatHeuristicFunction4.java
 *
 * Created on 22 de marzo de 2006, 17:18
 */

package IA.Connectat;

import java.util.Comparator;
import java.util.ArrayList;

import aima.search.framework.HeuristicFunction;

public class ConnectatHeuristicFunction4 implements HeuristicFunction  
{
  
  public double getHeuristicValue(Object state)
  {
		ConnectatBoard CBoard=(ConnectatBoard)state;
                int sumatoriGrauRepetidors=0;
                int graus[] = CBoard.getGrau();
                for (int i=CBoard.getNCentrals(); i<CBoard.getNCentrals()+CBoard.getNRepetidors(); i++)
                    sumatoriGrauRepetidors+=graus[i];
		return CBoard.getErrorTotal()*(CBoard.getNumRepetidors()+1)/(1+sumatoriGrauRepetidors)/(1+sumatoriGrauRepetidors);						
	}  
}
