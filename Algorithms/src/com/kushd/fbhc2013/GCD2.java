package com.kushd.fbhc2013;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GCD2 {
	
	private static Map<Integer,Integer> primaMap = new HashMap<Integer,Integer>();
	static{
		primaMap.put(2, 0);
		primaMap.put(3, 1);
		primaMap.put(5, 2);
		primaMap.put(7, 3);
		primaMap.put(11, 4);
		primaMap.put(13, 5);
		primaMap.put(17, 6);
		primaMap.put(19, 7);
		primaMap.put(23, 8);
		primaMap.put(29, 9);
		primaMap.put(31, 10);
		primaMap.put(37, 11);
		primaMap.put(41, 12);
		primaMap.put(43, 13);
		primaMap.put(47, 14);
		primaMap.put(53, 15);
		primaMap.put(59, 16);
		primaMap.put(61, 17);
	}
	
	public static void main(String[] args) {
		FileInputStream fio=null;
		DataInputStream in=null;
		try {
			fio = new FileInputStream("/Users/kushal/Downloads/gcd-1.txt");
			in = new DataInputStream(fio);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine = br.readLine();
			int tc = Integer.parseInt(strLine);
			for(int i=0;i<tc;i++){
				strLine = br.readLine();
				int nn = Integer.parseInt((strLine.split(" "))[0]);
				int kk = Integer.parseInt((strLine.split(" "))[1]);
				int[] numbers = new int[nn];
				strLine = br.readLine();
				String[] aas = strLine.split(" ");
				int cnt=0;	
				for(String aan : aas){
					numbers[cnt] = Integer.parseInt(aan);
					cnt++;
				}
				extra=0;
				int ans = process(nn,kk,numbers);
				System.out.println("Case #"+(i+1)+": "+ans);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	private static int extra=0;
	private static int MAXBITS = 1024*256;

	private static int process(int nn, int kk, int[] numbers) {
		int extra = preProcess(kk,numbers);
		Arrays.sort(numbers);
		int[][] dpArray = new int[nn][MAXBITS];
		for(int p=0;p<nn;p++){
			helper(dpArray, p, numbers);
		}
		
		int fmin=Integer.MAX_VALUE;
		for(int q=0;q<MAXBITS;q++){
			if(dpArray[nn-1][q] < fmin){
				fmin = dpArray[nn-1][q];
			}
		}
		return ((kk*fmin)+extra);
	}

	private static void helper(int[][] dpArray,int p,int[] numbers) {
		if(p==0 && numbers[p] == 0){
			dpArray[p][0] = 0;
			for(int q=1;q<MAXBITS;q++){
				dpArray[p][q] = getValueAtIndex(numbers[p],q,dpArray,p);
			}
		}else{
			for(int q=0;q<MAXBITS;q++){
				dpArray[p][q] = getValueAtIndex(numbers[p],q,dpArray,p);
			}
		}
		
	}

	private static int getValueAtIndex(int number, int mask,int[][] dpArray,int index) {
		int value = Integer.MAX_VALUE;
		for(int i=number;i<=61;i++){
			if(i==0){
				continue;
			}
			if(canRemoveMask(i,mask)){
				int modmask = removeMask(i,mask);
				if(index == 0){
					if(modmask == 0 && ((i-number) < value)){
						value = (i-number);
						break;
					}
				}else{
					if((dpArray[index-1][modmask] != Integer.MAX_VALUE) ){
						if((dpArray[index-1][modmask] + (i-number)) < value){
							value = dpArray[index-1][modmask] + (i-number);
							break;
						}
					}
				}
				
			}
		}
		return value;
	}

	private static boolean canRemoveMask(int n, int mask) {
		Set<Integer> primes = primaMap.keySet();
		for(Integer prime : primes){
			if(n%prime==0){
				int index = primaMap.get(prime);
				if((mask & (1<<index)) == 0){
					return false;
				}
			}
		}
		return true;
	}

	private static int removeMask(int number, int mask) {
		
		Set<Integer> primes = primaMap.keySet();
		for(Integer prime : primes){
			if(number%prime==0){
				int index = primaMap.get(prime);
				int allOne = ((MAXBITS)-1);
				allOne = allOne & (~(1<<index));
				mask = (mask & allOne);
			}
		}
		return mask;
	}

	private static int preProcess(int kk, int[] numbers) {
		int cnt=0;
		for(int number : numbers){
			int temp = number%kk;
			if(temp!=0){
				extra = extra + (kk-temp);
				numbers[cnt] = number + (kk-temp);
			}
			numbers[cnt] = numbers[cnt]/kk;
			cnt++;
		}
		return extra;
	}
	

}
/*


try {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	String strLine = null;
	while((strLine = br.readLine()) != null ) {
		int nn = Integer.parseInt((strLine.split(" "))[0]);
		int mm = Integer.parseInt((strLine.split(" "))[1]);
		int[] printers = new int[nn];
        int[] papers = new int[mm];
		String strLine1 = br.readLine();
        String strLine2 = br.readLine();
		String[] printerRanks = strLine1.split(" ");
        String[] paperRanks = strLine2.split(" ");
		int cnt=0;	
		for(String printerRank : printerRanks){
			printers[cnt] = Integer.parseInt(printerRank);
			cnt++;
		}
		cnt=0;
        for(String paperRank : paperRanks){
			papers[cnt] = Integer.parseInt(paperRank);
			cnt++;
		}
		int ans = process(printers,papers);
		System.out.println(ans);
	}
} catch (Exception e) {
	e.printStackTrace();
}

*/