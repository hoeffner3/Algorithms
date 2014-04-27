package com.kushd.hackerrank;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NSumConcept {
	
	public static void main(String[] args) {
		try {
			StonePile sp = new StonePile();
			int[] sgvalues = sp.getSGValues();
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
				int xor=0;
				for(int kl=0;kl<nn;kl++){
					xor = xor ^ sgvalues[numbers[kl]];
				}
				String ans = xor==0?"BOB":"ALICE";
				System.out.println(ans);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

}


class StonePile {
	
	private Map<Integer, ArrayList<ArrayList<Integer>> > map;
	private final static int MAX_SIZE = 50;
	
	public StonePile() {
		this.map = new HashMap<Integer, ArrayList<ArrayList<Integer>> >();
		preprocess();
	}
	
	private void preprocess(){
		for(int i=1;i<=MAX_SIZE;i++){
			decompose(i, i, 0, new ArrayList<Integer>());
		}
	}
	
	private void decompose(int index, int sum, int last, ArrayList<Integer> list){
		if(sum == 0 && list.size()>1){
			if(!map.containsKey(index)){
				map.put(index, new ArrayList<ArrayList<Integer>>());
			}
			ArrayList<Integer> newlist = new ArrayList<Integer>();
			for(Integer ii : list){
				newlist.add(ii);
			}
			(map.get(index)).add(newlist);
		}
		
		for(int i=last+1;i<=sum;i++){
			list.add(i);
			decompose(index,sum-i,i,list);
			list.remove(new Integer(i));
		}
	}
	
	public int[] getSGValues(){
		int[] sgValues = new int[MAX_SIZE+1];
		sgValues[1] = 0;
		sgValues[2] = 0;
		sgValues[3] = 1;
		
		for(int i=4;i<=MAX_SIZE;i++){
			ArrayList<ArrayList<Integer>> breakups = map.get(i);
			ArrayList<Integer> mexlist = new ArrayList<Integer>();
			for(ArrayList<Integer> breakup : breakups){
				int xor=0;
				for(Integer e : breakup){
					xor = xor ^ sgValues[e.intValue()];
				}
				mexlist.add(xor);
			}
			sgValues[i] = getMex(mexlist);
		}
		return sgValues;
	}
	
	private int getMex(ArrayList<Integer> mexlist){
		int i=0;
		while(mexlist.contains(i)){
			i=i+1;
		}
		return i;
	}
	
}