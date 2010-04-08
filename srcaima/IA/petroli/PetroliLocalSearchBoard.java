package IA.petroli;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;


public class PetroliLocalSearchBoard
{

	public static Plataforma[] plataformes; //Les plataformes seran sempre les mateixes.
	public PlataformaConectada[] connexions; //Aqui aniran les arrels dels arbres distribuidors
	//public int petPotencial[];
	public static int[][] distancies;
	public int petroliTotal;
	public int costTotal;
	public int[] platPropera;
	public static int numDist;
	public static int numPetro;
	public static int mitjana_distancia;
	
	
	public PetroliLocalSearchBoard() {}
	
	public PetroliLocalSearchBoard(PetroliLocalSearchBoard plsb) 
	{		
		this.connexions = new PlataformaConectada[plsb.connexions.length];
	//	this.petPotencial = new int[plsb.petPotencial.length];
		this.platPropera = new int[plsb.platPropera.length];
		
		for (int i = 0; i < connexions.length; i++)
			this.connexions[i] = (PlataformaConectada) plsb.connexions[i].clone();
		
	//	for (int i =0; i < petPotencial.length; i++)
	//		this.petPotencial[i] = plsb.petPotencial[i]; 
		
		this.petroliTotal = plsb.petroliTotal;
		this.costTotal = plsb.costTotal;	
		
		for (int i =0; i < platPropera.length; i++)
			this.platPropera[i] = plsb.platPropera[i];

	}
	
	
	public void loadRandomScenario(int numDist, int numPetro, boolean save)
	{
		loadRandomScenario(numDist,numPetro,save,1);
	}
	
	public void loadRandomScenario(int numDist, int numPetro, boolean save, int initialState)
	{
		 if (numDist < 1) numDist = 1;
		 if (numPetro < 1) numPetro = 1;
		 ArrayList distribuidores = new ArrayList();
		 ArrayList petroliferes = new ArrayList();
		
//		 Generem les plataformes distribuidores
		for (int i=0; i < numDist; i++) 
		{
			distribuidores.add(new PlataformaDistribuidora(PetroliUtils.getRandomIntBetween(0,PetroliUtils.MAX_EIX_X)));
			
		}
		
		//Generem les plataformes petroliferes
		for (int i=numDist; i < numDist + numPetro; i++) 
		{
			petroliferes.add(new PlataformaPetrolifera(PetroliUtils.getRandomIntBetween(0, PetroliUtils.MAX_EIX_X),
																					PetroliUtils.getRandomIntBetween(1, PetroliUtils.MAX_EIX_Y),
																						PetroliUtils.getRandomIntBetween(1, 5)));
		}
		
		load(petroliferes,distribuidores);
		
		//saveInitialState("initial.pet");
		
		if (initialState == 2) initialState2();
		if (initialState == 3) initialState3();
		else return;
		
	}
	
	public void load(ArrayList plataformesPetro, ArrayList plataformesDist)
	{
		if (plataformesPetro == null) return;
		else if (plataformesDist == null) return;
		
	   
	    
	    numDist = plataformesDist.size();
	    numPetro = plataformesPetro.size();
	    
		plataformes = new Plataforma[numDist + numPetro];
		connexions = new PlataformaConectada[numDist + numPetro];
		//petPotencial = new int[numDist];
		platPropera = new int[plataformes.length];
		
	//	for (int i = 0; i < petPotencial.length; i++)
		//	petPotencial[i]=0;
		
		
		
		// Generem les plataformes distribuidores
		int id = 0;
		for (Iterator it = plataformesDist.iterator(); it.hasNext();) 
		{
			plataformes[id] = (PlataformaDistribuidora)it.next();
			connexions[id] = new PlataformaConectada(0);
			id++;
		}
		
		//Generem les plataformes petroliferes
		for (Iterator it = plataformesPetro.iterator(); it.hasNext();)
		{
			plataformes[id] = (PlataformaPetrolifera)it.next();
			connexions[id] = new PlataformaConectada(((PlataformaPetrolifera)plataformes[id]).getPetroli());
			id++;
		}
		
		

		distancies = new int[plataformes.length][plataformes.length];
		
		long temps = System.currentTimeMillis();
		for (int i =0; i < plataformes.length; i++)
		{
			for (int j = 0; j < plataformes.length; j++)
				distancies[i][j] = PetroliUtils.getDistanceBetweenTwoPlattforms(plataformes[i], plataformes[j]); 
		}
		
					System.out.println("Temps: " + (System.currentTimeMillis() - temps));
		
		System.out.print("       ");
		for (int i = 0; i<plataformes.length; i++)
			System.out.print(i + "  ");
		System.out.println("");
     	for (int i = 0; i<plataformes.length; i++)
		{
			System.out.print("Plat." + i + " ");
			for (int j =0; j < plataformes.length; j++)
			{	System.out.print(distancies[i][j]);
			    if (distancies[i][j] < 10) System.out.print("  ");
			    else System.out.print(" ");}
			
			System.out.println(" ");
		}
	
		System.out.println("");
		
		for (int i = 0; i < plataformes.length; i++)
		{
			plataformes[i].print();
			System.out.println("");
		}
		
		for (int i = 0; i < plataformes.length; i++)
		{
			platPropera[i] = getPlatProperaNoConectada(i);
		}
		

		int aux = 0;
		for (int i =0; i< plataformes.length; i++)
			{ if (i != 0) aux += distancies[0][i]; }
	
		if (aux!=0 && (numPetro+numDist!=0)) mitjana_distancia = aux / (numPetro+numDist);
		else mitjana_distancia = 0;
		
			
	}
	
	
	private void initialState2()
	{
		ArrayList llistaNodes = new ArrayList();
		ArrayList llistaNodesAux = new ArrayList();
		
		int propera = -1;
		for (int i = 0; i < numDist; i++)
		{
			propera=platPropera[i];
			if (propera!=-1) 
			{
				connectar(propera,i);
				llistaNodes.add(new Integer(i));
				llistaNodes.add(new Integer(propera));
			}
						
		}
		
		int node = 0;
		while (llistaNodes.size()!=0)
		{
			for (Iterator it = llistaNodes.iterator(); it.hasNext();)
			{
				llistaNodesAux = new ArrayList();
				node = ((Integer)it.next()).intValue();
				propera = platPropera[node];
				if ((propera!=-1) && (connexions[node].getPetBombejat() < 16))
				{
					llistaNodesAux = new ArrayList();
					connectar(propera,node);
					llistaNodesAux.add(new Integer(node));
					llistaNodesAux.add(new Integer(propera));
				}
			}
			
			llistaNodes = new ArrayList();
			for (Iterator it = llistaNodesAux.iterator(); it.hasNext(); )
				llistaNodes.add(it.next());
		}
		
	}
	
	
	private void initialState3() {
//	  recorrem tot el vector de plataformes (en busca de plataformes petroliferes) i les conectem entre elles
		for (int i = 0; i < plataformes.length; i++) {
			connexions[i].setBombeig(PetroliUtils.CONST_COST_BOMBEIG_D);
			
			if (plataformes[i].isPlataformaPetrolifera()) {
				PlataformaPetrolifera temp = (PlataformaPetrolifera) plataformes[i];
				int mesPropera = 0;
				int distanciaMinima = distancies[i][mesPropera];
				
				for (int j = 1; j < plataformes.length; j++) {
				int distanciaPlataformaActual = distancies[i][j];
				//System.out.println("Distancia entre " + plataformes[i].getId() + " - " + plataformes[j].getId() + ": " + distanciaPlataformaActual);
					if ( (i != j) && (distanciaPlataformaActual < distanciaMinima) && (plataformes[i].getY() > plataformes[j].getY()) && ((connexions[j].isConnected()) || (plataformes[j].isPlataformaDistribuidora()) ) ) {
						mesPropera = j;
						distanciaMinima = distancies[i][mesPropera];
					}
				}
				connectar(i,mesPropera);
//				System.out.println("plataforma[" + i + "](" + plataformes[i].getX() + "," + plataformes[i].getY() + ") desemboca a plataforma[" + mesPropera + "]( " + plataformes[mesPropera].getX() + "," + plataformes[mesPropera].getY() + ")");
			}
		}
	}
	
	
	
	
	public void connectar(int origen, int desti)
	{
		
		if (connexions[origen].isConnected()) return; 
		if (plataformes[origen].isPlataformaDistribuidora()) return;
		if (plataformes[desti].isPlataformaPetrolifera() && !connexions[desti].isConnected()) return;
		
		
		connexions[origen].setSortida(desti); 
		connexions[origen].setConnected(true);
		connexions[desti].setNumConnex(connexions[desti].getNumConnex()+1);
		
	/*	if (plataformes[desti].isPlataformaDistribuidora())
			connexions[origen].setArrel(desti);
		else
			connexions[origen].setArrel(connexions[desti].getArrel());*/
		
		
		for (int i = 0; i < platPropera.length; i++)
		{
			if (platPropera[i]==origen) platPropera[i] = getPlatProperaNoConectada(i);
		}
		
		
		
		PlataformaPetrolifera pOrigen = (PlataformaPetrolifera) plataformes[origen];
		//petPotencial[connexions[origen].getArrel()] = petPotencial[connexions[origen].getArrel()] + pOrigen.getPetroli();
		
		
	
		//System.out.println("I ara modificarem el bombeig d'origen " + origen);
		modificarBombeig(origen,PetroliUtils.CONST_TIPUS_BOMBEIG_D);
		int aux=connexions[origen].getSortida();
		//while (aux!=getArrel(origen))
		while (!plataformes[aux].isPlataformaDistribuidora())
		{
			//System.out.println("I el de " + aux + "tinguent que connexions[origen] te arrel " + connexions[origen].getArrel() + " i ");
			modificarBombeig(aux,PetroliUtils.CONST_TIPUS_BOMBEIG_D);
			aux = connexions[aux].getSortida();
		}
		
		
		
		
	}
	
	
	
	
	 private void propagar(int plataforma, int quantitat, int cost)
		{
	 	     //   System.out.println("Propaguem a " + plataforma + " quantitat " + quantitat + " i cost " + cost);
				PlataformaConectada pcAux = (PlataformaConectada) connexions[plataforma].clone();
				//Incrementem el petroli del que disposa la plataforma
				connexions[plataforma].setPetSortida(connexions[plataforma].getPetSortida()+quantitat);
				connexions[plataforma].setCost(pcAux.getCost() + cost);
				//Calculem el petroli que es bombejar� amb el nou canvi
				if (connexions[plataforma].getPetSortida() > connexions[plataforma].getBombeig())
					connexions[plataforma].setPetBombejat(connexions[plataforma].getBombeig());
				else
					connexions[plataforma].setPetBombejat(connexions[plataforma].getPetSortida());

				
				if (connexions[plataforma].isConnected())
				{ 
					//System.out.println("Propagarem les diferencies");
					//System.out.println("LA tuberia abans costava " + pcAux.getCostTuberia());
					connexions[plataforma].setCostTuberia(calculaCostTuberia(distancies[plataforma][(connexions[plataforma].getSortida())],connexions[plataforma].getPetBombejat()));
					//System.out.println("I ara, " connexions[plataforma].getCostTuberia());
					cost += PetroliUtils.getCostBombeig(connexions[plataforma].getBombeig()) - PetroliUtils.getCostBombeig(pcAux.getBombeig());
					cost += connexions[plataforma].getCostTuberia() - pcAux.getCostTuberia();
				
					propagar(connexions[plataforma].getSortida(), connexions[plataforma].getPetBombejat() - pcAux.getPetBombejat(),cost);
				}
				
				if (plataformes[plataforma].isPlataformaDistribuidora())
				{ //Si arriba m�s o menys petroli a una plataforma Distribuidora SEMPRE surt a compte modificar el seu bombeig
					this.costTotal += connexions[plataforma].getCost() - pcAux.getCost(); 
					this.petroliTotal += connexions[plataforma].getPetBombejat() - pcAux.getPetBombejat();
					if (connexions[plataforma].getPetSortida() <= PetroliUtils.CONST_TIPUS_BOMBEIG_NO) modificarBombeig(plataforma,PetroliUtils.CONST_TIPUS_BOMBEIG_NO);
					else if (connexions[plataforma].getPetSortida() <= PetroliUtils.CONST_TIPUS_BOMBEIG_NO) modificarBombeig(plataforma,PetroliUtils.CONST_TIPUS_BOMBEIG_NO);
					else if (connexions[plataforma].getPetSortida() <= PetroliUtils.CONST_TIPUS_BOMBEIG_A) modificarBombeig(plataforma,PetroliUtils.CONST_TIPUS_BOMBEIG_A);
					else if (connexions[plataforma].getPetSortida() <= PetroliUtils.CONST_TIPUS_BOMBEIG_B) modificarBombeig(plataforma,PetroliUtils.CONST_TIPUS_BOMBEIG_B);
					else if (connexions[plataforma].getPetSortida() <= PetroliUtils.CONST_TIPUS_BOMBEIG_C) modificarBombeig(plataforma,PetroliUtils.CONST_TIPUS_BOMBEIG_C);
					else modificarBombeig(plataforma,PetroliUtils.CONST_TIPUS_BOMBEIG_D);
				}
				
				
			return;
		}
	
	
	
	
	
	
	public void modificarBombeig(int plataforma, int tipusBombeig)
	{
		
		//System.out.println("Modificar bombeig de plataforma");
		
		if ( (tipusBombeig!=PetroliUtils.CONST_TIPUS_BOMBEIG_A) && 
			 (tipusBombeig!=PetroliUtils.CONST_TIPUS_BOMBEIG_B) && 
			 (tipusBombeig!=PetroliUtils.CONST_TIPUS_BOMBEIG_C) && 
			 (tipusBombeig!=PetroliUtils.CONST_TIPUS_BOMBEIG_D) &&
			 (tipusBombeig!=PetroliUtils.CONST_TIPUS_BOMBEIG_NO) )  return;
		//System.out.println("Actualitzem el petroli bombejat");

		PlataformaConectada aux = (PlataformaConectada) connexions[plataforma].clone();
		
		if ( aux.getBombeig() == tipusBombeig) return;
		
		connexions[plataforma].setBombeig(tipusBombeig);
		connexions[plataforma].setCost(connexions[plataforma].getCost() - PetroliUtils.getCostBombeig(aux.getBombeig()) + PetroliUtils.getCostBombeig(connexions[plataforma].getBombeig()));
		
		
		
		if (connexions[plataforma].getPetSortida() > connexions[plataforma].getBombeig())
			connexions[plataforma].setPetBombejat(connexions[plataforma].getBombeig());				
		else
			connexions[plataforma].setPetBombejat(connexions[plataforma].getPetSortida());

		
		//Si és plataforma distribuidora, vol dir que bombejem més petroli final si es que en tenim
		if (plataformes[plataforma].isPlataformaDistribuidora()) 
		{
			this.petroliTotal = this.petroliTotal  - aux.getPetBombejat() + connexions[plataforma].getPetBombejat();
			this.costTotal = this.costTotal - aux.getCost() + connexions[plataforma].getCost();
		}
		else
		{ 
			if (!connexions[plataforma].isConnected()) return;
			
			connexions[plataforma].setCostTuberia(calculaCostTuberia(distancies[plataforma][(connexions[plataforma].getSortida())],connexions[plataforma].getPetBombejat()));
			int costPropagat = PetroliUtils.getCostBombeig(connexions[plataforma].getBombeig()) - PetroliUtils.getCostBombeig(aux.getBombeig());
			costPropagat = costPropagat - aux.getCostTuberia() + connexions[plataforma].getCostTuberia() ;
			
			propagar(connexions[plataforma].getSortida(), connexions[plataforma].getPetBombejat() - aux.getPetBombejat(),costPropagat);
		}
		
	}
	
	
	public void disminuirBombeig(int plataforma)
	{
		//System.out.println("DISMINUIR UNITATS DE \"PUTENSIA\" A " + plataforma);
		if (connexions[plataforma].getBombeig() == PetroliUtils.CONST_TIPUS_BOMBEIG_D) modificarBombeig(plataforma, PetroliUtils.CONST_TIPUS_BOMBEIG_C);
		else if (connexions[plataforma].getBombeig() == PetroliUtils.CONST_TIPUS_BOMBEIG_C) modificarBombeig(plataforma, PetroliUtils.CONST_TIPUS_BOMBEIG_B);
		else if (connexions[plataforma].getBombeig() == PetroliUtils.CONST_TIPUS_BOMBEIG_B) modificarBombeig(plataforma, PetroliUtils.CONST_TIPUS_BOMBEIG_A);
		else if (connexions[plataforma].getBombeig() == PetroliUtils.CONST_TIPUS_BOMBEIG_A) return;
	}

	
	
	public void intercanviar(int plataformaOrigen, int plataformaDesti)
	{
		//System.out.println("Intercanviem " + plataformaOrigen + " amb " + plataformaDesti);
		
		if (!plataformes[plataformaOrigen].isPlataformaPetrolifera()) return;
		if (!connexions[plataformaOrigen].isConnected()) return;
		if (connexions[plataformaOrigen].getSortida()==plataformaDesti) return;
		if ((plataformes[plataformaDesti].isPlataformaDistribuidora()) || (plataformes[plataformaDesti].isPlataformaPetrolifera() && connexions[plataformaDesti].isConnected()))
		{

		//	System.out.println("L'arrel de la plat original era: " + getArrel(plataformaOrigen) );
			
			propagar(connexions[plataformaOrigen].getSortida(),-(connexions[plataformaOrigen].getPetBombejat()),-(connexions[plataformaOrigen].getCost() + connexions[plataformaOrigen].getCostTuberia()));
	
			connexions[plataformaOrigen].setPetBombejat(0);
			connexions[plataformaOrigen].setCostTuberia(0);
			connexions[plataformaOrigen].setCost(connexions[plataformaOrigen].getCost() - PetroliUtils.getCostBombeig(connexions[plataformaOrigen].getBombeig()));
			connexions[plataformaOrigen].setBombeig(0);
			connexions[connexions[plataformaOrigen].getSortida()].setNumConnex(connexions[connexions[plataformaOrigen].getSortida()].getNumConnex()-1);
			
			
			//modificarBombeig(plataformaOrigen, PetroliUtils.CONST_TIPUS_BOMBEIG_NO);
			connexions[plataformaOrigen].setSortida(plataformaDesti); 
			connexions[plataformaOrigen].setConnected(true);
			connexions[plataformaDesti].setNumConnex(connexions[plataformaDesti].getNumConnex()+1);
			
		/*	if (plataformes[plataformaDesti].isPlataformaDistribuidora())
				connexions[plataformaOrigen].setArrel(plataformaDesti);
			else
				connexions[plataformaOrigen].setArrel(connexions[plataformaDesti].getArrel());*/
			
			
			for (int i = 0; i < platPropera.length; i++)
			{
				if (platPropera[i]==plataformaOrigen) platPropera[i] = getPlatProperaNoConectada(i);
			}
			
			
			
			PlataformaPetrolifera pOrigen = (PlataformaPetrolifera) plataformes[plataformaOrigen];
			
			propagar(connexions[plataformaOrigen].getSortida(),0,connexions[plataformaOrigen].getCost());
			modificarBombeig(plataformaOrigen,PetroliUtils.CONST_TIPUS_BOMBEIG_D);
			int aux=connexions[plataformaOrigen].getSortida();
		//	System.out.println("l'arrel d'origen es " + getArrel(plataformaOrigen));
		//	System.out.println("I comprovem aux " + aux);
			while (aux!=getArrel(plataformaOrigen))
			{
				modificarBombeig(aux,PetroliUtils.CONST_TIPUS_BOMBEIG_D);
				aux = connexions[aux].getSortida();
			}
		}
	}
	
	
	public void treure(int plataforma)
	{
		propagar(connexions[plataforma].getSortida(),-(connexions[plataforma].getPetBombejat()),-(connexions[plataforma].getCost() + connexions[plataforma].getCostTuberia()));
		connexions[plataforma].setPetBombejat(0);
		connexions[plataforma].setCostTuberia(0);
		connexions[plataforma].setCost(0);
		connexions[plataforma].setBombeig(0);
		connexions[plataforma].setConnected(false);
		connexions[connexions[plataforma].getSortida()].setNumConnex(connexions[connexions[plataforma].getSortida()].getNumConnex()-1);
		connexions[plataforma].setSortida(-1);
	}
	
	
	
	private int calculaCostTuberia(int distancia, int petroli)
	{
		return distancia * (new Double(Math.log(distancia))).intValue() * petroli;
	}
	
	
	
	public void printBoard()
	{
		System.out.println();
		for (int i = 0; i < plataformes.length; i++) 
		{
			System.out.print(""+i+" ");
			plataformes[i].print();
			System.out.println("");
			connexions[i].print();
			System.out.println("");
		}
		System.out.println("");
		System.out.println("El petroliTotal es " + this.petroliTotal + " i el cost es " + this.costTotal);
		int unitatsDePutensia=0;
	//	for (int i = 0; i < petPotencial.length; i++)
	//		unitatsDePutensia += petPotencial[i];
		System.out.println("I un petroli potencial de " + unitatsDePutensia);
		System.out.println("");
		for (int i = 0; i < plataformes.length; i++)
			System.out.print("(" + i + ") - " + platPropera[i] + ", ");
		System.out.println("");
			
		System.out.println("");
		
	}
	
	
	
	private int getPlatProperaNoConectada2(int plataforma)
	{
		if (numPetro < 1) return -1;
		int propera=-1;
		int menor = Integer.MAX_VALUE;
		
		
			for (int i = numDist; i < plataformes.length;i++)
			{
				if ( !connexions[i].isConnected() && distancies[plataforma][i] != 0)
				{
					if ( distancies[plataforma][i] < menor)
					{
						menor = distancies[plataforma][i];
						propera = i;
					}
				}
			}
		
		
		return propera;
	}
	
	
	
	//Molt millor aquesta manera de buscar la més propera
	private int getPlatProperaNoConectada(int plataforma)
	{
		if (numPetro < 1) {
            return -1;
        }
		int propera=-1;
		int major = Integer.MIN_VALUE;
		
		
			for (int i = numDist; i < plataformes.length;i++)
			{
				if ( !connexions[i].isConnected() && distancies[plataforma][i] != 0)
				{
				    int petroli = ((PlataformaPetrolifera)plataformes[i]).getPetroli();
				    int distancia = distancies[plataforma][i];
				    int temp = petroli/distancia;
					if ( major < (((PlataformaPetrolifera)plataformes[i]).getPetroli()*1000)/distancies[plataforma][i])
					{
						major = (((PlataformaPetrolifera)plataformes[i]).getPetroli()*1000)/distancies[plataforma][i];
						propera = i;
					}
				}
			}
		
		
		return propera;
	}
	
	
	public int getArrel(int plataforma)
	{
		if (!connexions[plataforma].isConnected()) return -1;
		
		if (plataformes[plataforma].isPlataformaDistribuidora()) return plataforma;
		else
		{
			int aux = connexions[plataforma].getSortida();
			while ( !plataformes[aux].isPlataformaDistribuidora() )
				aux = connexions[aux].getSortida();
			
			return aux;	
		}
	}
	
	
	public boolean readInitialState(String file)
	{
		return readInitialState(file,1);
	}
	
	
	
	
	/**
	 * Mtode per llegir dades d'entrada del problema. D'aquesta manera carreguem un mateix escenari
	 * en el que poder realitzar experiments comparatius.
	 * 
	 * EL QUE ESPEREM LLEGIR DEL FITXER DE TEXT S EL SEGUENT:


	//Indiquem les diferents plataformes i la seva distribucio
	//Restriccio: Han d'haver-hi 180 camions en total.
	//PDISTRIBUCIO-{X}
	//PPETROLI-{X}-{Y}-{CAPACITAT}
	PDISTRIBUCIO-15
	PDISTRIBUCIO-4
	PPETROLI-7-15-4
	PPETROLI-7-12-6
	PPETROLI-7-6-10
	
	

	 * On totes les lineas que comencin per // s'entendra que sn comentaris i no seran parsejades.
	 */
	public boolean readInitialState(String file, int initialState)
	{
	try
		{


			BufferedReader bf = new BufferedReader(new FileReader(file));
			String lineBuffer;
			int hora;
			PlataformaPetrolifera ppet = null;
			PlataformaDistribuidora pdist = null;
			ArrayList plataformesDist = new ArrayList();
			ArrayList plataformesPetro = new ArrayList();


			while ((lineBuffer = bf.readLine())!=null)
			{
				if (!lineBuffer.startsWith("////"))
				{
					if (lineBuffer.startsWith("PDISTRIBUCIO"))
					{
						String[] distri = lineBuffer.split("-");
						plataformesDist.add(new PlataformaDistribuidora(new Integer(distri[1]).intValue()));
						
					}
					else if (lineBuffer.startsWith("PPETROLI"))
					{
						String[] petro = lineBuffer.split("-");
						plataformesPetro.add(new PlataformaPetrolifera(new Integer(petro[1]).intValue(),new Integer(petro[2]).intValue(),new Integer(petro[3]).intValue()));
					}

				}
			}
			
			load(plataformesPetro,plataformesDist);
			
			if ( initialState == 2) initialState2();			
			if ( initialState == 3) initialState3();
		}
		catch (Exception e) { e.printStackTrace(); return false; }
	

		return true;

	}


	/**
	 * Salva l'estat inicial del problema en un fitxer de text.
	 * @param file Nom del fitxer on es guardar l'estat inicial
	 * @return Cert si tot ha anat b, Fals si s'ha produit algun error durant l'escriptura.
	 */
	public boolean saveInitialState(String file)
	{
		
		try
		{
		System.out.println("Fitxer: " +file);
			PrintStream ps=new PrintStream(new FileOutputStream(file)); 


			ps.println("// ESCENARI");
			
			if (plataformes != null)
			{
				for (int i = 0; i < plataformes.length; i++)
				{
					if (plataformes[i].isPlataformaDistribuidora())
						ps.println("PDISTRIBUCIO-"+plataformes[i].getX());
					else 
						ps.println("PPETROLI-"+plataformes[i].getX()+"-"+plataformes[i].getY()+"-"+((PlataformaPetrolifera)plataformes[i]).getPetroli()); 
				}
			}
			
			ps.close();
		}
		catch (Exception e) { e.printStackTrace(); return false; }
		
		return true;
	}
	
	
	

	
	 
	
	
	
	
}


