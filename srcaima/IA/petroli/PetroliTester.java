package IA.petroli;


import java.util.Iterator;

import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;
import aima.search.informed.HillClimbingSearch;
import aima.search.informed.SimulatedAnnealingSearch;


public class PetroliTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		System.out.println("------Benvinguts al Tester de les Plataformes Petroliferes-------------------");
		System.out.println("-----------------------------------------------------------------------------");
		
		PetroliLocalSearchBoard board = new PetroliLocalSearchBoard();
		board.loadRandomScenario(20,50, true);
		//board.readInitialState("initial.pet");
	    board.printBoard();
	    
	    
	    Problem problem = new Problem(board,new PetroliSuccesorsA(),new PetroliGoalTest(),new PetroliHeuristic());
	    //Search search = new SimulatedAnnealingSearch();
	    HillClimbingSearch search = new HillClimbingSearch();
	    PetroliLocalSearchBoard finalState = null;
	    try 
	    {
			SearchAgent agent = new SearchAgent(problem,search);
			finalState = (PetroliLocalSearchBoard)search.getLastSearchState();
			System.out.println("L'estat finalisim:");
			for (Iterator it = agent.getActions().iterator(); it.hasNext(); )
			{
				System.out.println((String)it.next());
			}
			finalState.printBoard();
		} 
	    catch (Exception e) 
	    {
			e.printStackTrace();
		}

	    if (finalState!=null)
	    {
	    	 //printPetroli petroPainter = new printPetroli(finalState);
//	    	PetroliWindow window = new PetroliWindow(finalState);
//	    	window.setVisible(true);
	    }
	   
		
		
		
	}
	

}
