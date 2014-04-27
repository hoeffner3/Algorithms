package com.kushd.fbhc2013;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Debt {
	
	public static void main(String[] args) {
		MyScanner sc = new MyScanner();
		String s   = sc.nextLine(); 
		int n = Integer.parseInt((s.split(" "))[0]);
		int[] persons = new int[n+1];
		int m = Integer.parseInt((s.split(" "))[1]);
		for(int jj=0;jj<m;jj++){
			String ss = sc.nextLine();
			process(ss,persons);
		}
		int ans=0;
		for(int ii=1;ii<persons.length;ii++){
			if(persons[ii] < 0){
				ans = ans + (-1*persons[ii]);
			}
		}
		System.out.println(ans);
	}

	private static void process(String line, int[] persons) {
		int giver = Integer.parseInt((line.split(" "))[0]);
		int receiver = Integer.parseInt((line.split(" "))[1]);
		int debt = Integer.parseInt((line.split(" "))[2]);
		persons[giver] = persons[giver] - debt;
		persons[receiver] = persons[receiver] + debt;
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
