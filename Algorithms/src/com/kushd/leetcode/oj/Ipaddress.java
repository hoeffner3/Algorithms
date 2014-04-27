package com.kushd.leetcode.oj;
import java.util.ArrayList;
import java.util.Stack;

public class Ipaddress {
	
	public static void main(String[] args) {
		String s = "0279245587303";
		ArrayList<String> list = restoreIpAddresses(s);
		System.out.println("hi");
		for(String ss : list){
			System.out.println(ss);
		}
		
	}

	public static ArrayList<String> restoreIpAddresses(String s) {
        return recurse(s,1);
        
    }
    
    private static ArrayList<String> recurse(String s, int index){
        if(index == 4){
            ArrayList<String> list = new ArrayList<String>();
            if(s.length() >=1 && s.length() <= 3 && Integer.parseInt(s) >=0 && Integer.parseInt(s) <=255){
                list.add(new String(s));
            }
            return list;
        }
        ArrayList<String> list = new ArrayList<String>();
        int size = s.length();
        for(int i =1; i<=size;i++){
            if(i>3){
                break;
            }
            String temp = s.substring(0,i);
            if(temp.length() < size && Integer.parseInt(temp) >=0 && Integer.parseInt(temp) <=255){
                ArrayList<String> rList = recurse(s.substring(i,size),index+1);
                for(String ss : rList){
                    list.add(new String(temp+"."+ss));
                }
            }
        }
        return list;
    }
}
