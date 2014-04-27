package com.kushd.hackerrank;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class AdvMergeSort {

    public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String strLine = br.readLine();
			int tc = Integer.parseInt(strLine.trim());
			for(int pp=0;pp<tc;pp++){
				strLine = br.readLine();
				int nn = Integer.parseInt(strLine.trim());
				int[] numbers = new int[nn];
				strLine = br.readLine();
				String[] values = strLine.split(" ");
				int cnt=0;	
				for(String value : values){
					numbers[cnt] = Integer.parseInt(value);
					cnt++;
				}
				int ans = process(numbers,0,numbers.length-1);
				System.out.println(ans);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    private static int process(int[] numbers, int start, int end){
    	if(start==end){
    		return 0;
    	}
    	int mid = start + (end-start)/2;
    	int leftInv = process(numbers,start,mid);
    	int rightInv = process(numbers,mid+1,end);
    	int mergeInv = merge(numbers,start,mid,mid+1,end);
        
        return (leftInv+rightInv+mergeInv);
    }
    
    private static int merge(int[] numbers, int lstart,int lend,int rstart, int rend){
    	int inv=0;
    	int[] temp = new int[rend-lstart+1];
    	int lcnt=lstart;
    	int rcnt=rstart;
    	for(int i=0;i<temp.length;i++){
    		if((lcnt<=lend && rcnt<=rend && numbers[lcnt] <= numbers[rcnt]) || (rcnt>rend)){
    			temp[i] = numbers[lcnt];
    			lcnt++;
    		}else{
    			temp[i] = numbers[rcnt];
    			rcnt++;
    			inv = inv + (lend+1-lcnt);
    		}
    	}
    	int i=lstart;
    	for(int j=0;j<temp.length;j++){
    		numbers[i] = temp[j];
    		i++;
    	}

    	return inv;
    }
    
}