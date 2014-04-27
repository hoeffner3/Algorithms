package com.kushd.fbhc2013;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class CoinGame {
	
	public static void main(String[] args) {
		FileInputStream fio=null;
		DataInputStream in=null;
		try {
			fio = new FileInputStream("/Users/kushal/Downloads/coins_game.txt");
			in = new DataInputStream(fio);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine = br.readLine();
			int tc = Integer.parseInt(strLine);
			for(int i=0;i<tc;i++){
				strLine = br.readLine();
				int nn = Integer.parseInt((strLine.split(" "))[0]);
				int kk = Integer.parseInt((strLine.split(" "))[1]);
				int cc = Integer.parseInt((strLine.split(" "))[2]);
				int ans = process(nn,kk,cc,cc,cc+nn-1);
				System.out.println("Case #"+(i+1)+": "+ans);
			}
			
			
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	private static int process(int nn, int kk, int cc, int min, int max) {
		while(min != max){
			
			int temp = kk/nn;
			int allowedcc = temp*nn;
			if(cc <= allowedcc){
				break;
			}else{
				int posmax = min + (nn-(kk%nn));
				max = Math.min(max, posmax);
			}
			min = min+1;
			nn = nn-1;
		}
		
		return min;
	}

}
