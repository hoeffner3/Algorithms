package com.kushd.codeforces;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class CF1 {
	
	
	public static void main(String[] args) {
		try {
			String strLine=null;
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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
				process(numbers);
				for(int h=0;h<numbers.length;h++){
					System.out.print(numbers[h]+" ");
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void process(int[] numbers) {
		Arrays.sort(numbers);
	}
	
	

}
