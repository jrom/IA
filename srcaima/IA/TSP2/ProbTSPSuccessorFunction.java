package IA.TSP2;

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;


import java.util.ArrayList;
import java.util.List;

/**
 * @author Javier Bejar
 *
 */
public class ProbTSPSuccessorFunction implements SuccessorFunction {

    public List getSuccessors(Object aState) {
        ArrayList retVal = new ArrayList();
        ProbTSPBoard board = (ProbTSPBoard) aState;
        ProbTSPHeuristicFunction TSPHF = new ProbTSPHeuristicFunction();

        // No permitimos intercambiar la primera ciudad
        for (int i = 0; i < board.getNCities(); i++) {
            for (int j = i + 1; j < board.getNCities(); j++) {
                ProbTSPBoard newBoard = new ProbTSPBoard(board.getNCities(), board.getPath(), board.getCityPos());

                newBoard.swapCities(i, j);

                double v = TSPHF.getHeuristicValue(newBoard);
                //System.out.println(v);
                String S = new String(i + "|" + j);

                retVal.add(new Successor(S, newBoard));
            }
        }

        return retVal;
    }
}


