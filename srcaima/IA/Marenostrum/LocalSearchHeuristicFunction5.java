/***********************************************************************
 ***                                                                 ***
 ***   Pr�ctica IA Q1 / 2005                                         ***
 ***                                                                 ***
 ***   Alumno: Rafael Olivera Cano                                   ***
 ***   DNI:    47785413-S                                            ***
 ***   Grupo:  12                                                    ***
 ***                                                                 ***
 ***   LocalSearchHeuristicFunction5.java                             ***
 ***                                                                 ***
 ***********************************************************************/
 
package IA.Marenostrum;

import java.util.Comparator;
import java.util.ArrayList;

import aima.search.framework.HeuristicFunction;

public class LocalSearchHeuristicFunction5 implements HeuristicFunction  
{
  public boolean equals(Object obj) 
  {
      boolean retValue;
      
      retValue = super.equals(obj);
      return retValue;
  }
  
  public double getHeuristicValue(Object state)
  {
		LocalSearchBoard LSBoard=(LocalSearchBoard)state;				
		return (LSBoard.getProcsCount(1) * 5) + (LSBoard.getProcsCount(2) * 4) + (LSBoard.getProcsCount(3) * 3) + (LSBoard.getProcsCount(4) * 2) + (LSBoard.getProcsCount(5) * 1) + LSBoard.getIntervalosCount() + LSBoard.getRangoCount();
	}  
}
