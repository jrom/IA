/*
 * ProbIA1OrderingFunction.java
 *
 * Created on February 5, 2004, 12:58 PM
 */

package IA.probIA15;


import IA.probIA15.ProbIA15Board;

import aima.search.framework.HeuristicFunction;

/**
 *
 * @author  bejar
 */
public class ProbIA15HeuristicFunction2 implements HeuristicFunction{
    
    
    public double getHeuristicValue(Object n) {
       ProbIA15Board board=(ProbIA15Board)n;
       char [] conf;
       int sum=0,c=0;
       
       conf=board.getConfiguration();
       
       for(int i=0;i<5;i++) if (conf[i]=='B') sum++;
       if (board.nextGap('B')) c=1;
       
       return (sum-c);
    }
    

    public boolean equals(Object obj) {
        boolean retValue;
        
        retValue = super.equals(obj);
        return retValue;
    }
    
}
