package com.kushd.moderate;

import java.io.*;
import java.util.*;

public class NGram {
    
    public static void main(String[] args){
        Map<String,Integer> map = ngram("to be or not to be",7);
        Set<String> keys = map.keySet();
        for(String key : keys){
            System.out.println(key+": "+map.get(key));
        }
    }
    
    private static Map<String,Integer> ngram(String input, int n){
        Map<String,Integer> map = new LinkedHashMap<String,Integer>();
        String[] words = input.split(" ");
        int i=0;
        while(i<words.length-n+1){
            StringBuilder sb = new StringBuilder();
            for(int j=i;j<i+n;j++){
                sb.append(words[j]);
                sb.append(" ");
            }
            if(map.get(sb.toString()) == null){
                map.put(sb.toString(),0);
            }
            map.put(sb.toString(),map.get(sb.toString())+1);
            i++;
        }
        
        return map;
    }
    
    
}