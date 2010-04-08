package IA.Centrals;


import java.util.Vector;

//import Representacio.doint;


public class SortManager {
	
	private Nodo [] vNodos;
	
	public SortManager(Nodo [] vNodos2){
		this.vNodos =vNodos2;
		
	}
	
	public void quickSortY (Vector matriu, int posInici,  int posFinal) {
		if (posInici>=posFinal) { 
			return;
		} else { 
			int posProv=posInici+1; 
			for (int i=posInici+1;i<posFinal+1;i++) {
				if (anteriorAY(vNodos[((Integer)matriu.get(i)).intValue()].getCordX(),vNodos[((Integer)matriu.get(posInici)).intValue()].getCordX())) {
					intercanviaY(matriu,posProv,i);
					posProv++;
				}
			}
			intercanviaY(matriu,posInici,posProv-1);
			quickSortY(matriu,posInici,posProv-2);
			quickSortY(matriu,posProv,posFinal);  
		}
	}

	public void intercanviaY (Vector matriu,  
			int i,int j) {
		Integer temp = (Integer)matriu.get(i);
		matriu.set(i,matriu.get(j));
		matriu.set(j,temp);
	}
	
	public boolean anteriorAY (int obX,int obY) { 
		return obX<obY;
	}
	private class doint {
		public int dist;
		public int id;
		
	}
	
	
}
