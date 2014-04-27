package com.kushd.fbhc2013;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class LabelMaker {
	
	
	public static void main(String[] args) {
		FileInputStream fio=null;
		DataInputStream in=null;
		try {
			fio = new FileInputStream("/Users/kushal/Downloads/labelmaker-1.txt");
			in = new DataInputStream(fio);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine = br.readLine();
			int tc = Integer.parseInt(strLine);
			for(int i=0;i<tc;i++){
				strLine = br.readLine();
				String letters = (strLine.split(" "))[0];
				long numb = Long.parseLong((strLine.split(" "))[1]);
				Map<Integer,Character> map = new HashMap<Integer,Character>(); 
				for(int pp=0;pp<letters.length();pp++){
					map.put(pp, letters.charAt(pp));
				}
				process(map,numb,i,letters.length());
			}
			
			
			in.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	private static void process(Map<Integer, Character> map, long numb, int i,int base) {
		StringBuilder sb = new StringBuilder();
		while(numb != 0){
			long newnumb = numb-1;
			int r = (int) (newnumb%base);
			sb.append(map.get(r));
			numb = newnumb/base;
		}
		String ans = sb.reverse().toString();
		System.out.println("Case #"+(i+1)+": "+ans);
	}

}
