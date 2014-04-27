package com.kushd.leetcode.oj;
import java.util.HashMap;
import java.util.Map;

public class Interleave {
	
	public static void main(String[] args) {
		String s1 = "aabcc";
		String s2 = "dbbca";
		String s3 = "aadbcbbcac";
		System.out.println(isInterleave(s1, s2, s3));
	}
	
	private static Map<String,Boolean> map = new HashMap<String,Boolean>();
    public static boolean isInterleave(String s1, String s2, String s3) {
    	map.clear();
        int a = s1.length();
        int b = s2.length();
        int c = s3.length();
        if(c != a+b){
            return false;
        }
        return isInterleaveHelper(s1,s2,s3,a-1,b-1,c-1);
        
    }
    
    private static boolean isInterleaveHelper(String s1,String s2,String s3,int a,int b,int c){
    	if(a<0){
            return (s3.substring(0,c+1).equals(s2.substring(0,b+1)));
        }
        if(b<0){
            return (s3.substring(0,c+1).equals(s1.substring(0,a+1)));
        }
        if(map.get(a+""+b+""+c) != null){
            return map.get(a+""+b+""+c);
        }
        boolean ans = false;
        if(s3.charAt(c) == s2.charAt(b) && s3.charAt(c) == s1.charAt(a)){
            ans = (isInterleaveHelper(s1,s2,s3,a-1,b,c-1) || isInterleaveHelper(s1,s2,s3,a,b-1,c-1));
        }else if(s3.charAt(c) == s2.charAt(b)){
            ans = isInterleaveHelper(s1,s2,s3,a,b-1,c-1);
        }else if(s3.charAt(c) == s1.charAt(a)){
            ans = isInterleaveHelper(s1,s2,s3,a-1,b,c-1);
        }
        map.put(a+""+b+""+c,ans);
        return ans;
    }

}
