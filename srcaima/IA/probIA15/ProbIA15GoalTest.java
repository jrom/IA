/*
 * ProbIA15Problem.java
 *
 * Created on February 5, 2004, 12:58 PM
 */

package IA.probIA15;

import aima.search.framework.GoalTest;

public class ProbIA15GoalTest implements GoalTest{
    
    
    public boolean isGoalState(Object aState) {
        ProbIA15Board board= (ProbIA15Board) aState;

        return board.isGoal();
    }
    
    
   
}
