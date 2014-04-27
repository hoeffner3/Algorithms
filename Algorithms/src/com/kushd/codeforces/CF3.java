package com.kushd.codeforces;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CF3 {
	
	private static Map<Integer,Edge> map = new HashMap<Integer,Edge>();
	private static List<Path> pts = new ArrayList<Path>();

	public static void main(String[] args) {
		map.clear();
		pts.clear();
		try {
			String strLine=null;
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				strLine = br.readLine();
				int nn = Integer.parseInt(strLine.split(" ")[0]);
				int mm = Integer.parseInt(strLine.split(" ")[1]);
				if(mm%2!=0){
					System.out.println("No solution");
					return;
				}
				for(int i=0;i<mm;i++){
					strLine = br.readLine();
					Edge edge = new Edge(Integer.parseInt(strLine.split(" ")[0]),Integer.parseInt(strLine.split(" ")[1]));
					map.put(Integer.parseInt(strLine.split(" ")[0]),edge);
					map.put(Integer.parseInt(strLine.split(" ")[1]),edge);
				}
				
				List<Path> paths = process(mm);
				if(paths.isEmpty()){
					System.out.println("No solution");
				}
				for(Path path : paths){
					System.out.println(path.firstNode+" "+path.secondNode+" "+path.thirdNode);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static List<Path> process(int mm) {
		Path[] paths = new Path[mm/2];
		helper(paths,0,getRandomEdge());
		return pts;
	}
	
	private static Edge getRandomEdge(){
		Collection<Edge> edges = map.values();
		if(edges.isEmpty()){
			return null;
		}
		Iterator<Edge> iterator = edges.iterator();
		return iterator.next();
	}
	
	private static void helper(Path[] paths,int index, Edge curEdge){
		if(index == paths.length){
			for(int i=0;i<paths.length;i++){
				pts.add(paths[i]);
			}
			return;
		}
	}
	
}


class Path {
	
	int firstNode;
	int secondNode;
	int thirdNode;
	
	Path(int firstNode, int secondNode, int thirdNode){
		this.firstNode = firstNode;
		this.secondNode = secondNode;
		this.thirdNode = thirdNode;
	}
}

class Edge {
	
	int firstEnd;
	int secondEnd;
	
	Edge(int firstEnd,int secondEnd){
		this.firstEnd = firstEnd;
		this.secondEnd = secondEnd;
	}
}
