package com.kushd.hackerrank;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.*;

public class XORProblem {
	
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String strLine = br.readLine();
			int tc = Integer.parseInt(strLine.trim());
			for(int pp=0;pp<tc;pp++){
				strLine = br.readLine();
				int nn = Integer.parseInt(strLine.split(" ")[0]);
				int qq = Integer.parseInt(strLine.split(" ")[1]);
				int[] numbers = new int[nn];
				strLine = br.readLine();
				String[] values = strLine.split(" ");
				int cnt=0;	
				for(String value : values){
					numbers[cnt] = Short.parseShort(value);
					cnt++;
				}
				int[][] tempArray = new int[qq][3];
				for(int jj=0;jj<qq;jj++){
					strLine = br.readLine();
					tempArray[jj][0] = Integer.parseInt(strLine.split(" ")[0]);
					tempArray[jj][1] = Integer.parseInt(strLine.split(" ")[1]);
					tempArray[jj][2] = Integer.parseInt(strLine.split(" ")[2]);
				}
				//System.out.println(System.currentTimeMillis());
				NMap nMap = new NMap();
				nMap.preprocess(numbers);
				//System.out.println(System.currentTimeMillis());
				for(int jj=0;jj<qq;jj++){
					int index = nMap.query(tempArray[jj][0],tempArray[jj][1],tempArray[jj][2]); 
					int aaa =  tempArray[jj][0];
					int fans = (aaa ^ numbers[index]);
					System.out.println(fans);
				}
				//System.out.println(System.currentTimeMillis());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

}

class NMap{
	
	private int MAX_LENGTH = 15;
	
	private DDDArr[] mainmap = new DDDArr[MAX_LENGTH+1];
	
	public NMap() {
		int res=1;
		for(int i=1;i<=MAX_LENGTH;i++){
			res = 2 * res;
			mainmap[i] = new DDDArr(res);
		}
	}
	
	public void preprocess(int[] numbers){
		int bitmask;
		for(int i=0;i<numbers.length;i++){
			for(int j=1;j<=MAX_LENGTH;j++){
				bitmask = numbers[i] >> (MAX_LENGTH-j);
				if(mainmap[j].bitindex[bitmask] == null){
					mainmap[j].bitindex[bitmask] =  new DDDNode();
				}
				mainmap[j].bitindex[bitmask].indexes.add(i+1);
			}
		}
	}
	
	public int query(int aa, int start,int end){
		int a = aa ^ ((1<<MAX_LENGTH) - 1);
		int key=0;
		int bitmask;
		int bit;
		for(int j=1;j<=MAX_LENGTH;j++){
			bitmask = a >> (MAX_LENGTH-j);
			bit = 1 & bitmask;
			key = key << 1;
			key = key | bit;
			if(!search(key,j,start,end)){
				key = key ^ 1;
			}
		}
		List<Integer> internalList = mainmap[MAX_LENGTH].bitindex[key].indexes;
			int index = internalList.get(0);
		
		return index-1;
	}

	private boolean search(int key, int len,int start, int end) {
		if(null == mainmap[len].bitindex[key]){
			return false;
		}
		List<Integer> indexes = mainmap[len].bitindex[key].indexes;
		int high = indexes.size()-1;
		int low = 0;
		while(low<=high){
			int mid = (low+high)/2;
			int index = indexes.get(mid);
			if(index > end){
				high = mid-1;
			}else if(index < start){
				low = mid+1;
			}else{
				return true;
			}
		}
		return false;
	}
	
}

class DDDArr {
	
	public DDDNode[] bitindex;
	
	public DDDArr(int res) {
		bitindex = new DDDNode[res];
	}
	
}

class DDDNode {
	
	public List<Integer> indexes;
	
	public DDDNode() {
		indexes = new ArrayList<Integer>();
	}
	
}
