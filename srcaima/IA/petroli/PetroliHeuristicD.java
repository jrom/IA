package IA.petroli;

import aima.search.framework.HeuristicFunction;

public class PetroliHeuristicD implements HeuristicFunction   
{

	public double getHeuristicValue(Object state)
	{
		PetroliLocalSearchBoard plsb = (PetroliLocalSearchBoard)state;
//		System.out.println("Funciona");
		return  plsb.costTotal - plsb.petroliTotal * (plsb.mitjana_distancia*(new Double(Math.log(plsb.mitjana_distancia))).intValue()*3);
	}

}