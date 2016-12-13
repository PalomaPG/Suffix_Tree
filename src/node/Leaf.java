package node;

import java.util.HashMap;
import java.util.LinkedList;

import suffixtree.*;


public class Leaf extends Node {

	protected int position;
	
	public Leaf(){
		position = -1;
		//st.counter_by_phase[st.fase]++;
		
	}
	
	@Override
	public void getLeavesValues(LinkedList<Integer> positions) {
		// TODO Auto-generated method stub
		positions.add(position);
	}



	@Override
	public int getPosition() {
		// TODO Auto-generated method stub
		return position;
	}
	

	
}
