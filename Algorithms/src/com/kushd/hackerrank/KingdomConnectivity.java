package com.kushd.hackerrank;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class KingdomConnectivity {
	public static void main(String[] args) {
		
	
	try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String strLine = null;
			strLine = br.readLine();
			int nn = Integer.parseInt(strLine.split(" ")[0]);
			int ee = Integer.parseInt(strLine.split(" ")[1]);
			KGraph kgraph = new KGraph(nn,0,nn-1);
			for(int i=0;i<nn;i++){
				kgraph.addNode(i);
			}
			for(int i=0;i<ee;i++){
				strLine = br.readLine();
				int u = Integer.parseInt(strLine.split(" ")[0]);
				int v = Integer.parseInt(strLine.split(" ")[1]);
				kgraph.addEdge(u-1, v-1);
			}
			
			
			String ans = kgraph.findPaths();
			System.out.println(ans);
	} catch (Exception e) {
		e.printStackTrace();
	}
	}
}


class KGraph {
	
	private KVertex[] nodes;
	private int src;
	private int dest;
	
	public KGraph(int N, int src, int dest){
		this.nodes = new KVertex[N];
		this.src = src;
		this.dest = dest;
	}
	
	public void addNode(int id){
		nodes[id] = new KVertex();
	}
	
	public void addEdge(int u,int v){
		nodes[u].adj.add(new KEdge(v));
	}
	
	private static long MOD = 1000000000;
	
	private static int addMod(long a, long b){
		return (int) ((a+b)%MOD);
	}
	
	
	
	public String findPaths(){
		boolean ans = isCycle(src);
		if(ans){
			return "INFINITE PATHS";
		}else{
			return Integer.toString(calPaths(src));
		}
	}
	
	private int calPaths(int u){
		if(u==dest){
			return 1;
		}
		if(nodes[u].nofPaths!=-1){
			return nodes[u].nofPaths;
		}
		int ans=0;
		List<KEdge> adj = nodes[u].adj;
		for(KEdge edge : adj){
			if(nodes[edge.target].dirty == false){
				ans = addMod(ans,calPaths(edge.target));
			}
		}
		nodes[u].nofPaths = ans;
		return ans;
	}
	
	private boolean isCycle(int u){
		nodes[u].stvisited = true;
		//
			nodes[u].visited = true;
			if(u==dest){
				nodes[u].stvisited = false;
				return false;
			}
			List<KEdge> adj = nodes[u].adj;
			for(KEdge edge : adj){
				if(nodes[edge.target].stvisited){
					if(markDirty(edge.target)){
						nodes[u].stvisited = false;
						return true;
					}
				}
				if(nodes[edge.target].visited == false){
					if(isCycle(edge.target)){
						nodes[u].stvisited = false;
						return true;
					}
				}
			}
		//
		nodes[u].stvisited = false;
		return false;
	}
	
	private boolean markDirty(int start){
		nodes[start].dirty = true;
		nodes[start].visited = true;
		if(start==dest){
			return true;
		}
		List<KEdge> adj = nodes[start].adj;
		for(KEdge edge : adj){
			if(nodes[edge.target].dirty == false){
				if(markDirty(edge.target)){
					return true;
				}
			}
		}
		return false;
	}
	
}

class KVertex {
	
	public int nofPaths;
	public KVertex prev;
	public List<KEdge> adj;
	public boolean visited;
	public boolean stvisited;
	public boolean dirty;
	
	public KVertex() {
		this.adj = new ArrayList<KEdge>();
		this.visited = false;
		this.stvisited = false;
		this.dirty = false;
		this.nofPaths = -1;
	}
}

class KEdge {
	
	public int target;
	
	public KEdge(int target) {
		this.target = target;
	}
}
