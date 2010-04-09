package IA;

import java.util.List;

import aima.search.framework.Problem;
import aima.search.framework.SearchAgent;
import aima.search.informed.HillClimbingSearch;
import aima.search.informed.SimulatedAnnealingSearch;

public class Main
{
  public static void main (String[] args) throws Exception
  {
    SquareBoard board = new SquareBoard(10, 50);
    Problem problem;
    
    problem = new Problem(board,
                          new SquareSuccessorFunction(),
                          new SquareGoalTest(),
                          new SquareHeuristic());

    HillClimbingSearch search = new HillClimbingSearch();
    SearchAgent agent = new SearchAgent(problem, search);
    List actions = agent.getActions();
    
    System.out.println("Expanded nodes: " + agent.getInstrumentation().get("nodesExpanded"));

    System.out.println("Printing actions:");
    for(int i = 0; i < actions.size(); i++)
      System.out.println(actions.get(i));

  }
}
