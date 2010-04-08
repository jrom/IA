
package IA.petroli;

import java.util.ArrayList;
import java.util.Iterator;


public class PlataformaConectada implements Cloneable
{
	private boolean isConnected;
	private int bombeig;
	private int sortida;
	private int petSortida;
	private int petBombejat;
	private int cost; // Preu acumulat fins el moment
	private int costTuberia;
	//private boolean isFulla;
	private int numConnex;
	
	public PlataformaConectada(int petSortida)
	{
		setConnected(false);
		setBombeig(PetroliUtils.CONST_TIPUS_BOMBEIG_NO);
		setSortida(-1);
		setPetBombejat(0);
		setPetSortida(petSortida);
		setCost(0);
		setCostTuberia(0);
		setNumConnex(0);
	//	setArrel(-1);
	}
	
	

	public boolean isConnected() { return isConnected; }
	public void setConnected(boolean isConnected) { this.isConnected = isConnected; }
	public int getBombeig() { return bombeig; }
	public void setBombeig(int bombeig) { this.bombeig = bombeig; }
	public int getSortida() { return sortida; }
	public void setSortida(int sortida) { this.sortida = sortida; }
	public int getPetSortida() { return petSortida; }
	public void setPetSortida(int petSortida) { this.petSortida = petSortida; }	
	public int getPetBombejat() { return petBombejat; }
	public void setPetBombejat(int petBombejat) { this.petBombejat = petBombejat; }	
	public int getCost() { return cost; }
	public void setCost(int cost) { this.cost = cost; }	
	public int getCostTuberia() { return costTuberia; }
	public void setCostTuberia(int costTuberia) { this.costTuberia = costTuberia; }	
	public int getNumConnex() { return this.numConnex; }
	public void setNumConnex(int numConnex) { this.numConnex = numConnex; }
	
//	public int getArrel() { return arrel; }
//	public void setArrel(int arrel) { this.arrel = arrel; }
	
	public Object clone(){
        Object obj=null;
        try{
            obj=super.clone();
        }catch(CloneNotSupportedException ex){
            System.out.println(" no se puede duplicar");
        }
        return obj;
    }



	/**
	 * 
	 * 
	 */
	public void print() 
	{
		String result;
		if (isConnected())  result = "Plataforma conectada a " + getSortida() + ", "; 
		else result = "Plataforma no conectada, ";
		
		
		result += "amb petSortint de " + getPetSortida() + ", bombeig de " + getBombeig() + " i petBombejat " + getPetBombejat() + ". El cost es " + getCost() + " i el de la tub es: " + getCostTuberia(); 
		System.out.print(result);
	}
	
	
	
	
	
	
}
