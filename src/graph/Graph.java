package graph;

import java.io.InputStream;
import java.util.Scanner;

public class Graph {
	
	private Bag<Integer>[] bg;
	int V;
	int E;
	

	//will read the scanner object
	public Graph(Scanner scan) {
		//call to the overloaded constructor
		this(scan.nextInt());
		
		//V=scan.nextInt();
		int E = scan.nextInt();
		for(int i =0;i<E;i++){
			int v= scan.nextInt();
			int w= scan.nextInt();
			addEdge(v, w);
		}
		
	}
	
	//Create a new empty adjacency list array from the number of vertices
	public Graph(int V){
		this.V=V;
		this.E=0;
		bg= (Bag<Integer>[]) new Bag[V]; //casting done
		for(int i=0;i<V;i++){
			bg[i]= new Bag<Integer>();
		}
	}
	
	public void addEdge(int v,int w){
		E++;
		bg[v].add(w);
		bg[w].add(v);
	}
	
	public Iterable<Integer> adj(int v){
		return bg[v];
		
	}
	
	public Integer V(){
		return V;
	}
	
	public Integer E(){
		return E;
	}

}
