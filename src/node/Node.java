package node;

import java.util.HashMap;
import java.util.LinkedList;


public abstract class Node {		   
	    
	    		
	    protected int position;	
	   
	    
		private boolean notRegistered(char c) {
			// TODO Auto-generated method stub
			return false;
		}		
		
		// If the node is a root, returns root. Otherwise, returns the node of its suffixlink.

		
		//public abstract Object [] searchFinalArc(int fase, String beta, Edge last);
		
		//public abstract void print();
		
		//public abstract void labelPrint();
		
		public abstract int  getPosition();
		
		public abstract void getLeavesValues(LinkedList<Integer> positions);
		
		//public abstract HashMap<Character, Edge> getChildren();


		
	
}
