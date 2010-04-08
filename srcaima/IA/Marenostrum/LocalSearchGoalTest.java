/***********************************************************************
 ***                                                                 ***
 ***   Práctica IA Q1 / 2005                                         ***
 ***                                                                 ***
 ***   Alumno: Rafael Olivera Cano                                   ***
 ***   DNI:    47785413-S                                            ***
 ***   Grupo:  12                                                    ***
 ***                                                                 ***
 ***   LocalSearchGoalTest.java                                      ***
 ***                                                                 ***
 ***********************************************************************/
 
package IA.Marenostrum;

import java.util.ArrayList;
import aima.search.framework.GoalTest;

import aima.search.*;

public class LocalSearchGoalTest implements GoalTest 
{
  public boolean isGoalState(Object aState) 
  {
  	LocalSearchBoard LSBoard=(LocalSearchBoard) aState;
  	if (LSBoard.getProcesosSiAsignadosCount() == 168) return true;
  	return false;  	
  }
}