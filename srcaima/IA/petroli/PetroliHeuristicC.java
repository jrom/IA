package IA.petroli;

import aima.search.framework.HeuristicFunction;

public class PetroliHeuristicC implements HeuristicFunction   
{

	//Maximitzar petroli sense tenir en compte el cost (segurament no disminuirà bombeig)
	public double getHeuristicValue(Object state)
	{
		PetroliLocalSearchBoard plsb = (PetroliLocalSearchBoard)state;
//		System.out.println("heuristic: -" + plsb.petroliTotal);
		return  -(plsb.petroliTotal);
	}

}