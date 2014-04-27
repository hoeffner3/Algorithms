package com.kushd.moderate;

public class LongestPalindrome {
	
	public static void main(String[] args){
		String input = "abababa";
		int[] lp = palindrome(input);
		int i=0;
		for(int vl : lp){
			System.out.println("lp["+i+"] is "+vl);
			i++;
		}
	}
	
	public static int[] palindrome(String input){
		
		int[] lp = new int[(input.length()*2)+1];
		int i=0; boolean issuffix;
		while(i<lp.length){
			if(i%2==0){
				lp[i] = getEven(input,i);
				issuffix = (lp[i]/2==(input.length()-(i/2)));
			}else{
				lp[i] = getOdd(input,i);
				issuffix = ((lp[i]-1)/2==(input.length()-((i+1)/2)));
			}
			int j = i-1;
			int actualp = actualStart(lp[i], i);
			i++;
			while(j>=0&&i<lp.length){
				if(issuffix||(lp[j]==0)||(actualStart(lp[j], j)<actualp)){
					lp[i] = lp[j];
					i++;j--;
				}else{
					break;
				}
			}
		}
		return lp;
	}
	
	private static int getEven(String cinput, int n){
		int left = n/2-1;
		int right = n/2;
		int result=0;
		while(left>=0&& right<cinput.length()){
			char lt = (cinput.charAt(left));
			char rt = (cinput.charAt(right));
			if(lt==rt){
				result+=2;
				left--;right++;
			}
			else{
				break;
			}
		}
		return result;
	}
	
	private static int getOdd(String cinput,int n){
		int right = ((n-1)/2)+1;
		int left = ((n-1)/2)-1;
		int result = 1;
		while(left>=0&& right<cinput.length()){
			char lt = (cinput.charAt(left));
			char rt = (cinput.charAt(right));
			if(lt==rt){
				result+=2;
				left--;right++;
			}
			else{
				break;
			}
		}
		return result;
	}
	
	private static int actualStart(int value, int n){
		return (n-value)/2;
	}

}
