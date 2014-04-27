package com.kushd.hackerrank;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

public class AStarImpl {
	
	public static void main(String[] args) {
        try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String strLine = null;
			
				strLine = br.readLine();
				int nn = Integer.parseInt(strLine.split(" ")[0]);
				int mm = Integer.parseInt(strLine.split(" ")[1]);
				Point src = new Point(nn,mm);
				strLine = br.readLine();
				nn = Integer.parseInt(strLine.split(" ")[0]);
				mm = Integer.parseInt(strLine.split(" ")[1]);
				Point dest = new Point(nn,mm);
				strLine = br.readLine();
				nn = Integer.parseInt(strLine.split(" ")[0]);
				mm = Integer.parseInt(strLine.split(" ")[1]);
				char[][] board = new char[nn][mm];
				for(int i=0;i<nn;i++){
					strLine = br.readLine();
					for(int j=0;j<mm;j++){
						board[i][j] = strLine.charAt(j);
					}
				}
				process(board,src,dest);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	private static void process(char[][] board, Point src, Point dest) {
		AStar astar = new AStar(src, dest, board);
		PPath path = astar.getShortestPath();
		Stack<PPath> st = new Stack<PPath>();
		int cnt=0;
		while(path.value.x != src.x || path.value.y != src.y){
			cnt++;
			st.push(path);
			path = path.prev;
		}
		System.out.println(cnt);
		System.out.println(path.value.x + " " + path.value.y);
		while(!st.isEmpty()){
			PPath cur = st.pop();
			System.out.println(cur.value.x + " " + cur.value.y);
		}
	}

}

class AStar {
	
	private PriorityQueue<PPath> pQueue;
	private Point src;
	private Point dest;
	private char[][] board;
	
	AStar(Point src, Point dest, char[][] board){
		this.src = src;
		this.dest = dest;
		this.board = board;
		this.pQueue = new PriorityQueue<PPath>();
	}
	
	public PPath getShortestPath(){
		PPath start = new PPath(null, src.x, src.y, getEstimatedDist(src.x, src.y));
		pQueue.add(start);
		PPath cur = pQueue.poll();
		while(cur.value.x != dest.x || cur.value.y != dest.y){
			List<PPath> nextPaths = getNext(cur);
			for(PPath nextPath : nextPaths){
				pQueue.add(nextPath);
			}
			cur = pQueue.poll();
		}
		
		return cur;
	}
	
	private List<PPath> getNext(PPath cur) {
		List<PPath> list = new ArrayList<PPath>();
		int prevRow;
		int prevCol;
		if(null == cur.prev){
			prevRow =-1;
			prevCol =-1;
		}else{
			prevRow = cur.prev.value.x;
			prevCol = cur.prev.value.y;
		}
		int row = cur.value.x -1;
		int col = cur.value.y;
		if(board[row][col] != '%' && row != prevRow ){
			list.add(new PPath(cur, row, col, getEstimatedDist(row, col)));
		}
		row = cur.value.x +1;
		col = cur.value.y;
		if(board[row][col] != '%' && row != prevRow ){
			list.add(new PPath(cur, row, col, getEstimatedDist(row, col)));
		}
		row = cur.value.x;
		col = cur.value.y-1;
		if(board[row][col] != '%' && col != prevCol ){
			list.add(new PPath(cur, row, col, getEstimatedDist(row, col)));
		}
		row = cur.value.x;
		col = cur.value.y+1;
		if(board[row][col] != '%' && col != prevCol ){
			list.add(new PPath(cur, row, col, getEstimatedDist(row, col)));
		}
		return list;
	}

	private int getEstimatedDist(int x, int y){
		return Math.abs(x-src.x) + Math.abs(x-dest.x) + Math.abs(y-src.y) + Math.abs(y-dest.y);
	}
	
}

class PPath implements Comparable<PPath>{
	
	public Point value;
	public int distance;
	public PPath prev;
	
	
	public PPath(PPath prev, int x, int y, int dist) {
		this.value = new Point(x, y);
		this.distance = dist;
		this.prev = prev;
	}


	@Override
	public int compareTo(PPath arg0) {
		if(arg0.distance > distance){
			return -1;
		}else if(arg0.distance < distance){
			return 1;
		}
		return 0;
	}
	
	
}


