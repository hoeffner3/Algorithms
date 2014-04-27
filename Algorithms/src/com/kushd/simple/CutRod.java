
package com.kushd.simple;

public class CutRod {
	
	private static int[] pr = {1,5,8,9,10,17,17,20,24,30};
	
	public static void main(String[] args){
		int sz = 10;
		int r = cutRod(pr, sz);
		System.out.println(r);
		r = memcutRod(pr,sz);
		System.out.println(r);
		r = bottomupcutRod(pr, sz);
		System.out.println(r);
	}
	
	private static int cutRod(int[] prices, int index){
		if(index==0){
			return 0;
		}
		
		int q = -99999;
		for(int i=1;i<=index;i++){
			q = Math.max(q, (prices[i-1]+cutRod(prices,index-i)));
		}
		return q;
	}
	
	private static int memcutRod(int[] prices, int index){
		int[] res = new int[index];
		for(int k=0;k<index;k++){
			res[k] = -1;
		}
		return memauxcutRod(prices,index,res);
	}
	
	private static int memauxcutRod(int[] prices, int index, int[] res){
		if(index==0){
			return 0;
		}
		if(res[index-1]>=0){
			return res[index-1];
		}
		int q=-99999;
		for(int i=1;i<=index;i++){
			q = Math.max(q, (prices[i-1]+memauxcutRod(prices,index-i,res)));
		}
		res[index-1] = q;
		return q;
	}
	
	private static int bottomupcutRod(int[] prices, int index){
		int[] res = new int[index+1];
		res[0] = 0;
		int q;
		for(int j=1;j<=index;j++){
			q = -9999;
			for(int i=1;i<=j;i++){
				q = Math.max(q, prices[i-1]+res[j-i]);
			}
			res[j] = q;
		}
		return res[index];
	}

}
