package node;

public class SuffixLink {
		
	protected NonLeaf node;
	
	public SuffixLink(NonLeaf n){
		node = n;
		//node.st.counter_by_phase[node.st.fase]++;
	}

	public void setNext(NonLeaf new_node) {
		// TODO Auto-generated method stub
		node = new_node;
	}

	public NonLeaf getNext() {
		// TODO Auto-generated method stub
		return node;
	}
}
