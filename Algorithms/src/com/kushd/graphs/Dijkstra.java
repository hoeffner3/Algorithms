package com.kushd.graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

public class Dijkstra {
	
	
	
	public static void computePaths(Vertex source){
		
		source.minDistance = 0;
		PriorityQueue<Vertex> pqueue = new PriorityQueue<Vertex>();
		pqueue.add(source);
		
		while(!pqueue.isEmpty()){
			Vertex current = pqueue.poll();
			
			for(Edge e : current.adj){
				Vertex v = e.target;
				Double distance2v = current.minDistance + e.weight;
				if(distance2v  < v.minDistance){
					pqueue.remove(v);
					v.minDistance = distance2v;
					v.prev = current;
					pqueue.add(v);
				}
			}
		}
		
	}
	
	public static List<Vertex> getShortestPathTo(Vertex target){
		List<Vertex> path = new ArrayList<Vertex>();
		for(Vertex v = target; v!=null;v = v.prev){
			path.add(v);
		}
		Collections.reverse(path);
		return path;
		
	}
	
	public static void main(String[] args){
		 Vertex v0 = new Vertex("Redvile");
			Vertex v1 = new Vertex("Blueville");
			Vertex v2 = new Vertex("Greenville");
			Vertex v3 = new Vertex("Orangeville");
			Vertex v4 = new Vertex("Purpleville");

			v0.adj = new Edge[]{ new Edge(v1, 5),
			                             new Edge(v2, 10),
		                               new Edge(v3, 8) };
			v1.adj = new Edge[]{ new Edge(v0, 5),
			                             new Edge(v2, 3),
			                             new Edge(v4, 7) };
			v2.adj = new Edge[]{ new Edge(v0, 10),
		                               new Edge(v1, 3) };
			v3.adj = new Edge[]{ new Edge(v0, 8),
			                             new Edge(v4, 2) };
			v4.adj = new Edge[]{ new Edge(v1, 7),
		                               new Edge(v3, 2) };
			
			Vertex[] vertices = {v0,v1,v2,v3,v4};
			computePaths(v0);
			for(Vertex v : vertices){
				System.out.println(v.minDistance);
			}
			
			for(Vertex v : vertices){
				System.out.println("Paths");
				Iterator<Vertex> itr = (getShortestPathTo(v)).iterator();
				while(itr.hasNext()){
					System.out.println((itr.next()).name);
				}
				System.out.println("");
			}
			
	}
	
	
}


class Vertex implements Comparable<Vertex>{

	public final String name;
	public Edge[] adj;
	public double minDistance = Double.POSITIVE_INFINITY;
	public Vertex prev;
	
	
	public Vertex(String argName){
		name = argName;
	}
	
	@Override
	public int compareTo(Vertex o) {
		
		return Double.compare(minDistance, o.minDistance);
	}
	
	
}

class Edge {
	
	public final Vertex target;
	public final double weight;
	
	public Edge(Vertex t, double w){
		target = t;
		weight = w;
	}
	
}