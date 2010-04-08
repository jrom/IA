package IA.probPathFinder;

import aima.search.framework.GoalTest;


public class ProbPathFinderGoalTest implements GoalTest {

  public boolean isGoalState(Object aState) {
    ProbPathFinderBoard board=(ProbPathFinderBoard)aState;
    return(board.isGoalState());
  }

}
