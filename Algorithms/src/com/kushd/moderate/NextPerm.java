package com.kushd.moderate;

public class NextPerm {

	public static void main(String[] args){
        System.out.println(nextNum(0));
    }
    
    /**
     * Returns the next higher number which has exact same set of digits as original number. If no such number exists 
     * then returns the original number
     * @param num
     * @return
     */
    private static int nextNum(int num){
         String input = Integer.toString(num);
         int i=input.length()-1;
         while(i>0){
             if(input.charAt(i-1) < input.charAt(i)){
                 return helper(input,i-1);
             }
             i--;
         }
         return num;
    }
 
     private static int helper(String input, int index){
         StringBuilder sb = new StringBuilder();
         int i=0;
         while(i < index){
             sb.append(input.charAt(i));
             i++;
         }
         i=input.length()-1;
         while(i>index){
             if(input.charAt(i) > input.charAt(index)){
                 break;
             }
             i--;
         }
         int secIndex = i;
         sb.append(input.charAt(secIndex));
         i=input.length()-1;
         while(i>index){
             if(i==secIndex){
                 sb.append(input.charAt(index));
             }else{
                 sb.append(input.charAt(i));
             }
             i--;
         }
         
         return Integer.parseInt(sb.toString());
     }
	
}
