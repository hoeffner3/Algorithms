package com.kushd.simple;




public class BasicProblems {

	/**
	 * @param args
	 */
	private static int[] array = {23,12,45,24,56,2,52,35,14,29,23,12,45,24,56,2,52,35,14,29};
	
	public static void main(String[] args) {
		
		//System.out.println(fibbb(40));
		//Map<String,Long> map = new HashMap<String,Long>();
		//long[][] ans = nCR(100000,3);
		//System.out.println(ans[100000][3]);
		//System.out.println(System.currentTimeMillis());
		//int[] array = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27};
		//int[] data = new int [13];
		//combinationUtil(array, data, 0, 26, 0, 13);
		//System.out.println(System.currentTimeMillis());
		/*Map<String,Integer> map = new HashMap<String,Integer>();
		for(int i=0;i<500000;i++){
			String aa = "aaaaaaaaaaaaaaaaaa"+i;
			map.put(aa, i);
		}*/
		
		System.out.println(findInsPoint("fffffffff", 'f'));
	}
	
	public static int fibbb(int n){
		if(n==0||n==1){
			return 1;
		}
		
		//int ans = fibbb(n-1) + fibbb(n-2);
		int i=0;
		int ans=0;
		int p=1;
		int k=1;
		while(i<(n-1)){
			ans = p+k;
			p=k;
			k=ans;
			i++;
		}
		return ans;
	}
	
	public static long fact(long n){
		if(n==0||n==1){
			return 1;
		}
		long ans=1;
		long i=2;
		while(i<=n){
			ans = ans*i;
			i++;
		}
		return ans;
	}
	
	public static long[][] nCR(long n, long r){
		long[][] array = new long[(int) n+1][(int) r+1];
		
		for(int i=0;i<=n;i++){
			array[i][0] = 1; 
		}
		for(int j=1;j<=r;j++){
			array[0][j] = 0;
		}
		for(int i=1;i<=n;i++){
			for(int j=1;j<=r;j++){
				if(r>n){
					array[i][j] = 0;
				}else{
					array[i][j] = array[i-1][j] + array[i-1][j-1];
				}
			}
		}
		
		return array;
	}
	
	public static void combinationUtil(int arr[], int data[], int start, int end, int index, int r)
	{
	    // Current combination is ready to be printed, print it
	    if (index == r)
	    {
	        for (int j=0; j<r; j++){
	        	System.out.println(data[j]);
	        }
	        System.out.println("");
	        return;
	    }
	 
	    // replace index with all possible elements. The condition
	    // "end-i+1 >= r-index" makes sure that including one element
	    // at index will make a combination with remaining elements
	    // at remaining positions
	    for (int i=start; i<=end && end-i+1 >= r-index; i++)
	    {
	        data[index] = arr[i];
	        combinationUtil(arr, data, i+1, end, index+1, r);
	    }
	}
	
	
	public static char findInsPoint(String sortedString, char c) {
		 
	    int len = sortedString.length();
	    int low = 0;
	    int high = len-1;
	    
	    while(low <= high){
	        int mid = low + (high-low)/2;
	        
	        if(sortedString.charAt(mid) <= c){
	            if(mid+1 < len && sortedString.charAt(mid+1) > c){
	                return sortedString.charAt(mid+1);
	            }else{
	                low = mid+1;
	            }
	        }else{
	            if(mid-1 >=0 && sortedString.charAt(mid-1) < c){
	                return sortedString.charAt(mid);
	            }else{
	                high = mid-1;
	            }
	        }
	    }
	    
	    return sortedString.charAt(0);
	 
	}
	

}
