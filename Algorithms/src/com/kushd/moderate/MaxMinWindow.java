package com.kushd.moderate;

import java.util.LinkedList;
import java.util.Queue;

public class MaxMinWindow {
	
	public static void main(String[] args) {
		int[] arr = {6,9,4,7,4,1};
		int w=3;
		System.out.println(helper(arr, w));
	}
	
	
	
	public static int helper(int[] array, int w){
		
		
		
		int[] max = getMax(array,w);
		int[] min = getMin(array,w);
		
		int maxDev=0;
		for(int i=0;i<max.length;i++){
			if((max[i]-min[i]) > maxDev){
				maxDev = (max[i]-min[i]);
			}
		}
		return maxDev;
	}

	private static int[] getMax(int[] array, int w) {
		LinkedList<Integer> queue = new LinkedList<Integer>();
		int[] max = new int[array.length-w+1];
		for(int i=0;i<w;i++){
			while(!queue.isEmpty() && array[i] >= array[queue.peekLast()]){
				queue.removeLast();
			}
			queue.addLast(i);
		}
		for(int i=w;i<array.length;i++){
			max[i-w] = array[queue.peekFirst()];
			while(!queue.isEmpty() && array[i] >= array[queue.peekLast()]){
				queue.removeLast();
			}
			while(!queue.isEmpty() && queue.peekFirst() < (i-w)){
				queue.removeFirst();
			}
			queue.addLast(i);
			
		}
		max[array.length-w] = array[queue.peekFirst()];
		return max;
	}
	
	private static int[] getMin(int[] array, int w) {
		LinkedList<Integer> queue = new LinkedList<Integer>();
		int[] min = new int[array.length-w+1];
		for(int i=0;i<w;i++){
			while(!queue.isEmpty() && array[i] <= array[queue.peekLast()]){
				queue.removeLast();
			}
			queue.addLast(i);
		}
		for(int i=w;i<array.length;i++){
			min[i-w] = array[queue.peekFirst()];
			while(!queue.isEmpty() && array[i] <= array[queue.peekLast()]){
				queue.removeLast();
			}
			while(!queue.isEmpty() && queue.peekFirst() < (i-w)){
				queue.removeFirst();
			}
			queue.addLast(i);
		}
		min[array.length-w] = array[queue.peekFirst()];
		return min;
	}
	
	

}
