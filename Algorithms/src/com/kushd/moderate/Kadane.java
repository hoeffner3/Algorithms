package com.kushd.moderate;

import java.lang.*;
import java.math.*;
import java.util.*;
import java.io.*;

public class Kadane {
	
	
	public static void main(String[] args) {
		FileInputStream fio=null;
		DataInputStream in=null;
		try {
			fio = new FileInputStream("/Users/kushal/Downloads/input000.txt");
			in = new DataInputStream(fio);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine = null;
			while((strLine = br.readLine()) != null ) {
				int nn = Integer.parseInt(strLine.trim());
				int[] numbers = new int[nn];
				String strLine1 = br.readLine();
				String[] numberStrings = strLine1.split(" ");
				int cnt=0;	
				for(String numberString : numberStrings){
					numbers[cnt] = Integer.parseInt(numberString);
					cnt++;
				}
				int ans = process(numbers);
				System.out.println(ans);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static int process(int[] numbers) {
		int track=-1;
		int start=-1;
		int end=-1;
		int maxCnt=0;
		int cnt=0;
		int i=0;
		while(i<numbers.length){
			if(numbers[i] == 0){
				cnt++;
				if(cnt==1){
					track=i;
				}
			}else{
				if(cnt>0){
					cnt--;
				}
			}
			if(cnt>maxCnt){
				maxCnt=cnt;
				start=track;
				end=i;
			}
			i++;
		}
		
		if(start!=-1){
			for(int pp=start;pp<=end;pp++){
				numbers[pp] = (numbers[pp]+1)%2;
			}
		}
		int finalCount=0;
		for(int pp=0;pp<numbers.length;pp++){
			finalCount = finalCount + numbers[pp];
		}
		return finalCount;
	}

}
