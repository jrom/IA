
package IA.probIA15;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import aima.search.framework.GraphSearch;
import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;
import aima.search.framework.TreeSearch;
import aima.search.informed.AStarSearch;
import aima.search.uninformed.BreadthFirstSearch;
import aima.search.uninformed.DepthLimitedSearch;
import aima.search.uninformed.IterativeDeepeningSearch;
import aima.search.informed.IterativeDeepeningAStarSearch;


public class ProbIA15Demo {
    
    public static void main(String[] args){
        ProbIA15Board IAP15=new ProbIA15Board();
        IAP15BreadthFirstSearch(IAP15);
        IAP15DepthLimitedSearch(IAP15,7); 
        IAP15IterativeDeepeningSearch(IAP15);
        IAP15IterativeDeepeningAStarSearchH1(IAP15);
        IAP15IterativeDeepeningAStarSearchH2(IAP15);
        IAP15AStarSearchH1(IAP15);
        IAP15AStarSearchH2(IAP15);
        
    }
    
    private static void IAP15BreadthFirstSearch(ProbIA15Board IAP15) {
        System.out.println("\nIA15 Breadth First  -->");
        try {
            Problem problem =  new Problem(IAP15,new ProbIA15SuccessorFunction(), new ProbIA15GoalTest());
            Search search =  new BreadthFirstSearch(new TreeSearch());
            SearchAgent agent = new SearchAgent(problem,search);
            
            System.out.println();
            printActions(agent.getActions());
            printInstrumentation(agent.getInstrumentation());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void IAP15IterativeDeepeningSearch(ProbIA15Board IAP15) {
        System.out.println("\nIA15 Iterative Deepening  -->");
        try {
            Problem problem =  new Problem(IAP15,new ProbIA15SuccessorFunction(), new ProbIA15GoalTest());
            Search search =  new IterativeDeepeningSearch();
            SearchAgent agent = new SearchAgent(problem,search);
            
            System.out.println();
            printActions(agent.getActions());
            printInstrumentation(agent.getInstrumentation());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

     private static void IAP15IterativeDeepeningAStarSearchH1(ProbIA15Board IAP15) {
        System.out.println("\nIA15 Iterative Deepening AStar H1 -->");
        try {
            Problem problem =  new Problem(IAP15,new ProbIA15SuccessorFunction(), new ProbIA15GoalTest(),new ProbIA15HeuristicFunction());
            Search search =  new IterativeDeepeningAStarSearch();
            SearchAgent agent = new SearchAgent(problem,search);
            
            System.out.println();
            printActions(agent.getActions());
            printInstrumentation(agent.getInstrumentation());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

      private static void IAP15IterativeDeepeningAStarSearchH2(ProbIA15Board IAP15) {
        System.out.println("\nIA15 Iterative Deepening AStar H2 -->");
        try {
            Problem problem =  new Problem(IAP15,new ProbIA15SuccessorFunction(), new ProbIA15GoalTest(),new ProbIA15HeuristicFunction2());
            Search search =  new IterativeDeepeningAStarSearch();
            SearchAgent agent = new SearchAgent(problem,search);
            
            System.out.println();
            printActions(agent.getActions());
            printInstrumentation(agent.getInstrumentation());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   private static void IAP15DepthLimitedSearch(ProbIA15Board IAP15,int limit) {
        System.out.println("\nIA15 Depth Limited Search  -->");
        try {
            Problem problem =  new Problem(IAP15,new ProbIA15SuccessorFunction(), new ProbIA15GoalTest());
            Search search =  new DepthLimitedSearch(limit);
            SearchAgent agent = new SearchAgent(problem,search);
            
            System.out.println();
            printActions(agent.getActions());
            printInstrumentation(agent.getInstrumentation());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void IAP15AStarSearchH1(ProbIA15Board TSPB) {
        System.out.println("\nIA15 AStar H1  -->");
        try {
            Problem problem =  new Problem(TSPB,new ProbIA15SuccessorFunction(), new ProbIA15GoalTest(),new ProbIA15HeuristicFunction());
            Search search =  new AStarSearch(new GraphSearch());
            SearchAgent agent = new SearchAgent(problem,search);
            
            System.out.println();
            printActions(agent.getActions());
            printInstrumentation(agent.getInstrumentation());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
        private static void IAP15AStarSearchH2(ProbIA15Board TSPB) {
        System.out.println("\nIA15 AStar H2  -->");
        try {
            Problem problem =  new Problem(TSPB,new ProbIA15SuccessorFunction(), new ProbIA15GoalTest(),new ProbIA15HeuristicFunction2());
            Search search =  new AStarSearch(new GraphSearch());
            SearchAgent agent = new SearchAgent(problem,search);
            
            System.out.println();
            printActions(agent.getActions());
            printInstrumentation(agent.getInstrumentation());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    

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
