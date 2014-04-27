package com.kushd.moderate;

public class KMP {
	
	// l(i) = the longest prefix of P(i-1) which is also a suffix of P(i)
	// f(i) = the longest prefix of P(i-2) which is also a suffix of P(i-1)
	//hence l(i) = f(i+1)
	
	
	
	public static int[] patternf(String pattern){
		char[] cpattern = pattern.toCharArray();
		int[] l = new int[pattern.length()];
		l[0] = -1;
		int i=1; int j=0;
		while(i<pattern.length()){
			if(cpattern[i] == cpattern[j]){
				l[i] = j; j++;i++;
			}else{
				if(j!=0){
					j = l[j-1]+1;
				}else{
					l[i] = -1;i++;
				}
			}
		}
		return l;
	}
	
	public static boolean patternMatching(String text, String pattern, int[] f){
		char[] ctext = text.toCharArray();
		char[] cpattern = pattern.toCharArray();
		
		int x=0; int y=0;
		while(y<text.length()){
			if(ctext[y] == cpattern[x]){
				y++;
				x++;
			}else{
				if(x==0){
					y++;
				}else{
					x = f[x]+1;
				}
			}
			
			if(x==pattern.length()){
				return true;
			}
		}
		return false;
	}

}
