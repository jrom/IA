
package IA.probAntenas;


import java.util.ArrayList;
import java.util.List;

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;
/**
 * @author Ravi Mohan
 *  
 */
public class ProbAntenasSuccessorFunctionHC implements SuccessorFunction {
 
    
    @SuppressWarnings("unchecked")
  public List getSuccessors(Object aState) {
    ArrayList retVal= new ArrayList();
    ProbAntenasBoard board=(ProbAntenasBoard) aState;
    for(int i=0;i<board.getNumAntenas();i++){
      // Movemos hacia Arriba  
       ProbAntenasBoard newBoardU= new ProbAntenasBoard(board.getNumAntenas(),board.getMaxPotencia(),board.getAntenas(),board.getDimPlano(),board.getPlano());
       if (newBoardU.MoverAntena(i, -1,0)) {
          String S=new String(ProbAntenasBoard.MOVER+" "+i+" Up");
          retVal.add(new Successor(S,newBoardU));
       }
      // Movemos hacia Abajo  
       ProbAntenasBoard newBoardD= new ProbAntenasBoard(board.getNumAntenas(),board.getMaxPotencia(),board.getAntenas(),board.getDimPlano(),board.getPlano());
       if (newBoardD.MoverAntena(i, 1,0)) {
          String S=new String(ProbAntenasBoard.MOVER+" "+i+" Down");
          retVal.add(new Successor(S,newBoardD));
       }
       
      // Movemos hacia la izquierda  
       ProbAntenasBoard newBoardL= new ProbAntenasBoard(board.getNumAntenas(),board.getMaxPotencia(),board.getAntenas(),board.getDimPlano(),board.getPlano());
       if (newBoardL.MoverAntena(i, 0, -1)) {
          String S=new String(ProbAntenasBoard.MOVER+" "+i+" Left");
          retVal.add(new Successor(S,newBoardL));
       }
      // Movemos hacia la derecha 
       ProbAntenasBoard newBoardR= new ProbAntenasBoard(board.getNumAntenas(),board.getMaxPotencia(),board.getAntenas(),board.getDimPlano(),board.getPlano());
       if (newBoardR.MoverAntena(i, 0,1)) {
          String S=new String(ProbAntenasBoard.MOVER+" "+i+ "Right");
          retVal.add(new Successor(S,newBoardR));
       }
       
       ProbAntenasBoard newBoardPU= new ProbAntenasBoard(board.getNumAntenas(),board.getMaxPotencia(),board.getAntenas(),board.getDimPlano(),board.getPlano());
       if (newBoardPU.aumentaPotencia(i)) {
          String S=new String(ProbAntenasBoard.AUMENTAR+" "+i);
          retVal.add(new Successor(S,newBoardPU));
       }
       
       ProbAntenasBoard newBoardPD= new ProbAntenasBoard(board.getNumAntenas(),board.getMaxPotencia(),board.getAntenas(),board.getDimPlano(),board.getPlano());
       if (newBoardPD.disminuyePotencia(i)) {
          String S=new String(ProbAntenasBoard.DISMINUIR+" "+i);
          retVal.add(new Successor(S,newBoardPD));
       }
       
    }
     
     return retVal;
  }

}