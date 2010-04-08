package IA.Electrica;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;

public class ElectricaSuccessorFunction3 implements SuccessorFunction {
	// DEFINITIU
	//intercanvi + afegir/treure
	// 1 Per cada cons amb paquet treureli paquet i buscar un cons sens paquet
	// per asignarli aquest paquet
	// 2 Per aquets mateix cons, buscarli un altre paquet
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
				// j sens
				// paquet per asignarli aquest paquet
				for (int j = 0; j < nc; j++) {
					int numpj = newboard.obteUnaDemanda(j).consultaNumPaquet();
					if (numpj == -1 && j != i && newboard.restriccions(nump, j)) {
						Electrica newboard1 = new Electrica(newboard);
						newboard1.assignaPaquet(nump, j);
						String S = new String("desassignar p" + nump + " a c"
								+ i + " i assignar a c" + j + "\n");
						retVal
								.add(new Successor(S,
										newboard1));

					}
				}
				// 2 Per aquets mateix cons i, buscarli un altre paquet j
				for (int j = 0; j < np; j++) {
					if (nump != j && newboard.restriccions(j, i)) {
						Electrica newboard2 = new Electrica(newboard);
						newboard2.assignaPaquet(j, i);
						String S = new String("desassignar p" + nump + " a c"
								+ i + " i assignar-li el p" + j + "\n");
						retVal
								.add(new Successor(S,
										newboard2));

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
}