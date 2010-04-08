/*
 * ConnectatBoard.java
 *
 * Created on 22 de marzo de 2006, 17:16
 */

package IA.Connectat;

import java.util.Vector;
import java.util.HashMap;
import java.util.Set;
import java.util.Random;

/**
 *
 * @author  e7709254
 */
public class ConnectatBoard {
    final static int CENTRAL = 1;
    final static int REPETIDOR = 2;
    
    private static int N;
    private static int M;
    private static int alfa, beta, gamma;
    private static int max_num_repetidors; //numero repetidors que podem usar
    private static int ncentrals; //numero de centrals de la ciutat
    private static int nrepetidors; //numero de repetidors de la ciutat
    private static Vector centrals; //posicions de les centrals 
    private static Vector repetidors; //posicions dels repetidors
    
    //indica si hi ha algun node en una coordenada 0: res 1: central 2: repet.
    private static int ciutat[][];
    
    private int error_total;
    private int num_repetidors; //numero de repetidors que estem usant
    private boolean connexions[][]; //la fila i indica les connex del node i
    private int grau[]; //informacio on tenim calculat el grau de cada node
    
    private Random myRandom;
    
    public class node {
        private int x, y, ident;
        
        public node(int xx, int yy, int id) {
            x=xx;
            y=yy;
            ident=id;
        }
        public int getX() { return x; }
        public int getY() { return y; }
        public int getIdent() { return ident; }
        public int getDist() { return x+y; }
    }

  
    /** Creates a new instance of ConnectatBoard */
    public ConnectatBoard() {
        centrals = new Vector();
        repetidors = new Vector();
        
        error_total=0;
        num_repetidors=0;
        alfa=3;
        beta=2;
        gamma=1;
        max_num_repetidors=10;
        myRandom = new Random();
    }
    
    public ConnectatBoard(ConnectatBoard c) {
        int i, j;
        boolean aux[][]=c.getConnexions();
        connexions = new boolean[aux.length][aux.length];
        for (i=0; i<ncentrals+nrepetidors; i++) 
            for (j=0; j<ncentrals+nrepetidors; j++) 
                connexions[i][j]=aux[i][j];
          
        int aux2[]=c.getGrau();
        grau = new int[aux2.length];
        
        for (i=0; i<ncentrals+nrepetidors; i++) 
                grau[i]=aux2[i];

	error_total = c.getErrorTotal();
	num_repetidors = c.getNumRepetidors();
        

        myRandom = new Random();
    }
    
    public void generarCiutat(int n, int m, int ncentr, int nrepe) {
        error_total=0;
        N=n;
        M=m;
        ncentrals=ncentr;
        nrepetidors=nrepe;
        connexions = new boolean[ncentrals+nrepetidors][ncentrals+nrepetidors];
        for (int y=0; y<ncentrals+nrepetidors; y++) {
            for (int z=0; z<ncentrals+nrepetidors; z++) {
                connexions[y][z]=false;
            }
        }
        grau = new int[ncentrals+nrepetidors];
        for (int i=0; i<ncentrals+nrepetidors;i++) {
            grau[i]=0;
        }
        
        ciutat= new int[N][M];
        centrals.clear();
        repetidors.clear();
        
        int i, j;
        for (i=0; i<N; i++) {
            for (j=0; j<M; j++) {
                ciutat[i][j]=0;
            }   
        }
        boolean acabar;
        for (i=0; i<ncentrals; i++) {
            acabar=false;
            while (!acabar) {
                node nod=new node(myRandom.nextInt(N),myRandom.nextInt(M),i);
                if (ciutat[nod.getX()][nod.getY()]==0) {
                    centrals.add(nod);
                    ciutat[nod.getX()][nod.getY()]=CENTRAL;
                    acabar=true;
                }
            }
        }
        for (j=0; j<nrepetidors; j++) {
            acabar=false;
            while (!acabar) {
                node nod=new node(myRandom.nextInt(N),myRandom.nextInt(M),ncentrals+j);
                if (ciutat[nod.getX()][nod.getY()]==0) {
                    repetidors.add(nod);
                    ciutat[nod.getX()][nod.getY()]=REPETIDOR;
                    acabar=true;
                }
            }
        }
    }
    
    public void generarEstatInicial(int tipus) {
        //primer esborrem totes les connexions
        num_repetidors=0;
        for (int i=0; i<ncentrals+nrepetidors; i++) {
            for (int j=0; j<ncentrals+nrepetidors; j++) {
                connexions[i][j]=false;
            }
        }
        switch (tipus) {
            case 0:
                    for (int i=0; i<ncentrals-1; i++) {
                        connexions[i][i+1]=true;
                        connexions[i+1][i]=true;
                    }
                    break;            
            case 1:
                    for (int i=0; i<ncentrals-1; i=i+2) {
                        connexions[i][i+1]=true;
                        connexions[i+1][i]=true;
                        if (i<ncentrals-2) { 
                            connexions[i][i+2]=true;
                            connexions[i+2][i]=true;
                        }
                    }
                    break;            
            case 2:
                    Vector centr = new Vector(centrals);
              
                    int origen=((node)centr.elementAt(0)).getIdent(),desti;
                    int posx=((node)centr.elementAt(0)).getX(), posy=((node)centr.elementAt(0)).getY();
                    centr.removeElementAt(0);
                    
                    while(centr.size()!=0) {
                        
                            //buscar la central més propera
                            int posmin=0;
                            int x=((node)centr.elementAt(0)).getX(), y=((node)centr.elementAt(0)).getY();
                            int valor=Math.abs(x-posx)+Math.abs(y-posy);
                            
                            for (int i=1;i<centr.size(); i++) {
                                x=((node)centr.elementAt(i)).getX();
                                y=((node)centr.elementAt(i)).getY();
                                
                                if (Math.abs(x-posx)+Math.abs(y-posy)<valor) {
                                    valor=Math.abs(x-posx)+Math.abs(y-posy);
                                    posmin=i;
                                }
                            }
                           
                            desti=((node)centr.elementAt(posmin)).getIdent();

                            connexions[origen][desti]=true;
                            connexions[desti][origen]=true;
                            
                            posx=((node)centr.elementAt(posmin)).getX(); 
                            posy=((node)centr.elementAt(posmin)).getY();
                            
                            origen=desti;
                            
                            centr.removeElementAt(posmin);
                    }
                    
                    break;
            case 3:
                    centr = new Vector(centrals);
                    origen=((node)centr.elementAt(0)).getIdent();
                    posx=((node)centr.elementAt(0)).getX(); posy=((node)centr.elementAt(0)).getY();
                    centr.removeElementAt(0);
                    
                    while(centr.size()!=0) {
                        
                            //buscar la central més propera
                            int posmin=0;
                            int x=((node)centr.elementAt(0)).getX(), y=((node)centr.elementAt(0)).getY();
                            int valor=Math.abs(x-posx)+Math.abs(y-posy);
                            
                            for (int i=1;i<centr.size(); i++) {
                                x=((node)centr.elementAt(i)).getX();
                                y=((node)centr.elementAt(i)).getY();
                                
                                if (Math.abs(x-posx)+Math.abs(y-posy)<valor) {
                                    valor=Math.abs(x-posx)+Math.abs(y-posy);
                                    posmin=i;
                                }
                            }
                            
                            
                            desti=((node)centr.elementAt(posmin)).getIdent();

                            connexions[origen][desti]=true;
                            connexions[desti][origen]=true;
                            centr.removeElementAt(posmin);
                            
                            if (centr.size()!=0) {
                                //buscar la central més propera
                                posmin=0;
                                x=((node)centr.elementAt(0)).getX(); y=((node)centr.elementAt(0)).getY();
                                valor=Math.abs(x-posx)+Math.abs(y-posy);

                                for (int i=1;i<centr.size(); i++) {
                                    x=((node)centr.elementAt(i)).getX();
                                    y=((node)centr.elementAt(i)).getY();

                                    if (Math.abs(x-posx)+Math.abs(y-posy)<valor) {
                                        valor=Math.abs(x-posx)+Math.abs(y-posy);
                                        posmin=i;
                                    }
                                }

                                desti=((node)centr.elementAt(posmin)).getIdent();

                                connexions[origen][desti]=true;
                                connexions[desti][origen]=true;
                                //System.out.println("posmin: "+posmin);
                                posx=((node)centr.elementAt(posmin)).getX(); 
                                posy=((node)centr.elementAt(posmin)).getY();
                                centr.removeElementAt(posmin);
                                origen=desti;
                            }

                    }
                    
                            
                    break;
            case 4:
                    Vector centrep = new Vector(centrals);
                    num_repetidors=max_num_repetidors;
                    for (int i=0; i<max_num_repetidors; i++) {
                        centrep.add(repetidors.elementAt(i));
                    }
                    origen=((node)centrep.elementAt(0)).getIdent();
                    posx=((node)centrep.elementAt(0)).getX(); posy=((node)centrep.elementAt(0)).getY();
                    centrep.removeElementAt(0);
                    
                    while(centrep.size()!=0) {
                        
                            //buscar la central més propera
                            int posmin=0;
                            int x=((node)centrep.elementAt(0)).getX(), y=((node)centrep.elementAt(0)).getY();
                            int valor=Math.abs(x-posx)+Math.abs(y-posy);
                            
                            for (int i=1;i<centrep.size(); i++) {
                                x=((node)centrep.elementAt(i)).getX();
                                y=((node)centrep.elementAt(i)).getY();
                                
                                if (Math.abs(x-posx)+Math.abs(y-posy)<valor) {
                                    valor=Math.abs(x-posx)+Math.abs(y-posy);
                                    posmin=i;
                                }
                            }
                           
                            desti=((node)centrep.elementAt(posmin)).getIdent();

                            connexions[origen][desti]=true;
                            connexions[desti][origen]=true;
                            
                            posx=((node)centrep.elementAt(posmin)).getX(); 
                            posy=((node)centrep.elementAt(posmin)).getY();
                            
                            origen=desti;
                            
                            centrep.removeElementAt(posmin);
                    }
                    break;
        }
        calcularGraus();
        error_total=calcularError();
        
    }
    
    
    //OPERADORS
    
    public void afegirRepetidor(int repetidor,int u, int v) {
        connexions[u][v]=false;
        connexions[v][u]=false;
        connexions[u][ncentrals+repetidor]=true;
        connexions[ncentrals+repetidor][u]=true;
        connexions[v][ncentrals+repetidor]=true;
        connexions[ncentrals+repetidor][v]=true;
        calcularGraus();
        error_total=calcularError();
        num_repetidors++;
    }
    
        public void afegirRepetidor(int repetidor,int u, int v, int w) {
        connexions[u][v]=false;
        connexions[v][u]=false;
        
        connexions[v][w]=false;
        connexions[w][v]=false;
        
        connexions[u][ncentrals+repetidor]=true;
        connexions[ncentrals+repetidor][u]=true;
        connexions[v][ncentrals+repetidor]=true;
        connexions[ncentrals+repetidor][v]=true;
        connexions[w][ncentrals+repetidor]=true;
        connexions[ncentrals+repetidor][w]=true;
        
        calcularGraus();
        error_total=calcularError();
        num_repetidors++;
    }
    
    public void afegirRepetidor(int repetidor,int u, int v, int w, int s) {
        connexions[u][v]=false;
        connexions[v][u]=false;
        
        connexions[v][w]=false;
        connexions[w][v]=false;
        
        connexions[w][s]=false;
        connexions[s][w]=false;
        
        connexions[u][ncentrals+repetidor]=true;
        connexions[ncentrals+repetidor][u]=true;
        connexions[v][ncentrals+repetidor]=true;
        connexions[ncentrals+repetidor][v]=true;
        connexions[w][ncentrals+repetidor]=true;
        connexions[ncentrals+repetidor][w]=true;
        connexions[s][ncentrals+repetidor]=true;
        connexions[ncentrals+repetidor][s]=true;
        
        calcularGraus();
        error_total=calcularError();
        num_repetidors++;
    }
        public void afegirRepetidor(int repetidor,int u, int v, int w, int s, int t) {
        connexions[u][v]=false;
        connexions[v][u]=false;
        
        connexions[v][w]=false;
        connexions[w][v]=false;
        
        connexions[w][s]=false;
        connexions[s][w]=false;
        
        connexions[s][t]=false;
        connexions[t][s]=false;
        
        connexions[u][ncentrals+repetidor]=true;
        connexions[ncentrals+repetidor][u]=true;
        connexions[v][ncentrals+repetidor]=true;
        connexions[ncentrals+repetidor][v]=true;
        connexions[w][ncentrals+repetidor]=true;
        connexions[ncentrals+repetidor][w]=true;
        connexions[s][ncentrals+repetidor]=true;
        connexions[ncentrals+repetidor][s]=true;
        connexions[t][ncentrals+repetidor]=true;
        connexions[ncentrals+repetidor][t]=true;
        
        calcularGraus();
        error_total=calcularError();
        num_repetidors++;
    }
        
    public void treureRepetidor(int repetidor) {
        Vector v=new Vector();
        for (int i=0; i<ncentrals+nrepetidors; i++) {
            if (connexions[repetidor+ncentrals][i]) {
                v.add(new Integer(i));
                connexions[repetidor+ncentrals][i]=false;
                connexions[i][repetidor+ncentrals]=false;
            }
        }
        for (int i=0; i<v.size()-1; i++) {
            connexions[((Integer)v.elementAt(i)).intValue()][((Integer)v.elementAt(i+1)).intValue()]=true;
            connexions[((Integer)v.elementAt(i+1)).intValue()][((Integer)v.elementAt(i)).intValue()]=true;
        }
        
        calcularGraus();
        error_total=calcularError();
        num_repetidors--;
        
    }
    
    public void swap(int a, int b, boolean c) {
        
        boolean aux1[] = new boolean[ncentrals+nrepetidors];
        boolean aux2[] = new boolean[ncentrals+nrepetidors];
        
        for (int i=0; i<ncentrals+nrepetidors; i++) {
            aux1[i]=connexions[a][i];
            aux2[i]=connexions[b][i];
        }
        
        for (int i=0; i<ncentrals+nrepetidors; i++) {
            connexions[a][i]=aux2[i];
            connexions[b][i]=aux1[i];
        }
        for (int i=0; i<ncentrals+nrepetidors; i++) {
            connexions[i][a]=aux2[i];
            connexions[i][b]=aux1[i];
        }
        
        calcularGraus();
        error_total=calcularError();
    }
    
    public void treureAresta(int a, int b) {
        connexions[a][b]=false;
        connexions[b][a]=false;
        calcularGraus();
        error_total=calcularError();
    }
    public void posarAresta(int a, int b) {
        connexions[a][b]=true;
        connexions[b][a]=true;
        calcularGraus();
        error_total=calcularError();
    }
    public Vector finsOnArriba(int u) {
       Vector v1 = new Vector();
       for (int i=0; i<ncentrals+nrepetidors; i++)
          if (connexions[u][i]) {
                  v1.addAll(finsOnArribaRec(i,u));
                  v1.add(new Integer(i));
          }
       return v1;
    }
    // Retorna els elements adjacents menys el que t'ha portat fins aqui
    private Vector finsOnArribaRec(int u, int origen) {
       Vector v1 = new Vector();
       for (int i=0; i<ncentrals+nrepetidors; i++)
          if (connexions[u][i]&&i!=origen) {
                  v1.addAll(finsOnArribaRec(i,u));
                  v1.add(new Integer(i));
          }
       return v1;
    }

    
    /*
    public void moureAresta(int a, int b, int c) {
        
        connexions[a][b]=false;
        connexions[b][a]=false;
        
        connexions[b][c]=true;
        connexions[c][b]=true;
        
        calcularGraus();
        error_total=calcularError();
    }*/
    
    //operacions auxiliars
    
    public void actualitzarParametres(int a, int b , int g,int mnr) {
        alfa=a;
        beta=b;
        gamma=g;
        max_num_repetidors=mnr;
        error_total=calcularError();
    }
    
    private void calcularGraus() {
        for (int i=0; i<ncentrals+nrepetidors; i++) {
            int gr=0;
            for (int j=0; j<ncentrals+nrepetidors; j++) {
                if (connexions[i][j]) gr++;
            }
            grau[i]=gr;
        }
    }
    
    private int calcularError() {
        int error=0;
        for (int i=0;i<ncentrals+nrepetidors;i++) {
            for (int j=i+1; j<ncentrals+nrepetidors;j++) {
                if (connexions[i][j]) {
                    int sumatorigraus=grau[i]+grau[j];
                    int aportacio;
                    if (i<ncentrals&&j<ncentrals) { //dues centrals
                        int x1 = ((node)centrals.elementAt(i)).getX();
                        int y1 = ((node)centrals.elementAt(i)).getY();
                        int x2 = ((node)centrals.elementAt(j)).getX();
                        int y2 = ((node)centrals.elementAt(j)).getY();
                        aportacio=(int)Math.pow((double)(Math.abs(x1-x2)+Math.abs(y1-y2)),(double)alfa);
                        
                    } else if (i>=ncentrals&&j>=ncentrals) { //dos repetidors
                        
                        int x1 = ((node)repetidors.elementAt(i-ncentrals)).getX();
                        int y1 = ((node)repetidors.elementAt(i-ncentrals)).getY();
                        int x2 = ((node)repetidors.elementAt(j-ncentrals)).getX();
                        int y2 = ((node)repetidors.elementAt(j-ncentrals)).getY();
                        aportacio=(int)Math.pow((double)(Math.abs(x1-x2)+Math.abs(y1-y2)),(double)gamma);
                        
                    } else { //una central i un repetidor
                        int x1,x2,y1,y2;
                        if (i<ncentrals) {
                            x1 = ((node)centrals.elementAt(i)).getX();
                            y1 = ((node)centrals.elementAt(i)).getY();
                            x2 = ((node)repetidors.elementAt(j-ncentrals)).getX();
                            y2 = ((node)repetidors.elementAt(j-ncentrals)).getY();
                        } else {
                            x1 = ((node)centrals.elementAt(j)).getX();
                            y1 = ((node)centrals.elementAt(j)).getY();
                            x2 = ((node)repetidors.elementAt(i-ncentrals)).getX();
                            y2 = ((node)repetidors.elementAt(i-ncentrals)).getY();
                        }
                        aportacio=(int)Math.pow((double)(Math.abs(x1-x2)+Math.abs(y1-y2)),(double)beta);
                        
                    }
                    error+=sumatorigraus*aportacio;
                }
            }
        }
        return error;
    }
    
    //GETTERS
    
    public boolean[][] getConnexions() {
        return connexions;
    }
     
    public int[] getGrau() {
        return grau;
    }  
    
    public int getErrorTotal() {
        return error_total;
    }     
    
    public int getNumRepetidors() {
        return num_repetidors;
    }
    
    public String toString() {        
        return "";
    }

    public int getN() {
        return N;
    }
    
    public int getM() {
        return M;
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
        
    public int getMaxNumRepetidors() {
        return max_num_repetidors;
    }
    
    public int getNCentrals() {
        return ncentrals;
    }
    
    public int getNRepetidors() {
        return nrepetidors;
    }
    
    public int[][] getCiutat() {
        return ciutat;
    }
    
    public Vector getCentrals() {
        return centrals;
    }   
    
    public Vector getRepetidors() {
        return repetidors;
    }
    
}

