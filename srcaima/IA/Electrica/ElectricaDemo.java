package IA.Electrica;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

/*
import IA.probTSP.ProbTSPGoalTest;
import IA.probTSP.ProbTSPSuccessorFunction;
import IA.probTSP.ProbTSPHeuristicFunction;
import IA.probTSP.ProbTSPBoard;*/

import aima.search.framework.GraphSearch;
import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;
import aima.search.framework.TreeSearch;
import aima.search.informed.SimulatedAnnealingSearch;
import aima.search.informed.HillClimbingSearch;
import aima.search.informed.GreedyBestFirstSearch;


public class ElectricaDemo {



    public static void main(String[] args){
        Electrica board=new Electrica(3,4);
        ElectricaHillClimbingSearch(board);
       // ElectricaSimulatedAnnealingSearch(TSPB);
    }
    
    private static void ElectricaHillClimbingSearch(Electrica board) {
        System.out.println("\nHillClimbing  -->");
        try {
            
            Problem problem=new Problem(board,new ElectricaSuccessorFunction (), new ElectricaGoalTest(), new ElectricaHeuristicFunction());
            Search search= new HillClimbingSearch();
            SearchAgent agent = new SearchAgent (problem,search);


            System.out.println();
            printActions(agent.getActions());
            printInstrumentation(agent.getInstrumentation());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /*
private static void TSPSimulatedAnnealingSearch(ProbTSPBoard TSPB) {
        System.out.println("\nTSP Simulated Annealing  -->");
        try {
            Problem problem =  new Problem(TSPB,new ProbTSPSuccessorFunction(), new ProbTSPGoalTest(),new ProbTSPHeuristicFunction());
            SimulatedAnnealingSearch search =  new SimulatedAnnealingSearch(2000,100,5,0.001);
            //search.traceOn();
            SearchAgent agent = new SearchAgent(problem,search);
            
            System.out.println();
            printActions(agent.getActions());
            printInstrumentation(agent.getInstrumentation());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
*/
    
private static void printInstrumentation(Properties properties) {
        Iterator keys = properties.keySet().iterator();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            String property = properties.getProperty(key);
            System.out.println(key + " : " + property);
        }
        
    }
    
    private static void printActions(List actions) {
        for (int i = 0; i < actions.size(); i++) {
            String action = (String) actions.get(i);
            System.out.println(action);
        }
    }
    
    
}


