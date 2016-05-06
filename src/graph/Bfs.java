package graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class Bfs {

	private boolean marked[];
	private int[] edgeTo; //Track the connected vertex
	private int[] shortestPath;
	private int s;
	
	
	public Bfs(Graph G, int s) {
		this.s = s;
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		shortestPath = new int[G.V()];
		bfs(G,s);
		// TODO Auto-generated constructor stub
	}

	//Iterative bfs using queue
	private void bfs(Graph g, int s) {
		Queue<Integer> qs = new Queue<Integer>();
		for(int i=0;i<g.V();i++){
			shortestPath[i]=Integer.MAX_VALUE;
		}
		marked[s]=true;
		qs.enqueue(s);
		shortestPath[s]=0;
		while(!qs.isEmpty()){
			int temp = qs.dequeue();
			for(int j:g.adj(temp)){
				if(!marked[j]){
					marked[j]=true;
					edgeTo[j]=temp;
					shortestPath[j]=shortestPath[temp]+1;
					qs.enqueue(j);
				}
			}
		}	
	}
	
	//Returns the shortest path since it is bfs, compare this method to dfs.
	public Iterable<Integer> pathTo(int v){
		Stack<Integer> st = new Stack<Integer>();
		for(int x=v;x!=s;x=edgeTo[x]){
			st.push(x);
		}
		st.push(s);
		return st;
	}
	

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		
		File file = new File("/Users/mahmoodcontractor/Downloads/tinyG.txt");
		Scanner scan = new Scanner(file);
		Graph g = new Graph(scan);
		Bfs bs = new Bfs(g,0);
		bs.pathTo(8);

	}

}
