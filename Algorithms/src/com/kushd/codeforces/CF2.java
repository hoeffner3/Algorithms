package com.kushd.codeforces;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CF2 {
	
	
	public static void main(String[] args) {
		try {
			String strLine=null;
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				strLine = br.readLine();
				int nn = Integer.parseInt(strLine.trim());
				strLine = br.readLine();
				String actions = strLine.trim();
				
				int left = process(actions);
				System.out.println(left);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static int process(String actions) {
		int finalStanding=0;
		int leftL = getleftL(actions);
		int leftR = getleftR(actions);
		finalStanding = finalStanding + (leftR-leftL-1);
		if(leftR == actions.length()){
			return finalStanding;
		}
		actions = actions.substring(leftR);
		int rightR = getrightR(actions);
		int rightL = getrightL(actions);
		finalStanding = finalStanding + (rightR-rightL-1);
		
		if(rightL <0){
			return finalStanding;
		}
		actions = actions.substring(0,rightL+1);
		
		int tempcnt=0;
		for(int i=0;i<actions.length();i++){
			if(actions.charAt(i) == 'R'){
				finalStanding = finalStanding + tempcnt;
				tempcnt = 0;
			}
			else if(actions.charAt(i) == 'L'){
				if(tempcnt%2 != 0){
					finalStanding = finalStanding + 1;
				}
				tempcnt=0;
			}else{
				tempcnt++;
			}
		}
		return finalStanding;
	}
	
	private static int getrightL(String actions){
		for(int i=actions.length()-1;i>=0;i--){
			if(actions.charAt(i) == 'L'){
				return i;
			}
		}
		return -1;
	}
	
	private static int getrightR(String actions){
		for(int i=actions.length()-1;i>=0;i--){
			if(actions.charAt(i) == 'R'){
				return i;
			}
			if(actions.charAt(i) == 'L'){
				return actions.length();
			}
		}
		return actions.length();
	}
	
	private static int getleftR(String actions){
		for(int i=0;i<actions.length();i++){
			if(actions.charAt(i) == 'R'){
				return i;
			}
		}
		return actions.length();
	}
	
	private static int getleftL(String actions){
		for(int i=0;i<actions.length();i++){
			if(actions.charAt(i) == 'L'){
				return i;
			}
			if(actions.charAt(i) == 'R'){
				return -1;
			}
		}
		return -1;
	}

}
