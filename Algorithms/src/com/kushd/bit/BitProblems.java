package com.kushd.bit;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class BitProblems {

	public static void main(String[] args){
		//System.out.println(printBinary(0.72));
		//updateBits(5, 6, 7, 8);
		//System.out.println((~((1>>2)-1)));
		System.out.println(getNext(5));
	}
	
	public static int updateBits(int n, int m, int i, int j){
		int allOnes = ~0;
		System.out.println(allOnes);
		int left = allOnes << (j+1);
		
		int right = ((1<<i)-1);
		
		int mask = left | right;
		
		int n_cleared = n&mask;
		int m_shifted = m << i;
		
		return n_cleared | m_shifted;
	}
	
	public static String printBinary(double num){
		if(num >= 1 || num <= 0){
			return "ERROR";
		}
		StringBuilder binary = new StringBuilder();
		binary.append(".");
		while(num > 0){
			if(binary.length()>=64){
				return "ERR";
			}
			double r = 2*num;
			if(r>=1){
				binary.append(1);
				num = r-1;
			}else{
				binary.append(0);
				num = r;
			}
		}
		return binary.toString();
	}
	
	public static int getNext(int n){
		int temp =n; //0000 0101
		int c0=0;
		int c1=0;
		while(((temp&1)==0) && (temp!=0)){
			c0++;
			temp = (temp>>1);
		}
		//c0 = 1 and temp = 0000 0010
		while((temp&1)==1){
			c1++;
			temp = (temp>>1);
		}
		//c1 = 1 and temp = 0000 0001
		if(c0+c1 == 31 || c0+c1 == 0){
			return -1;
		}
		int p = c0+c1;
		//p=2
		n = n|(1<<p); // 0000 0101 | 0000 0100 = 
		n = n&(~((1>>p)-1)); // n = 0000 0000
		n = n|((1<<(c1-1))-1); 
		
		return n;
	}
	
	public static int swapOddEvenBits(int x){
		return (((x&(0xaaaaaaaa))>>1) | ((x&(0x55555555))<<1));
	}
	
	public static int findMissing(ArrayList<Integer> array){
		return findMissing(array, Integer.SIZE-1);
	}
	
	public static int findMissing(ArrayList<Integer> input, int column){
		if(column < 0){
			return 0;
		}
		ArrayList<Integer> oneBits = new ArrayList<Integer>(input.size()/2);
		ArrayList<Integer> zeroBits = new ArrayList<Integer>(input.size()/2);
		
		for( Integer t : input){
			if(/*t.fetch(column) == 0*/true){
				zeroBits.add(t);
			}else{
				oneBits.add(t);
			}
		}
		
		if(zeroBits.size() <= oneBits.size()){
			int v = findMissing(zeroBits, column-1);
			return (v<<1)|0;
		}else{
			int v = findMissing(oneBits,column-1);
			return (v<<1)|1;
		}
		
	}
	
	public static void findOpenNumber() throws FileNotFoundException{
		int bitsize = 1048576;
		int blockNum = 4096;
		byte[] bitfield = new byte[bitsize/8];
		int[] blocks = new int[blockNum];
		
		int starting = -1;
		Scanner in = new Scanner(new FileReader(""));
		
		while(in.hasNext()){
			int n = in.nextInt();
			blocks[n/bitsize]++;
		}
		for(int i=0;i<blocks.length;i++){
			if(blocks[i]<bitsize){
				starting = i*bitsize;
				break;
			}
		}
		
		in = new Scanner(new FileReader(""));
		while(in.hasNext()){
			int n = in.nextInt();
			if(n>=starting && n<(starting+bitsize)){
				bitfield[(n-starting)/8] |= 1<<((n-starting)%8);
			}
		}
		
		for(int i=0;i<bitfield.length;i++){
			for(int j=0;j<8;j++){
				if((bitfield[i]&(1<<j))==0){
					System.out.println((starting+i*8+j));
				}
			}
		}
	}
	
}
