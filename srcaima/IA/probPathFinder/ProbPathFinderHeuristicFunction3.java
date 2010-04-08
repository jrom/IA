package IA.probPathFinder;

import aima.search.framework.HeuristicFunction;

public class ProbPathFinderHeuristicFunction3 implements HeuristicFunction  {

  public boolean equals(Object obj) {
      boolean retValue;
      
      retValue = super.equals(obj);
      return retValue;
  }
  
  public double getHeuristicValue(Object state) {
   ProbPathFinderBoard board=(ProbPathFinderBoard)state;
   return (board.cityBlock()+board.blocking());
  }
  
}
