package com.kushd.hackerrank;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class NCRDP {
	
	public static void main(String[] args) {
        try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String strLine = null;
			
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
				long ans = process(numbers);
				System.out.println(ans);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    private static long process(int[] numbers){
        Map<Integer,Boolean> map = new HashMap<Integer,Boolean>();
        for(int i=0;i<numbers.length;i++){
            if(!map.containsKey(numbers[i])){
                map.put(numbers[i],true);
            }
        }
        int size = map.size();
        long ans = nCR(size,3);
        return ans;
    }
    
    public static long nCR(long n, long r){
		long[][] array = new long[(int) n+1][(int) r+1];
		
		for(int i=0;i<=n;i++){
			array[i][0] = 1; 
		}
		for(int j=1;j<=r;j++){
			array[0][j] = 0;
		}
		for(int i=1;i<=n;i++){
			for(int j=1;j<=r;j++){
				if(r>n){
					array[i][j] = 0;
				}else{
					array[i][j] = array[i-1][j] + array[i-1][j-1];
				}
			}
		}
		
		return array[(int) n][(int) r];
	}

}
