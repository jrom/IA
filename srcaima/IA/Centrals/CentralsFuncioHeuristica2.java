package IA.Centrals;

import aima.search.framework.HeuristicFunction;
import java.util.Random;

public class CentralsFuncioHeuristica2 implements HeuristicFunction {
	
	public boolean equals(Object obj) {
	      boolean retValue;
	      
	      retValue = super.equals(obj);
	      return retValue;
	  }
	
	public double getHeuristicValue(Object state) {
		Representacio actual = (Representacio)state;
		//int error = actual.getErrorTotal(2);
		int error = actual.getErrorTotal(1);
		int rutil = actual.getRepetidorsUtil();
		int numr = actual.getNumRepetidors();
		
		
		//return(error+((error)/numr)*rutil);
		return (error/(numr-rutil+1));
	}
}
