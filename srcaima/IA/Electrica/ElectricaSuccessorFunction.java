package IA.Electrica;


import java.util.ArrayList;
import java.util.List;

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;
//import IA.probTSP.ProbTSPHeuristicFunction;


public class ElectricaSuccessorFunction implements SuccessorFunction{
  
//	DEFINITIU 
	//intercanvi
//	per cada consumidor sense paquet buscar tot el vector i cada consumidor asignat intercanviar els paquets si es respecten restricc
		
		public List getSuccessors(Object aState) {
			ArrayList retVal = new ArrayList();
			Electrica board = (Electrica) aState;

			int nc = board.obteNdemandes();
			int np = board.obteNpaquets();
			


			// per cada consumidor sense paquet buscar tot el vector i cada consumidor asignat intercanviar els paquets si es respecten restricc
			

			for (int i = 0; i < nc; i++) {
				if (board.obteUnaDemanda(i).consultaNumPaquet() == -1){
				
					for (int j=0;j<nc;j++){
						if (i!=j && board.obteUnaDemanda(j).consultaNumPaquet()!=-1){
							Electrica newboard = new Electrica(board);
							int nump=newboard.obteUnaDemanda(j).consultaNumPaquet();
							newboard.desassignarPaquet(j);
							if (newboard.restriccions(nump,i)){
								newboard.assignaPaquet(nump,i);
								String S = new String("desassignar p"+nump+" a c" + j+" i assignar a c"+i + "\n");
								retVal.add(new Successor(newboard.toString(), newboard));
							}
						}
					}
					
					
				}
				
				
						
				
			}

			return (retVal);

		}

}
