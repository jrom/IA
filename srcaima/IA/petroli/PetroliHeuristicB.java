package IA.petroli;

import aima.search.framework.HeuristicFunction;

public class PetroliHeuristicB implements HeuristicFunction   
{

	//Mateix heuristic pero augmentant una mica el pes del cost
	public double getHeuristicValue(Object state)
	{
		PetroliLocalSearchBoard plsb = (PetroliLocalSearchBoard)state;
		int max_eix = Math.max(PetroliUtils.MAX_EIX_X, PetroliUtils.MAX_EIX_Y);
		int cost = plsb.costTotal*45;
		int petroli = plsb.petroliTotal * (max_eix*(new Double(Math.log(max_eix))).intValue()*PetroliUtils.CONST_TIPUS_BOMBEIG_D);
//		System.out.println("Cost: " + cost + " - Petroli: " + petroli);
		return  cost - petroli;
	}

}