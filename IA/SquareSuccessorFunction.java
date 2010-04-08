package IA;

import java.util.List;
import java.util.ArrayList;

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;

public class SquareSuccessorFunction implements SuccessorFunction
{
  public List getSuccessors(Object state)
  {
    SquareBoard board = (SquareBoard) state;
		List<Successor> successors = new ArrayList<Successor>();
		// TODO WORK FUCKING BASTARDS
		
		return successors;
  }
}
