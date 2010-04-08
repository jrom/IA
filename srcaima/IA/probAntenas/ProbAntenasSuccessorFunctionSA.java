package IA.probAntenas;

import java.util.ArrayList;
import java.util.List;

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;
import java.util.Random;

/**
 * @author Ravi Mohan
 *  
 */
public class ProbAntenasSuccessorFunctionSA implements SuccessorFunction {

    @SuppressWarnings({"unchecked", "unchecked"})
    public List getSuccessors(Object aState) {
        ArrayList retVal = new ArrayList();
        ProbAntenasBoard board = (ProbAntenasBoard) aState;
        Random myRandom = new Random();


        boolean done = false;

        while (!done) {
            int op = myRandom.nextInt(6);
        int i = myRandom.nextInt(board.getNumAntenas());
            switch (op) {
                case 0:
                    // Movemos hacia Arriba
                    ProbAntenasBoard newBoardU = new ProbAntenasBoard(board.getNumAntenas(), board.getMaxPotencia(), board.getAntenas(), board.getDimPlano(), board.getPlano());
                    if (newBoardU.MoverAntena(i, -1, 0)) {
                        String S = new String(ProbAntenasBoard.MOVER + " " + i + " Up");
                        retVal.add(new Successor(S, newBoardU));
                        done = true;
                    }
                    break;
                case 1:
                    // Movemos hacia Abajo
                    ProbAntenasBoard newBoardD = new ProbAntenasBoard(board.getNumAntenas(), board.getMaxPotencia(), board.getAntenas(), board.getDimPlano(), board.getPlano());
                    if (newBoardD.MoverAntena(i, 1, 0)) {
                        String S = new String(ProbAntenasBoard.MOVER + " " + i + " Down");
                        retVal.add(new Successor(S, newBoardD));
                        done = true;
                    }
                    break;
                case 2:
                    // Movemos hacia la izquierda
                    ProbAntenasBoard newBoardL = new ProbAntenasBoard(board.getNumAntenas(), board.getMaxPotencia(), board.getAntenas(), board.getDimPlano(), board.getPlano());
                    if (newBoardL.MoverAntena(i, 0, -1)) {
                        String S = new String(ProbAntenasBoard.MOVER + " " + i + " Left");
                        retVal.add(new Successor(S, newBoardL));
                        done = true;
                    }
                    break;
                case 3:
                    // Movemos hacia la derecha
                    ProbAntenasBoard newBoardR = new ProbAntenasBoard(board.getNumAntenas(), board.getMaxPotencia(), board.getAntenas(), board.getDimPlano(), board.getPlano());
                    if (newBoardR.MoverAntena(i, 0, 1)) {
                        String S = new String(ProbAntenasBoard.MOVER + " " + i + "Right");
                        retVal.add(new Successor(S, newBoardR));
                        done = true;
                    }
                    break;
                case 4:
                    ProbAntenasBoard newBoardPU = new ProbAntenasBoard(board.getNumAntenas(), board.getMaxPotencia(), board.getAntenas(), board.getDimPlano(), board.getPlano());
                    if (newBoardPU.aumentaPotencia(i)) {
                        String S = new String(ProbAntenasBoard.AUMENTAR + " " + i);
                        retVal.add(new Successor(S, newBoardPU));
                        done = true;
                    }
                    break;
                case 5:
                    ProbAntenasBoard newBoardPD = new ProbAntenasBoard(board.getNumAntenas(), board.getMaxPotencia(), board.getAntenas(), board.getDimPlano(), board.getPlano());
                    if (newBoardPD.disminuyePotencia(i)) {
                        String S = new String(ProbAntenasBoard.DISMINUIR + " " + i);
                        retVal.add(new Successor(S, newBoardPD));
                        done = true;
                    }
                    break;



            }
        }

        return retVal;
    }
}
