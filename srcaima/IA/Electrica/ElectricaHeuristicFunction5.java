package IA.Electrica;

import aima.search.framework.HeuristicFunction;
import java.util.Comparator;
import java.util.ArrayList;

public class ElectricaHeuristicFunction5 implements HeuristicFunction
{
	/*
	//fer el maxim i la mitja i penalitzar la diferencia
	public int getHeuristicValue(Object state) 
	{
	   Electrica board= (Electrica) state;
	   int h5=0;
	   int h5b=0;
	   int q1=0;
	   int max=0;
	   double mitja=0.0;
	   
	   for (int i=0;i<board.obteNcomp();i++){
		   for (int k=0;k<board.obteUnaComp(i).consultaQuantsPaquets();k++){
			   q1+=board.obteUnaComp(i).obtePaquetIndexat(k).consultaQgastada();
			  
		   }
		   
			   if (q1>max) max=q1;
 			  if (q1==0)h5+=100000;
			  mitja+=(double)q1;
			  q1=0;
			 
		  
	   }
	   mitja=mitja/((double)board.obteNcomp());
	   for (int i=0;i<board.obteNcomp();i++){
		   for (int k=0;k<board.obteUnaComp(i).consultaQuantsPaquets();k++){
			   q1+=board.obteUnaComp(i).obtePaquetIndexat(k).consultaQgastada();
			  
		   }
		   
			  
 			  
			 h5b+=(mitja-q1)*(mitja-q1);
			
			  q1=0;
			 
		  
	   }
	   
	   return (h5*h5+h5b+board.obteValHeuristic(5));
	}*/
	
	//penalitzar num de comp sense q  
	public double getHeuristicValue(Object state)
	{
	   Electrica board= (Electrica) state;
	   int h5=0;
	   int q1=0;
	   for (int i=0;i<board.obteNcomp();i++){
		   for (int k=0;k<board.obteUnaComp(i).consultaQuantsPaquets();k++){
			   q1+=board.obteUnaComp(i).obtePaquetIndexat(k).consultaQgastada();
			  
		   }
		   
			   
			  if (q1==0)h5+=100000;
			  q1=0;
			 
		  
	   }
	   return (h5*h5+board.obteValHeuristic(5));
	}	
	/*public int getHeuristicValue(Object state) 
	{
	   Electrica board= (Electrica) state;
	   int h5=0;
	   int q1=0;int qq1=0;int q2=0;int qq2=0;
	   for (int i=0;i<board.obteNcomp();i++){
		   for (int k=0;k<board.obteUnaComp(i).consultaQuantsPaquets();k++){
			   q1+=board.obteUnaComp(i).obtePaquetIndexat(k).consultaQgastada();
			  
		   }
		   for (int j=0;j<board.obteNcomp();j++){
			   //int c1=calculQ(i);
			 
			   for (int k=0;k<board.obteUnaComp(j).consultaQuantsPaquets();k++){
				   q2+=board.obteUnaComp(j).obtePaquetIndexat(k).consultaQgastada();
			   }
			   
			  if (q1>=q2)h5+=q1-q2;
			  else h5+=q2-q1;
			 
		   }
	   }
	   return (2*h5+board.obteValHeuristic(5));
	}*/
	 	  
	

}
