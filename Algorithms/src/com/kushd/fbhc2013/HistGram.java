package com.kushd.fbhc2013;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;





public class HistGram {
	
	public static void main(String[] args) {
		MyScanner sc = new MyScanner();
		String s   = sc.nextLine(); 
		int n = Integer.parseInt((s.split(" "))[0]);
		int m = Integer.parseInt((s.split(" "))[1]);
		int[] numbers = new int[n];
		for(int jj=0;jj<n;jj++){
			String ss = sc.nextLine();
			
			for(int ii=0;ii<m;ii++ ){
				if(ss.charAt(ii) == '1'){
					numbers[jj] = numbers[jj]+1;
				}
			}
		}
		int ans=largestRectangleArea(numbers);
		
		System.out.println(ans);
	}

	

	
	public static int largestRectangleArea(int[] height) {
        int size = height.length;
        if(size == 0){
            return 0;
        }
        int[] leftArray = new int[size];
        for(int i=0;i<size;i++){
            leftArray[i] =0;
        }
        helper(height,leftArray);
        int[] rightArray = new int[size];
        for(int i=0;i<size;i++){
            rightArray[i] =0;
        }
        reverse(height,size);
        helper(height,rightArray);
        reverse(rightArray,size);
        reverse(height,size);
        int max = 0;
        for(int i=0;i<size;i++){
            if(((leftArray[i]+rightArray[i]+1)*height[i]) > max){
                max = ((leftArray[i]+rightArray[i]+1)*height[i]);
            }
        }
        return max;
    }
    
    private static void helper(int[] height, int[] leftArray){
        int size = height.length;
        Stack<Integer> st = new Stack<Integer>();
        st.push(0);
        int i=1;
        while(i<size){
            int cur = height[st.peek()];
            while(height[i] < cur){
                int temp = st.pop();
                if(!st.isEmpty()){
                    leftArray[st.peek()] = leftArray[st.peek()] + leftArray[temp]+1;
                    cur = height[st.peek()];
                }else{
                    break;
                }
            }
            st.push(i);
            i++;
        }
        while(st.size() > 1){
            int temp = st.pop();
            leftArray[st.peek()] = leftArray[st.peek()] + leftArray[temp]+1;
        }
    }
    
    private static void reverse(int[] array, int size){
        for(int i=0;i<size/2;i++){
            int temp = array[i];
            array[i] = array[size-i-1];
            array[size-i-1] = temp;
        }
    }
    
    
    public static class MyScanner {
	      BufferedReader br;
	      StringTokenizer st;
	 
	      public MyScanner() {
	         br = new BufferedReader(new InputStreamReader(System.in));
	      }
	 
	      String next() {
	          while (st == null || !st.hasMoreElements()) {
	              try {
	                  st = new StringTokenizer(br.readLine());
	              } catch (IOException e) {
	                  e.printStackTrace();
	              }
	          }
	          return st.nextToken();
	      }
	 
	      int nextInt() {
	          return Integer.parseInt(next());
	      }
	 
	      long nextLong() {
	          return Long.parseLong(next());
	      }
	 
	      double nextDouble() {
	          return Double.parseDouble(next());
	      }
	 
	      String nextLine(){
	          String str = "";
		  try {
		     str = br.readLine();
		  } catch (IOException e) {
		     e.printStackTrace();
		  }
		  return str;
	      }

	   }

	
}
