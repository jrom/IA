package IA.Electrica;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;

public class ElectricaSuccessorFunction5 implements SuccessorFunction {
//	 DEFINITIU
	// creuament doble seguit + afegir/treure
	// 1 Per cada cons amb paquet buscar un altre cons amb paquet i intercanviar
	// paquest si restriccions ok
	// 3 afegir asig paquet-cons
	// 4 treure asig paquet-cons

public List getSuccessors(Object aState) {

		ArrayList retVal = new ArrayList();
		Electrica board = (Electrica) aState;

		int np = board.obteNpaquets();
		int nc = board.obteNdemandes();

		for (int i = 0; i < nc; i++) {
			int nump = board.obteUnaDemanda(i).consultaNumPaquet();
			if (nump != -1) {
				Electrica newboard = new Electrica(board);
				newboard.desassignarPaquet(i);
				// 1Per cada cons i amb paquet treureli paquet i buscar un cons
				// j amb
				// paquet per intercanviar
				for (int j = 0; j < nc; j++) {
					int numpj = newboard.obteUnaDemanda(j).consultaNumPaquet();
					if (numpj != -1 && j != i ){
						Electrica newboard1 = new Electrica(newboard);
						newboard1.desassignarPaquet(j);
						if ( newboard1.restriccions(nump, j) && newboard1.restriccions(numpj, i)) {
							
							newboard1.assignaPaquet(nump, j);
							newboard1.assignaPaquet(numpj, i);
							String S = new String("creuar c" + i + " p"
									+ nump + " i c" + j + " p"+numpj+"\n");
							retVal
							.add(new Successor(S,
									newboard1));

						}
					}
				}
				
				// 4 treure asig paquet-cons
				String S = new String("desassignar p" + nump + " a c" + i);
				retVal.add(new Successor(S, newboard));
			}
			// 3 afegir asig paquet k - cons i
			else {
				for (int k=0;k<np;k++){
					if (board.restriccions(k,i)){
						Electrica newboard3 = new Electrica(board);
						newboard3.assignaPaquet(k,i);
						String S = new String("assignar p" + k + " a c"
								+ i + "\n");
						retVal
								.add(new Successor(S,
										newboard3));
						
					}
				}
			}
		}
	

		
		return (retVal);
	}
	
	
	/*public List getSuccessors(Object aState) {
		// afegir
		// treure els parells
		// intercanviar la meitat ENCARA NO FET

		ArrayList retVal = new ArrayList();
		Electrica board = (Electrica) aState;
		int np = board.obteNpaquets();
		int nc = board.obteNdemandes();

		// afegir
		Electrica newboard2 = new Electrica(board);
		String S2 = null;
		for (int i = 0; i < nc; i++) {
			int nump = board.obteUnaDemanda(i).consultaNumPaquet();
			if (nump == -1) {
				for (int k = 0; k < np; k++) {
					if (board.restriccions(k, i)) {
						Electrica newboard = new Electrica(board);
						newboard.assignaPaquet(k, i);
						String S = new String("assignar p" + k + " a c" + i
								+ "\n");
						retVal.add(new Successor(S, newboard));

					}
				}
			}
			// treure els parells
			else {
				if (i % 2 == 0) {

					newboard2.desassignarPaquet(i);
					S2 = new String(S2 + "desassignar p"
							+ newboard2.obteUnaDemanda(i).consultaNumPaquet()
							+ " a c" + i + "\n");
				}
			}
		}
		// es guarda el estat sense els parells
		retVal.add(new Successor(S2, newboard2));

		// intercanviar la meitat
		Electrica newboard3 = new Electrica(board);
		String S3 = null;
		for (int i = 0; i < nc / 2; i++) {
			int nump = board.obteUnaDemanda(i).consultaNumPaquet();
			if (nump != -1) {

				newboard3.desassignarPaquet(i);
				// 1Per cada cons i amb paquet treureli paquet i buscar un cons
				// j amb
				// paquet per intercanviar
				for (int j = 0; j < nc; j++) {
					int numpj = newboard3.obteUnaDemanda(j).consultaNumPaquet();
					if (numpj != -1 && j != i) {

						newboard3.desassignarPaquet(j);
						if (newboard3.restriccions(nump, j)
								&& newboard3.restriccions(numpj, i)) {

							newboard3.assignaPaquet(nump, j);
							newboard3.assignaPaquet(numpj, i);
							S3 = new String(S3 + "creuar c" + i + " p" + nump
									+ " i c" + j + " p" + numpj + "\n");

						}
						else {
							newboard3.assignaPaquet(numpj,j);
							j=nc;
						}
					}
				}

			}
		}
		retVal.add(new Successor(S3, newboard3));

		return (retVal);
	}*/

}
