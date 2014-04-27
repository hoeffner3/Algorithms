package com.kushd.fbhc2013;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Parking {
	
	public static void main(String[] args) {
		FileInputStream fio=null;
		DataInputStream in=null;
		try {
			fio = new FileInputStream("/Users/kushal/Downloads/aaaaaa-1.txt");
			in = new DataInputStream(fio);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine = br.readLine();
			int tc = Integer.parseInt(strLine);
			for(int i=0;i<tc;i++){
				strLine = br.readLine();
				int row = Integer.parseInt((strLine.split(" "))[0]);
				int col = Integer.parseInt((strLine.split(" "))[1]);
				int[][] matrix = new int[row][col];
				for(int j=0;j<row;j++){
					strLine= br.readLine();
					for(int k=0;k<col;k++){
						if(strLine.charAt(k) == '.'){
							matrix[j][k] = 0;
						}else{
							matrix[j][k] = 1;
						}
					}
				}
				int[][] pNodes = new int[matrix.length][matrix[0].length];
				for(int pp=0;pp<pNodes.length;pp++){
					for(int qq=0;qq<pNodes[0].length;qq++){
						pNodes[pp][qq] = 0;
					}
				}
				int[][] reachl = new int[matrix.length][matrix[0].length];
				for(int pp=0;pp<reachl.length;pp++){
					for(int qq=0;qq<reachl[0].length;qq++){
						reachl[pp][qq] = 0;
					}
				}
				int[][] reachu = new int[matrix.length][matrix[0].length];
				for(int pp=0;pp<reachu.length;pp++){
					for(int qq=0;qq<reachu[0].length;qq++){
						reachu[pp][qq] = 0;
					}
				}
				
				process(matrix,pNodes);
				processR(matrix,reachl,reachu);
				int ans  = process2(pNodes,reachl,reachu,matrix);
				System.out.println("Case #"+(i+1)+": "+ans);
			}

			in.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	private static int process2(int[][] pNodes,int[][] reachl,int[][] reachu,int[][] matrix) {
		int fmax = pNodes[0][0];
		int[][] leftMax = new int[pNodes.length][pNodes[0].length];
		for(int pp=0;pp<leftMax.length;pp++){
			for(int qq=0;qq<leftMax[0].length;qq++){
				leftMax[pp][qq] = 0;
			}
		}
		for(int pp=0;pp<pNodes.length;pp++){
			for(int qq=0;qq<pNodes[0].length;qq++){
				if(matrix[pp][qq] == 0){
					int temp1 = qq-1>=0?leftMax[pp][qq-1]:0;
					int temp2 = pp+1<pNodes.length?pNodes[pp+1][qq]:0;
					temp1 = Math.max(temp1,temp2);
					leftMax[pp][qq] = temp1+1;
				}
			}
		}
		int[][] upMax = new int[pNodes.length][pNodes[0].length];
		for(int pp=0;pp<upMax.length;pp++){
			for(int qq=0;qq<upMax[0].length;qq++){
				upMax[pp][qq] = 0;
			}
		}
		for(int pp=0;pp<pNodes.length;pp++){
			for(int qq=0;qq<pNodes[0].length;qq++){
				if(matrix[pp][qq] == 0){
					int temp1 = pp-1>=0?upMax[pp-1][qq]:0;
					int temp2 = qq+1<pNodes[0].length?pNodes[pp][qq+1]:0;
					temp1 = Math.max(temp1,temp2);
					upMax[pp][qq] = temp1+1;
				}
			}
		}
		//actual calculation
		for(int pp=0;pp<leftMax.length;pp++){
			for(int qq=0;qq<leftMax[0].length;qq++){
				if(reachl[pp][qq] != 0 && qq-1>=0){
					fmax = Math.max(fmax,(reachl[pp][qq]+leftMax[pp][qq-1]));
				}
			}
		}
		for(int pp=0;pp<upMax.length;pp++){
			for(int qq=0;qq<upMax[0].length;qq++){
				if(reachu[pp][qq] != 0 && pp-1>=0){
					fmax = Math.max(fmax,(reachu[pp][qq]+upMax[pp-1][qq]));
				}
			}
		}
		return fmax;
	}

	private static void process(int[][] matrix,int[][] pNodes) {
		for(int pp=pNodes.length-1;pp>=0;pp--){
			for(int qq=pNodes[0].length-1;qq>=0;qq--){
					if(matrix[pp][qq] == 0){
					int temp1 = pp+1 < matrix.length?pNodes[pp+1][qq]:0;
					int temp2 = qq+1 < matrix[0].length?pNodes[pp][qq+1]:0;
					pNodes[pp][qq] = Math.max(temp1, temp2) + 1;
				}
			}
		}
		
	}
	
	private static void processR(int[][] matrix,int[][] reachl,int[][] reachu) {
		int[][] reachs = new int[matrix.length][matrix[0].length];
		reachs[0][0] = 1;
		for(int pp=0;pp<reachs.length;pp++){
			for(int qq=0;qq<reachs[0].length;qq++){
					if(pp==0&&qq==0){
						continue;
					}
					if(matrix[pp][qq] == 0){
						int reach=0;
						if(pp-1>=0 && matrix[pp-1][qq] == 0){
							reach = reachs[pp-1][qq];
						}
						if(qq-1>=0 && matrix[pp][qq-1] == 0){
							reach = Math.max(reach, reachs[pp][qq-1]);
						}
						reachs[pp][qq] = reach==0?0:reach+1;
					}
				}
			}
		
		reachl[0][0] = 1;
		for(int pp=0;pp<reachl.length;pp++){
			for(int qq=0;qq<reachl[0].length;qq++){
					if(pp==0&&qq==0){
						continue;
					}
					if(matrix[pp][qq] == 0){
						int reach=0;
						if(pp-1>=0 && matrix[pp-1][qq] == 0){
							reach = reachs[pp-1][qq];
						}
						reachl[pp][qq] = reach==0?0:reach+1;
					}
				}
			}
		
		reachu[0][0] = 1;
		for(int pp=0;pp<reachu.length;pp++){
			for(int qq=0;qq<reachu[0].length;qq++){
					if(pp==0&&qq==0){
						continue;
					}
					if(matrix[pp][qq] == 0){
						int reach=0;
						if(qq-1>=0 && matrix[pp][qq-1] == 0){
							reach = reachs[pp][qq-1];
						}
						reachu[pp][qq] = reach==0?0:reach+1;
					}
				}
			}
	}
		
	
	
	

}

