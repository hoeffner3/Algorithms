package com.kushd.fbhc2013;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class HoldEm {
	
	public static void main(String[] args) {
		FileInputStream fio=null;
		DataInputStream in=null;
		try {
			fio = new FileInputStream("/Users/kushal/Downloads/holdem.txt");
			in = new DataInputStream(fio);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine = br.readLine();
			int tc = Integer.parseInt(strLine);
			for(int i=0;i<tc;i++){
				strLine = br.readLine();
				int nn = Integer.parseInt((strLine.split(" "))[0]);
				int hh = Integer.parseInt((strLine.split(" "))[1]);
				int[][] numbers = new int[hh][2];
				for(int q=0;q<hh;q++){
					strLine = br.readLine();
					numbers[q][0] = Integer.parseInt((strLine.split(" "))[0]);
					numbers[q][1] = Integer.parseInt((strLine.split(" "))[1]);
				}
				//
				long denom = preprocess(nn);
				StringBuilder sb = new StringBuilder();
				for(int p=0;p<hh;p++){
					String ans = process(nn,numbers[p][0],numbers[p][1],denom);
					sb.append(ans);
				}
				System.out.println("Case #"+(i+1)+": "+sb.toString());
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	

	private static long preprocess(int nn) {
		long temp1 = (nn-2)*(nn-3)/2;
		long temp2 = (temp1*(temp1-1)*(temp1-2))/6;
		return temp2;
	}



	private static String process(int nn, int first, int second, long denom ) {
		long x = getFav(nn,first,second);
		long num = x*(x-1)*(x-2)/6;
		double dnum = num;
		double ddenom = denom;
		double prob = dnum/ddenom;
		if(prob >= 0.4){
			return "B";
		}else{
			return "F";
		}
	}



	private static long getFav(int nn, int first, int second) {
		if(first > second){
			int temp = first;
			first = second;
			second = temp;
		}
		long cnt=0;
		for(int i=1;i<=nn;i++){
			if(i==first||i==second){
				continue;
			}
			for(int j=i+1;j<=nn;j++){
				if(j==first||j==second){
					continue;
				}
				if((first+second) > (i+j)){
					cnt++;
				}else if((first+second) == (i+j)){
					if(second > j){
						cnt++;
					}
				}
			}
		}
		return cnt;
	}

}
