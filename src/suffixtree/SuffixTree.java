package suffixtree;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;

import node.*;

public  class SuffixTree {
	
	public int fase = 0;
	
	private NonLeaf root;
	protected String text; // String del cual se sacar�n los sufijos
	private End end;
	public int [] counter_by_phase;
	private SuffixLink sl_aux;
	
	public SuffixTree(String text){
		this.text = text;
		root = new Root();
		end = new End();
		sl_aux = null;
	}	
	

	public NonLeaf getRoot(){
		return root;
	}
	

	
	
	public void ukkonen(){
		
		//text = text.concat("$");
		//counter_by_phase = new int [text.length() + 1];
		//resetCounter();
		
		for(int i=0; i<text.length(); i++){
			end.setEnd(i);
			for(int j=0; j<=i; j++){
				extension(i,j);
			}
		}
	}
	
	public void extension(int i, int j){
		
		if(i==0){

			//System.err.println(text.charAt(i));
			root.addEntry(text.charAt(i), new Edge(i, end, new Leaf()));
			
		}
		
		else if(i==j && i>0){

			if(root.getChildren().get(text.charAt(i))==null){
				root.addEntry(text.charAt(i), new Edge(i, end, new Leaf()));
				
			}
			
		}
		else{//j< i, i>0

			//Existe un camino de j a i
			Edge actual_edge = root.getChildren().get(text.charAt(j));
			checkPath(actual_edge, j, i);
			
		}
		
		
	}
	
	public void checkPath(Edge edge, int j,int i){
		
		Node edge_child = edge.getChild();
		String edge_label = edge.getLabel(text);
		String substr= text.substring(j, i+1);
		
		if(text.charAt(i)=='r' && text.charAt(j)=='i'){
			System.err.println("The rest of the text: "+substr);
			}
		
		
		
		if(edge_child instanceof Leaf){
			
				
				edge_label = edge.getLabel(text).substring(0, i-j+1);
		//System.err.println(edge_label);
				if(!edge_label.equals(substr)){
				//System.err.println("holiii");
					if(text.charAt(i)=='r'){
						System.err.println("Edge label (leaf): "+edge_label);
						System.err.println("Substring path: "+substr);
						}
					NonLeaf new_node = new NonLeaf(root);
				
					int diff = i-j-1;
					int new_end = edge.getStar()+diff;
					edge.setEnd(new End(new_end));
					edge.setChild(new_node);

				
					Edge new_edge_1 = new Edge(new_end+1 ,end,new Leaf());
					Edge new_edge_2 = new Edge(i,end,new Leaf());
				
				
					new_node.addEntry(text.charAt(new_end+1), new_edge_1);
					new_node.addEntry(text.charAt(i), new_edge_2);
				
					if(sl_aux==null){
						sl_aux = new_node.getSuffixLink();
					}
					else{
						sl_aux.setNext(new_node);
						sl_aux = new_node.getSuffixLink();
					}
				}
				return;
			
			}
		else{
				
			if(i-j+1 <= edge_label.length()){
					
				if(!edge_label.equals(substr)){
				
					NonLeaf new_node = new NonLeaf(root);
					int diff = i-j-1;
					
					int new_end = edge.getStar()+diff;
					
					Edge new_edge_1 = new Edge(new_end+1 ,edge.getEnd(), edge_child);
					Edge new_edge_2 = new Edge(i,end,new Leaf());
					edge.setEnd(new End(new_end));
					edge.setChild(new_node);
				
					if(text.charAt(i)=='r' && text.charAt(j)=='i'){
						System.err.println("Edge label (inner, enough) : "+edge_label);
						System.err.println("Substring path: "+substr);
					}
					new_node.addEntry(text.charAt(new_end), new_edge_1);
					new_node.addEntry(text.charAt(i), new_edge_2);
					
					if(sl_aux==null){
						sl_aux = new_node.getSuffixLink();
					}
					else{
						sl_aux.setNext(new_node);
						sl_aux = new_node.getSuffixLink();
					}
					}
						return;
				}

			
			
				
			else {
				
					
					/*El substr es mayor a edge_label*/
					SuffixLink child_sl = ((NonLeaf)edge_child).getSuffixLink();
					char chosen_char = substr.charAt(edge_label.length());
					j=j+edge_label.length();
					Edge chosen_edge = ((NonLeaf)edge_child).getChildren().get(chosen_char);
										NonLeaf next = child_sl.getNext();

					if(text.charAt(i)=='r' && text.charAt(j)=='i'){
						System.err.println("Edge label (inner) : "+edge_label);
						System.err.println("Substring path: "+substr);
					}
					Edge sl_edge =next.getChildren().get(chosen_char);
					
					if(text.charAt(i)=='r' && text.charAt(j)=='i'){
						System.err.println("Chosen edge: "+chosen_edge.getLabel(text));

					}
					
					if(next != root && sl_edge != null){
						checkPath(sl_edge, j,i);
						if(substr.length()<3){
							System.err.println("SuffixLink");
						}
					}
					checkPath(chosen_edge, j, i);

				}
			}
		return;
	}
	

	
	/*public AbsSuffixTree ukkonen(String s) {		
		//root.setName(num);
		
		text = s.concat("$");	
		counter_by_phase = new int [text.length() + 1]; // fase=text.length()-1: '$' ; fase=text.length(): convertToReal
		resetCounter();
		
		counter_by_phase[fase] += 2;
		counter_by_phase[fase] += 3; //Por el constructor de root
		
		root.addChild(new Arc(root, new Leaf(0, this), 0, -1));		// -1 = END
		//labelPrint();
		//System.out.println();
		int finish;
		int op;
		for (fase = 1; fase < text.length(); fase ++) {
			//fase i	
			//System.out.println();
			//System.out.println("fase " + fase);			
			w = null;
			v = root;
			counter_by_phase[fase] += 2;
			
			for (int j = 0; j <= fase; j ++) {		
				/*System.out.println();
				System.out.println();*/
				//System.out.println("j = " + j);		
				//finish = extension(fase, j);
				/*System.out.println("v : " + v.getName());
				if (w != null) System.out.println("w : " + w.getName());
				else System.out.println("w : null");			
				labelPrint();
				System.out.println();
				
				if (finish == 1) break;
			}
			
		}
		return this;
	}*/
	
/*	
	public int extension(int i, int j) {	
		//extensionByRules(i, j, root, text);	
		//Extensi�n normal
		int finish;
		if (j == 0) {			
			finish = extensionByRules(i, j, root, text);					
		}
		//Extensi�n con suffix links
		else {			
			NotLeafNode ini;				
			ini = v.getInitialNode();			
			//System.out.println("gamma= " + gamma);			
			if (v instanceof Root) finish = extensionByRules(i, j, ini, text);
			else {								
				//System.out.println("Se recorre desde " + ini.getName() + " por " + gamma);				
				finish = extensionByRules(i, j, ini, gamma);					
			}
		}		
		// Si se sigui� la regla 2.2 en la extensi�n anterior
		if (w != null) {
			if (count_w == 1) {
				//System.out.println("Se crea SuffixLink entre " + w.getName() + " y " + last.getName());				
				w.setSuffixLink(new SuffixLink(last));
				
				w = null;
				counter_by_phase[fase]++;
			}
			else {
				count_w = 1;
				counter_by_phase[fase]++;
			}
		}
		return finish;
	}
	
public void printST(Node n){
		
		if(n instanceof Leaf) return;
		
		else{
			HashMap<Character, Arc> children_= n.getChildren();
			Iterator<Entry<Character, Arc>> it = children_.entrySet().iterator();
			
			try{
			    PrintWriter writer = new PrintWriter("output/tree-printing.txt", "UTF-8");
			    while(it.hasNext()){
			    	Entry<Character, Arc> pair = it.next();
			    	writer.print("Key character:");
			    	writer.println(pair.getKey());
			    	writer.println("Edge label:");
			    	writer.println(pair.getValue().getKey());
			    	printST(pair.getValue().getChild());
			    	//System.out.println(pair.getValue().getKey());
			    	}
			    writer.close();
				} catch (IOException e) {
				   // do something
				}
		}
		
	}
	
*/	
	/*public abstract int extensionByRules(int i, int j, NonLeaf ini, String s);
	
	public abstract LinkedList<Integer> search(String s, LinkedList<Integer> positions, Node root, String text);*/
	public void printST(Node n){
		
		if(n instanceof Leaf) return;
		
		else{
			HashMap<Character, Edge> children_= ((NonLeaf)n).getChildren();
			Iterator<Entry<Character, Edge>> it = children_.entrySet().iterator();
			
			//try{
			    //PrintWriter writer = new PrintWriter("output/tree-printing.txt", "UTF-8");
			    while(it.hasNext()){
			    	Entry<Character, Edge> pair = it.next();
			    	if(n == root){System.err.println("root!");}
			    	else System.err.println(n.toString());
			    	System.err.println("Key character:");
			    	System.err.println(pair.getKey());
			    	System.err.println("Edge label:");
			    	System.err.println(pair.getValue().getLabel(text));
			    	System.err.println("---------------------------------------------");
			    	printST(pair.getValue().getChild());
			    	
			    	//System.out.println(pair.getValue().getKey());
			    	}
			   // writer.close();
				//} catch (IOException e) {
				   // do something
				//}*/
		}
		
	}
	
}