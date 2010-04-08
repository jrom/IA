package IA.petroli;

import aima.search.framework.HeuristicFunction;

public class PetroliHeuristic implements HeuristicFunction   {

	public double getHeuristicValue(Object state) {
		PetroliLocalSearchBoard plsb = (PetroliLocalSearchBoard)state;
		int max_eix = Math.max(PetroliUtils.MAX_EIX_X, PetroliUtils.MAX_EIX_Y);
		return  plsb.costTotal - plsb.petroliTotal * (max_eix*(new Double(Math.log(max_eix))).intValue()*PetroliUtils.CONST_TIPUS_BOMBEIG_D);
	}

}
