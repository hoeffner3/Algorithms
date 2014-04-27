package com.kushd.hackerrank;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class LegoBlock {
	
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String strLine = br.readLine();
			int tc = Integer.parseInt(strLine.trim());
			for(int pp=0;pp<tc;pp++){
				strLine = br.readLine();
				int nn = Integer.parseInt(strLine.split(" ")[0]);
				int mm = Integer.parseInt(strLine.split(" ")[1]);
				
				long ans = process(nn,mm);
				System.out.println(ans);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	private static long MOD = 1000000007;
	
	private static long addMod(long a, long b){
		return ((a+b)%MOD);
	}
	
	private static long multMod(long a, long b){
		return ((a*b)%MOD);
	}
	
	
	
	private static long subMod(long a, long b){
		long ans = a-b;
		while(ans<0){
			ans = ans + MOD;
		}
		return  ans;
	}

	private static long process(int nn, int mm) {
		long[] rowComb = getRowsComb(mm);
		long[] totalComb = new long[mm+1];
		for(int i=1;i<=mm;i++){
			long tt=1;
			for(int pp=1;pp<=nn;pp++){
				tt = multMod(tt, rowComb[i]);
			}
			totalComb[i] = tt;
		}
		long[] finalComb = new long[mm+1];
		finalComb[1] = 1;
		for(int i=2;i<=mm;i++){
			long temp = 0;
			for(int j=1;j<i;j++){
				long temp2 = multMod(finalComb[j],totalComb[i-j]);
				temp = addMod(temp,temp2);
			}
			finalComb[i] = subMod(totalComb[i],temp);
		}
		return finalComb[mm];
	}

	private static long[] getRowsComb(int mm) {
		long[] rowComb = new long[mm+1];
		rowComb[0] = 1;
		rowComb[1] = 1;
		for(int i=2;i<=mm;i++){
			rowComb[i] = 0;
			for(int j=1;j<=4;j++){
				if(i-j>=0){
					rowComb[i] = addMod(rowComb[i], rowComb[i-j]);
				}
			}
		}
		return rowComb;
	}

}
