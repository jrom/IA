package IA;

import java.util.List;
import java.util.Properties;
import java.util.Iterator;

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

    printInstrumentation(agent.getInstrumentation());
    printActions(agent.getActions());
    
    System.out.println("Search Outcome=" + search.getOutcome());
		System.out.println("Final State=\n" + search.getLastSearchState());

  }
  
  
  // Copiat d'exemples d'AIMA
  private static void printInstrumentation(Properties properties)
  {
    Iterator keys = properties.keySet().iterator();
    while (keys.hasNext())
    {
      String key = (String) keys.next();
      String property = properties.getProperty(key);
      System.out.println(key + " : " + property);
    }      
  }
  
  private static void printActions(List actions)
  {
    for (int i = 0; i < actions.size(); i++)
    {
      String action = (String) actions.get(i);
      System.out.println(action);
    }
  }
}
