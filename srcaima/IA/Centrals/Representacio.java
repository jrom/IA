package IA.Centrals;


import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Vector;


import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;
import aima.search.informed.HillClimbingSearch;
public class Representacio {
	
	
	private static int NUM_CONEXIONS_CENTRAL = 3;
	private static int NUM_CONEXIONS_REPETIDOR = 5;
	
	
	// Vector de Vectors
	private Vector estat;
	private Nodo [] vNodos;
	
	private int numCentrals;
	private int numRepetidors;
	private int columna;
	private int fila;
	private int alfa;
	private int beta;
	private int gamma;
	private int rnoutil;
	private int maxRepetidors;
	private Random r= new Random();
	private int errSolInicial;
	private int sol;
	
	public Representacio (int numCentrals, int numRepetidors, int amplada, int altura, int alfa, int beta, int gamma, int maxRepetidors) {
		
		this.alfa=alfa;
		this.beta=beta;
		this.gamma=gamma;
		this.maxRepetidors=maxRepetidors;
		this.numCentrals = numCentrals;
		this.numRepetidors = numRepetidors;
		this.columna = amplada;
		this.fila = altura;
		this.vNodos = new Nodo[this.numCentrals+this.numRepetidors];
		rnoutil = 0;
		int x,y;
		int i;
		
		int [][] tablero = new int[fila][columna];
		int tx,ty;
		
		for(tx=0;tx<fila;tx++)
			for(ty=0;ty<columna;ty++)
				tablero[tx][ty]=0;
		
		boolean trobat=false;
		
		for (i=0;i<this.numCentrals;i++) {
			x=Math.abs(r.nextInt()%fila);
			y=Math.abs(r.nextInt()%columna);
			trobat=false;
			while(!trobat){
				x=Math.abs(r.nextInt()%fila);
				y=Math.abs(r.nextInt()%columna);
				
				while (x>=this.fila || y >=this.columna )
				{
					x=Math.abs(r.nextInt()%fila);
					y=Math.abs(r.nextInt()%columna);
				}
				if (tablero[x][y]==0)trobat=true;
				
			}
			tablero[x][y]=1;
			this.vNodos[i]= new Nodo(x,y,true);
			
		}
		for (int j=0;j<this.numRepetidors;j++) {
			x=Math.abs(r.nextInt()%fila);
			y=Math.abs(r.nextInt()%columna);
			trobat=false;
			while(!trobat){
				x=Math.abs(r.nextInt()%fila);
				y=Math.abs(r.nextInt()%columna);
				
				while (x>=this.fila || y >=this.columna )
				{
					x=Math.abs(r.nextInt()%fila);
					y=Math.abs(r.nextInt()%columna);
				}
				if (tablero[x][y]==0)trobat=true;
			}
			tablero[x][y]=2;
			this.vNodos[i+j]= new Nodo(x,y,false);
		}
	}
	
	// Constructor per copia ...
	public Representacio (Representacio estatvell) {
		this.numCentrals = estatvell.getNumCentrals();
		this.numRepetidors = estatvell.getNumRepetidors();
		this.columna = estatvell.getAmplada();
		this.fila = estatvell.getAltura();
		this.vNodos = estatvell.getCentrals();
		this.estat = new Vector();
		this.rnoutil=estatvell.getRnoutil();
		this.maxRepetidors = estatvell.getMaxRep() ;
		this.errSolInicial = estatvell.getErrorMax();
		this.alfa=estatvell.getAlfa();
		this.beta=estatvell.getBeta();
		this.gamma=estatvell.getGamma();
		this.sol = estatvell.getTipusSol();
		
		Vector estat2 = estatvell.getEstat();
		
		for(int i=0;i<estat2.size();i++){
			this.estat.addElement(new Vector());
			for(int j=0;j<((Vector)estat2.get(i)).size();j++){
				((Vector)estat.get(i)).add(new Integer(((Integer)((Vector)estat2.get(i)).get(j)).intValue()));
			}	
		}
		//this.estat = estat2;
	}
	
	
	public int getTipusSol() {
		return this.sol; 
	}
	
	/* 
	 * Soluci� Inicial molt cutre, tanto ke ni va (by andres) ... fa el seguent:
	 * 1 -> 2
	 * 2 -> 3
	 * 3 -> 4
	 * 4 -> 5
	 * 5 -> 6
	 * 6 -> 7
	 * ...
	 */
	public void solucioInicial (int num) {
		switch(num){
		case 1: 
			solucioInicial1();
			break;
		case 2: 
			solucioInicial2();
			break;
		case 3: 
			solucioInicial3();
			break;
		case 4: 
			solucioInicial4();
			break;
		case 5: 
			solucioInicial5();
			break;
		case 6: 
			solucioInicial6();
			break;
		default:
			solucioInicial1();
		break;
		}
		errSolInicial=this.getErrorTotal(1);
		this.sol=num;
	}
	public void solucioInicial1 () {
		this.estat = new Vector();
		int i;
		for (i=0;i<this.numCentrals;i++) {
			this.estat.addElement(new Vector());
			if(i==this.numCentrals/2 && this.getNumRepetidors()>0 ){
				((Vector) this.estat.elementAt(i)).addElement(new Integer(i));
				((Vector) this.estat.elementAt(i)).addElement(new Integer(i-1));
				((Vector) this.estat.elementAt(i)).addElement(new Integer(this.numCentrals));
				
			}else if(i==(this.numCentrals/2)+1 && this.getNumRepetidors()>0 ){
				((Vector) this.estat.elementAt(i)).addElement(new Integer(i));
				((Vector) this.estat.elementAt(i)).addElement(new Integer(this.numCentrals));
				((Vector) this.estat.elementAt(i)).addElement(new Integer(i+1));
			}
			else{
				((Vector) this.estat.elementAt(i)).addElement(new Integer(i));
				if(i!=numCentrals-1)((Vector) this.estat.elementAt(i)).addElement(new Integer(i+1));
				if(i!=0)((Vector) this.estat.elementAt(i)).addElement(new Integer(i-1));
			}
		}
		
		//Repetidor!!
		if ( this.getNumRepetidors()>0 ) {
			this.estat.addElement(new Vector());
			((Vector) this.estat.elementAt(i)).addElement(new Integer(this.numCentrals));
			((Vector) this.estat.elementAt(i)).addElement(new Integer(this.numCentrals/2));
			((Vector) this.estat.elementAt(i)).addElement(new Integer((this.numCentrals/2)+1));
		}
		//afegir altres repetidors buits
		
		for(int ri=1;ri<numRepetidors;ri++)
			this.estat.addElement(new Vector());
		
		rnoutil=numRepetidors-1;
	}
	

	
	public void solucioInicial2() {
		
		this.estat = new Vector();
		//InitSoluccio
		for (int i=0;i<this.getNumCentrals()+this.getNumRepetidors();i++) { 
			this.estat.addElement(new Vector());
			if(i<numCentrals)((Vector) this.estat.elementAt(i)).addElement(new Integer(i));
		}
		//Crear vector de Vectors ordeanando de x a y
		Vector [] order = new Vector[columna];
		SortManager sm = new SortManager(this.vNodos);
		
		for(int i=0;i<columna;i++){
			order[i]=new Vector();
			for(int j=0;j<numCentrals+numRepetidors;j++){
				if(vNodos[j].getCordY()==i && vNodos[j].getEsCentral()){
					order[i].addElement(new Integer(j));
				}
				
				sm.quickSortY(order[i],0,order[i].size()-1);
			}
		}
		
		//ARA TINDREM UN VECTOR DE VECTORS KE TE Per cada node la dist als altres nodes
		int vdist[][] = new int[numCentrals+numRepetidors][numCentrals+numRepetidors];
		
		for(int i=0;i<numCentrals+numRepetidors;i++){
			for(int j=0;j<numCentrals+numRepetidors;j++){
				vdist[i][j]=getDistance(i,j);
			}
		}
		int numconex=0;
		for(int i=0;i<columna;i++){
			Vector vx = order[i];
			if(vx.size()>0){
				for(int j=0;j<vx.size()-1;j++){
					int actual = ((Integer)vx.get(j)).intValue();
					int seguent = ((Integer)vx.get(j+1)).intValue();
					((Vector) this.estat.elementAt(actual)).addElement(new Integer(seguent));
					((Vector) this.estat.elementAt(seguent)).addElement(new Integer(actual));
					numconex++;
				}
			}
		}
		for(int i=0;i<columna-1 && numconex!=numCentrals-1;i++){
			Vector vx = order[i];
			if(vx.size()>0){
				int seguentcol=0;
				boolean trobat=false;
				for(int j=i+1;j<columna && !trobat;j++){
					if(order[j].size()>0){
						trobat=true;
						seguentcol=j;
					}
				}
				//ara connectem les 2 columnes
				int mindist = Integer.MAX_VALUE;
				int nodeiselect=0;
				int nodejselect=0;
				for(int ni=0;ni<numCentrals+numRepetidors;ni++){
					for(int nj=0;nj<numCentrals+numRepetidors;nj++){
						if(order[i].contains(new Integer(ni))&& order[seguentcol].contains(new Integer(nj))){
							if(vdist[ni][nj]<mindist){
								nodeiselect=ni;
								nodejselect=nj;
								mindist=vdist[ni][nj];
							}
						}
					}
				}
				((Vector) this.estat.elementAt(nodeiselect)).addElement(new Integer(nodejselect));
				((Vector) this.estat.elementAt(nodejselect)).addElement(new Integer(nodeiselect));
				numconex++;
			}		
		}
	}
	
	public void solucioInicial3() {
		solucioInicial2();
		Search search=null; //new DepthLimitedSearch(2*nc);;
		Problem problem =null;
		boolean [] ops = new boolean[6];
		ops[0]=false;ops[1]=true;ops[2]=true;ops[3]=true;ops[4]=true;ops[5]=true;
		problem = new Problem (this,new CentralsGeneradorEstats(1000,ops),	new CentralsEstatFinal(), new CentralsFuncioHeuristica4());
		search =  new HillClimbingSearch();
		
		Date d1,d2;
		Calendar a,b;
		
		d1=new Date();
		try {
			SearchAgent agent = new SearchAgent(problem,search);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		d2=new Date();
		
		a= Calendar.getInstance();
		b= Calendar.getInstance();
		a.setTime(d1);
		b.setTime(d2);
		
		long m=b.getTimeInMillis()-a.getTimeInMillis();
		
		System.out.println("temps solucio4="+m+" ms");
		
		Representacio estatnou = (Representacio) ((HillClimbingSearch)search).getLastSearchState();
		
		this.numCentrals = estatnou.getNumCentrals();
		this.numRepetidors = estatnou.getNumRepetidors();
		this.columna = estatnou.getAmplada();
		this.fila = estatnou.getAltura();
		this.vNodos = estatnou.getCentrals();
		this.estat = new Vector();
		this.rnoutil=estatnou.getRnoutil();
		this.maxRepetidors = estatnou.getMaxRep() ;
		this.errSolInicial = estatnou.getErrorMax();
		this.alfa=estatnou.getAlfa();
		this.beta=estatnou.getBeta();
		this.gamma=estatnou.getGamma();
		this.sol = estatnou.getTipusSol();
		
		Vector estat2 = estatnou.getEstat();
		
		for(int i=0;i<estat2.size();i++){
			this.estat.addElement(new Vector());
			for(int j=0;j<((Vector)estat2.get(i)).size();j++){
				((Vector)estat.get(i)).add(new Integer(((Integer)((Vector)estat2.get(i)).get(j)).intValue()));
			}	
		}
		
	}
	
	public void solucioInicial4() {
		this.estat = new Vector();
		//boolean[] usats = new boolean[this.getNumCentrals()+this.getNumRepetidors()];
		for (int i=0;i<this.getNumCentrals();i++) { 
			this.estat.addElement(new Vector());
			((Vector) this.estat.elementAt(i)).addElement(new Integer(i));
		}
		for(int ri=0;ri<numRepetidors;ri++){
			this.estat.addElement(new Vector());
		}
		
		doint [] distr = new doint[this.getNumRepetidors()];
		for (int i=this.getNumCentrals();i<this.getNumCentrals()+this.getNumRepetidors();i++) {
			doint[] dest=mesProperD(i);
			int sum=0;
			for(int j=0;j<5;j++){
				sum+=dest[j].dist;
			}
			distr[i-this.getNumCentrals()] = new doint();
			distr[i-this.getNumCentrals()].dist=sum;
			distr[i-this.getNumCentrals()].id=i;
		}
		quickSortD(distr,0,distr.length-1);
		
	
		for(int i=0;i<this.maxRepetidors;i++){
			((Vector) this.estat.elementAt(distr[i].id)).addElement(new Integer(distr[i].id));
			int idr = distr[i].id;
			doint[] dest=mesProperD(idr);
			int conn=0;
			for(int j=0;j<dest.length && conn<3;j++){
				if(dest[j].id<this.getNumCentrals()&&((Vector) this.estat.get(dest[j].id)).size()<2){
					((Vector) this.estat.elementAt(idr)).addElement(new Integer(dest[j].id));
					((Vector) this.estat.elementAt(dest[j].id)).addElement(new Integer(idr));
					conn++;
				}
			}
		}
		
		for(int i=0;i<this.maxRepetidors-1;i++){
			int idr1 = distr[i].id;
			int idr2 = distr[i+1].id;
			((Vector) this.estat.elementAt(idr1)).addElement(new Integer(idr2)); 
			((Vector) this.estat.elementAt(idr2)).addElement(new Integer(idr1));
		}
		
		for(int i=0;i<this.getNumCentrals();i++){
			if(((Vector) this.estat.elementAt(i)).size()<2){
				doint[] dest=mesProperD(i);
				boolean fi=false;
				for(int j=0;j<dest.length && !fi;j++){
					if(dest[j].id>=this.getNumCentrals()&&((Vector)this.estat.get(dest[j].id)).size()<6)
					{
						boolean trobat=false;
						for(int k=0;k<maxRepetidors&&!trobat;k++){
							if(distr[k].id==dest[j].id)trobat=true;
						}
						if(trobat){
							((Vector) this.estat.elementAt(i)).addElement(new Integer(dest[j].id));
							((Vector) this.estat.elementAt(dest[j].id)).addElement(new Integer(i));
							fi=true;
						}
					}
					else if(dest[j].id<this.getNumCentrals()&&((Vector) this.estat.get(dest[j].id)).size()<4&&((Vector) this.estat.get(dest[j].id)).size()!=1){
						((Vector) this.estat.elementAt(i)).addElement(new Integer(dest[j].id));
						((Vector) this.estat.elementAt(dest[j].id)).addElement(new Integer(i));
						fi=true;
					}
				}
			}
		}
		
		rnoutil=maxRepetidors;
	}
	
	public void solucioInicial5() {
		this.estat = new Vector();
		//boolean[] usats = new boolean[this.getNumCentrals()+this.getNumRepetidors()];
		for (int i=0;i<this.getNumCentrals();i++) { 
			this.estat.addElement(new Vector());
			((Vector) this.estat.elementAt(i)).addElement(new Integer(i));
		}
		for(int ri=0;ri<numRepetidors;ri++){
			this.estat.addElement(new Vector());
		}
		
		doint [] distr = new doint[this.getNumRepetidors()];
		for (int i=this.getNumCentrals();i<this.getNumCentrals()+this.getNumRepetidors();i++) {
			doint[] dest=mesProperD(i);
			int sum=0;
			for(int j=0;j<5;j++){
				sum+=dest[j].dist;
			}
			distr[i-this.getNumCentrals()] = new doint();
			distr[i-this.getNumCentrals()].dist=sum;
			distr[i-this.getNumCentrals()].id=i;
		}
		quickSortD(distr,0,distr.length-1);
		
	
		//2A MANERA
		int totalrepconnex=0;
		int [] vconn = new int[maxRepetidors];
		for(int i=0;i<vconn.length;i++)vconn[i]=0;
		
		for(int i=0;i<this.maxRepetidors;i++)
			((Vector) this.estat.elementAt(distr[i].id)).addElement(new Integer(distr[i].id));
		
		while(totalrepconnex<maxRepetidors*3){
		
			for(int i=0;i<this.maxRepetidors;i++){
			
			int idr = distr[i].id;
			doint[] dest=mesProperD(idr);
			int conn=0;
			boolean afegit=false;
			if( vconn[i]<3){
				for(int j=0;j<dest.length && !afegit;j++){
					if(dest[j].id<this.getNumCentrals()&&((Vector) this.estat.get(dest[j].id)).size()<2){
						((Vector) this.estat.elementAt(idr)).addElement(new Integer(dest[j].id));
						((Vector) this.estat.elementAt(dest[j].id)).addElement(new Integer(idr));
						vconn[i]++;
						afegit=true;
						totalrepconnex++;
					}
				}
			}
		}
		}
		
		for(int i=0;i<this.maxRepetidors-1;i++){
			int idr1 = distr[i].id;
			int idr2 = distr[i+1].id;
			((Vector) this.estat.elementAt(idr1)).addElement(new Integer(idr2)); 
			((Vector) this.estat.elementAt(idr2)).addElement(new Integer(idr1));
		}
		
		for(int i=0;i<this.getNumCentrals();i++){
			if(((Vector) this.estat.elementAt(i)).size()<2){
				doint[] dest=mesProperD(i);
				boolean fi=false;
				for(int j=0;j<dest.length && !fi;j++){
					if(dest[j].id>=this.getNumCentrals()&&((Vector)this.estat.get(dest[j].id)).size()<6)
					{
						boolean trobat=false;
						for(int k=0;k<maxRepetidors&&!trobat;k++){
							if(distr[k].id==dest[j].id)trobat=true;
						}
						if(trobat){
							((Vector) this.estat.elementAt(i)).addElement(new Integer(dest[j].id));
							((Vector) this.estat.elementAt(dest[j].id)).addElement(new Integer(i));
							fi=true;
						}
					}
					else if(dest[j].id<this.getNumCentrals()&&((Vector) this.estat.get(dest[j].id)).size()<4&&((Vector) this.estat.get(dest[j].id)).size()!=1){
						((Vector) this.estat.elementAt(i)).addElement(new Integer(dest[j].id));
						((Vector) this.estat.elementAt(dest[j].id)).addElement(new Integer(i));
						fi=true;
					}
				}
			}
		}
		
		rnoutil=maxRepetidors;
	}
	
	public void solucioInicial6() {
		this.estat = new Vector();
		//boolean[] usats = new boolean[this.getNumCentrals()+this.getNumRepetidors()];
		for (int i=0;i<this.getNumCentrals();i++) { 
			this.estat.addElement(new Vector());
			((Vector) this.estat.elementAt(i)).addElement(new Integer(i));
		}
		for(int ri=0;ri<numRepetidors;ri++){
			this.estat.addElement(new Vector());
		}
		
		doint [] distr = new doint[this.getNumRepetidors()];
		for (int i=this.getNumCentrals();i<this.getNumCentrals()+this.getNumRepetidors();i++) {
			doint[] dest=mesProperD(i);
			int sum=0;
			for(int j=0;j<3;j++){
				if(dest[j].id<this.getNumCentrals()){
				sum+=dest[j].dist;
				}
			}
			distr[i-this.getNumCentrals()] = new doint();
			distr[i-this.getNumCentrals()].dist=sum;
			distr[i-this.getNumCentrals()].id=i;
		}
		quickSortD(distr,0,distr.length-1);
		
	
		
		int totalrepconnex=0;
		int [] vconn = new int[maxRepetidors];
		for(int i=0;i<vconn.length;i++)vconn[i]=0;
		
		for(int i=0;i<this.maxRepetidors;i++)
			((Vector) this.estat.elementAt(distr[i].id)).addElement(new Integer(distr[i].id));
		
		int nc = this.getNumCentrals();
		int count=0;
		while(totalrepconnex<maxRepetidors*3 && count<1000){
		
			for(int i=0;i<this.maxRepetidors;i++){
			
			int idr = distr[i].id;
			doint[] dest=mesProperD(idr);
			int conn=0;
			boolean afegit=false;
			if( vconn[i]<3){
				for(int j=0;j<dest.length && !afegit;j++){
					if(dest[j].id<this.getNumCentrals()&&((Vector) this.estat.get(dest[j].id)).size()<2){
						((Vector) this.estat.elementAt(idr)).addElement(new Integer(dest[j].id));
						((Vector) this.estat.elementAt(dest[j].id)).addElement(new Integer(idr));
						vconn[i]++;
						afegit=true;
						totalrepconnex++;
					}
					count++;
				}
			}
		}
		}
		
		for(int i=0;i<this.maxRepetidors-1;i++){
			int idr1 = distr[i].id;
			int idr2 = distr[i+1].id;
			((Vector) this.estat.elementAt(idr1)).addElement(new Integer(idr2)); 
			((Vector) this.estat.elementAt(idr2)).addElement(new Integer(idr1));
		}
		
		for(int i=0;i<this.getNumCentrals();i++){
			if(((Vector) this.estat.elementAt(i)).size()<2){
				doint[] dest=mesProperD(i);
				boolean fi=false;
				for(int j=0;j<dest.length && !fi;j++){
					if(dest[j].id>=this.getNumCentrals()&&((Vector)this.estat.get(dest[j].id)).size()<6)
					{
						boolean trobat=false;
						for(int k=0;k<maxRepetidors&&!trobat;k++){
							if(distr[k].id==dest[j].id)trobat=true;
						}
						if(trobat){
							((Vector) this.estat.elementAt(i)).addElement(new Integer(dest[j].id));
							((Vector) this.estat.elementAt(dest[j].id)).addElement(new Integer(i));
							fi=true;
						}
					}
					else if(dest[j].id<this.getNumCentrals()&&((Vector) this.estat.get(dest[j].id)).size()<4&&((Vector) this.estat.get(dest[j].id)).size()!=1){
						((Vector) this.estat.elementAt(i)).addElement(new Integer(dest[j].id));
						((Vector) this.estat.elementAt(dest[j].id)).addElement(new Integer(i));
						fi=true;
					}
				}
			}
		}
		
		rnoutil=maxRepetidors;
	}
	
	public class doint {
		public int dist;
		public int id;
		
	}
	
	private doint[] mesProperD(int i) {
		doint[] res = new doint[this.getNumCentrals()+this.getNumRepetidors()];
		
		for (int j=0;j<this.getNumCentrals()+this.getNumRepetidors();j++) {
			res[j]=new doint();
			res[j].id=j;
			if (j!=i) {
				res[j].dist=getDistance(i,j);
				
			} else {
				res[j].dist=Integer.MAX_VALUE;
			}
		}
		
		quickSortD(res,0,res.length-1);
		
		return res;
	}
	
	private void quickSortD (doint[] matriu, int posInici,  int posFinal) {
		if (posInici>=posFinal) { 
			return;
		} else { 
			int posProv=posInici+1; 
			for (int i=posInici+1;i<posFinal+1;i++) {
				if (anteriorAD(matriu[i].dist,matriu[posInici].dist)) {
					intercanviaD(matriu,posProv,i);
					posProv++;
				}
			}
			intercanviaD(matriu,posInici,posProv-1);
			quickSortD(matriu,posInici,posProv-2);
			quickSortD(matriu,posProv,posFinal);  
		}
	}
	
	public void intercanviaD (doint[] matriu,  
			int i,int j) {
		doint temporal = new doint();
		temporal.dist=matriu[i].dist;
		temporal.id=matriu[i].id;
		matriu[i]=matriu[j];
		matriu[j]=temporal;
	}
	
	public boolean anteriorAD (int obX,int obY) { 
		return obX<obY;
	}
	
	public int getAltura() {
		return fila;
	}
	
	public int getAmplada() {
		return columna;
	}
	
	public Nodo[] getCentrals() {
		return vNodos;
	}
	
	public Vector getEstat() {
		return estat;
	}
	
	public int getNumCentrals() {
		return numCentrals;
	}
	
	public int getRnoutil() {
		return rnoutil;
	}
	
	public int getNumRepetidors() {
		return numRepetidors;
	}
	
	public int getAlfa() {
		return alfa;
	}
	
	public int getBeta() {
		return beta;
	}
	
	public int getGamma() {
		return gamma;
	}
	
	public int getMaxRep() {
		return maxRepetidors;
	}
	
	public int getRepetidorsUtil(){
		int rutil=0;
		for (int i=numCentrals;i<estat.size();i++) {
			if(((Vector)estat.get(i)).size()!=0) {
				rutil++;
			}
		}
		return rutil;
	}
	
	public int [][] getPlano(){
		
		int [][] tablero = new int[fila][columna];
		int tx,ty;
		
		for(tx=0;tx<fila;tx++)
			for(ty=0;ty<columna;ty++)
				tablero[tx][ty]=0;
		
		for(int i=0;i<numCentrals+numRepetidors;i++)
		{
			if(vNodos[i].getEsCentral())
				tablero[vNodos[i].getCordX()][vNodos[i].getCordY()]=1;
			else tablero[vNodos[i].getCordX()][vNodos[i].getCordY()]=2;
		}
		return tablero; 
	}
	
	public Vector getSol(){
		
		return this.estat; 
	}
	
	public Nodo[] getVNodo(){
		
		
		return this.vNodos; 
	}
	
	
	public void printtablero(){
		int[][] tablero = this.getPlano();
		System.out.println("tauler");
		for(int ii=0;ii<fila;ii++){
			for(int j=0;j<columna;j++){
				System.out.print(tablero[ii][j]);}
			System.out.println();
		}
	}
	
	public void printSolucio () {
		System.out.println("SOLUCIO");
		for (int i=0;i<this.estat.size();i++) {
			Vector v = (Vector) this.estat.get(i);
			for (int j=0;j<v.size();j++) {
				System.out.print(v.get(j)+", ");
			}
			System.out.println();
		}
	}
	
	public void printVNodos () {
		System.out.println("VNODOS");
		for (int i=0;i<vNodos.length;i++) System.out.println(vNodos[i].getCordX()+" "+vNodos[i].getCordY()+" "+vNodos[i].getEsCentral());
		
	}
	
	public boolean isGoalState() {
		return false;
	}
	
	public boolean interConexio (int origen, int antic, int nou) {
		//System.out.println("inteconnexio --> "+ " "+origen+ " "+antic+ " "+nou);
		boolean nouescentral = false;
		
		if (origen == nou) return false;
		if (antic == origen) return false;
		
		//miramos ke los nuevos no tengan mas de max num connexiones 
		if(((Integer)((Vector)this.estat.get(nou)).get(0)).intValue()>=numCentrals){
			if(((Vector)this.estat.get(nou)).size()-1>=this.NUM_CONEXIONS_REPETIDOR)return false;
			
		}else{
			nouescentral=true;
			if(((Vector)this.estat.get(nou)).size()-1>=this.NUM_CONEXIONS_CENTRAL )return false;
		}
		
		
		if(((Integer)((Vector)this.estat.get(antic)).get(0)).intValue()>=numCentrals){
			if(((Vector)this.estat.get(antic)).size()==3)return false;
		}
		
		if (((Vector)this.estat.get(origen)).contains(new Integer(nou))) return false;
		if (((Vector)this.estat.get(antic)).size()<3) return false; // Quedaria no connex.
		
		if (((Vector)this.estat.get(origen)).size()<3) {
			((Vector)this.estat.get(origen)).remove(new Integer(antic));
			((Vector)this.estat.get(antic)).remove(new Integer(origen));
			((Vector)this.estat.get(origen)).add(new Integer(nou));
			((Vector)this.estat.get(nou)).add(new Integer(origen));
			return true;
		}
		// S'ha de fer comprobacio per comprobar que la solucion es connexa.
		((Vector)this.estat.get(origen)).remove(new Integer(antic));
		((Vector)this.estat.get(antic)).remove(new Integer(origen));
		((Vector)this.estat.get(origen)).add(new Integer(nou));
		((Vector)this.estat.get(nou)).add(new Integer(origen));
		
		int indx = this.getNodeMaxGrau();
		
		boolean[] arribats = new boolean[this.estat.size()];
		
		for (int j=0;j<arribats.length ;j++)arribats[j] = false;
		for (int j=0;j<arribats.length ;j++)
			if(((Vector)estat.get(j)).size()==0) arribats[j] = true;
		
		arribats[indx]=true;
		
		for (int k=0;k<arribats.length;k++){
			if (arribats[k]==false){
				if(!potArribar(indx,k,arribats)) return false;
			}
		}
		return true;
	}
	
	public boolean interConexio2 (int origen, int antic, int nou) {
		//System.out.println("inteconnexio --> "+ " "+origen+ " "+antic+ " "+nou);
		boolean nouescentral = false;
		
		if (origen == nou) return false;
		if (antic == origen) return false;
		
		//miramos ke los nuevos no tengan mas de max num connexiones 
		if(((Integer)((Vector)this.estat.get(nou)).get(0)).intValue()>=numCentrals){
			if(((Vector)this.estat.get(nou)).size()-1>=this.NUM_CONEXIONS_REPETIDOR)return false;
			
		}else{
			nouescentral=true;
			if(((Vector)this.estat.get(nou)).size()-1>=this.NUM_CONEXIONS_CENTRAL )return false;
		}
		
		
		if(((Integer)((Vector)this.estat.get(antic)).get(0)).intValue()>=numCentrals){
			if(((Vector)this.estat.get(antic)).size()==3)return false;
		}
		
		if (((Vector)this.estat.get(origen)).contains(new Integer(nou))) return false;
		if (((Vector)this.estat.get(antic)).size()<3) return false; // Quedaria no connex.
		
		//+INTELIGENT!
		if(nouescentral){//per probar numes amb alfa = 6
			if(getDistance(origen,antic)<getDistance(origen,nou)){
				//System.out.println("ha podat!!!");
				return false;
			}
		}
		
		if (((Vector)this.estat.get(origen)).size()<3) {
			((Vector)this.estat.get(origen)).remove(new Integer(antic));
			((Vector)this.estat.get(antic)).remove(new Integer(origen));
			((Vector)this.estat.get(origen)).add(new Integer(nou));
			((Vector)this.estat.get(nou)).add(new Integer(origen));
			return true;
		}
		// S'ha de fer comprobacio per comprobar que la solucion es connexa.
		((Vector)this.estat.get(origen)).remove(new Integer(antic));
		((Vector)this.estat.get(antic)).remove(new Integer(origen));
		((Vector)this.estat.get(origen)).add(new Integer(nou));
		((Vector)this.estat.get(nou)).add(new Integer(origen));
		
		//printSolucio();
		int indx = this.getNodeMaxGrau();
		
		boolean[] arribats = new boolean[this.estat.size()];
		
		for (int j=0;j<arribats.length ;j++)arribats[j] = false;
		for (int j=0;j<arribats.length ;j++)
			if(((Vector)estat.get(j)).size()==0) arribats[j] = true;
		
		arribats[indx]=true;
		
		for (int k=0;k<arribats.length;k++){
			if (arribats[k]==false){
				if(!potArribar(indx,k,arribats)) return false;
			}
		}
		return true;
	}
	
	private boolean potArribar(int indx, int desti, boolean[] arribats) {
		//this.printSolucio();
		//System.out.println("mi listaa es -> "+((Vector)this.estat.get(indx)).get(0).toString()+" el seg -> "+((Vector)this.estat.get(indx)).get(1).toString());
		for (int i=1;i<((Vector)this.estat.get(indx)).size();i++){
			int actual = Integer.parseInt(((Vector)this.estat.get(indx)).get(i).toString());
			if(arribats[actual]==false){
				arribats[actual]=true;
				if(actual==desti)return true;
				else{
					//System.out.println("potArribar(actual,desti,arribats,indx) -> "+ actual+" "+ desti+" "+ arribats.toString()+" "+ indx+" ");
					if(potArribar(actual,desti,arribats))return true;
				}
				
				
			}
		}
		return false;
	}
	
	private int getNodeMaxGrau() {
		int max=0;
		int node=0;
		for (int i=0;i<this.estat.size();i++) {
			if(((Vector)estat.get(i)).size()!=0) {
				int actual = Integer.parseInt(((Vector)this.estat.get(i)).get(0).toString());
				
				if (((Vector)this.estat.get(i)).size()>max) {
					node=i;
					max=((Vector)this.estat.get(i)).size();
				}
			}
		}
		return node;
	}
	
	public int getErrorTotal(int heur) {
		int error=0;
		for (int i=0;i<this.estat.size();i++){
			if(((Vector)estat.get(i)).size()!=0) {
				for (int j=1;j<((Vector)this.estat.get(i)).size();j++) {
					int actual = Integer.parseInt(((Vector)this.estat.get(i)).get(j).toString());
					if(actual>i)error+=getError(i,actual,heur);
				}
			}
		}
		return error;//+rnoutil*50;
		
	}
	
	private int getError(int i, int j, int heur){
		
		
		int sumconnexions= ((Vector)this.estat.get(i)).size() + ((Vector)this.estat.get(j)).size()-2;
		int distance = getDistance(i,j);
		
		int elev=1;		
		
		if(vNodos[i].getEsCentral() && vNodos[j].getEsCentral()) 
			elev = alfa;
		if(vNodos[i].getEsCentral() != vNodos[j].getEsCentral()) 
			elev = beta;
		if(!vNodos[i].getEsCentral() && !vNodos[j].getEsCentral()) 
			elev = gamma;
		
		
		
		//PROVA -1-
		//int exp = (int) Math.pow(distance,elev);
		//exp = exp*sumconnexions;
		//return exp;
		if(heur==1){
			int res = (int)Math.pow((double)distance,(double)elev);
			res=res*sumconnexions;
			return res;
		}
		
		//PROVA -2-
		if(heur==2)return distance;
		
		//PROVA -3-
		if(heur==3)return distance*sumconnexions;
		
		return 0;
	}
	
	private int getDistance(int i, int j){
		return Math.abs(vNodos[i].getCordX()-vNodos[j].getCordX())+Math.abs(vNodos[i].getCordY()-vNodos[j].getCordY());
		
	}
	
	public boolean afegirRepetidor(int or1, int or2, int rep) {
		//tindriem ke comprovare no maximo de repetidores utilizables
		
		//this.estat.add(new Vector());
		rnoutil--;
		//this.estat.c
		//treiem connex vella
		((Vector)this.estat.get(or1)).remove(new Integer(or2));
		((Vector)this.estat.get(or2)).remove(new Integer(or1));
		//afegim a rep rep
		((Vector)this.estat.get(rep)).add(new Integer(rep));
		//afegim conex a or1-rep
		((Vector)this.estat.get(or1)).add(new Integer(rep));
		((Vector)this.estat.get(rep)).add(new Integer(or1));
		//afegim conex a or2-rep
		((Vector)this.estat.get(or2)).add(new Integer(rep));
		((Vector)this.estat.get(rep)).add(new Integer(or2));
		
		//printSolucio();
		return true;
	}
	
	public void treureRepetidor(int numr) {
		for(int i =1;i<((Vector)this.estat.get(numr)).size()-1;i++){
			int actual = ((Integer)((Vector)this.estat.get(numr)).get(i)).intValue();
			int seguent = ((Integer)((Vector)this.estat.get(numr)).get(i+1)).intValue();
			((Vector)this.estat.get(actual)).add(new Integer(seguent));
			((Vector)this.estat.get(seguent)).add(new Integer(actual));
			((Vector)this.estat.get(actual)).remove( new Integer(numr));
			((Vector)this.estat.get(seguent)).remove( new Integer(numr));
		}
		((Vector)this.estat.get(numr)).removeAllElements();
		//System.out.println("size r="+((Vector)this.estat.get(numr)).size());
	}
	
	public void interRepetidor (int r1, int r2) {
		
		
		// Afegim a ell mateix
		((Vector)this.estat.get(r2)).add(new Integer(r2));
		// Afegim totes les conexions que tenia el repetidor antic.
		for(int i=1;i<((Vector)this.estat.get(r1)).size();i++){
			int actual = ((Integer)((Vector)this.estat.get(r1)).get(i)).intValue();
			((Vector)this.estat.get(actual)).remove(new Integer(r1));
			((Vector)this.estat.get(r2)).add(new Integer(actual));
			((Vector)this.estat.get(actual)).add(new Integer(r2));
		}
		((Vector)this.estat.get(r1)).removeAllElements();	
		
	}
	
	public boolean interRepetidor2 (int r1, int r2) {
		
		// Afegim a ell mateix
		//((Vector)this.estat.get(r2)).add(new Integer(r2));
		// Afegim totes les conexions que tenia el repetidor antic.
		if(((Vector)this.estat.get(r1)).size()+((Vector)this.estat.get(r2)).size()<=7){
		for(int i=1;i<((Vector)this.estat.get(r1)).size();i++){
			int actual = ((Integer)((Vector)this.estat.get(r1)).get(i)).intValue();
			((Vector)this.estat.get(actual)).remove(new Integer(r1));
			((Vector)this.estat.get(r2)).add(new Integer(actual));
			((Vector)this.estat.get(actual)).add(new Integer(r2));
		}
		((Vector)this.estat.get(r1)).removeAllElements();	
		return true;
		}else return false;
		
	}
	
	public void setMaxRepetidors(int maxRepetidors2) {
		this.maxRepetidors = maxRepetidors2;
		
	}
	
	public int getErrorMax() {
		return errSolInicial;
	}		
}
