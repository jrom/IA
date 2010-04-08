
package IA.probIA15;


import java.util.ArrayList;
import java.util.List;

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;
/**
 * @author Javier Bejar
 *  
 */
public class ProbIA15SuccessorFunction implements SuccessorFunction {
 
    
  public List getSuccessors(Object aState) {
       ArrayList retVal= new ArrayList();
      ProbIA15Board board=(ProbIA15Board) aState;

      for(int i=0;i<5;i++){
          if (board.puedeDesplazarDerecha(i)){
            ProbIA15Board newBoard= new ProbIA15Board(board.getConfiguration());
            newBoard.desplazarDerecha(i);
            retVal.add(new Successor(new String(ProbIA15Board.DESP_DERECHA+" "+newBoard.toString()),newBoard));
          }
          if (board.puedeDesplazarIzquierda(i)){
            ProbIA15Board newBoard= new ProbIA15Board(board.getConfiguration());
            newBoard.desplazarIzquierda(i);
            retVal.add(new Successor(new String(ProbIA15Board.DESP_IZQUIERDA+" "+newBoard.toString()),newBoard));

          }
          if (board.puedeSaltarUnaDerecha(i)){
            ProbIA15Board newBoard= new ProbIA15Board(board.getConfiguration());
            newBoard.saltarUnaDerecha(i);
            retVal.add(new Successor(new String(ProbIA15Board.SALTO_DERECHA+" "+newBoard.toString()),newBoard));

          }
          if (board.puedeSaltarUnaIzquierda(i)){
            ProbIA15Board newBoard= new ProbIA15Board(board.getConfiguration());
            newBoard.saltarUnaIzquierda(i);
            retVal.add(new Successor(new String(ProbIA15Board.SALTO_IZQUIERDA+" "+newBoard.toString()),newBoard));

          }

      }
          return (retVal);

  }

}