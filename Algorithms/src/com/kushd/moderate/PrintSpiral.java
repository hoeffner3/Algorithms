package com.kushd.moderate;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PrintSpiral {
	
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String strLine = null;
				strLine = br.readLine();
				int row = Integer.parseInt(strLine.split(" ")[1]);
				int col = Integer.parseInt(strLine.split(" ")[0]);
				int[][] matrix = new int[row][col];
				for(int i=0;i<row;i++){
					strLine = br.readLine();
					String[] values = strLine.split(" ");
					for(int j=0;j<col;j++){
						matrix[i][j] = Integer.parseInt(values[j]);
					}
				}
				PS ps = new PS();
				ps.printSpiral(matrix, row, col);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

class PS {
	
	
	public void printSpiral(int[][] matrix,int rows,int cols){
		int totalv = rows*cols;
		int cntr=0;
		for(int layer=0;layer<=rows/2;layer++){
			int rowfirst = layer;
			int rowlast = rows-layer-1;
			int colfirst = layer;
			int collast = cols-layer-1;
			if(cntr<totalv){
				cntr += printRowL2R(rowfirst,colfirst,collast,matrix);
				cntr += printColL2R(collast,rowfirst,rowlast,matrix);
				if(rowfirst<rowlast)
					cntr += printRowR2L(rowlast, collast, colfirst, matrix);
				if(colfirst<collast)
					cntr += printColR2L(colfirst, rowlast, rowfirst, matrix);
			}
		}
	}
	
	private int printColR2L(int col, int s, int e, int[][] matrix){
		int cntr=0;
		for(int i=s-1;i>e;i--){
			System.out.print(matrix[i][col]+" ");
			cntr++;
		}
		return cntr;
	}
	
	private int printRowR2L(int row, int s, int e,int[][] matrix){
		int cntr=0;
		for(int i=s;i>=e;i--){
			System.out.print(matrix[row][i]+" ");
			cntr++;
		}
		return cntr;
	}
	
	private int printColL2R(int col, int s, int e, int[][] matrix){
		int cntr=0;
		for(int i=s+1;i<e;i++){
			System.out.print(matrix[i][col]+" ");
			cntr++;
		}
		return cntr;
	}
	
	private int printRowL2R(int row, int s, int e,int[][] matrix){
		int cntr=0;
		for(int i=s;i<=e;i++){
			System.out.print(matrix[row][i]+" ");
			cntr++;
		}
		return cntr;
	}
	
}