package IA;

import aima.search.framework.HeuristicFunction;

public class SquareHeuristic implements HeuristicFunction
{
  public double getHeuristicValue(Object state)
  {
    SquareBoard board = (SquareBoard) state;
    return Math.random() * 20; // :D
  }
}
