package com.kushd.quora;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class DCCooling {
	
	public static void main(String[] args) {
		int[][] dc = new int[][] {
				{2,0,0,0,0,0,0},
				{0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0},
				{3,0,0,0,0,1,1}
		};
		int result = dcCooling(dc);
		System.out.println(result);
	}
	
	private static int result;
	
	public static int dcCooling(int[][] dc){
		result=0;
		int rowSize = dc.length;
		if(rowSize == 0){
			return result;
		}
		int colSize = dc[0].length;
		int maxZeros=0;
		for(int i=0;i<rowSize;i++){
			for(int j=0;j<colSize;j++){
				if(dc[i][j] == 0){
					maxZeros = maxZeros +1;
				}
			}
		}
		Map<String,Boolean> map = new HashMap<String,Boolean>();
		int row=-1;
		int col=-1;
		for(int i=0;i<rowSize;i++){
			for(int j=0;j<colSize;j++){
				if(dc[i][j] == 2){
					row = i;
					col = j;
				}
			}
		}
		if(row != -1 && col != -1){
			count(dc, map, 0, maxZeros, row, col);
		}
		return result;
	}
	
	
	private static void count(int[][] dc, Map<String,Boolean> map, int count, int maxZeros, int row, int col){
		if(dc[row][col] == 3){
			if(count == maxZeros){
				result = result + 1;
			}
			return;
		}
		int rowSize = dc.length;
		int colSize = dc[0].length;
		Stack<String> stack = new Stack<String>();
		if(row-1>=0 && (dc[row-1][col] == 0 || dc[row-1][col] == 3) && map.get((row-1)+"-"+(col)) == null ){
			stack.push((row-1)+"-"+(col));
		}
		if(row+1<rowSize && (dc[row+1][col] == 0 || dc[row+1][col] == 3) && map.get((row+1)+"-"+(col)) == null ){
			stack.push((row+1)+"-"+(col));
		}
		if(col-1>=0 && (dc[row][col-1] == 0 || dc[row][col-1] == 3) && map.get((row)+"-"+(col-1)) == null ){
			stack.push((row)+"-"+(col-1));
		}
		if(col+1<colSize && (dc[row][col+1] == 0 || dc[row][col+1] == 3) && map.get((row)+"-"+(col+1)) == null ){
			stack.push((row)+"-"+(col+1));
		}
		while(!stack.isEmpty()){
			String point = stack.pop();
			map.put(point,true);
			int tr = Integer.parseInt(point.split("-")[0]);
			int tc = Integer.parseInt(point.split("-")[1]);
			count(dc, map, (dc[tr][tc] == 0)?(count+1):(count), maxZeros, tr, tc);
			map.remove(point);
		}
	}

}


