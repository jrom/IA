package IA.Electrica;
import aima.search.framework.GoalTest;

import aima.search.*;

public class ElectricaGoalTest implements GoalTest{

   public boolean isGoalState(Object aState) {
    Electrica board=(Electrica) aState;
    return(board.isGoalState());
  }
}