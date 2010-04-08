package IA.Centrals;

import aima.search.framework.HeuristicFunction;

public class CentralsFuncioHeuristica1 implements HeuristicFunction {
	
	public boolean equals(Object obj) {
	      boolean retValue;
	      
	      retValue = super.equals(obj);
	      return retValue;
	  }
	
	public double getHeuristicValue(Object state) {
		Representacio actual = (Representacio)state;
		int error = actual.getErrorTotal(1);
		//return(Integer.MAX_VALUE -error);
		return(error);
	}
}
