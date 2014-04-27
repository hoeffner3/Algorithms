package com.kushd.moderate;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WordBreak {

	static Set<String> dict;
	
	public static void main(String[] args){
		dict = new HashSet<String>();
		dict.add("new");
		dict.add("apple");
		dict.add("pie");
		String input = "pieapsplenew";
		Result minR = parseString(input, 0, 0, new HashMap<Integer,Result>());
		System.out.println(minR.parse+ "*****"+minR.inv);
		//System.out.println(segmentString(input, dict));
	}
	
	public static String segmentString(String input, Set<String> dict){
		if(dict.contains(input))
			return input;
		int len = input.length();
		for(int i=0;i<len;i++){
			String prefix = input.substring(0, i);
			if(dict.contains(prefix)){
				String suffix = input.substring(i, len);
				String recSuffix = segmentString(suffix, dict);
				if(recSuffix!=null){
					return prefix+" "+recSuffix;
				}
			}
		}
		return null;
	}
	
	public static Result parseString(String sentence, int start, int end, Map<Integer,Result> cache){
		if(end>=sentence.length()){
			return new Result(end-start, "");
		}
		if(cache.get(start) != null){
			return cache.get(start).cloneR();
		}
		String subword = sentence.substring(start,end+1);
		Result bestExact = parseString(sentence, end+1, end+1, cache);
		if(dict.contains(subword)){
			bestExact = new Result(bestExact.inv,subword + " " + bestExact.parse);
		}else{
			//bestExact.inv += subword.length();
			bestExact = new Result(bestExact.inv + subword.length(),subword.toUpperCase() + " " + bestExact.parse);
		}
		
		Result bestExtend = parseString(sentence, start, end+1, cache);
		
		Result minR = Result.minR(bestExact, bestExtend);
		cache.put(start, minR);
		return minR;
	}
	
}

class Result{
	int inv;
	String parse;
	Result(int i, String p){
		inv =i;
		parse=p;
	}
	
	protected Result cloneR()  {
		
		return new Result(this.inv, this.parse);
	}
	protected static Result minR(Result r1, Result r2){
		return r1.inv > r2.inv ? r2:r1;
	}
	
}
