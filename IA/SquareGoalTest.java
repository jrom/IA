package IA;

import aima.search.framework.GoalTest;

public class SquareGoalTest implements GoalTest
{

  public boolean isGoalState(Object state)
  {
		SquareBoard board = (SquareBoard) state;
    // TODO check if is a fucking goal state...

    // Per ara retornem fals, segons l'enunciat en busqueda local no es pot saber si s'ha arribat a un estat final, per tant s'ha de negar sempre
		return false;
	}

}
