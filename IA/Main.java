package IA;

import java.util.List;
import java.util.Properties;
import java.util.Iterator;

import aima.search.framework.Problem;
import aima.search.framework.SearchAgent;
import aima.search.framework.Search;
import aima.search.informed.HillClimbingSearch;
import aima.search.informed.SimulatedAnnealingSearch;

public class Main
{
  public static void main (String[] args) throws Exception
  {
    SquareBoard board = new SquareBoard(5, 20);
    Problem problem1, problem2;

    // Heuristic 1
    problem1 = new Problem(board,
                          new SquareSuccessorFunction(),
                          new SquareGoalTest(),
                          new SquareHeuristicDistTotal());

    // Heuristic 2
    problem2 = new Problem(board,
                          new SquareSuccessorFunction(),
                          new SquareGoalTest(),
                          new SquareHeuristicDistSemblant());

    // Heuristic 1
    // Hill Climbing
		experimenta("Hill Climbing - Heuristic 1",
                problem1,
                new HillClimbingSearch());


		// Simulated Annealing
    experimenta("Simulated Annealing - Heuristic 1",
                problem1,
                new SimulatedAnnealingSearch(5000,100,50,0.001)
                );

    // Heuristic 2
    // Hill Climbing
		experimenta("Hill Climbing - Heuristic 2",
                problem2,
                new HillClimbingSearch());


		// Simulated Annealing
    experimenta("Simulated Annealing - Heuristic 2",
                problem2,
                new SimulatedAnnealingSearch(5000,100,50,0.001)
                );

  }

  public static void experimenta(String nom, Problem problem, Search search) throws Exception
  {
    SearchAgent agent;
    long ini, fi;

    System.out.println("====================================================");
		System.out.println(nom);

    ini = System.currentTimeMillis();
    agent = new SearchAgent(problem, search);
    fi = System.currentTimeMillis();

    printInstrumentation(agent.getInstrumentation());
    // printActions(agent.getActions());

    if (search.getClass().isInstance(new HillClimbingSearch()))
		  System.out.println(((SquareBoard) ((HillClimbingSearch) search).getLastSearchState()).info());
    else if (search.getClass().isInstance(new SimulatedAnnealingSearch()))
      System.out.println(((SquareBoard) ((SimulatedAnnealingSearch) search).getLastSearchState()).info());
		System.out.println("Elapsed time: "+(fi-ini)+"ms");
		System.out.println("\n");
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
