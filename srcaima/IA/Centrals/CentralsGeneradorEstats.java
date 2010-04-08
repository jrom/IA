package IA.Centrals;


import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;

public class CentralsGeneradorEstats implements SuccessorFunction {
	
	private int debug;
	private int iter;
	private boolean[] vops;
	
	public CentralsGeneradorEstats(int deb, boolean[] ops){
		this.debug = deb;
		iter=0;
		vops = new boolean[6];
		for(int i=0;i<6;i++)vops[i] = ops[i];
	}
	
	public List getSuccessors(Object state) {
		
		
		
		int count = 0;
		ArrayList retVal= new ArrayList();
		Representacio estat = (Representacio) state;
		
		
		//	pintem la solucio escollida anteriorment
		/*cjf.setPlano(estat.getAmplada(),estat.getAltura(),estat.getPlano(),estat.getSol(),estat.getVNodo() );//3=tp
		 cjf.repaint();
		 BufferedReader in = new BufferedReader( new InputStreamReader(System.in) );
		 
		 
		 System.out.print("Ingrese string ");
		 String r="";
		 try {
		 r = in.readLine();
		 } catch (IOException e) {
		 // TODO Auto-generated catch block
		  e.printStackTrace();
		  }
		  System.out.println("string = "+r);
		  */
		if(debug>iter){
			iter++;
			
			CentralsFuncioHeuristica1 CHF =new CentralsFuncioHeuristica1();
			Vector solActual = (Vector)estat.getSol();
			
			
			if(vops[0]){ //OPERADOR INTERCANVIAR CONNEXIO 1
				for(int i=0;i<solActual.size();i++){
					if(((Vector)solActual.get(i)).size()!=0) {
						for(int j=1; j<((Vector)solActual.get(i)).size();j++) {
							for (int k=0;k<solActual.size();k++) {
								if(((Vector)solActual.get(k)).size()!=0) {
									Representacio newEstat = new Representacio(estat);
									
									if (newEstat.interConexio(i,Integer.parseInt(((Vector)solActual.get(i)).get(j).toString()),k)) {
										
										//int v2=CHF.getHeuristicValue(newEstat);
										String S=new String("Interconexion= "+" "+i+" Up");
										retVal.add(new Successor(S,newEstat));
										count++;
										
									}
								}
								
							}
						}
					}
				}
			}
			if(vops[1]){ //OPEARDOR INTERCANVIAR CONNEXIO 2
				for(int i=0;i<solActual.size();i++){
					if(((Vector)solActual.get(i)).size()!=0) {
						for(int j=1; j<((Vector)solActual.get(i)).size();j++) {
							for (int k=0;k<solActual.size();k++) {
								if(((Vector)solActual.get(k)).size()!=0) {
									Representacio newEstat = new Representacio(estat);
									
									if (newEstat.interConexio2(i,Integer.parseInt(((Vector)solActual.get(i)).get(j).toString()),k)) {
										
										//int v2=CHF.getHeuristicValue(newEstat);
										String S=new String("Interconexion= "+" "+i+" Up");
										retVal.add(new Successor(S,newEstat));
										count++;
										
									}
								}
								
							}
						}
					}
				}
			}
			
			//VARIABLES PER GESTIONAR OPERADORS DE REPETIDORS
			int numrep = estat.getNumRepetidors();
			boolean [] reputil= new boolean[numrep];
			
			for(int i = 0;i< reputil.length;i++)reputil[i]=false;
			
			
			int rutil=0;
			for (int i=estat.getNumCentrals();i<estat.getEstat().size();i++) {
				if(((Vector)estat.getEstat().get(i)).size()!=0) {
					reputil[i-estat.getNumCentrals()]=true;
					rutil++;
				}
			}
			
			if(vops[2]){ //OPEARDOR AFEGIR REPETIDOR
				if(rutil<estat.getMaxRep())
				{
					for(int i = estat.getNumCentrals();i<estat.getNumCentrals()+estat.getNumRepetidors();i++){
						if(!reputil[i-estat.getNumCentrals()]){
							for (int j=0;j<estat.getEstat().size();j++){
								if(((Vector)estat.getEstat().get(j)).size()!=0) {
									for (int k=1;k<((Vector)estat.getEstat().get(j)).size();k++) {
										int actual = Integer.parseInt(((Vector)estat.getEstat().get(j)).get(k).toString());
										if(actual>j){
											Representacio newEstat = new Representacio(estat);
											if(newEstat.afegirRepetidor(j,actual,i)){
												//int v2=CHF.getHeuristicValue(newEstat);
												String S=new String("AfegirRepetidor");
												retVal.add(new Successor(S,newEstat));
												count++;
											}
											
										}
									}
								}
							}
						}
					}
				}
			}
			
			if(vops[3]){ //OPEARDOR TREURE REP
				for(int i = estat.getNumCentrals();i<estat.getNumCentrals()+estat.getNumRepetidors();i++){
					if(reputil[i-estat.getNumCentrals()]){
						Representacio newEstat = new Representacio(estat);
						newEstat.treureRepetidor(i);
						//int v2=CHF.getHeuristicValue(newEstat);
						String S=new String("AfegirRepetidor");
						retVal.add(new Successor(S,newEstat));
						count++;
						
						
					}
					
				}
				
			}
			
					
			if(vops[4]){ //OPEARDOR INTERCANVIAR REPETIDOR
				for(int i = estat.getNumCentrals();i<estat.getNumCentrals()+estat.getNumRepetidors();i++){
					if(reputil[i-estat.getNumCentrals()]){
						for(int j = estat.getNumCentrals();j<estat.getNumCentrals()+estat.getNumRepetidors();j++){
							if(!reputil[j-estat.getNumCentrals()]){
								Representacio newEstat = new Representacio(estat);
								newEstat.interRepetidor(i,j);
								//int v2=CHF.getHeuristicValue(newEstat);
								String S=new String("InterRepetidor");
								retVal.add(new Successor(S,newEstat));
								count++;
							}
						}
						
					}
					
				}
			}
			
			if(vops[5]){ //OPEARDOR INTERCANVIAR REPETIDOR 2
				int cvell = count;
				for(int i = estat.getNumCentrals();i<estat.getNumCentrals()+estat.getNumRepetidors();i++){
					if(reputil[i-estat.getNumCentrals()]){
						for(int j = i+1;j<estat.getNumCentrals()+estat.getNumRepetidors();j++){
							if(reputil[j-estat.getNumCentrals()]){
								Representacio newEstat = new Representacio(estat);
								if(newEstat.interRepetidor2(i,j)){
								//int v2=CHF.getHeuristicValue(newEstat);
								String S=new String("InterRepetidor");
								retVal.add(new Successor(S,newEstat));
								count++;
								}
							}
							
						}
						
					}
					
				}
			System.out.println("cunt-cvell="+(count-cvell));
			}
			
			
		}//FI DEBUG ITER
		
		System.out.println("num estats generats= " + count);
		return retVal;
		
	}
}
