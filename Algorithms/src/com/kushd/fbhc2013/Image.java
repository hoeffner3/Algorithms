package com.kushd.fbhc2013;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Image {
	
	
	public static void main(String[] args) {
		FileInputStream fio=null;
		DataInputStream in=null;
		try {
			fio = new FileInputStream("/Users/kushal/Downloads/square_detector.txt");
			in = new DataInputStream(fio);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine = br.readLine();
			int tc = Integer.parseInt(strLine);
			for(int i=0;i<tc;i++){
				strLine = br.readLine();
				int sq = Integer.parseInt(strLine);
				int[][] matrix = new int[sq][sq];
				for(int j=0;j<sq;j++){
					strLine= br.readLine();
					for(int k=0;k<sq;k++){
						if(strLine.charAt(k) == '.'){
							matrix[j][k] = 0;
						}else{
							matrix[j][k] = 1;
						}
					}
				}
				process(matrix,sq,i);
			}
			
			
			in.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	private static void process(int[][] matrix, int sq, int iii) {
		int startR=0;
		int startC=0;
		boolean flag=false;
		for(int j=0;j<sq;j++){
			for(int k=0;k<sq;k++){
				if(matrix[j][k] == 1 && !flag){
					startR=j;
					startC=k;
					flag=true;
				}
			}
		}
		if(!flag){
			System.out.println("Case #"+(iii+1)+": NO");
			return;
		}
		int temp=startC;
		while(temp<sq){
			if(matrix[startR][temp] == 0){
				break;
			}
			temp++;
		}
		int length = temp-startC;
		int endR = startR+length;
		int endC = startC+length;
		for(int pp=startR;pp<endR;pp++){
			for(int qq=startC;qq<endC;qq++){
				if(matrix[pp][qq] == 0){
					System.out.println("Case #"+(iii+1)+": NO");
					return;
				}else{
					matrix[pp][qq] = 0;
				}
			}
		}
		for(int aa=0;aa<sq;aa++){
			for(int bb=0;bb<sq;bb++){
				if(matrix[aa][bb] == 1){
					System.out.println("Case #"+(iii+1)+": NO");
					return;
				}
			}
		}
		System.out.println("Case #"+(iii+1)+": YES");
		return;
	}

}
