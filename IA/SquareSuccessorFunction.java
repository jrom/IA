package IA;

import java.util.List;

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;

public class SquareSuccessorFunction implements SuccessorFunction
{
  public List getSuccessors(Object state)
  {
    SquareBoard board = (SquareBoard) state;
		List<Successor> successors;
		successors = board.getSuccessors();
    // System.out.println("Successor list size: " + successors.size());
    // for(int i = 0; i < successors.size(); i++)
    //   System.out.println(successors.get(i).getState());
		return successors;
  }
}
