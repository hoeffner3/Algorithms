package com.kushd.graphs;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TSP {
	
	public static void main(String[] args){
		FileInputStream fio=null;
		DataInputStream in=null;
		try {
			fio = new FileInputStream(args[0]);
			in = new DataInputStream(fio);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine=null;
			List<Pnt> points = new ArrayList<Pnt>();
			int cnt=0;
			while( (strLine=br.readLine()) != null ){
				Pnt pnt = parseline(strLine);
				points.add(pnt);
				cnt++;
			}
			double[][] graph = new double[cnt+1][cnt+1];
			for(Pnt pnt : points){
				for(Pnt inpnt : points){
					graph[pnt.index][inpnt.index] = getDist(pnt,inpnt);
				}
			}
			
			TSPImpl tspimpl = new TSPImpl();
			tspimpl.getMP(graph);
			//ATSP atsp = new ATSP();
			//atsp.getMP(graph);
			
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	private static Pnt parseline(String strLine) {
		String[] vals = strLine.split(" ");
		int index = Integer.parseInt((vals[0].trim()));
		int size = vals.length;
		double lon = Double.parseDouble((vals[size-1].trim()).substring(0,vals[size-1].length()-1));
		double lat = Double.parseDouble((vals[size-2].trim()).substring(1,vals[size-2].length()-1));
		
		return new Pnt(index, lat, lon);
	}

	private static double getDist(Pnt pnt, Pnt inpnt) {
		double theDistance = (Math.sin(Math.toRadians(pnt.lat)) *
	            Math.sin(Math.toRadians(inpnt.lat)) +
	            Math.cos(Math.toRadians(pnt.lat)) *
	            Math.cos(Math.toRadians(inpnt.lat)) *
	            Math.cos(Math.toRadians(pnt.lon - inpnt.lon)));

	    return ((Math.toDegrees(Math.acos(theDistance))) * 69.09);
		//return Math.sqrt(Math.pow(pnt.lat-inpnt.lat, 2) + Math.pow(pnt.lon-inpnt.lon, 2));
		
	}
	
	
}

class Pnt {
	int index;
	double lat;
	double lon;
	
	public Pnt(int index, double lat, double lon) {
		this.index = index;
		this.lat = lat;
		this.lon = lon;
	}
	
}

class TSPImpl {
	
	private Map<String, Double> cache;
	private Map<String, String> pCache;
	private double[][] graph;
	private TSPNode[] nodes;
	private int SIZE;
	
	public TSPImpl() {
		this.cache = new HashMap<String, Double>();
		this.pCache = new HashMap<String,String>();
	}
	
	public void getMP(double[][] graph){
		SIZE = graph.length-1;
		this.graph = graph;
		cache.clear();
		pCache.clear();
		nodes = new TSPNode[graph.length];
		for(int i=1;i<graph.length;i++){
			nodes[i] = new TSPNode(false);
		}
		double totaldist = getMinimumPath(1,"1",1);
		
		System.out.println("1");
		String[] values = nodes[1].nextlist.split("/");
		int cnt=0;
		while(cnt<SIZE-1){
			System.out.println(values[cnt]);
			cnt++;
		}
		System.out.println(totaldist);
	}
	
	private String sortString(String s){
		if(s.length()==1){
			return s;
		}
		StringBuilder sb = new StringBuilder();
		sb.append("1");
		char adj = s.charAt(s.length()-1);
		boolean adjusted=false;
		for(int i=2;i<s.length()-2;i=i+2){
			if(adj < s.charAt(i)){
				sb.append("/"+adj);
				adjusted=true;
			}
			sb.append("/"+s.charAt(i));
		}
		if(!adjusted){
			sb.append("/"+adj);
		}
		return sb.toString();
	}
	
	//graph index starts from 1
	private double getMinimumPath(int index, String path, int level ){
		nodes[index].visited=true;
		if(level == SIZE){
			nodes[index].nextlist="";
			nodes[index].visited=false;
			return 0;
		}
		if(cache.get(path)!=null){
			nodes[index].nextlist = pCache.get(path);
			nodes[index].visited=false;
			return cache.get(path);
		}
		
		double min=Double.MAX_VALUE;
		String spath = sortString(path);
		for(int i=1;i<=SIZE;i++){
			if(!nodes[i].visited){
				double curmin = graph[index][i] + getMinimumPath(i,spath+"/"+i,level+1);
				if(min > curmin){
					min = curmin;
					nodes[index].nextlist = i + "/" + nodes[i].nextlist;
				}
			}
		}
		pCache.put(path, nodes[index].nextlist);
		cache.put(path, min);
		nodes[index].visited=false;
		return min;
	}

	
}

class TSPNode {
	boolean visited;
	String nextlist;
	
	public TSPNode(boolean visited) {
		this.visited=false;
	}
	
	
}




class ATSP {
	
	private TSPNode[] nodes;
	private double[][] graph;
	private int SIZE;
	private double totaldist=0;
	
	public void getMP(double[][] graph){
		SIZE = graph.length-1;
		totaldist=0;
		this.graph = graph;
		nodes = new TSPNode[graph.length];
		for(int i=1;i<graph.length;i++){
			nodes[i] = new TSPNode(false);
		}
		nodes[1].visited=true;
		int cur = 1;
		System.out.println(cur);
		int cnt=1;
		while(cnt<SIZE){
			cur = getNext(cur);
			nodes[cur].visited = true;
			System.out.println(cur);
			cnt++;
		}
		System.out.println(totaldist);
	}

	private int getNext(int cur) {
		int next=-1;
		double min = Double.MAX_VALUE;
		for(int i=1;i<=SIZE;i++){
			if(nodes[i].visited==false){
				if(min > graph[cur][i]){
					min = graph[cur][i];
					next = i;
				}
			}
		}
		totaldist=totaldist+min;
		return next;
	}
	
	
}