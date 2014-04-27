package com.kushd.hackerrank;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SSDiff {
	
	
	public static void main(String[] args) {
        try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String strLine = null;
			
				strLine = br.readLine();
				int tc = Integer.parseInt(strLine.trim());
				for(int tt=0;tt<tc;tt++){
					strLine = br.readLine();
					String[] values = strLine.split(" ");
					int k = Integer.parseInt(values[0]);
					String one = values[1];
					String two = values[2];
					int ans = process(k,one,two);
					System.out.println(ans);
				
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	private static int process(int k, String one, String two) {
		int len = one.length();
		int max=0;
		
		
		for(int sec=0;sec<len;sec++){
			int i=0;
			int j=sec;
			int curL = 0;
			int diffcnt = 0;
			while(j<len){
				if(one.charAt(i) != two.charAt(j)){
					diffcnt++;
				}
				if(diffcnt > k){
					diffcnt--;
					if(one.charAt(i-curL) != two.charAt(j-curL)){
						diffcnt--;
					}
					curL--;
					if(curL < 0){
						i++;j++;
						curL=0; diffcnt=0;
					}
				}else{
					curL++;
					max = Math.max(max, curL);
					i++;j++;
				}
			}
			max = Math.max(max, curL);
		}
		
		
		
		for(int fir=0;fir<len;fir++){
			int i=fir;
			int j=0;
			int curL = 0;
			int diffcnt = 0;
			while(i<len){
				if(one.charAt(i) != two.charAt(j)){
					diffcnt++;
				}
				if(diffcnt > k){
					diffcnt--;
					if(one.charAt(i-curL) != two.charAt(j-curL)){
						diffcnt--;
					}
					curL--;
					if(curL < 0){
						i++;j++;
						curL=0; diffcnt=0;
					}
				}else{
					curL++;
					max = Math.max(max, curL);
					i++;j++;
				}
			}
			max = Math.max(max, curL);
		}
		
		
		
		return max;
	}

}
