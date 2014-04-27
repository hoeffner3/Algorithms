package com.kushd.fbhc2013;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BeamBalancer {
	
	public static void main(String[] args) {
		MyScanner sc = new MyScanner();
		String s   = sc.nextLine(); 
		String ans = process(s);
		System.out.println(ans);
		
	}

	private static String process(String strLine) {
		int pivot=-1;
		for(int i=0;i<strLine.length();i++){
			if(strLine.charAt(i) == '^'){
				pivot = i;
				break;
			}
		}
		long leftSum=0;
		for(int i=0;i<pivot;i++){
			if(strLine.charAt(i) != '='){
				int temp = Integer.parseInt(""+strLine.charAt(i));
				leftSum = leftSum + ((pivot-i)*temp);
			}
		}
		long rightSum=0;
		for(int i=pivot+1;i<strLine.length();i++){
			if(strLine.charAt(i) != '='){
				int temp = Integer.parseInt(""+strLine.charAt(i));
				rightSum = rightSum + ((i-pivot)*temp);
			}
		}
		if(leftSum == rightSum){
			return "balance";
		}else if(leftSum > rightSum){
			return "left";
		}else{
			return "right";
		}
	}
	
	
	
	
	
	public static class MyScanner {
	      BufferedReader br;
	      StringTokenizer st;
	 
	      public MyScanner() {
	         br = new BufferedReader(new InputStreamReader(System.in));
	      }
	 
	      String next() {
	          while (st == null || !st.hasMoreElements()) {
	              try {
	                  st = new StringTokenizer(br.readLine());
	              } catch (IOException e) {
	                  e.printStackTrace();
	              }
	          }
	          return st.nextToken();
	      }
	 
	      int nextInt() {
	          return Integer.parseInt(next());
	      }
	 
	      long nextLong() {
	          return Long.parseLong(next());
	      }
	 
	      double nextDouble() {
	          return Double.parseDouble(next());
	      }
	 
	      String nextLine(){
	          String str = "";
		  try {
		     str = br.readLine();
		  } catch (IOException e) {
		     e.printStackTrace();
		  }
		  return str;
	      }

	   }

}
