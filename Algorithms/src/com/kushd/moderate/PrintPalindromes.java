package com.kushd.moderate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class PrintPalindromes {
	
	private static Set<String> pset = new TreeSet<String>(); 
	
	public static void main(String[] args) {
		printPalindromes(null);
	}
	
	static void printPalindromes(String[] inputArray) {
		
		pset.clear();
		
		String[] input = new String[4];
		input[0] = "abcb";
		input[1] = "bbdb";
		input[2] = "cbca";
		input[3] = "bcbd";
		
		List<String> list = helper(input);
		
		for(String e : list){
			palindromes(e);
		}
		
			for(String entry : pset){
				if(entry.length() >=3){
						System.out.println(entry);
				}
			}
		
		

    }

	static List<String> helper(String[] input){
		List<String> list = new ArrayList<String>();
		int row = input.length;
		int col = input[0].length();
		
		for(int i=0;i<row;i++){
			list.add(input[i]);
		}
		for(int i=0;i<col;i++){
			String temp = "";
			for(int j=0;j<row;j++){
				temp = temp + input[j].charAt(i);
			}
			list.add(temp);
		}
		
		list.addAll(diagonalize(input));


		return list;
		
	}
	
	public static List<String> diagonalize(String[] input) {
	    final List<String> diags = new ArrayList<String>();
	    int rr = input.length;
		int cc = input[0].length();
	    for (int row = rr - 1; row > 0; --row) {
	        diags.add(getDiagonal(row, 0,rr,cc,input));
	    }
	    for (int col = 0; col < cc; ++col) {
	        diags.add(getDiagonal(0, col,rr,cc,input));
	    }
	    for (int row = rr - 1; row > 0; --row) {
	        diags.add(getDiagonal2(row, cc-1,rr,cc,input));
	    }
	    for (int col = 0; col < cc; ++col) {
	        diags.add(getDiagonal2(0, col,rr,cc,input));
	    }
	    return diags;
	}
	
	private static String getDiagonal2(int x, int y, int row, int col, String[] input) {
	    String temp = "";
	    while (x < row && y >= 0) {
	        temp = temp + input[x++].charAt(y--);
	    }
	    return temp;
	}

	private static String getDiagonal(int x, int y, int row, int col, String[] input) {
	    String temp = "";
	    while (x < row && y < col) {
	        temp = temp + input[x++].charAt(y++);
	    }
	    return temp;
	}
	
	
	public static void palindromes(final String input) {
	     for (int i = 0; i < input.length(); i++) {
	         getPalindromes(input,i,i+1);
	         getPalindromes(input,i,i);
	     } 
	  }

	  public static void getPalindromes(String s, int i, int j) {
	      while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
	    	  pset.add(s.substring(i,j+1));
	            i--; j++;
	      }
	  }

}
