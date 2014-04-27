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

public class GCD {
	
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
		primaMap.put(67, 18);
		primaMap.put(71, 19);
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
				fmin=Integer.MAX_VALUE;
				extra=0;
				int ans = process(nn,kk,numbers);
				System.out.println("Case #"+(i+1)+": "+ans);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	private static int fmin=Integer.MAX_VALUE;
	private static int extra=0;

	private static int process(int nn, int kk, int[] numbers) {
		int extra = preProcess(kk,numbers);
		Arrays.sort(numbers);
		int fmin=Integer.MAX_VALUE;
		for(int i=numbers[0];i<=71;i++){
			int newcost = (i-numbers[0]);
			if(newcost < fmin){
				int newmask = getMask(i,0);
				fmin = recurse(numbers,1,newmask,newcost);
			}else{
				break;
			}
		}
		return ((kk*fmin)+extra);
	}

	private static int recurse(int[] numbers, int index, int mask, int cost) {
		if(index>=numbers.length){
			if(cost < fmin){
				fmin = cost;
			}
			return fmin;
		}
		
		for(int i=numbers[index];i<=71;i++){
			int newcost = cost + (i-numbers[index]);
			if(newcost < fmin ){
				if(canMask(mask,i)){
					int newmask = getMask(i, mask);
					recurse(numbers, index+1, newmask, newcost);
				}
			}else{
				break;
			}
			
		}
		return fmin;
	}

	private static boolean canMask(int mask, int n) {
		Set<Integer> primes = primaMap.keySet();
		for(Integer prime : primes){
			if(n%prime==0){
				int index = primaMap.get(prime);
				if((mask & (1<<index)) != 0){
					return false;
				}
			}
		}
		return true;
	}

	private static int getMask(int number, int mask) {
		if(number==0){
			return ((1024*1024)-1);
		}
		if(number==1){
			return mask;
		}
		Set<Integer> primes = primaMap.keySet();
		for(Integer prime : primes){
			if(number%prime==0){
				int index = primaMap.get(prime);
				mask = (mask | (1<<index));
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
