/*
 * ConnectatProves.java
 *
 * Created on 16 de abril de 2006, 17:38
 */

package IA.Connectat;

import java.util.Random;
import aima.search.framework.*;
import aima.search.informed.SimulatedAnnealingSearch;
import aima.search.informed.HillClimbingSearch;
/**
 *
 * @author  e7867258
 */
public class ConnectatProves {
    static ConnectatBoard ciutat;
    static Random myRandom;
    
    
    
    
    /** Creates a new instance of ConnectatProves */
    public ConnectatProves() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int i=1000;
       while(i>0){
           i--;
           provaHC();
           //provaSA();
        }
    }
    
    private static void provaSA(){
        
        //Variables per a escriurte el fitxer resultat
        int info_einicial, info_operadors, info_heuristic, info_k = 0, info_iter = 0, info_passos_iter = 0;
        String info_algoritme;
        double info_lambda = 0;
        
        ConnectatCercador cerca;
        SuccessorFunction operadors=null;
        HeuristicFunction heuristic=null;
    
        myRandom = new Random();
        ciutat = new ConnectatBoard();
        
        int n = (myRandom.nextInt(3)+1)*8;
        int ncent = (myRandom.nextInt(30)+10)*n*n/500;
        int nrep = (myRandom.nextInt(30)+10)*n*n/500;
        int alfa=3,beta=2,gamma=1;
        
        ciutat.generarCiutat(n,n,ncent,nrep);
        ciutat.actualitzarParametres(alfa, beta, gamma, nrep);
        
        info_einicial=3;
        ciutat.generarEstatInicial(info_einicial);
        
        int op = myRandom.nextInt(7);
        while (op!=5&&op!=6) op = myRandom.nextInt(7);
        info_operadors=op+1;
         switch (op){
             case 0:
                 operadors = new ConnectatSuccessorFunction1();
                 break;
             case 1:
                 operadors = new ConnectatSuccessorFunction2();
                 break;
             case 2:
                 operadors = new ConnectatSuccessorFunction3();
                 break;
             case 3:
                 operadors = new ConnectatSuccessorFunction4();
                 break;
             case 4:
                 operadors = new ConnectatSuccessorFunction5();
                 break;
             case 5:
                 operadors = new ConnectatSuccessorFunction6();
                 break;
             case 6:
                 operadors = new ConnectatSuccessorFunction7();
                 break;
                 
         }
        
        
        int heu = myRandom.nextInt(5);
        while (heu!=1&&heu!=4) heu = myRandom.nextInt(5);
        info_heuristic=heu+1;
         switch (heu){
             case 0:
                 heuristic = new ConnectatHeuristicFunction1();
                 break;
             case 1:
                 heuristic = new ConnectatHeuristicFunction2();
                 break;
             case 2:
                 heuristic = new ConnectatHeuristicFunction3();
                 break;
             case 3:
                 heuristic = new ConnectatHeuristicFunction4();
                 break;
             case 4:
                 heuristic = new ConnectatHeuristicFunction5();
                 break;
                 
         }
         
         info_k=5;
         info_iter=2000;
         info_passos_iter=5;
         info_lambda=0.25;
         
         
        System.err.println("n: "+n+" ncent: "+ncent+" nrep: "+nrep+" heu: "+info_heuristic+" op: "+info_operadors+" ei: "+(info_einicial+1));
         try{
        cerca = new ConnectatCercador(ciutat,operadors,heuristic, info_iter, info_passos_iter, info_k,info_lambda);
        cerca.executarCerca();
        cerca.fitxerResultats(ciutat,info_einicial+1,"SA", info_operadors, info_heuristic, info_k,info_iter, info_passos_iter, info_lambda);
         }catch(Exception e){
             System.err.println("No passa res: "+e.toString());
         }
    }
    private static void provaHC(){
        
        //Variables per a escriurte el fitxer resultat
        int info_einicial, info_operadors, info_heuristic, info_k, info_iter, info_passos_iter;
        String info_algoritme;
        double info_lambda;
        
        ConnectatCercador cerca;
        SuccessorFunction operadors=null;
        HeuristicFunction heuristic=null;
    
        myRandom = new Random();
        ciutat = new ConnectatBoard();
        
        int n = (myRandom.nextInt(3)+1)*8;
        int ncent = (myRandom.nextInt(30)+10)*n*n/500;
        int nrep = (myRandom.nextInt(30)+10)*n*n/500;
        int alfa=myRandom.nextInt(6)+3;
        int beta=myRandom.nextInt(alfa-2)+2;
        int gamma=myRandom.nextInt(beta-1)+1;
        
        ciutat.generarCiutat(n,n,ncent,nrep);
        ciutat.actualitzarParametres(alfa, beta, gamma, nrep);
        
        info_einicial=2;//myRandom.nextInt(5);
        ciutat.generarEstatInicial(info_einicial);
        
        int op = myRandom.nextInt(7);
        while (op!=5&&op!=6) op = myRandom.nextInt(7);
        info_operadors=op+1;
         switch (op){
             case 0:
                 operadors = new ConnectatSuccessorFunction1();
                 break;
             case 1:
                 operadors = new ConnectatSuccessorFunction2();
                 break;
             case 2:
                 operadors = new ConnectatSuccessorFunction3();
                 break;
             case 3:
                 operadors = new ConnectatSuccessorFunction4();
                 break;
             case 4:
                 operadors = new ConnectatSuccessorFunction5();
                 break;
             case 5:
                 operadors = new ConnectatSuccessorFunction6();
                 break;
             case 6:
                 operadors = new ConnectatSuccessorFunction7();
                 break;
                 
         }
        
        
        int heu = myRandom.nextInt(5);
        while (heu!=1&&heu!=4) heu = myRandom.nextInt(5);
        info_heuristic=heu+1;
         switch (heu){
             case 0:
                 heuristic = new ConnectatHeuristicFunction1();
                 break;
             case 1:
                 heuristic = new ConnectatHeuristicFunction2();
                 break;
             case 2:
                 heuristic = new ConnectatHeuristicFunction3();
                 break;
             case 3:
                 heuristic = new ConnectatHeuristicFunction4();
                 break;
             case 4:
                 heuristic = new ConnectatHeuristicFunction5();
                 break;
                 
         }
        System.err.println("n: "+n+" ncent: "+ncent+" nrep: "+nrep+" heu: "+info_heuristic+" op: "+info_operadors+" ei: "+(info_einicial+1));
         try{
        cerca = new ConnectatCercador(ciutat,operadors,heuristic);
        cerca.executarCerca();
        cerca.fitxerResultats(ciutat,info_einicial+1,"HC", info_operadors, info_heuristic, -1, -1, -1, -1);
         }catch(Exception e){
             System.err.println("No passa res: "+e.toString());
         }
    }
}
