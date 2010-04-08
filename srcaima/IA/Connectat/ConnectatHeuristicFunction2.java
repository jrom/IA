/*
 * ConnectatHeuristicFunction2.java
 *
 * Created on 22 de marzo de 2006, 17:18
 */

package IA.Connectat;

import java.util.Comparator;
import java.util.ArrayList;

import aima.search.framework.HeuristicFunction;

public class ConnectatHeuristicFunction2 implements HeuristicFunction  
{
  
  public double getHeuristicValue(Object state)
  {
		ConnectatBoard CBoard=(ConnectatBoard)state;		
		return CBoard.getErrorTotal()+CBoard.getNumRepetidors()*CBoard.getN()*CBoard.getM();//*CBoard.getNCentrals()*CBoard.getMaxNumRepetidors();						
	}  
}
