package com.kushd.moderate;

import java.io.*;
import java.util.*;

public class AddNum {
    
    public static void main(String[] args){
        System.out.println(addnum("1.2345-21.24"));
    }   
    /**
     * @param input
     *      two numbers joined by a + sign ("A+B") where both A and B are non-negative float numbers
     * @return
     * 
     **/
    private static String addnum(String input){
        String[] nums = input.split("-");
        int rcntF = 0;
        int lcntF = 0;
        int indexF = nums[0].indexOf('.');
        if(indexF==-1){
            lcntF = nums[0].length();
        }else{
            lcntF = indexF;
            rcntF = nums[0].length()-indexF-1;
        }
        
        int rcntS = 0;
        int lcntS = 0;
        int indexS = nums[1].indexOf('.');
        if(indexS==-1){
            lcntS = nums[1].length();
        }else{
            lcntS = indexS;
            rcntS = nums[1].length()-indexS-1;
        }
        StringBuilder sb = new StringBuilder();
        int maxrcnt = Math.max(rcntS,rcntF);
        int carry=0;
        for(int i=maxrcnt;i>0;i--){
            int temp=0;
            if(lcntF+i < nums[0].length()){
               temp = temp + Integer.parseInt(nums[0].substring(lcntF+i,lcntF+i+1)); 
            }
            if(lcntS+i < nums[1].length()){
               temp = temp + Integer.parseInt(nums[1].substring(lcntS+i,lcntS+i+1)); 
            }
            temp = temp + carry;
            int temp2 = temp%10;
            carry = temp/10;
            sb.append(temp2);
        }
        if(indexF != -1 || indexS != -1){
            sb.append(".");
        }
        int f = lcntF-1;
        int s = lcntS-1;
        while(s>=0||f>=0){
            int temp=0;
            if(f>=0){
                temp = temp + Integer.parseInt(nums[0].substring(f,f+1)); 
            }
            if(s>=0){
                temp = temp + Integer.parseInt(nums[1].substring(s,s+1));
            }
            temp = temp + carry;
            int temp2 = temp%10;
            carry = temp/10;
            sb.append(temp2);
            f--;s--;
        }
        if(carry>0){
            sb.append(carry);
        }
        
        return sb.reverse().toString();
    }
    
    
}