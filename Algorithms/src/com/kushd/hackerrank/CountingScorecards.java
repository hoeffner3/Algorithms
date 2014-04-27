package com.kushd.hackerrank;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CountingScorecards {
	
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
				int ans = process(numbers);
				ans = ans%1000000007;
				System.out.println(ans);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	private static int TOTAL_LEN;
	private static int TOTAL_SUM;
	private static int[][][][] dpArray;
	
	private static int process(int[] numbers) {
		TOTAL_LEN = numbers.length;
		TOTAL_SUM = (TOTAL_LEN*(TOTAL_LEN-1))/2;
		int len = TOTAL_LEN;
		int sum = TOTAL_SUM;
		Arrays.sort(numbers);
		for(int i=0;i<=(len/2)-1;i++){
			int temp = numbers[i];
			numbers[i] = numbers[len-i-1];
			numbers[len-i-1] = temp;
		}
		//code starts here
		int[] tempNum = new int [TOTAL_LEN];
		int[] wins = new int[2];
		wins[0] =0;
		wins[1] =TOTAL_LEN-1;
		int i=0; 
		while(i<TOTAL_LEN && numbers[i] >=0){
			if(sum < 0 || wins[1] < numbers[i] || wins[0] > numbers[i]){
				return 0;
			}
			sum = sum - numbers[i];
			len = len - 1;
			tempNum[TOTAL_LEN-len-1] = numbers[i];
			wins = getRangeWin(tempNum, sum, len);
			i++;
		}
		if(sum<0){
			return 0;
		}
		if(len==0){
			return 1;
		}
		//actual code starts here
		int maxWin = wins[1];
		int minWin = wins[0];
		dpArray = new int[len+1][sum+1][maxWin+1][maxWin+1];
		for(int p=0;p<=len;p++){
			for(int q=0;q<=sum;q++){
				for(int r=0;r<=maxWin;r++){
					for(int s=0;s<=maxWin;s++){
						dpArray[p][q][r][s] = -1;
					}
				}
			}
		}
		recurse(len,sum,minWin,maxWin,tempNum);
		
		
		return dpArray[len][sum][minWin][maxWin];
	}

	private static int recurse(int len, int sum, int minWin, int maxWin,int[] tempNUm) {
		if(len == 1){
			if(maxWin >=sum&&sum>= minWin){
				return 1;
			}else{
				return 0;
			}
		}
		if(maxWin<0||minWin<0){
			return 0;
		}
		if(dpArray[len][sum][minWin][maxWin]!=-1){
			return dpArray[len][sum][minWin][maxWin];
		}
		long ffsum=0;
		for(int tt=sum-minWin;tt>=(sum-maxWin);tt--){
			tempNUm[TOTAL_LEN-len] = sum-tt;
			int[] nextWins = getRangeWin(tempNUm, tt, len-1);
			//int[] nextWins = getNextMaxMinWin(tt, len-1, minWin, maxWin);
			if(nextWins[0] >=0 && nextWins[1] >= nextWins[0]){
				ffsum = ffsum + recurse(len-1, tt, nextWins[0], nextWins[1],tempNUm);
			}
		}
		if(ffsum > 1000000007){
			ffsum = ffsum%1000000007;
		}
		dpArray[len][sum][minWin][maxWin] = (int) ffsum;
		return dpArray[len][sum][minWin][maxWin];
	}

	private static int[] getNextMaxMinWin(int sum, int len, int minWin,
			int maxWin) {
		int[] nextwins = new int[2];
		nextwins[0]=-1;
		nextwins[1]=-1;
		int i=maxWin;
		while(i>=minWin){
			int totalWins = (TOTAL_SUM-sum) + i;
			int totalloss = (len-1)*(TOTAL_LEN-len+1) + ((TOTAL_LEN-len+1)*(TOTAL_LEN-1) - totalWins);
			if(totalWins<=totalloss){
				nextwins[1] = i;
				break;
			}
			i--;
		}
		i=minWin;
		while(i<=nextwins[1]){
			int totalLoss = ((TOTAL_LEN-len+1)*(TOTAL_LEN-1) - ((TOTAL_SUM-sum) + i) );
			int totalWins = ((TOTAL_SUM-sum) + i) + (len-1)*(TOTAL_LEN-len+1);
			if(totalLoss <= totalWins){
				nextwins[0] = i;
				break;
			}
			i++;
		}
		return nextwins;
	}

	private static int[] getRangeWin(int[] numbers, int sum, int len) {
		int[] nextwins = new int[2];
		nextwins[0]=-1;
		nextwins[1]=-1;
		int size = TOTAL_LEN-len;
		Map<Integer,Boolean> map = new HashMap<Integer,Boolean>();
		for(int i=0;i<TOTAL_LEN;i++){
			map.put(i, true);
		}
		int max = TOTAL_LEN-1;
		int min = 0;
		for(int i=0;i<size;i++){
			if(numbers[i]>max || numbers[i]<min){
				return nextwins;
			}
			if(map.get(numbers[i]) == true){
				map.put(numbers[i], false);
				if(numbers[i]==max){
					max = findmax(map);
				}
				if(numbers[i]==min){
					min = findmin(map);
				}
			}else{
				int tt= numbers[i]+1;
				while(map.get(tt) != true){
					tt++;
				}
				int inc = tt - numbers[i];
				map.put(tt, false);
				int at = numbers[i]-1;
				while(map.get(at)!=true){
					at--;
				}
				map.put(at+inc, true);
				map.put(at, false);
				max = findmax(map);
				min = findmin(map);
			}
		}
		nextwins[0] = min;
		nextwins[1] = max;
		return nextwins;
	}

	private static int findmax(Map<Integer, Boolean> map) {
		int max=-1;
		for(int i=TOTAL_LEN-1;i>=0;i--){
			if(map.get(i) == true){
				max = i;
				break;
			}
		}
		return max;
	}
	
	private static int findmin(Map<Integer,Boolean> map){
		int min=-1;
		for(int i=0;i<=TOTAL_LEN-1;i++){
			if(map.get(i) == true){
				min = i;
				break;
			}
		}
		return min;
	}

}
