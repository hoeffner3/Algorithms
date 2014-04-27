package com.kushd.moderate;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MultiplyNum {
	
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String strLine = null;
				strLine = br.readLine();
				int nn = Integer.parseInt(strLine.trim());
				for(int tc=0;tc<nn;tc++){
					int isNeg = 0;
					strLine = br.readLine();
					String n1 = strLine.split(" ")[0];
					String n2 = strLine.split(" ")[1];
					int[] a1;
					int[] a2;
					if(n1.charAt(0) == '-'){
						a1 = new int[n1.length()-1];
						int j=0;
						for(int i=1;i<n1.length();i++){
							a1[j] = Integer.parseInt(n1.substring(i, i+1));
							j++;
						}
						isNeg=(isNeg+1)%2;
					}else{
						a1 = new int[n1.length()];
						int j=0;
						for(int i=0;i<n1.length();i++){
							a1[j] = Integer.parseInt(n1.substring(i, i+1));
							j++;
						}
					}
					
					if(n2.charAt(0) == '-'){
						a2 = new int[n2.length()-1];
						int j=0;
						for(int i=1;i<n2.length();i++){
							a2[j] = Integer.parseInt(n2.substring(i, i+1));
							j++;
						}
						isNeg=(isNeg+1)%2;
					}else{
						a2 = new int[n2.length()];
						int j=0;
						for(int i=0;i<n2.length();i++){
							a2[j] = Integer.parseInt(n2.substring(i, i+1));
							j++;
						}
					}
					
					int[] mult = multiply(a1,a2);
					if(isNeg==1){
						System.out.print("-");
					}
					boolean startingzero = true;
					for(int kk=0;kk<mult.length;kk++){
						if(!startingzero || mult[kk]!=0){
							startingzero=false;
							System.out.print(mult[kk]);
						}
					}
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static int[] multiply(int[] a1, int[] a2) {
		int[] mult = new int[a1.length+a2.length];
        int s = mult.length-1;
        
        for(int j=a2.length-1; j>=0; j--){
            int carry=0;
            int shift = s;
            for(int i=a1.length-1; i>=0; i--){
                int m = a1[i]*a2[j];
                int sum = m+mult[shift]+carry;
                int num = sum%10;
                int c = sum/10;
                mult[shift] = num;
                carry=c;
                shift--;
            }
            mult[shift]=mult[shift]+carry;            
            s--;            
        }
        
		return mult;
	}

}
