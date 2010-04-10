package IA;

import java.util.Properties;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import aima.search.framework.Problem;
import aima.search.framework.SearchAgent;
import aima.search.framework.Search;
import aima.search.informed.HillClimbingSearch;
import aima.search.informed.SimulatedAnnealingSearch;

public class Main
{
  public static int ITER = 5;
  static ArrayList<Stats> stats = new ArrayList<Stats>();

  public static void main (String[] args) throws Exception
  {
    SquareBoard board = new SquareBoard(5, 20);
    board.solIni1();
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
		experimenta("Hill Climbing - Heu 1 - Ini 1",
                problem1,
                new HillClimbingSearch());


		// Simulated Annealing
    experimenta("Simulated Annealing - Heu 1 - Ini 1",
                problem1,
                new SimulatedAnnealingSearch(5000,100,50,0.01)
                );

    // Heuristic 2
    // Hill Climbing
		experimenta("Hill Climbing - Heu 2 - Ini 1",
                problem2,
                new HillClimbingSearch());


		// Simulated Annealing
    experimenta("Simulated Annealing - Heu 2 - Ini 1",
                problem2,
                new SimulatedAnnealingSearch(5000,100,50,0.01)
                );
                


    // Ara amb sol ini 2
    board.solIni2();
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
		experimenta("Hill Climbing - Heu 1 - Ini 2",
                problem1,
                new HillClimbingSearch());


		// Simulated Annealing
    experimenta("Simulated Annealing - Heu 1 - Ini 2",
                problem1,
                new SimulatedAnnealingSearch(5000,100,50,0.01)
                );

    // Heuristic 2
    // Hill Climbing
		experimenta("Hill Climbing - Heu 2 - Ini 2",
                problem2,
                new HillClimbingSearch());


		// Simulated Annealing
    experimenta("Simulated Annealing - Heu 2 - Ini 2",
                problem2,
                new SimulatedAnnealingSearch(5000,100,50,0.01)
                );

    System.out.println("+-----------------------------------------------+---------+-------+-------+----------+----------+----------+");
    System.out.println("|                                   Experiment  | Elapsed | Nodes | Steps |    H1    |    H2    |    H3    |");
    System.out.println("+-----------------------------------------------+---------+-------+-------+----------+----------+----------+");
    for(int i = 0; i < stats.size(); i++)
    {
      Exec ex = stats.get(i).stats();
      System.out.format("| %45s |   %5d | %5d | %5d | %8.2f | %8.2f | %8.2f |\n", stats.get(i).name, ex.elapsed, ex.nodes, ex.steps, ex.h1, ex.h2, ex.h3);
    }
    System.out.println("+-----------------------------------------------+---------+-------+-------+----------+----------+----------+");
  }

  public static void experimenta(String nom, Problem problem, Search search) throws Exception
  {
    Stats st = new Stats(nom);
    SearchAgent agent;
    long ini, fi;
    int nodes, steps;
    double h1 = 0.0, h2 = 0.0, h3 = 0.0;

		System.out.print(nom+" ");
		for(int i = 0; i < ITER; i++)
		{
		  ini = System.currentTimeMillis();
      agent = new SearchAgent(problem, search);
      fi = System.currentTimeMillis();

      nodes = Integer.parseInt( (String) agent.getInstrumentation().get("nodesExpanded"));
      steps = agent.getActions().size();

      if (search.getClass().isInstance(new HillClimbingSearch()))
      {
        h1 = ((SquareBoard) ((HillClimbingSearch) search).getLastSearchState()).getHeuristic1();
        h2 = ((SquareBoard) ((HillClimbingSearch) search).getLastSearchState()).getHeuristic2();
        h3 = ((SquareBoard) ((HillClimbingSearch) search).getLastSearchState()).getHeuristic3();
      }
      else if (search.getClass().isInstance(new SimulatedAnnealingSearch()))
      {
        h1 = ((SquareBoard) ((SimulatedAnnealingSearch) search).getLastSearchState()).getHeuristic1();
        h2 = ((SquareBoard) ((SimulatedAnnealingSearch) search).getLastSearchState()).getHeuristic2();
        h3 = ((SquareBoard) ((SimulatedAnnealingSearch) search).getLastSearchState()).getHeuristic3();

      }
      st.add(nodes, steps, h1, h2, h3, (fi-ini));
      System.out.print(("."));
		}
    stats.add(st);
		System.out.println("");
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
  
  static class Exec
  {
    int nodes;
    int steps;
    double h1;
    double h2;
    double h3;
    long elapsed;

    Exec()
    {
      nodes = 0;
      steps = 0;
      h1 = 0;
      h2 = 0;
      elapsed = 0;
    }

    Exec(int nodes_, int steps_, double h1_, double h2_, double h3_, long elapsed_)
    {
      nodes = nodes_;
      steps = steps_;
      h1 = h1_;
      h2 = h2_;
      h3 = h3_;
      elapsed = elapsed_;
    }
  }

  static class Stats
  {
    ArrayList<Exec> execs = new ArrayList<Exec>();
    String name;

    Stats(String desc)
    {
      name = desc;
    }

    void add(int n, int s, double h1, double h2, double h3, long e)
    {
      Exec ex = new Exec(n,s,h1,h2,h3,e);
      execs.add(ex);
    }

    Exec stats()
    {
      Exec ex = new Exec();
      for (int i = 0; i < execs.size(); i++)
      {
        ex.nodes += execs.get(i).nodes;
        ex.steps += execs.get(i).steps;
        ex.h1 += execs.get(i).h1;
        ex.h2 += execs.get(i).h2;
        ex.h3 += execs.get(i).h3;
        ex.elapsed += execs.get(i).elapsed;
      }
      ex.nodes /= execs.size();
      ex.steps /= execs.size();
      ex.h1 /= execs.size();
      ex.h2 /= execs.size();
      ex.h3 /= execs.size();
      ex.elapsed /= execs.size();
      return ex;
    }
  }
}
