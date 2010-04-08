package IA.probAntenas;

import aima.search.framework.HeuristicFunction;

public class ProbAntenasHeuristicFunction implements HeuristicFunction  {

  public boolean equals(Object obj) {
      boolean retValue;
      
      retValue = super.equals(obj);
      return retValue;
  }
  
  public double getHeuristicValue(Object state) {
   ProbAntenasBoard board=(ProbAntenasBoard)state;
   int cob=board.calculaCobertura();
   int pot=board.potenciaEfectiva();
   int heur=0;
   if (pot>board.getMaxPotencia()) return(java.lang.Integer.MAX_VALUE);
   heur=((board.getDimPlano()*board.getDimPlano())-cob);
   return(heur);
  }
  
}
