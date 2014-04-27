package com.kushd.simple;

public class HeapSort {
	
	private static int[] array = {4,1,3,2,16,9,10,14,8,7};
	
	public static void main(String[] args) {
		
		heapSort(array);
		for(int k=0;k<array.length;k++){
			System.out.println(array[k]);
		}
		//displayHeap(array);
	}
	
	private static void displayHeap(int[] arr){
		int size = arr.length;
		int i=0;
		int level = 1;
		while(i<size){
			if(i>=level){
				System.out.println("");
				level = level*2;
				level = level+1;
			}
			System.out.print(arr[i]+" ");
			i++;
		}
	}
	
	private static void heapSort(int[] arr){
		int size = (arr.length-1);
		buildHeap(arr,size);
		int i=size;
		while(i>0){
			int temp = arr[0];
			arr[0] = arr[i];
			arr[i] = temp;
			
			size = size-1;
			i--;
			maxHeapify(arr, 0, size);
		}
	}
	
	public static int findKsmallest(int[] arr, int k){
		buildHeap(arr,k-1);
		int size = arr.length;
		int i=k;
		while(i<size){
			if(arr[i]<arr[0]){
				arr[0] = arr[i];
				maxHeapify(arr, 0, k-1);
			}
			i++;
		}
		return arr[0];
	}
	
	private static void buildHeap(int[] arr,int size){
		int halfsize = (size+1)/2;
		int i=halfsize;
		while(i>=0){
			maxHeapify(arr, i,size);
			i--;
		}
	}
	
	private static void maxHeapify(int[] arr, int n,int size){
		int left = 2*n+1;
		int right = 2*n+2;
		int largest = n;
		if(left <= size && arr[left] > arr[n]){
			largest = left;
		}
		
		if(right <= size && arr[right] > arr[largest] ){
			largest = right;
		}
		
		if(largest != n){
			int temp = arr[largest];
			arr[largest] = arr[n];
			arr[n] = temp;
			maxHeapify(arr, largest,size);
		}
			
	}

}
