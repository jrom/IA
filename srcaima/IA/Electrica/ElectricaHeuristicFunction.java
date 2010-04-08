package IA.Electrica;

import aima.search.framework.HeuristicFunction;
import java.util.Comparator;
import java.util.ArrayList;
	 
public class ElectricaHeuristicFunction  implements HeuristicFunction   {
//	 energica mes ecologica (maxima f)
	 	  
	public double getHeuristicValue(Object state)
	{
		Electrica board= (Electrica) state;
		return (board.obteValHeuristic(1));
	}
	 	  
	 public boolean equals(Object obj) 
	 {
		 boolean retValue;
		 retValue = super.equals(obj);
		 return retValue;
	 }
}