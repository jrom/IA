package IA.Centrals;


import aima.search.framework.GoalTest;

public class CentralsEstatFinal implements GoalTest {

	public boolean isGoalState(Object state) {
		return ((Representacio)(state)).isGoalState();
		
	}

}
