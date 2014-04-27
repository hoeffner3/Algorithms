package com.kushd.leetcode.oj;
public class DivideNum {
	
	public static void main(String[] args) {
		System.out.println(Integer.MAX_VALUE);
		int sol = divide(-1010369383,-2147483648);
		System.out.println(sol);
	}
	
	public static int divide(int dividend, int divisor) {
        int sign=(getSign(dividend) * getSign(divisor));
        long ldivisor = Math.abs((long)(divisor));
        long ldividend = Math.abs((long)(dividend));
        if(ldivisor==0){
            return Integer.MAX_VALUE;
        }
        int i=1;
        long temp = ldivisor;
        long prevTemp = 0;
        int sol=0;
        while(ldividend >= ldivisor){
            prevTemp = temp;
            temp = temp + temp;
            if(temp > ldividend){
                ldividend = (ldividend - prevTemp);
                temp = ldivisor;
                sol=sol+i;
                i=1;
            }else{
                i = i + i;
            }
        }
        return (sol*sign);
    }
    
   
    
    private static int getSign(int n){
		int x = (((n>>31)&0x1)^1);
		if(x==1){
			return 1;
		}else{
			return -1;
		}
	}

}
