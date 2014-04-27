package com.kushd.cj2014;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CJMagicTrick {
	
	
	public static void main(String[] args) {
		FileInputStream fio=null;
		DataInputStream in=null;
		try {
			fio = new FileInputStream("/Users/kushal/Downloads/A-small-attempt0.in");
			in = new DataInputStream(fio);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine = br.readLine();
			int tc = Integer.parseInt(strLine);
			for(int i=0;i<tc;i++){
				strLine = br.readLine();
				int firstrow = Integer.parseInt(strLine);
				int[][] firstArr = new int[5][5];
				for(int j=1;j<5;j++){
					strLine = br.readLine();
					String[] values =  strLine.split(" ");
					for(int k=1;k<5;k++){
						firstArr[j][k] = Integer.parseInt(values[k-1]);
					}
				}
				strLine = br.readLine();
				int secondrow = Integer.parseInt(strLine);
				int[][] secondArr = new int[5][5];
				for(int j=1;j<5;j++){
					strLine = br.readLine();
					String[] values =  strLine.split(" ");
					for(int k=1;k<5;k++){
						secondArr[j][k] = Integer.parseInt(values[k-1]);
					}
				}
				String ans = process(firstrow, firstArr, secondrow, secondArr);
				System.out.println("Case #"+(i+1)+": "+ans);
			}
			
			
			in.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	private static String process(int firstrow, int[][] firstArr,
			int secondrow, int[][] secondArr) {
		List<Integer> possibleAns1 = new ArrayList<Integer>();
		for(int i=1;i<5;i++){
			possibleAns1.add(firstArr[firstrow][i]);
		}
		
		List<Integer> possibleAns2 = new ArrayList<Integer>();
		for(int i=1;i<5;i++){
			possibleAns2.add(secondArr[secondrow][i]);
		}
		
		List<Integer> finalAns = intersection(possibleAns1, possibleAns2);
		if(finalAns.isEmpty()){
			return "Volunteer cheated!";
		}else if(finalAns.size() > 1){
			return "Bad magician!";
		}else{
			return Integer.toString(finalAns.get(0));
		}
	}

	private static <T> List<T> intersection(List<T> list1, List<T> list2) {
        List<T> list = new ArrayList<T>();

        for (T t : list1) {
            if(list2.contains(t)) {
                list.add(t);
            }
        }

        return list;
    }
	

}
