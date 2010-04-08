/*
 * Pr�ctica de IA (B�squeda)
 * FIB - UPC
 * Curso 2006-2007
 * Cuatrimestre de Oto�o
 *
 * Daniel Garc�a P�rez
 * Sergio Vico Marfil
 *
 * DistribucionDemo.java
 *
 */

package IA.Electricidad;

import java.util.Random;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import aima.search.framework.GraphSearch;
import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;
import aima.search.framework.TreeSearch;
import aima.search.informed.SimulatedAnnealingSearch;
import aima.search.informed.HillClimbingSearch;
import aima.search.informed.GreedyBestFirstSearch;

public class DistribucionDemo {
    
    public DistribucionDemo() {
    }
    
   
    public static void main(String[] args) {
        // inicializamos los valores
        inicializar();
        printDates();
        Distribucion dist = new Distribucion();
        DistHillClimbingSearch(dist);
        DistSimulatedAnnealingSearch(dist);
    }
    
    private static void inicializar() {
        Random r = new Random();
        OfertaDemanda od = OfertaDemanda.getOfertaDemanda();
        int maxC = r.nextInt(10);
        int maxP = r.nextInt(5);
        
        for (int i = 0; i < maxC; i++ ) {
            od.addConsumidor(i,"c-"+String.valueOf(i),r.nextInt(2)+1,r.nextInt(9)+1,r.nextInt(300)+200);
        }
        for (int j = 0; j < maxP; j++ ) {
            od.addPaquete(j,"p-"+String.valueOf(j),r.nextInt(2)+1,r.nextInt(9)+1,r.nextInt(4000)+1000,r.nextInt(45)+5);
        }
    }
    
    private static void DistHillClimbingSearch(Distribucion dist) {
        System.out.println("\n--HillClimbing--");
        try {
            Problem problem =  new Problem(dist,new DistribucionSuccessorFunction1(), new DistribucionGoalTest(),new DistribucionHeuristicFunction4());
            HillClimbingSearch search =  new HillClimbingSearch();
            SearchAgent agent = new SearchAgent(problem,search);
            
            System.out.println();
            printActions(agent.getActions());
            printInstrumentation(agent.getInstrumentation());
            System.out.println("\n"+((Distribucion)search.getLastSearchState()).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void DistSimulatedAnnealingSearch(Distribucion dist) {
        System.out.println("\n--Simulated Annealing--");
        try {
            Problem problem =  new Problem(dist,new DistribucionSuccessorFunction1(), new DistribucionGoalTest(),new DistribucionHeuristicFunction4());
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
    
    private static void printDates() {
        OfertaDemanda od = OfertaDemanda.getOfertaDemanda();
        
        System.out.println("\nDatos de paquetes\n");
        for (int i = 0; i < od.getNumOferta(); i++) {
            System.out.println("Id:"+i+"\tCompany:"+od.getPCompany(i)+"\tCo2:"+od.getPCo2(i)+"\tkWh:"+od.getPKWhTotal(i)+"\tPrecio:"+od.getPPrecio(i)+"\tBeneficio:"+od.getPBeneficio(i)+"\n");
        }
        System.out.println("\nDatos de consumidores\n");
        for (int j = 0; j < od.getNumDemanda(); j++) {
            System.out.println("Id:"+j+"\tNom:"+od.getCNom(j)+"\tCo2Min:"+od.getCCo2Min(j)+"\tkWh:"+od.getCKWh(j)+"\tPrecio:"+od.getCPrecioMax(j)+"\n");
        }
    } 

    
}
