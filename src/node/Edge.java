package node;

import java.util.LinkedList;

public class Edge {

	protected NonLeaf parent;
	private int start;
	private End end;
	protected Node child;
	//protected StringBuffer key;	
	protected int [] label;
	
	
	
	/***
	 * 
	 * @param p
	 * @param c
	 * @param start
	 * @param fin
	 */
	
	public Edge(int start, End end, Node node){
		this.start = start;
		this.end =end;
		this.child = node;
	}
	
	public Edge(NonLeaf p, Node c, int start, int fin){		
			parent = p;
			child = c;
			label = new int[2];
			label[0] = start;
			label[1] = fin;		
			
	}
	
	public Node getChild() {
		//parent.st.counter_by_phase[parent.st.fase]++;
		return child;		
	}
	

	
	/*public String getKey() {
		parent.st.counter_by_phase[parent.st.fase]++;
		return child.st.getText().substring(label[0], label[1] + 1);
	}
	*/
	/*public void extendKey(char c){
		key.append(c);
	}*/
	
	public boolean notInList(LinkedList<Edge> children) {
		// TODO Auto-generated method stub
		return false;
	}
	

	public String getLabel(String text) {
		// TODO Auto-generated method stub
		return text.substring(start, end.getEnd()+1);
	}
	
	public void setEnd(End end){
		this.end = end;
	}
	/*public String toString(){
		return child.st.getText().substring(label[0], label[1] + 1);
	}*/



	public int getStar() {
		// TODO Auto-generated method stub
		return start;
	}

	public void setChild(NonLeaf new_node) {
		// TODO Auto-generated method stub
		child = new_node;
	}

	public End getEnd() {
		// TODO Auto-generated method stub
		return end;
	}
	
}