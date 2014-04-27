package com.kushd.leetcode.oj;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class FindLadders {
	
	public static void main(String[] args) {
		String start = "a";
		String end = "c";
		HashSet<String> dict = new HashSet<String>();
		dict.add("a");
		dict.add("b");
		dict.add("c");
		
		ArrayList<ArrayList<String>> finalList = findLadders(start, end, dict);
		for(ArrayList<String> list : finalList){
			System.out.println("{");
			for(String element : list){
				System.out.println(element);
			}
			System.out.println("}");
		}
	}
	
	
	private static ArrayList<ArrayList<String>> finalList = new ArrayList<ArrayList<String>>();
    
    public static ArrayList<ArrayList<String>> findLadders(String start, String end, HashSet<String> dict) {
        finalList.clear();
        Queue<ArrayList<String>> queue1 = new LinkedList<ArrayList<String>>();
        Queue<ArrayList<String>> queue2 = new LinkedList<ArrayList<String>>();
        ArrayList<String> list = new ArrayList<String>();
        list.add(start);
        queue1.add(list);
        boolean found = false;
        while(!queue1.isEmpty()){
            ArrayList<String> curList = queue1.remove();
            ArrayList<ArrayList<String>> nextList = helper(dict, curList);
            for(ArrayList<String> nlist : nextList){
                if(nlist.get(nlist.size()-1).equals(end)){
                    found=true;
                    finalList.add(nlist);
                }
                queue2.add(nlist);
            }
            if(queue1.isEmpty() && !found){
                while(!queue2.isEmpty()){
                    queue1.add(queue2.remove());
                }
            }
        }
        return finalList;
    }
    
    private static ArrayList<ArrayList<String>> helper(HashSet<String> dict, ArrayList<String> list){
        for(String element : list){
            dict.remove(element);
        } 
        String curStart = list.get(list.size()-1);
        ArrayList<ArrayList<String>> newList = new ArrayList<ArrayList<String>>();
        for(String key : dict){
            if(isValid(curStart,key)){
                ArrayList<String> innerList = new ArrayList<String>();
                innerList.addAll(list);
                innerList.add(key);
                newList.add(innerList);
            }
        }
        for(String element : list){
            dict.add(element);
        } 
        return newList;
    }
    
    private static boolean isValid(String start, String next){
        int count=0;
        for(int i=0;i<start.length();i++){
            if(start.charAt(i) != next.charAt(i)){
                count++;
            }
        }
        return (count == 1);
    }

}
