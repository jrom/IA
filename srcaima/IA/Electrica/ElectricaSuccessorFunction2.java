package IA.Electrica;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;

//	import IA.probTSP.ProbTSPHeuristicFunction;

public class ElectricaSuccessorFunction2 implements SuccessorFunction {

	public List getSuccessors(Object aState) {
		//DEFINITIU
		//afegir-treure
		// deassignar un paquet i asignar un paquet POC UTIL PERO DEFINITIU

		ArrayList retVal = new ArrayList();
		Electrica board = (Electrica) aState;

		int count = 0;
		int nc = board.obteNdemandes();
		int np = board.obteNpaquets();
		int j;
		//System.out.println("Generant successors");
		//per cada consumidor que no te paquet li assigno un paquet
		for (int i = 0; i < nc; i++) {
			Electrica newboard = new Electrica(board);
			if (newboard.obteUnaDemanda(i).consultaNumPaquet() == -1) {
				j = 0;
				//System.out.println("buscant paquet a asignar");
				while (j < np && !newboard.restriccions(j, i)){
					//System.out.println("No es pot assignar p"+j+" Q=" + (newboard.obteUnPaquet(j).consultaQini()-newboard.obteUnPaquet(j).consultaQgastada())+" F=" + newboard.obteUnPaquet(j).consultaF() +						 " P=" + newboard.obteUnPaquet(j).consultaP() +						"al c" + i + ": q=" + newboard.obteUnaDemanda(i).consultaQ()					+ " f=" + newboard.obteUnaDemanda(i).consultaF()+ " p=" + newboard.obteUnaDemanda(i).consultaP());
					j++;
				}
				if (j < np && newboard.restriccions(j, i)) {
					newboard.assignaPaquet(j, i);
					newboard.pas();

					//System.out.println(" trobat un paqeut a asignat \nEstat board");
					//board.EscriuEstat();
					//System.out.println("Estat newboard");
					//newboard.EscriuEstat();
					//System.out.println(" ");
					String S = new String("assignar p" + j + " a c" + i + "\n");
					// //retVal.add(new Successor(newboard.toString(),newboard));
					retVal.add(new Successor(S, newboard));
					 
					//System.out.println(newboard.obteValHeuristic(1));
					count++;
				}
			}
		}
		// per cada consumidor li desasigno un paquet
		for (int i = 0; i < nc; i++) {
			
			Electrica newboard = new Electrica(board);
			if (newboard.obteUnaDemanda(i).consultaNumPaquet() != -1){
			newboard.desassignarPaquet(i);
			newboard.pas();
			//System.out.println("Estat board");
			//board.EscriuEstat();
			//System.out.println("Estat newboard");
			//newboard.EscriuEstat();
			//System.out.println(" ");
			String S = new String("desassignar a c" + i + "\n");
			// retVal.add(new Successor(newboard.toString(),newboard));
			retVal.add(new Successor(S, newboard));
			
			//System.out.println(newboard.obteValHeuristic(1));
			count++;
			}
		}
		

		//System.out.println("num estats generats= " + count);

		return retVal;

	}

}
