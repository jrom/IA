/*
 * ConnectatCercador.java
 *
 * Created on 14 de abril de 2006, 20:15
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package IA.Connectat;
/**
 * Resol el problema de cerca
 */

import aima.search.framework.*;
import aima.search.informed.SimulatedAnnealingSearch;
import aima.search.informed.HillClimbingSearch;

import java.io.*;

import java.util.List;
import java.util.Iterator;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class ConnectatCercador {
    
    public final static int HILL_CLIMBING = 0;
    public final static int SIMULATED_ANNEALING = 1;
    public final static int HC = HILL_CLIMBING;
    public final static int SA = SIMULATED_ANNEALING;
    
    Problem problem;
    Search search;
    SearchAgent agent;
    String propietats;
    String accions;
    
    String nodesexp;
    long temps;
    
    /** Creates a new instance of ConnectatCercador */
    public ConnectatCercador(ConnectatBoard ciutat, SuccessorFunction operadors, HeuristicFunction heuristic) {
         problem = new Problem(ciutat,operadors, new ConnectatGoalTest(),heuristic);
         search =  new HillClimbingSearch();
    }
    
    public ConnectatCercador(ConnectatBoard ciutat, SuccessorFunction operadors, HeuristicFunction heuristic,int it, int pit, int k, double lbd) {
        problem = new Problem(ciutat,operadors, new ConnectatGoalTest(),heuristic);
        search =  new SimulatedAnnealingSearch(it,pit,k,lbd);
    }
    
    public void executarCerca(){
        
     try {
       Date d1,d2;
       Calendar a,b;
       
       d1=new Date();
       agent = new SearchAgent(problem,search);
       d2=new Date();

       a= Calendar.getInstance();
       b= Calendar.getInstance();
       a.setTime(d1);
       b.setTime(d2);

       temps=b.getTimeInMillis()-a.getTimeInMillis();
       
       System.out.println(temps+" ms");
       
       setInstrumentation(agent.getInstrumentation());
       setAccions(agent.getActions());
         
   } catch (Exception e) {
            e.printStackTrace();
     }
    }
    
    public ConnectatBoard getEstatFinal(){
        if (search instanceof HillClimbingSearch){
        return (ConnectatBoard)((HillClimbingSearch)search).getLastSearchState();
        } else {
            return (ConnectatBoard)((SimulatedAnnealingSearch)search).getLastSearchState();
        }
    }
    
/***********************************************************************
 ***                                                                 ***
 ***   printInstrumentation                                          ***
 ***                                                                 ***
 ***********************************************************************/
	
	private void setInstrumentation(Properties properties) 
	{
            propietats = new String();
            propietats += "Temps de cerca: "+temps+" ms\n";
            Iterator keys = properties.keySet().iterator();
            if (keys.hasNext()) {
                String key = (String) keys.next();
                String property = properties.getProperty(key);
                propietats += "Nodes expandits: "+property+"\n";
                nodesexp=property;
            }
	}
        public String getPropietats(){
            return propietats;
        }

/***********************************************************************
 ***                                                                 ***
 ***   printActions                                                  ***
 ***                                                                 ***
 ***********************************************************************/
 
    private void setAccions(List actions) 
    {
        accions = new String();
        for (int i = 0; i < actions.size(); i++) {
            String action = (String) actions.get(i);
            accions += action+"\n";
        }
    }
    public String getAccions(){
        return accions;
    }
    
    
/***********************************************************************
 ***                                                                 ***
 ***   fitxerResultats                                               ***
 ***                                                                 ***
 ***********************************************************************/
    public void fitxerResultats(ConnectatBoard board,int estat_inicial,String algoritme,int operadors,int heuristic,int k,int iter,int passos_iter, double lambda){
   try {
       File fitxer = new File("resultats.txt");
       boolean nou=!fitxer.exists();
       fitxer.createNewFile();
       FileWriter out = new FileWriter(fitxer,true);
       if (nou){
           //noms de variables
           out.write("N\tM\tncentrals\tnrepetidors\testat_inicial\tmaxrep\talpha\tbeta\tgamma\talgoritme\toperadors\theuristic\tk\titer\tpassos_iter\tlambda\ttemps\tnodes_exp\terror_inicial\terror_final\trepet_usats\n");
       }
       ConnectatBoard board_final = getEstatFinal();
       if (k==-1){
           // Ser� HillClimbing, no volem variables de SA
           out.write(""+board.getN()+"\t"+board.getM()+"\t"+board.getNCentrals()+"\t"+board.getNRepetidors()+"\t"+estat_inicial+"\t"+board.getMaxNumRepetidors()+"\t"+board.getAlfa()+"\t"+board.getBeta()+"\t"+board.getGamma()+"\t"+algoritme+"\t"+operadors+"\t"+heuristic+"\t\t\t\t\t"+temps+"\t"+nodesexp+"\t"+board.getErrorTotal()+"\t"+board_final.getErrorTotal()+"\t"+board_final.getNumRepetidors()+"\n");
       }
       else
           out.write(""+board.getN()+"\t"+board.getM()+"\t"+board.getNCentrals()+"\t"+board.getNRepetidors()+"\t"+estat_inicial+"\t"+board.getMaxNumRepetidors()+"\t"+board.getAlfa()+"\t"+board.getBeta()+"\t"+board.getGamma()+"\t"+algoritme+"\t"+operadors+"\t"+heuristic+"\t"+k+"\t"+iter+"\t"+passos_iter+"\t"+lambda+"\t"+temps+"\t"+nodesexp+"\t"+board.getErrorTotal()+"\t"+board_final.getErrorTotal()+"\t"+board_final.getNumRepetidors()+"\n");
       
       out.close();
   } catch (Exception e){
       System.err.println("No s'ha pogut escriure el fitxer de resultats.");
       System.err.println(e.toString());
   }
    }
    
    
}
