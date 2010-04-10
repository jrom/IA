package IA;

import aima.search.framework.HeuristicFunction;

public class SquareHeuristicComb implements HeuristicFunction
{
  public double getHeuristicValue(Object state)
  {
    SquareBoard board = (SquareBoard) state;
    return board.getHeuristic3();
  }
}
