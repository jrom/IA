package IA.petroli;

import java.util.ArrayList;
import java.util.List;

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;

public class PetroliSuccesorsA implements SuccessorFunction
{
    static
    {
        numSuccessors = 0;
    }
    
    public static int numSuccessors;

	public List getSuccessors(Object state) 
	{
	    
		PetroliLocalSearchBoard estat = (PetroliLocalSearchBoard)state;
		ArrayList successors = new ArrayList();
		PetroliHeuristic ph = new PetroliHeuristic();
		int propera = -1;
		PetroliLocalSearchBoard nouEstat = null;
		
		
		
		System.out.println("--------------------------------------------------------" + ph.getHeuristicValue(estat) + "--------------------------------------------------------");
		
		
		for (int i = 0; i<estat.plataformes.length;i++)
		{
			
			// Connectem una plataforma
			
		//	System.out.println("Comprovarem si podem connectar plataforma " + i + " amb plataforma " + estat.platPropera[i] + ")");
			
			propera = estat.platPropera[i];
			
			//Connectem plataformes
			if (propera!=-1)
			{
				if	( (estat.plataformes[i].isPlataformaDistribuidora() || estat.connexions[i].isConnected()) && estat.plataformes[propera].isPlataformaPetrolifera() && !estat.connexions[propera].isConnected())
				{
					
					nouEstat = new PetroliLocalSearchBoard(estat);
					nouEstat.connectar(estat.platPropera[i],i);
					//nouEstat.printBoard();
				//	System.out.println("connectar("+propera+","+i+") h=" + ph.getHeuristicValue(nouEstat));
					successors.add(new Successor("connectar("+propera+","+i+") h=" + ph.getHeuristicValue(nouEstat),nouEstat));
					numSuccessors++;
				}
			}
			

			//O provem de disminuir el bombeig si el node 
			if (estat.connexions[i].isConnected() && estat.connexions[i].getBombeig()!=PetroliUtils.CONST_TIPUS_BOMBEIG_NO)
			{
				nouEstat = new PetroliLocalSearchBoard(estat);
				nouEstat.disminuirBombeig(i);
				//System.out.println("disminuirBombeig("+i+") h=" + ph.getHeuristicValue(nouEstat));
				successors.add(new Successor("disminuirBombeig("+i+") h=" + ph.getHeuristicValue(nouEstat),nouEstat));
				numSuccessors++;
				
			}
			
		
			
			//System.out.println("Calculem si podem fer algun intercanvi per " + i);
			if (estat.connexions[i].isConnected())
			{
			//	System.out.println("Provem si es possible de connectar " + i + " de forma diferent");
				
				int testOrig = 0;
				int testDesti = 0;
				
				int arreli = estat.getArrel(i);
				int arrelj = 0;
				
				int petDesti = 0;
				
				for (int j = 0; j < estat.plataformes.length; j++)
				{
					arrelj = estat.getArrel(j);
					
					
					if ( i != j)
					 if (  (estat.plataformes[j].isPlataformaDistribuidora() && arreli!=j) || 
					 	   (estat.connexions[j].isConnected() && arreli != arrelj ) )
					 {
					 	if (estat.plataformes[j].isPlataformaDistribuidora()) petDesti = estat.connexions[j].getPetBombejat();
						else petDesti = estat.connexions[arrelj].getPetBombejat();
					 	
					 	if (petDesti < PetroliUtils.CONST_TIPUS_BOMBEIG_D)
					 	{
					 	//	System.out.println("Int");
					 		nouEstat = new PetroliLocalSearchBoard(estat);
							nouEstat.intercanviar(i, j);
						//	System.out.println("intercanviar("+i+", "+j+") h=" + ph.getHeuristicValue(nouEstat));
							successors.add(new Successor("intercanviar("+i+", "+j+") h=" + ph.getHeuristicValue(nouEstat),nouEstat));
							numSuccessors++;
					 	}
						
						
					}
				}
			}
			
			
			if (estat.plataformes[i].isPlataformaPetrolifera() && estat.connexions[i].isConnected() && estat.connexions[i].getNumConnex() == 0)
			{
				nouEstat = new PetroliLocalSearchBoard(estat);
				nouEstat.treure(i);
				//System.out.println("treure(" + i + ") h=" + ph.getHeuristicValue(nouEstat));
				successors.add(new Successor("treure(" + i + ") h=" + ph.getHeuristicValue(nouEstat),nouEstat));
				numSuccessors++;
			}
			

			
			
			
		}
		
		
		
		
		return successors;
	}

}
