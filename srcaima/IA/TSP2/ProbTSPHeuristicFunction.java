package IA.TSP2;

import aima.search.framework.HeuristicFunction;

public class ProbTSPHeuristicFunction implements HeuristicFunction  {

    @Override
    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
  public boolean equals(Object obj) {
      boolean retValue;
      
      retValue = super.equals(obj);
      return retValue;
  }
  
  public double getHeuristicValue(Object state) {
   ProbTSPBoard board=(ProbTSPBoard)state;
   double sum=0.0;
   int nc;
   nc=board.getNCities();

   for(int i=0;i<nc;i++) sum=sum+board.distCities(i);

   return (sum);
  }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }
  
}
