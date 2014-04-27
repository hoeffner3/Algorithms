package com.kushd.leetcode.oj;
import java.util.HashMap;
import java.util.Map;

public class Scramble {
	
	public static void main(String[] args) {
		String s1 = "dsanujiiqwfsysnfsrwbrfhhpqicbw";
		String s2 = "dabbciwqphhfrwrsfnsysfwqiijuns";
		long time = System.currentTimeMillis();
		boolean ans = isScramble(s1, s2);
		System.out.println(time);
		System.out.println(System.currentTimeMillis());
		System.out.println("hurray");
		System.out.println(ans);
		
	}
	
	private static Map<String, Boolean> map = new HashMap<String, Boolean>();
    public static boolean isScramble(String s1, String s2) {
        map.clear();
        if(s1.length() != s2.length()){
            return false;
        }
        if(s1.length() == 0){
            return true;
        }
        int size = s1.length();
        return isScrambleHelper(s1,s2,size);
    }
    
    private static boolean isScrambleHelper(String s1,String s2,int size){
        if(size == 1){
            return (s1.equals(s2));
        }
        if(map.get(s1+"-"+s2) != null){
        	System.out.println(map.get(s1+"-"+s2));
            return map.get(s1+"-"+s2);
        }
        for(int i=1;i<size;i++){
            String leftS1 = getSubString(s1,0,i-1);
            String rightS1 = getSubString(s1,i,size-1);
            String leftS2 = getSubString(s2,0,i-1);
            String rightS2 = getSubString(s2,i,size-1);
            String rleftS2 = getRSubString(s2,0,i-1);
            String rrightS2 = getRSubString(s2,i,size-1);
            boolean oLeft = isScrambleHelper(leftS1,leftS2,i);
            map.put(leftS1+"-"+leftS2,oLeft);
            if(oLeft){
                boolean oRight = isScrambleHelper(rightS1,rightS2,size-i);
                map.put(rightS1+"-"+rightS2,oRight);
                if(oRight){
                    map.put(s1+"-"+s2,true);
                    return true;
                }else{
                    boolean rRight = isScrambleHelper(rightS1,rrightS2,size-i);
                    map.put(rightS1+"-"+rrightS2,rRight);
                    if(rRight){
                        map.put(s1+"-"+s2,true);
                        return true;
                    }else{
                        continue;
                    }
                }
            }else{
                boolean rLeft = isScrambleHelper(leftS1,rleftS2,i);
                map.put(leftS1+"-"+rleftS2,rLeft);
                if(rLeft){
                    boolean oRight = isScrambleHelper(rightS1,rightS2,size-i);
                    map.put(rightS1+"-"+rightS2,oRight);
                    if(oRight){
                        map.put(s1+"-"+s2,true);
                        return true;
                    }else{
                        boolean rRight = isScrambleHelper(rightS1,rrightS2,size-i);
                        map.put(rightS1+"-"+rrightS2,rRight);
                        if(rRight){
                            map.put(s1+"-"+s2,true);
                            return true;
                        }else{
                            continue;
                        }
                    }
                }else{
                    continue;
                }
            }
        }
        map.put(s1+"-"+s2,false);
        return false;
    }
    
    private static String getSubString(String s, int start, int end){
        StringBuilder sb = new StringBuilder();
        for(int i=start;i<=end;i++){
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }
    
    private static String getRSubString(String s, int start, int end){
        StringBuilder sb = new StringBuilder();
        for(int i=end;i>=start;i--){
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

}
