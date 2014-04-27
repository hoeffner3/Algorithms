package com.kushd.moderate;

public class IncreasingSequence {
	
	public static void main(String[] args) {
		int[] arr = {5,7,3,1000,1,2,55,98,6,72};
		System.out.println(helper2(arr));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void hill(int[] v) {
        int max=0;
        for(int i=0;i<v.length;i++){
            if(v[i] > max){
                max = v[i];
            }
        }
        
        int low=0;
        int high=10000;
        int flag=0;
        while(low<=high){
            int mid = low + (high-low)/2;
            if(helper(v,mid)){
                high = mid-1;
                flag=0;
            }else{
                low = mid+1;
                flag=1;
            }
        }
        
        if(flag==1){
            System.out.println(high);
        }else{
            System.out.println(low);
        }
        
    }
    
    private static boolean helper(int[] arr, int num){
        int[] newarr = new int[arr.length];
        newarr[0] = arr[0]-num;
        for(int i=1;i<arr.length;i++){
            if(arr[i] > newarr[i-1]){
                int temp = arr[i]-num;
                if(temp<=newarr[i-1]){
                    temp = newarr[i-1]+1;
                }
                newarr[i] = temp;
            }else{
                int temp = arr[i]+num;
                if(temp <= newarr[i-1]){
                    return false;
                }else{
                    newarr[i] = newarr[i-1]+1;
                }
            }
        }
        return true;
    }
    
    private static int helper2(int[] arr){
    	int[] newarr = new int[arr.length];
    	newarr[0] = arr[0];
    	int min=0;
    	for(int i=1;i<arr.length;i++){
    		if(arr[i] > newarr[i-1]){
    			int temp = arr[i]-min;
    			if(temp<=newarr[i-1]){
    				temp = newarr[i-1]+1;
    			}
    			newarr[i] = temp;
    		}else{
    			int temp = arr[i]+min;
    			if(temp>newarr[i-1]){
    				newarr[i] = newarr[i-1]+1;
    			}else{
    				int temp2 = (newarr[i-1]+temp-1)/2;
    				newarr[i-1] = temp2;
    				newarr[i] = temp2+1;
    				if((i-2) >=0 && (newarr[i-2] - newarr[i-1]) >=0){
    					min = min + (newarr[i-2] - newarr[i-1]+1);
    				}
    				if(Math.abs(arr[i-1]-newarr[i-1]) > min){
    					min = Math.abs(arr[i-1]-newarr[i-1]);
    				}
    				if(Math.abs(arr[i]-newarr[i]) > min){
    					min = Math.abs(arr[i]-newarr[i]);
    				}
    			}
    		}
    	}
    	return min;
    }

}
