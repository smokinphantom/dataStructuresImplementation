package graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class Dfs {

	//marked boolean is for tracking the visited vertices and count is for vertices visited from source
	private boolean marked[];
	private int[] edgeTo; //Track the connected vertex
	private int count;
	private int s;
	private int id[]; //
	private int countCC;
	
	public Dfs(Graph G, int s) {
		this.s=s;
		marked = new boolean[G.V()]; //initialize empty boolean array.
		edgeTo = new int[G.V()];
		dfs(G,s);
	}
	
	//Connected Components constructor. It will count the number of connected components.
	public Dfs(Graph G){
		marked = new boolean[G.V()]; //initialize empty boolean array.
		id = new int[G.V()]; //initialize empty boolean array.
		
		for(int i=0;i<G.V();i++){
			if(!marked[i])
			{
				dfs(G,s);
				countCC++;
			}
		}
	}
	
	private void dfs(Graph G,int s){
		marked[s] = true;
		count++;
		id[s] = countCC;
		for(int i:G.adj(s)){
			if(!marked[i]){
				dfs(G,i);
				edgeTo[i]=s;
			}
				
		}
	}
	
	//checks whether 2 vertices are connected or not
	public boolean connected(int v, int w)
	{
		return id[v]==id[w];
	}
	
	//count the number of connected components.
	public int countCC(int v){
		return id[v];
	}
	
	//iterate till we reach source i.e s and push all vertices to the stack. This will give a path from a vertex to 
	//source s
	public Iterable<Integer> pathTo(int v){
		Stack<Integer> st = new Stack<Integer>();
		for(int x=v;x!=s;x=edgeTo[x]){
			st.push(x);
		}
		st.push(s);
		return st;
	}
	
	private int count(){
		return count;
	}

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("/Users/mahmoodcontractor/Downloads/tinyG.txt");
		Scanner scan = new Scanner(file);
		Graph g = new Graph(scan);
		Dfs ds = new Dfs(g,0);
		//if count is equal to number of vertices it means the graph is connected as all vertices have been reached by dfs
		System.out.println("count is:"+ds.count()+"number of vertices:"+g.V());
		//this gives the path from in this eg 7 to source i.e 0 according to the text input.
		ds.pathTo(7);

	}

}
