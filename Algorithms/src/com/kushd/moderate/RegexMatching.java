package com.kushd.moderate;

public class RegexMatching {
	
	
	public static void main(String[] args) {
		System.out.println(stringMatchesRegex("", ""));
	}
	
	static int stringMatchesRegex(String inputString, String regexString) {
		
		return helper(inputString, regexString, 0,0);

    }
	
	static int helper(String input, String regex, int iIndex, int rIndex){
		if(rIndex == regex.length()){
			return (iIndex == input.length())?1:0;
		}
		if(iIndex == input.length()){
			if(rIndex == regex.length()){
				return 1;
			}else{
				return helper(input,regex,iIndex,rIndex+2);
			}
			
		}
		
		if((rIndex+1 == regex.length()) || (regex.charAt(rIndex+1) != '*' && regex.charAt(rIndex+1) != '+')){
			if((regex.charAt(rIndex) == input.charAt(iIndex))){
				return helper(input,regex,iIndex+1,rIndex+1);
			}
			return 0;
		}
		
		if(regex.charAt(rIndex+1) == '+'){
			if((regex.charAt(rIndex) == input.charAt(iIndex))){
				return helper(input,regex,iIndex+1,rIndex+2);
			}
			return 0;
		}
		
		while(iIndex < input.length() && (regex.charAt(rIndex) == input.charAt(iIndex))){
			if(helper(input,regex,iIndex,rIndex+2)==1){
				return 1;
			}
			iIndex++;
		}
		
		return helper(input,regex,iIndex,rIndex+2);
		
	}

	static int checkRegex(String regex, int rIndex) {
		int len = regex.length() - rIndex;
		if(len%2!=0){
			return 0;
		}
		for(int i=rIndex+1;i<regex.length();i=i+2){
			if(regex.charAt(i) != '*'){
				return 0;
			}
		}
		return 1;
	}


}
