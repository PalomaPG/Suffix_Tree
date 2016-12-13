package node;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;

public class NonLeaf extends Node {
	
	int name;
    protected HashMap<Character, Edge> children;
    private SuffixLink sl;
    
    public NonLeaf(){			
		children = new HashMap<Character, Edge>();
		//st.counter_by_phase[st.fase]++;
		sl = null;
	}
    
    public NonLeaf(NonLeaf node){
    	children =  new HashMap<Character, Edge>();
    	sl = new SuffixLink(node);
    }
    
    public void addEntry(char c , Edge edge){
    	children.put(c, edge);
    }
    
   /* public void replaceEnd(int end) {
    	Edge chld;
    	for (Character start: children.keySet()) {
    		chld = children.get(start);
    		st.counter_by_phase[st.fase]++;
    		
    		if (chld.getEndIndex() == -1) {
    			st.counter_by_phase[st.fase]++;
    			chld.setEndIndex(st.getText().length() - 1);
    		}
    		else {
    			((NonLeaf)chld.getChild()).replaceEnd(end); 
    		}					
		}				
	}*/
    
    public boolean notRegistered(char c) {
		return children.get(c) == null;
	}
    
    
    /*public void addChild(Edge edge){
		children.put(st.getText().charAt(edge.getLabel()[0]), edge);
		st.counter_by_phase[st.fase]++;
	}
	
	public void removeChild(Edge edge) {
		children.remove(st.getText().charAt(edge.getLabel()[0]));
		st.counter_by_phase[st.fase]++;
	}
    
    public void setName(int n) {
		name = n;		
	}
	
	public int getName() {		
		return name;		
	}*/
	
	public HashMap<Character, Edge> getChildren(){
		//st.counter_by_phase[st.fase]++;
		return children;
	}
	
    
	
	/* [Arc, position] 
	 * position = -1: se termin� el recorrido en un nodo interno
	 * position = -2: se termin� el recorrido en una hoja
	 * position = p: se termin� el recorrido en medio del arco en la posici�n p de la llave de ese arco
	  */	 
	/*
	 * public Object [] searchFinalArc(int fase, String beta, Edge last) {
	
		Object [] res = new Object[2];	
		int g = beta.length();			
		st.counter_by_phase[st.fase] += 2;
		
		
		// Se termina beta en un nodo interno
		if (beta.equals("")) {
			res[0] = last;
			res[1] = -1;
			st.counter_by_phase[st.fase] += 2;
			
			st.setGamma(beta);
			return res;
		}
		
		st.setV(this);	
		Edge chld = null;
		st.counter_by_phase[st.fase]++;
		
		// Buscamos el arco que siga el camino de beta
		HashMap<Character, Edge> chldrn = getChildren();			
		
		if (chldrn.containsKey(beta.charAt(0))) {
			chld = chldrn.get(beta.charAt(0));
			st.counter_by_phase[st.fase]++;
			
		}
		else { 			
			res[0] = null;
			res[1] = -3;
			st.counter_by_phase[st.fase] += 2;
			
			return res;
		}
		
		int gg;
		int chld_start = chld.getLabel()[0];
		st.counter_by_phase[st.fase]++;
		
		int chld_fin = chld.getLabel()[1];
		st.counter_by_phase[st.fase]++;
		
		if (chld_fin == -1) gg = (fase - 1) - chld_start + 1;
		else gg = chld_fin - chld_start + 1;		
		st.counter_by_phase[st.fase]++;
				
		// Se pasa al nodo siguiente directamente.
		if (gg <= g) {			
			return chld.getChild().searchFinalArc(fase, beta.substring(gg), chld);
		}
		
		// Beta termina en mitad de un arco
		else {
			res[0] = chld;
			res[1] = g;
			st.counter_by_phase[st.fase] += 2;
			
			st.setGamma(beta);
			return res;
		}	 
	}
		
*/	
	
	

	
	
	/*public void print() {
		System.out.println();
		System.out.println("Node " + name);
		System.out.print("( ");
		Edge chld;
		for (Character start: children.keySet()) {
			chld = children.get(start);
			if (chld.getLabel()[1] == -1) {
				System.out.print("[" + st.getText().substring(chld.getLabel()[0], st.getText().length()) + "] ");
			}
			else {
				System.out.print("[" + st.getText().substring(chld.getLabel()[0], chld.getLabel()[1] + 1) + "] ");
			}
		}					
		System.out.println(")");
		for (Character start: children.keySet()) {
			chld = children.get(start);
			chld.child.print();			
		}			
	}*/
	
		
	/*public void labelPrint() {
		System.out.println();
		System.out.println("Node " + name);
		System.out.print("( ");
		Edge chld;
		for (Character start: children.keySet()) {
			chld = children.get(start);
			System.out.print("[" + chld.getLabel()[0] + "," + chld.getLabel()[1] + "] ");
		}					
		System.out.println(")");
		for (Character start: children.keySet()) {
			chld = children.get(start);
			chld.child.labelPrint();			
		}			
	}*/
	
	
	@Override
	public void getLeavesValues(LinkedList<Integer> positions) {
		// TODO Auto-generated method stub
		Iterator<Entry<Character, Edge>> it = children.entrySet().iterator();
		while(it.hasNext()){
			Entry<Character, Edge> pair = it.next();
			pair.getValue().child.getLeavesValues(positions);
		}
		
		
	}




	/*@Override
	public Object[] searchFinalArc(int fase, String beta, Edge last) {
		// TODO Auto-generated method stub
		return null;
	}*/


	@Override
	public int getPosition() {
		// TODO Auto-generated method stub
		return 0;
	}

	public SuffixLink getSuffixLink() {
		// TODO Auto-generated method stub
		return sl;
	}
	

}
