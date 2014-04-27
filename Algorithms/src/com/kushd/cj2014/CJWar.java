package com.kushd.cj2014;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class CJWar {
	
	
	public static void main(String[] args) {
		FileInputStream fio=null;
		DataInputStream in=null;
		try {
			fio = new FileInputStream("/Users/kushal/Downloads/D-large.in");
			in = new DataInputStream(fio);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine = br.readLine();
			int tc = Integer.parseInt(strLine);
			for(int i=0;i<tc;i++){
				strLine = br.readLine();
				int n = Integer.parseInt(strLine);
				float[] naomi = new float[n];
				strLine = br.readLine();
				String[] values = strLine.split(" ");
				for(int j=0;j<n;j++){
					naomi[j] = Float.parseFloat(values[j]);
				}
				float[] ken = new float[n];
				strLine = br.readLine();
				values = strLine.split(" ");
				for(int j=0;j<n;j++){
					ken[j] = Float.parseFloat(values[j]);
				}
				
				String ans = process(naomi,ken, n);
				System.out.println("Case #"+(i+1)+": "+ans);
			}
			
			
			in.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	private static String process(float[] naomi, float[] ken, int n) {
		Arrays.sort(naomi);
		Arrays.sort(ken);
		int kenlowcntr=0;
		int kenhighcntr=n-1;
		for(int i=n-1;i>=0;i--){
			if(naomi[i] > ken[kenhighcntr]){
				kenlowcntr++;
			}else{
				kenhighcntr--;
			}
		}
		String sans = Integer.toString(kenlowcntr);
		
		kenlowcntr=0;
		int naomilowcntr=0;
		while(naomilowcntr < n){
			if(naomi[naomilowcntr] < ken[kenlowcntr]){
				naomilowcntr++;
			}else{
				naomilowcntr++;
				kenlowcntr++;
			}
		}
		String fans = Integer.toString(kenlowcntr);
		
		return fans+" "+sans;
	}

}
