/***********************************************************************
 ***                                                                 ***
 ***   Pr�ctica IA Q1 / 2005                                         ***
 ***                                                                 ***
 ***   Alumno: Rafael Olivera Cano                                   ***
 ***   DNI:    47785413-S                                            ***
 ***   Grupo:  12                                                    ***
 ***                                                                 ***
 ***   LocalSearchSuccessorFunction.java                             ***
 ***                                                                 ***
 ***********************************************************************/
 
package IA.Marenostrum;

import java.util.Vector;
import java.util.ArrayList;
import java.util.List;

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;
import aima.search.framework.HeuristicFunction;

public class LocalSearchSuccessorFunction implements SuccessorFunction 
{
	HeuristicFunction LSHeuristicFunction = null;
	
	public List getSuccessors(Object aState) 
	{
		ArrayList listaSucesores = new ArrayList();
    LocalSearchBoard LSBoard=(LocalSearchBoard) aState;
        
    double h;
    int idProc, TE, horaMin, horaMax;
    int nProcesos;
     
    /* Trataremos cada proceso no asignado */        
    nProcesos = LSBoard.getProcesosNoAsignadosCount();
    for(int i = 0; i < nProcesos; i++)
    {
    	idProc = LSBoard.getIdProcesoNoAsignado(i);
    	TE = LSBoard.getTEProceso(idProc);
    	horaMin = LSBoard.getHoraMinProceso(idProc);
    	horaMax = LSBoard.getHoraMaxProceso(idProc);
    	
    	/* Trataremos cada posibilidad de inicio del proceso */
    	for (int j = horaMin; j <= horaMax; j++)
    	{ 
    		/* Comprobamos que se pueda asignar */
    		if (LSBoard.comprobarAsignacion(j, TE))
    		{    		
	    		LocalSearchBoard newLSBoard = new LocalSearchBoard(LSBoard.getHoras(), LSBoard.getProcesosSiAsignados(), LSBoard.getProcesosNoAsignados(), LSBoard.getHorasInicio(), LSBoard.getHorasLibres(), LSBoard.getProcs());
					newLSBoard.asignarProceso(idProc, j);					
					h = LSHeuristicFunction.getHeuristicValue(newLSBoard);
										
					String S = new String("Asignar " + idProc + "   TE: " + TE + "   h = " + h + "   p = " + newLSBoard.getProcesosSiAsignadosCount());
					listaSucesores.add(new Successor(S, newLSBoard));      					
				}
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