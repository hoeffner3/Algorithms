package com.kushd.fbhc2013;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class TennisGame {
	
	private static int scale = 13;
	private static BigDecimal thold = new BigDecimal("0.00000000001").setScale(scale, RoundingMode.HALF_UP);
	private static BigDecimal thold2 = new BigDecimal("0.000001").setScale(scale, RoundingMode.HALF_UP);
	private static BigDecimal kSum=new BigDecimal(0).setScale(scale, RoundingMode.HALF_UP);
	private static int K = 25;
	private static BigDecimal ps = new BigDecimal("0.984").setScale(scale, RoundingMode.HALF_UP);
	private static BigDecimal pr = new BigDecimal("0.222").setScale(scale, RoundingMode.HALF_UP);
	private static BigDecimal pi = new BigDecimal("0.993").setScale(scale, RoundingMode.HALF_UP);
	private static BigDecimal pu = new BigDecimal("0.336").setScale(scale, RoundingMode.HALF_UP);
	private static BigDecimal pw = new BigDecimal("0.207").setScale(scale, RoundingMode.HALF_UP);
	private static BigDecimal pd = new BigDecimal("0.084").setScale(scale, RoundingMode.HALF_UP);
	private static BigDecimal pl = new BigDecimal("0.478").setScale(scale, RoundingMode.HALF_UP);
	
	//private static int K = 2;
	//private static double ps = 0.600;
	//private static double pr = 0.200;
	//private static double pi = 0.500;
	//private static double pu = 0.100;
	//private static double pw = 1.000;
	//private static double pd = 0.100;
	//private static double pl = 1.000;
	
	public static void main(String[] args) {
		List<BigDecimal> list = new ArrayList<BigDecimal>();
		BigDecimal globalSum= new BigDecimal(0).setScale(scale,RoundingMode.HALF_UP);
		for(int i=0;i<K;i++){
			kSum = new BigDecimal(0).setScale(scale, RoundingMode.HALF_UP);
			list.clear();
			BigDecimal one = new BigDecimal(1).setScale(scale, RoundingMode.HALF_UP);
			findProbability(list, K, i, pi,one);
			System.out.println(kSum.toString());
			globalSum = globalSum.add(kSum);
			if(kSum.compareTo(thold2) < 0){
				break;
			}
		}
		DecimalFormat df = new DecimalFormat("#.0000000000"); 
		String tempString = df.format(globalSum);
		System.out.println("Global Sum : "+tempString);
		
	}
	
	private static boolean findProbability(List<BigDecimal> list, int wAllowed, int lAllowed, BigDecimal pi,
			BigDecimal mult){
		if(wAllowed == 0&&lAllowed==0){
			//BigDecimal temp = new BigDecimal(1).setScale(scale, RoundingMode.HALF_UP);
			//for(BigDecimal p : list){
			//	temp = temp.multiply(p);
			//}
			kSum = kSum.add(mult); 
			return true;
		}
		if(mult.compareTo(thold)<0){
			System.out.println("************************************************************");
			return false;
		}
		BigDecimal pWin = new BigDecimal(0).setScale(scale, RoundingMode.HALF_UP);
		BigDecimal pLoss = new BigDecimal(0).setScale(scale, RoundingMode.HALF_UP);
		BigDecimal nextpiW = new BigDecimal(0).setScale(scale, RoundingMode.HALF_UP);
		BigDecimal nextpiL = new BigDecimal(0).setScale(scale, RoundingMode.HALF_UP);
		BigDecimal temp1 = new BigDecimal(0).setScale(scale, RoundingMode.HALF_UP);
		BigDecimal temp2 = new BigDecimal(0).setScale(scale, RoundingMode.HALF_UP);
		BigDecimal temp3 = new BigDecimal(0).setScale(scale, RoundingMode.HALF_UP);
		BigDecimal temp4 = new BigDecimal(0).setScale(scale, RoundingMode.HALF_UP);
		BigDecimal one = new BigDecimal(1).setScale(scale, RoundingMode.HALF_UP);
		BigDecimal zero = new BigDecimal(0).setScale(scale, RoundingMode.HALF_UP);
		temp1 = ps.multiply(pi); temp2 = one.subtract(pi); temp3 = temp2.multiply(pr);
		pWin = temp1.add(temp3);
		pLoss = one.subtract(pWin);
		temp1 = pi.add(pu); temp2 = temp1.multiply(pw); temp3 = one.subtract(pw); temp4 = temp3.multiply(pi);
		nextpiW = temp2.add(temp4);
		temp1 = pi.subtract(pd); temp2 = temp1.multiply(pl); temp3 = one.subtract(pl); temp4 = temp3.multiply(pi);
		nextpiL = temp2.add(temp4);
		if(nextpiW.compareTo(one) > 0){
			nextpiW = new BigDecimal(1).setScale(scale, RoundingMode.HALF_UP);
		}
		if(nextpiL.compareTo(zero) < 0){
			nextpiL = new BigDecimal(0).setScale(scale, RoundingMode.HALF_UP);
		}
		
		if(lAllowed > 0){
			//list.add(pLoss);
			mult = mult.multiply(pLoss);
			boolean ress = findProbability(list, wAllowed, lAllowed-1, nextpiL,mult);
			mult = mult.divide(pLoss);
			//list.remove(pLoss);
			if(!ress){return false; }
		}
		if((wAllowed > 1) || (wAllowed==1&&lAllowed==0)){
			//list.add(pWin);
			mult = mult.multiply(pWin);
			boolean ress = findProbability(list, wAllowed-1, lAllowed, nextpiW,mult);
			mult = mult.divide(pWin);
			//list.remove(pWin);
			if(!ress){return false; }
		}
		return true;
	}

}
