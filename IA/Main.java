package IA;

import java.util.Properties;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.io.*; 

import aima.search.framework.Problem;
import aima.search.framework.SearchAgent;
import aima.search.framework.Search;
import aima.search.informed.HillClimbingSearch;
import aima.search.informed.SimulatedAnnealingSearch;

/* CLASSE DE TESTING DE LA PRÀCTICA */

public class Main
{
  public static int ITER = 10;
  static ArrayList<Stats> stats = new ArrayList<Stats>();
	public static boolean segon = true; // Activen els experiments del Bernat -> ja ho canviarem!
	
  public static void main (String[] args) throws Exception
  {
		System.out.println("----------------------------- Selecció d'experiments -----------------------------");
		System.out.println("Experiment 1: Comparació solucions inicials");
		System.out.println("Experiment 2: Estudi creixement de K");
		System.out.println("Experiment 3: Efectes de l'incorporació de la restricció addicional");
    // System.out.println("Experiment 5");
		System.out.println("Experiment 6: Ponderació entre heurístics");
		System.out.println("Experiment 7: Parametre Iteracions Totals de SimulatedAnnealing");
		System.out.println("Experiment 8: Parametre Iteracions per pas de SimulatedAnnealing");
		System.out.println("Experiment 9: Parametre K de SimulatedAnnealing");
		System.out.println("Experiment 10: Parametre Lambda de SimulatedAnnealing");
		System.out.println("Experiment 11: Comparació heurístics h1 i h2");
		System.out.println("Experiment 12: Comparació entre HC i SA");
		System.out.println("-----------------------------------------------------------------------------------");
		
		
		int experiment = -1;
		while (experiment > 12 || experiment < 1)
		{
			System.out.println("Introdueix el número d'experiment desitjat (de 1 a 12)");
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); 
			experiment = Integer.parseInt(in.readLine());
		}
		
		
		if(experiment < 4) mainSegon(experiment);
		else 
		{
      SquareBoard board = new SquareBoard(5, 20);
      System.out.println(board.solIni1());
      Problem problem1, problem2, problem3;

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

        // Heuristic 3
        problem3 = new Problem(board,
                              new SquareSuccessorFunction(),
                              new SquareGoalTest(),
                              new SquareHeuristicComb());


      if (experiment == 5)
      {
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


        // Heuristic 3
        // Hill Climbing
    		experimenta("Hill Climbing - Heu 3 - Ini 1",
                    problem3,
                    new HillClimbingSearch());


    		// Simulated Annealing
        experimenta("Simulated Annealing - Heu 3 - Ini 1",
                    problem3,
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

        // Heuristic 2
        problem3 = new Problem(board,
                              new SquareSuccessorFunction(),
                              new SquareGoalTest(),
                              new SquareHeuristicComb());

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

        // Heuristic 3
        // Hill Climbing
    		experimenta("Hill Climbing - Heu 3 - Ini 2",
                    problem3,
                    new HillClimbingSearch());


    		// Simulated Annealing
        experimenta("Simulated Annealing - Heu 3 - Ini 2",
                    problem3,
                    new SimulatedAnnealingSearch(5000,100,50,0.01)
                    );


        printStats(stats); // Imprimim els resultats que tenim guardats fins ara
      }

      if (experiment == 6)
      {
        // Experiment per provar el valor de ponderació dels heurístics
        // Fem servir el board i problem3 que ja tenen els valors que desitgem!
        board.solIni1();
        System.out.println(" ## PROVEM VALORS DE PONDERACIO HEURISTIC 3 ## ");
        stats = new ArrayList<Stats>(); // Netegem
        for(double kh = 0.1; kh <= 1.0; kh += 0.1)
        {
          board.KH21 = kh; // Modifiquem ponderació
          experimenta("Provant KH = "+kh, problem3, new SimulatedAnnealingSearch());
        }
        printStats(stats);
      }
    
      if (experiment == 7) // Modificar a true si volem realitzar aquest experiment
      { // Experiment per provar parametre ITERACIONS TOTALS de Simulated Annealing
        for(int exp = 0; exp < 10; exp++)
        {
          board = new SquareBoard(5,20);
          problem3 = new Problem(board,
                                new SquareSuccessorFunction(),
                                new SquareGoalTest(),
                                new SquareHeuristicComb());
          // Experiment per els parametres de SA
          board.solIni1(); // Of course, solucio inicial bona
          System.out.println(" ## PROVEM VALORS DE Simulated Annealing ## ");
          stats = new ArrayList<Stats>(); // Netegem
          for(int steps = 500; steps <= 10000; steps += 500)
          {
            experimenta("Provant SA steps = "+steps, problem3, new SimulatedAnnealingSearch(steps,100,50,0.01)); // steps, limit, K, lambda
          }
          printStats(stats);
        }
      }

      if (experiment == 8) // Modificar a true si volem realitzar aquest experiment
      { // Experiment per provar parametre Iteracions per pas de Simulated Annealing
        for(int exp = 0; exp < 10; exp++)
        {
          board = new SquareBoard(5,20);
          problem3 = new Problem(board,
                                new SquareSuccessorFunction(),
                                new SquareGoalTest(),
                                new SquareHeuristicComb());
          // Experiment per els parametres de SA
          board.solIni1(); // Of course, solucio inicial bona
          System.out.println(" ## PROVEM VALORS DE Simulated Annealing ## ");
          stats = new ArrayList<Stats>(); // Netegem
          for(int limit = 20; limit <= 200; limit += 10)
          {
            experimenta(""+limit, problem3, new SimulatedAnnealingSearch(3000,limit,50,0.01)); // steps, limit, K, lambda
          }
          printStats(stats);
        }
      }

      if (experiment == 9) // Modificar a true si volem realitzar aquest experiment
      { // Experiment per provar parametre K de Simulated Annealing
        for(int exp = 0; exp < 10; exp++)
        {
          board = new SquareBoard(5,20);
          problem3 = new Problem(board,
                                new SquareSuccessorFunction(),
                                new SquareGoalTest(),
                                new SquareHeuristicComb());
          // Experiment per els parametres de SA
          board.solIni1(); // Of course, solucio inicial bona
          System.out.println(" ## PROVEM VALORS DE Simulated Annealing ## ");
          stats = new ArrayList<Stats>(); // Netegem
          for(int kk = 10; kk <= 200; kk += 10)
          {
            experimenta(""+kk, problem3, new SimulatedAnnealingSearch(5500,150,kk,0.01)); // steps, limit, K, lambda
          }
          printStats(stats);
        }
      }

      if (experiment == 10) // Modificar a true si volem realitzar aquest experiment
      { // Experiment per provar parametre Lambda de Simulated Annealing
        for(int exp = 0; exp < 10; exp++)
        {
          board = new SquareBoard(5,20);
          problem3 = new Problem(board,
                                new SquareSuccessorFunction(),
                                new SquareGoalTest(),
                                new SquareHeuristicComb());
          // Experiment per els parametres de SA
          board.solIni1(); // Of course, solucio inicial bona
          System.out.println(" ## PROVEM VALORS DE Simulated Annealing ## ");
          stats = new ArrayList<Stats>(); // Netegem
          for(double lambda = 0.0005; lambda <= 0.2; lambda *= 2)
          {
            experimenta(""+lambda, problem3, new SimulatedAnnealingSearch(5500,150,110,lambda)); // steps, limit, K, lambda
          }
          printStats(stats);
        }
      }

      if (experiment == 11)
      { // Comparació H1 i H2
        for(int exp = 0; exp < 10; exp++)
        {
          board = new SquareBoard(5,20);
          board.solIni1(); // Of course, solucio inicial bona
          problem2 = new Problem(board,
                                new SquareSuccessorFunction(),
                                new SquareGoalTest(),
                                new SquareHeuristicDistTotal()); //H1
          problem3 = new Problem(board,
                                new SquareSuccessorFunction(),
                                new SquareGoalTest(),
                                new SquareHeuristicDistSemblant());

          System.out.println(" ## Comparem h1 i h2 ## ");
          stats = new ArrayList<Stats>(); // Netegem
          experimenta("H1", problem2, new SimulatedAnnealingSearch(5500,150,110,0.02)); // steps, limit, K, lambda
          experimenta("H2", problem3, new SimulatedAnnealingSearch(5500,150,110,0.02)); // steps, limit, K, lambda
          printStats(stats);
        }
      }

      if (experiment == 12)
      { // Comparació H1 i H2
        for(int exp = 0; exp < 20; exp++)
        {
          board = new SquareBoard(10,50);
          board.solIni1(); // Of course, solucio inicial bona
          problem1 = new Problem(board,
                                new SquareSuccessorFunction(),
                                new SquareGoalTest(),
                                new SquareHeuristicComb()); //H3
          stats = new ArrayList<Stats>(); // Netegem
          experimenta("HC", problem1, new HillClimbingSearch());
          experimenta("SA", problem1, new SimulatedAnnealingSearch(5500,150,110,0.02)); // steps, limit, K, lambda
          printStats(stats);
        }
      }

    }
  }

  public static void mainSegon(int experiment) throws Exception
	{
		stats = new ArrayList<Stats>(); 
		if(experiment == 1) // Es prova les diferents solucions inicials
		{
			System.out.println("Experiment Hill Climbing amb reinici aleatori");
			SquareBoard board = new SquareBoard(5, 20);
			
			String out;
			Stats s;
			Problem problem1;
			int IterRRHC = 100;
			
			long elapsed1 = 0;
			long elapsed2 = 0;
			long elapsed3 = 0;
			long elapsed32 = 0;
			long elapsed1sa = 0;
			long elapsed2sa = 0;
			long elapsed3sa = 0;
			int h1ini1 = 0;
			int h1ini2 = 0;
			int h1ini3 = 0;
			int h1ini32 = 0;
			int h1ini1sa = 0;
			int h1ini2sa = 0;
			int h1ini3sa = 0;	
						
			for (int k = 0; k < 5; k++)
			{
				double minim = Double.MAX_VALUE;
				for (int i = 0;i < IterRRHC; i++)
				{
			
					  out = board.solIni3();
						problem1 = new Problem(board,                        
						                         new SquareSuccessorFunction(),
						                         new SquareGoalTest(),        
						                         new SquareHeuristicDistTotal());
						s = experimenta("Hill Climbing random restart",                          
			    	            problem1,                                 
			    	            new HillClimbingSearch());                
						if (s.stats().h1 < minim) 
						{
							minim = s.stats().h1;
							if (i==(IterRRHC - 1)) System.out.println("It" + k + " " + out);
						}
						elapsed3 += s.stats().elapsed;
				}


				h1ini3 += minim;

			  board.solIni1();
				problem1 = new Problem(board,                        
				                         new SquareSuccessorFunction(),
				                         new SquareGoalTest(),        
				                         new SquareHeuristicDistTotal());
				s = experimenta("",
												problem1,
												new SimulatedAnnealingSearch(5500,150,50,0.01));
				h1ini1sa += s.stats().h1;
				elapsed1sa += s.stats().elapsed;
				
				
			
			  System.out.println(board.solIni1());
				problem1 = new Problem(board,                        
				                         new SquareSuccessorFunction(),
				                         new SquareGoalTest(),        
				                         new SquareHeuristicDistTotal());
				s = experimenta("",
												problem1,
												new HillClimbingSearch());
				h1ini1 += s.stats().h1;
				elapsed1 += s.stats().elapsed;
		
		
		
			 System.out.println(board.solIni1());
				problem1 = new Problem(board,                        
				                         new SquareSuccessorFunction(),
				                         new SquareGoalTest(),        
				                         new SquareHeuristicDistTotal());
				s = experimenta("",
												problem1,
												new SimulatedAnnealingSearch(5500,150,50,0.01));
				h1ini2sa += s.stats().h1;
				elapsed2sa += s.stats().elapsed;				

			  System.out.println(board.solIni2());				
				problem1 = new Problem(board,                        
				                         new SquareSuccessorFunction(),
				                         new SquareGoalTest(),        
				                         new SquareHeuristicDistTotal());
				s = experimenta("",
												problem1,
												new HillClimbingSearch());
				h1ini2 += s.stats().h1;
				elapsed2 += s.stats().elapsed;				

			  System.out.println(board.solIni3());
				problem1 = new Problem(board,                        
				                         new SquareSuccessorFunction(),
				                         new SquareGoalTest(),        
				                         new SquareHeuristicDistTotal());
				s = experimenta("",
												problem1,
												new SimulatedAnnealingSearch(5500,150,50,0.01));
				h1ini3sa += s.stats().h1;
				elapsed3sa += s.stats().elapsed;				
				
			  System.out.println(board.solIni3());	
				problem1 = new Problem(board,                        
				                         new SquareSuccessorFunction(),
				                         new SquareGoalTest(),        
				                         new SquareHeuristicDistTotal());
				s = experimenta("",
												problem1,	
												new HillClimbingSearch());
				h1ini32 += s.stats().h1;
				elapsed32 += s.stats().elapsed;				
			}
			System.out.println("*************** Valors H1 *****************");
			System.out.println("HC amb solIni1 x 10: " + h1ini1/10 + " Elapsed: " + elapsed1/10);
			System.out.println("HC amb solIni2 x 10: " + h1ini2/10 + " Elapsed: " + elapsed2/10);
			System.out.println("HC amb solIni3 x 10: " + h1ini32/10 + " Elapsed: " + elapsed32/10);			
			System.out.println("SA amb solIni1 x 10: " + h1ini1sa/10 + " Elapsed: " + elapsed1sa/10);
			System.out.println("SA amb solIni2 x 10: " + h1ini2sa/10 + " Elapsed: " + elapsed2sa/10);
			System.out.println("SA amb solIni3 x 10: " + h1ini3sa/10 + " Elapsed: " + elapsed3sa/10);
			System.out.println("Valor Mínim 50 execucions HCRR amb solIni3 x 10: " + h1ini3/10 + " Elapsed: " + elapsed3/10);
		} else if(experiment == 2) // Es prova l'increment de K
		{
			Stats s;
			
			for (int k = 2; k <= 10; k++)
			{
					SquareBoard board = new SquareBoard(k, 30);
					System.out.println("====================== K = " + k + " ======================");
		      board.solIni2();
		      Problem problem1;
					problem1 = new Problem(board,                        
					                         new SquareSuccessorFunction(),
					                         new SquareGoalTest(),        
					                         new SquareHeuristicDistTotal());
					s = experimenta("",
													problem1,	
													new HillClimbingSearch());
					System.out.println("HC: Steps=" + s.stats().steps + " Elapsed=" + s.stats().elapsed + " H1=" + s.stats().h1 + " Nodes=" + s.stats().nodes);
					board.solIni2();
					s = experimenta("",
													problem1,
													new SimulatedAnnealingSearch(5500,150,50,0.01));
					System.out.println("SA: Steps=" + s.stats().steps + " Elapsed=" + s.stats().elapsed + " H1=" + s.stats().h1 + " Nodes=" + s.stats().nodes);
				
			}
		
		
			
		} else if (experiment == 3) // Es prova la restricció addicional
		{
			segon = false;
			SquareBoard board1 = new SquareBoard(5, 30, true);
			Problem problem1;
		 
			
			board1.solIni1();
			problem1 = new Problem(board1,                        
			                         new SquareSuccessorFunction(),
			                         new SquareGoalTest(),        
			                         new SquareHeuristicDistTotal());
			experimenta("HC amb restricció addicional",
											problem1,
											new HillClimbingSearch());
		
			
			board1.solIni1();
			problem1 = new Problem(board1,                        
			                         new SquareSuccessorFunction(),
			                         new SquareGoalTest(),        
			                         new SquareHeuristicDistTotal());
			experimenta("SA amb restricció addicional",
											problem1,
											new SimulatedAnnealingSearch(5500,150,50,0.01));
		
			
			SquareBoard board2 = new SquareBoard(5, 30, false);
			
			board2.solIni1();
			problem1 = new Problem(board2,                        
			                         new SquareSuccessorFunction(),
			                         new SquareGoalTest(),        
			                         new SquareHeuristicDistTotal());
			experimenta("HC sense restricció addicional",
											problem1,
											new HillClimbingSearch());
	
			
			board2.solIni1();
			problem1 = new Problem(board2,                        
			                         new SquareSuccessorFunction(),
			                         new SquareGoalTest(),        
			                         new SquareHeuristicDistTotal());
			experimenta("SA sense restricció addicional",
											problem1,
											new SimulatedAnnealingSearch(5500,150,50,0.01));
	
			printStats(stats);
		}

		
	}

  public static Stats experimenta(String nom, Problem problem, Search search) throws Exception
  {
    Stats st = new Stats(nom);
    SearchAgent agent;
    long ini, fi;
    int nodes, steps;
    double h1 = 0.0, h2 = 0.0, h3 = 0.0;

    if (!segon) System.out.print(nom+" ");
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
      if (!segon) System.out.print(("."));
		}
    stats.add(st);
    if (!segon) System.out.println("");
		return st;
  }
  
  private static void printStats(ArrayList<Stats> ss)
  {
    System.out.println("+-----------------------------------------------+---------+-------+-------+----------+----------+----------+");
    System.out.println("|                                   Experiment  | Elapsed | Nodes | Steps |    H1    |    H2    |    H3    |");
    System.out.println("+-----------------------------------------------+---------+-------+-------+----------+----------+----------+");
    for(int i = 0; i < ss.size(); i++)
    {
      Exec ex = ss.get(i).stats();
      System.out.format("| %45s |   %5d | %5d | %5d | %8.2f | %8.2f | %8.2f |\n", ss.get(i).name, ex.elapsed, ex.nodes, ex.steps, ex.h1, ex.h2, ex.h3);
    }
    System.out.println("+-----------------------------------------------+---------+-------+-------+----------+----------+----------+");
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
