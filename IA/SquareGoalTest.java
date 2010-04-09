package IA;

import aima.search.framework.GoalTest;

public class SquareGoalTest implements GoalTest
{

  public boolean isGoalState(Object state)
  {
		SquareBoard board = (SquareBoard) state;
    // Retornem fals perque es tracta de cerca local
		return false;
	}

}
