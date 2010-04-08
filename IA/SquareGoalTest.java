package IA;

import aima.search.framework.GoalTest;

public class SquareGoalTest implements GoalTest
{

  public boolean isGoalState(Object state)
  {
		SquareBoard board = (SquareBoard) state;
    // TODO check if is a fucking goal state...
		return true; // Because I'm worth it XD
	}

}
