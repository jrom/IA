package IA.Centrals;

import aima.search.framework.HeuristicFunction;
import java.util.Random;

public class CentralsFuncioHeuristica4 implements HeuristicFunction {
	double ponderacio;
	
	
	public boolean equals(Object obj) {
	      boolean retValue;
	      
	      retValue = super.equals(obj);
	      return retValue;
	  }
	
	public double getHeuristicValue(Object state) {
		Representacio actual = (Representacio)state;
		
		return actual.getErrorTotal(2);
				
	}
}
