package IA.probAntenas;

import aima.search.framework.GoalTest;


public class ProbAntenasGoalTest implements GoalTest {

  public boolean isGoalState(Object aState) {
    ProbAntenasBoard board=(ProbAntenasBoard)aState;
    return(board.isGoalState());
  }

}
