/*
 * Prob15Board.java
 *
 * Created on January 29, 2004, 1:30 PM
 */

package IA.probIA15;

/**
 *
 * @author  bejar
 */
public class ProbIA15Board {
    
    
    public static String DESP_DERECHA = "Desplazar Derecha";
    public static String DESP_IZQUIERDA = "Desplazar Izquierda";
    public static String SALTO_DERECHA = "Saltar Derecha";
    public static String SALTO_IZQUIERDA = "Saltar Izquierda";

    private char []  board = {'N','N','B','B','O'};
    
    /** Creates a new instance of ProbIA15Board */
    public ProbIA15Board() {
    }
    
    public ProbIA15Board(char[] b) {
      for(int i=0;i<5;i++) board[i]=b[i];
    }
    
    public char [] getConfiguration(){
         return board;
    }
    
    private char getPos(int i){
      return(board[i]);
    }
    
    public int getGap(){
      int v=0;
      
      for (int i=0;i<5;i++) if (board[i]=='O') v=i;
      
      return v;
    }

    // indica si hay una ficha de tipo especifico junto al blanco
    public boolean nextGap(char c){
      int p;
      boolean fc=false; 

      p=getGap();
  
      if (p<4) fc=(board[p+1]==c);
      if (p>0) fc=fc || (board[p-1]==c);

      return(fc);

    }
    
    public boolean puedeDesplazarDerecha(int i) {
      if (i==4) return(false);
      else return(board[i+1]=='O');
    }
    
    public boolean puedeDesplazarIzquierda(int i){
      if (i==0) return(false);
      else return(board[i-1]=='O');
    }
    
    public boolean puedeSaltarUnaDerecha(int i) {
      if ((i==3)||(i==4)) return(false);
      else return((board[i+2]=='O') && (board[i]!=board[i+1]));
    }
    
    public boolean puedeSaltarUnaIzquierda(int i){
      if ((i==1)||(i==0)) return(false);
      else return((board[i-2]=='O') && (board[i]!=board[i-1]));
    }
    
    
    public void desplazarDerecha(int i){
       board[i+1]=board[i];
       board[i]='O';
    }
    
    public void desplazarIzquierda(int i){
       board[i-1]=board[i];
       board[i]='O';
    }   
    
    public void saltarUnaDerecha(int i){
       board[i+2]=board[i];
       board[i+1]=board[i];
       board[i]='O';
    }
    
    public void saltarUnaIzquierda(int i){
       board[i-2]=board[i];
       board[i-1]=board[i];
       board[i]='O';
    }
        
    public boolean equals(Object o){
     int i;
     boolean iguales=true;
     ProbIA15Board b2= (ProbIA15Board) o;
     
      for(i=0;i<5;i++) iguales= iguales && (b2.getPos(i)==this.getPos(i));
     return (iguales);
    }
    
    public boolean isGoal(){
     boolean noblanco=true;
     
     for(int i=0;i<5;i++) noblanco=noblanco && (board[i]!='B');
     return noblanco;
    }
    
    public String toString(){
       String retVal="|"+board[0]+"|"+board[1]+"|"+board[2]+"|"+board[3]+"|"+board[4]+"|";
       return retVal;
    }
}
