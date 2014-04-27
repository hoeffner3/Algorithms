package com.kushd.fbhc2013;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Tennis {
	
	public static void main(String[] args) {
		FileInputStream fio=null;
		DataInputStream in=null;
		try {
			fio = new FileInputStream("/Users/kushal/Downloads/tennison.txt");
			in = new DataInputStream(fio);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine = br.readLine();
			int tc = Integer.parseInt(strLine);
			for(int i=0;i<tc;i++){
				strLine = br.readLine();
				String[] array = strLine.split(" ");
				process(array,i);
			}
			
			
			in.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	private static int scale = 12;
	private static BigDecimal thold = new BigDecimal("0.0000000001").setScale(scale, RoundingMode.HALF_UP);
	private static BigDecimal thold2 = new BigDecimal("0.000001").setScale(scale, RoundingMode.HALF_UP);
	private static BigDecimal kSum=new BigDecimal(0).setScale(scale, RoundingMode.HALF_UP);
	private static int K;
	private static BigDecimal ps; 
	private static BigDecimal pr;
	private static BigDecimal pi; 
	private static BigDecimal pu; 
	private static BigDecimal pw;
	private static BigDecimal pd;
	private static BigDecimal pl;
	private static long curTime;

	private static void process(String[] array,int iii) {
		K = Integer.parseInt(array[0]);
		ps = new BigDecimal(array[1]).setScale(scale, RoundingMode.HALF_UP);
		pr = new BigDecimal(array[2]).setScale(scale, RoundingMode.HALF_UP);
		pi = new BigDecimal(array[3]).setScale(scale, RoundingMode.HALF_UP);
		pu = new BigDecimal(array[4]).setScale(scale, RoundingMode.HALF_UP);
		pw = new BigDecimal(array[5]).setScale(scale, RoundingMode.HALF_UP);
		pd = new BigDecimal(array[6]).setScale(scale, RoundingMode.HALF_UP);
		pl = new BigDecimal(array[7]).setScale(scale, RoundingMode.HALF_UP);
		
		List<BigDecimal> list = new ArrayList<BigDecimal>();
		BigDecimal globalSum= new BigDecimal(0).setScale(scale,RoundingMode.HALF_UP);
		for(int i=0;i<K;i++){
			kSum = new BigDecimal(0).setScale(scale, RoundingMode.HALF_UP);
			list.clear();
			BigDecimal one = new BigDecimal(1).setScale(scale, RoundingMode.HALF_UP);
			curTime = System.currentTimeMillis();
			findProbability(list, K, i, pi,one);
			//System.out.println(kSum.toString());
			globalSum = globalSum.add(kSum);
			if(kSum.compareTo(thold2) < 0){
				break;
			}
		}
		DecimalFormat df = new DecimalFormat("0.000000"); 
		String tempString = df.format(globalSum);
		System.out.println("Case #"+(iii+1)+": "+tempString);
		
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
		if(mult.compareTo(thold)<0 || (System.currentTimeMillis()-curTime)>1000000){
			//System.out.println("************************************************************");
			return false;
		}
		BigDecimal pWin = new BigDecimal(0).setScale(scale, RoundingMode.HALF_UP);
		BigDecimal pLoss = new BigDecimal(0).setScale(scale, RoundingMode.HALF_UP);
		BigDecimal nextpiW = new BigDecimal(0).setScale(scale, RoundingMode.HALF_UP);
		BigDecimal nextpiL = new BigDecimal(0).setScale(scale, RoundingMode.HALF_UP);
		BigDecimal temp1 = new BigDecimal(0).setScale(scale, RoundingMode.HALF_UP);
		BigDecimal temp2 = new BigDecimal(0).setScale(scale, RoundingMode.HALF_UP);
		BigDecimal temp3 = new BigDecimal(0).setScale(scale, RoundingMode.HALF_UP);
		BigDecimal one = new BigDecimal(1).setScale(scale, RoundingMode.HALF_UP);
		BigDecimal zero = new BigDecimal(0).setScale(scale, RoundingMode.HALF_UP);
		temp1 = ps.multiply(pi); temp2 = one.subtract(pi); temp3 = temp2.multiply(pr);
		pWin = temp1.add(temp3);
		pLoss = one.subtract(pWin);
		temp2 = pu.multiply(pw);
		nextpiW = pi.add(temp2);
		temp2 = pd.multiply(pl);
		nextpiL = pi.subtract(temp2);
		if(nextpiW.compareTo(one) > 0){
			nextpiW = new BigDecimal(1).setScale(scale, RoundingMode.HALF_UP);
		}
		if(nextpiL.compareTo(zero) < 0){
			nextpiL = new BigDecimal(0).setScale(scale, RoundingMode.HALF_UP);
		}
		//
		if(pLoss.compareTo(pWin) > 0){
			if(lAllowed > 0){
				mult = mult.multiply(pLoss);
				boolean ress = findProbability(list, wAllowed, lAllowed-1, nextpiL,mult);
				mult = mult.divide(pLoss);
				if(!ress){return false; }
			}
			if((wAllowed > 1) || (wAllowed==1&&lAllowed==0)){
				mult = mult.multiply(pWin);
				boolean ress = findProbability(list, wAllowed-1, lAllowed, nextpiW,mult);
				mult = mult.divide(pWin);
				if(!ress){return false; }
			}
		}else{
			if((wAllowed > 1) || (wAllowed==1&&lAllowed==0)){
				mult = mult.multiply(pWin);
				boolean ress = findProbability(list, wAllowed-1, lAllowed, nextpiW,mult);
				mult = mult.divide(pWin);
				if(!ress){return false; }
			}
			if(lAllowed > 0){
				mult = mult.multiply(pLoss);
				boolean ress = findProbability(list, wAllowed, lAllowed-1, nextpiL,mult);
				mult = mult.divide(pLoss);
				if(!ress){return false; }
			}
		}
		//
		return true;
	}

}
