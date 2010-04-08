package IA.probAntenas;

import java.util.Random;

public class ProbAntenasBoard {
    
  public class Antena {
      int x,y,pot;
      
      public Antena(){
         x=-1;
         y=-1;
         pot=0;
      }
      
      public void setCoordX(int i){
          x=i;
      }
      
      public void setCoordY(int i){
          y=i;
      }
      
      public void setPotencia(int i){
          pot=i;
      }
      
      public int getCoordX(){
          return(x);
      }
      
      public int getCoordY(){
          return(y);
      }
     
      public int getPotencia(){
          return(pot);
      }
      
  }

  private static int MAXPOTENCIA=5;
  private static int OCUPACION=2;
  public static String MOVER = "Mover";
  public static String AUMENTAR = "Mas Potencia";
  public static String DISMINUIR = "Menos Potencia";
  
  private int nant;
  private Antena [] ant;
  private int maxpot;
  private int tp;
  private int [][] plano;
  private Random r= new Random();
  
  private int lrandom(int l){
     return(Math.abs(r.nextInt())%l);
  }
  
  public ProbAntenasBoard(int n,int p, int t) {

    maxpot=p;
    nant=n;
    ant= new Antena[nant];
    for (int i=0;i<nant;i++) ant[i]=new Antena();
    tp=t;
    plano=new int[tp][tp];
    for(int i=0;i<tp;i++)
     for(int j=0;j<tp;j++)
       if (lrandom(OCUPACION)==0) plano[i][j]=1;
       else plano[i][j]=0;
  }
  
  public ProbAntenasBoard(int na, int mp, Antena []a, int t, int [][]p){
   maxpot=mp;
   nant=na;
   tp=t;
   ant= new Antena[nant];
   for (int i=0;i<nant;i++) ant[i]=new Antena();
   plano= new int[tp][tp];
   
   for(int i=0;i<nant;i++){
      ant[i].setCoordX(a[i].getCoordX());
      ant[i].setCoordY(a[i].getCoordY());
      ant[i].setPotencia(a[i].getPotencia());
   }
   
    for(int i=0;i<tp;i++)
     for(int j=0;j<tp;j++)
       plano[i][j]=p[i][j];
  }
  
public boolean libreDeAntena(int x, int y){
  boolean libre=true;
  
  for (int i=0;i<nant;i++)
      libre=libre&& ((ant[i].getCoordX()!=x)&&(ant[i].getCoordY()!=y));
  
  return(libre);
}  
  

public void solucionInicial(){
   boolean fin;
   int x,y;
   x=0;
   y=0;
   for(int i=0;i<nant;i++){
       fin=false;
       while (!fin){
        if(y==tp) fin=true;
        if ((!fin)&&(plano[x][y]!=1)&&libreDeAntena(x,y)){
          ant[i].setCoordX(x);
          ant[i].setCoordY(y);
          ant[i].setPotencia(0);
          fin=true;
        }
          x=x+1;
          if (x==tp) {
              x=0;
              y=y+1;
          }
          if(y==tp) fin=true;
       }
   }
 }
  
 public int getNumAntenas(){
     return(nant);
 }
 
 public int getMaxPotencia(){
     return(maxpot);
 }
 
 public Antena [] getAntenas(){
     return(ant);
 }
 
 public int getDimPlano(){
     return(tp);
 }
 
 public int [][] getPlano(){
     return(plano);
 }
  
/* Aumenta la potencia de la antena i hasta que se
  obtenga mayor cobertura */
public boolean aumentaPotencia(int i){
  boolean fin=false;
  int p=ant[i].getPotencia();
  int c=calculaCobertura();
  p++;
  while (p<MAXPOTENCIA && !fin){
   ant[i].setPotencia(p);
   if (c<calculaCobertura()) fin=true;
   p++;
  }  
  return(fin);
}  

/*
 *Es poco probable que obtengamos mejor cobertura disminuyendo 
 *la potencia, pero si el heuristico tiene encuenta el numero de antenas
 *esto lo mejoraria
 */
public boolean disminuyePotencia(int i){
  int p=ant[i].getPotencia();
  if (p>0) ant[i].setPotencia(p-1);  
  return(p>0);
} 

public void quitaAntena(int i){
  ant[i].setCoordX(-1);
  ant[i].setCoordY(-1);
  ant[i].setPotencia(0);
}

public void colocaAntena(int i, int x, int y){
  ant[i].setCoordX(x);
  ant[i].setCoordY(y);
}

/* Intenta mover una antena siguiendo la direccion indicada por
 dx y dy hasta que haya un cambio en la potencia
 */
public boolean MoverAntena(int i, int dx, int dy){
 int x,y,xi,yi;
 boolean fin;
 int c;
 
  xi= ant[i].getCoordX();
  yi= ant[i].getCoordY();
  
  x=xi+dx;
  y=yi+dy;
  fin=false;
  c=calculaCobertura();
  while ((x>=0)&&(x<tp)&&(y>=0)&&(y<tp)&&(!fin)){
    if(plano[x][y]!=1){  
     colocaAntena(i,x,y);  
     fin=(c<calculaCobertura());
    }
    x=x+dx;
    y=y+dy;
  }    
  return(fin);
}

public void printCobertura(){
  int cob=0;
  int pot;
  int [][] pl;
    int x,y;
  
  pl=new int [tp][tp];
  for(int i=0;i<tp;i++)
    for(int j=0;j<tp;j++)
      pl[i][j]=0;

  for (int i=0;i<nant;i++){
     pot=ant[i].getPotencia();
     if (pot>0) {
        x= ant[i].getCoordX();
        y= ant[i].getCoordY();
        for(int k=x-(pot-1);k< x+pot;k++)
         for(int l=y-(pot-1);l< y+pot;l++)
           if((k>=0)&&(k<tp)&&(l>=0)&&(l<tp))
              pl[k][l]=1;
     }
  }
  
  for(int i=0;i<tp;i++)
    for(int j=0;j<tp;j++)
      cob=cob+pl[i][j];

  for(int i=0;i<tp;i++){
    for(int j=0;j<tp;j++) 
      System.out.print(pl[i][j]);
    System.out.println();
  }
}

public int calculaCoberturaPerdida(){
  int cob=calculaCobertura();
  int cobTeorica=0;
  int pot=0;
  
  for (int i=0;i<nant;i++){
      pot=(ant[i].getPotencia()*2)-1;
      cobTeorica=cobTeorica+(pot*pot);
  }
  
  return(cobTeorica-cob);  
}

public int calculaCobertura(){
  int cob=0;
  int pot;
  int [][] pl;
  int x,y;
  
  pl=new int [tp][tp];
  for(int i=0;i<tp;i++)
    for(int j=0;j<tp;j++)
      pl[i][j]=0;

  for (int i=0;i<nant;i++){
     pot=ant[i].getPotencia();
        x= ant[i].getCoordX();
        y= ant[i].getCoordY();
        for(int k=x-(pot-1);k< x+pot;k++)
         for(int l=y-(pot-1);l< y+pot;l++)
           if((k>=0)&&(k<tp)&&(l>=0)&&(l<tp))
              pl[k][l]=1;
  }
  
  for(int i=0;i<tp;i++)
    for(int j=0;j<tp;j++)
      cob=cob+pl[i][j];
  
  return(cob);
}

public int antenasEfectivas(){
  int ae=0;
  
  for(int i=0;i<nant;i++) 
    if (ant[i].getPotencia()>0) ae++;
  return(ae);
}

public int potenciaEfectiva(){
  int pe=0;
  
  for(int i=0;i<nant;i++) 
    pe=pe+ant[i].getPotencia();
  return(pe);
}

public void printAntenas(){
    for (int i=0;i<nant;i++)
        System.out.println(ant[i].getCoordX()+" "+ ant[i].getCoordY()+" "+ant[i].getPotencia());
}

public String antenaToString(int i){
 return(" X="+ant[i].getCoordX()+" Y="+ ant[i].getCoordY()+" P="+ant[i].getPotencia());
}

public int [][] getState(){
  int [][] pl;
  int x,y;
  int pot;
  
  pl=new int [tp][tp];
  for(int i=0;i<tp;i++)
    for(int j=0;j<tp;j++)
      pl[i][j]=plano[i][j];    

  for(int i=0;i<nant;i++)
      if (ant[i].getPotencia()!=0) pl[ant[i].getCoordX()][ant[i].getCoordY()]=2;
  
   for (int i=0;i<nant;i++){
     pot=ant[i].getPotencia();
        x= ant[i].getCoordX();
        y= ant[i].getCoordY();
        for(int k=x-(pot-1);k< x+pot;k++)
         for(int l=y-(pot-1);l< y+pot;l++)
           if((k>=0)&&(k<tp)&&(l>=0)&&(l<tp))
              if (pl[k][l]==0) pl[k][l]=3;
  }
     
 return(pl);
}




  public boolean isGoalState(){
     return (false);
  }

}
