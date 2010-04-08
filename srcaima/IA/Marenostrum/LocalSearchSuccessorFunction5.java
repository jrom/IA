/***********************************************************************
 ***                                                                 ***
 ***   Pr�ctica IA Q1 / 2005                                         ***
 ***                                                                 ***
 ***   Alumno: Rafael Olivera Cano                                   ***
 ***   DNI:    47785413-S                                            ***
 ***   Grupo:  12                                                    ***
 ***                                                                 ***
 ***   LocalSearchSuccessorFunction5.java                             ***
 ***                                                                 ***
 ***********************************************************************/
 
package IA.Marenostrum;

import java.util.Vector;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;
import aima.search.framework.HeuristicFunction;

public class LocalSearchSuccessorFunction5 implements SuccessorFunction 
{
	HeuristicFunction LSHeuristicFunction = null;
	
	public List getSuccessors(Object aState) 
	{
		ArrayList listaSucesores = new ArrayList();
    LocalSearchBoard LSBoard=(LocalSearchBoard) aState;
      
    double h;
    int idProc, TE, horaMin, horaMax;
    int nProcesos;
    int horaInicio;
    Random myRandom = new Random();
    
    /* Trataremos cada proceso no asignado */        
    for(int i = 0; i < LSBoard.getProcesosNoAsignadosCount(); i++)
	  {
	   	idProc = LSBoard.getIdProcesoNoAsignado(i);
    	TE = LSBoard.getTEProceso(idProc);
    	horaMin = LSBoard.getHoraMinProceso(idProc);
    	horaMax = LSBoard.getHoraMaxProceso(idProc);
	    	
	   	/* Trataremos una posibilidad aleatoria de inicio del proceso */    	
	   	horaInicio = horaMin + myRandom.nextInt(horaMax - horaMin + 1);
	    	
	   	/* Comprobamos que se pueda asignar */
	   	if (LSBoard.comprobarForzar(horaInicio, TE))
	   	{    		
		   	LocalSearchBoard newLSBoard = new LocalSearchBoard(LSBoard.getHoras(), LSBoard.getProcesosSiAsignados(), LSBoard.getProcesosNoAsignados(), LSBoard.getHorasInicio(), LSBoard.getHorasLibres(), LSBoard.getProcs());
				newLSBoard.forzarProceso(idProc, horaInicio);					
				h = LSHeuristicFunction.getHeuristicValue(newLSBoard);
						
				String S = new String("Forzar " + idProc + "   TE: " + TE + "   h = " + h + "   p = " + newLSBoard.getProcesosSiAsignadosCount());
				listaSucesores.add(new Successor(S, newLSBoard));      					
			}			
	  }    
    return listaSucesores;    
  }
  
  public void setHeuristico(int pHeuristico)
  {
  	switch (pHeuristico)
  	{
  		case 1:
  			LSHeuristicFunction = new LocalSearchHeuristicFunction();
  			break;
  		case 2:
  			LSHeuristicFunction = new LocalSearchHeuristicFunction2();
  			break;
  		case 3:
  			LSHeuristicFunction = new LocalSearchHeuristicFunction3();
  			break;
  		case 4:
  			LSHeuristicFunction = new LocalSearchHeuristicFunction4();
  			break;
  		case 5:
  			LSHeuristicFunction = new LocalSearchHeuristicFunction5();
  			break;
  	}
  }
}