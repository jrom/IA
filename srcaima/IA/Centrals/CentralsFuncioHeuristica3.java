package IA.Centrals;

import aima.search.framework.HeuristicFunction;
import java.util.Random;

public class CentralsFuncioHeuristica3 implements HeuristicFunction {
	double ponderacio;
	
	public CentralsFuncioHeuristica3(int pond){
		
		this.ponderacio=(double)pond;
	}
	
	public boolean equals(Object obj) {
	      boolean retValue;
	      
	      retValue = super.equals(obj);
	      return retValue;
	  }
	
	public double getHeuristicValue(Object state) {
		Representacio actual = (Representacio)state;
		double error = actual.getErrorTotal(1);
		double rutil = actual.getRepetidorsUtil();
		double numr = actual.getNumRepetidors();
		double errorMax = actual.getErrorMax();
				
		double res = ((error/errorMax)*ponderacio)+((rutil/numr)*(100-ponderacio));
		res=res*100000;	
		//System.out.println("res= "+res);
		return (int)res;
				
	}
}
