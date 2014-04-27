package com.kushd.hackerrank;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SimpleRegression {
	
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String strLine = null;
				strLine = br.readLine();
				int nn = Integer.parseInt(strLine.split(" ")[0]);
				int mm = Integer.parseInt(strLine.split(" ")[1]);
				QAClassifier qac = new QAClassifier(mm);
				List<DataPoint> dataPoints = new ArrayList<DataPoint>(nn);
				for(int i=0;i<nn;i++){
					strLine = br.readLine();
					DataPoint dp = new DataPoint(mm);
					dp.identifier = strLine.split(" ")[0];
					dp.answer = strLine.split(" ")[1].equals("+1")?true:false;
					for(int j=0;j<mm;j++){
						String temp = strLine.split(" ")[j+2];
						dp.features.add(Double.parseDouble(temp.split(":")[1]));
					}
					dataPoints.add(dp);
				}
				List<Double> normalizer = getNorm(dataPoints,mm);
				for(DataPoint dp : dataPoints){
					dp.features = multList(normalizer, dp.features);
				}
				
				qac.train(dataPoints);
				strLine = br.readLine();
				int qq = Integer.parseInt(strLine.trim());
				List<DataPoint> dPoints = new ArrayList<DataPoint>(qq);
				for(int i=0;i<qq;i++){
					strLine = br.readLine();
					DataPoint dp = new DataPoint(mm);
					dp.identifier = strLine.split(" ")[0];
					for(int j=0;j<mm;j++){
						String temp = strLine.split(" ")[j+1];
						dp.features.add(Double.parseDouble(temp.split(":")[1]));
					}
					dp.features = multList(normalizer, dp.features);
					dPoints.add(dp);
				}
				
				qac.classify(dPoints);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static List<Double> getNorm(List<DataPoint> dataPoints,int mm) {
		List<Double> norm = new ArrayList<Double>(mm);
		for(int i=0;i<mm;i++){
			Double maxParamValue = getMaxParamValue(dataPoints,i);
			if(maxParamValue==0){
				norm.add(0.0);
			}else{
				norm.add(1/maxParamValue);
			}
		}
		return norm;
	}

	private static Double getMaxParamValue(List<DataPoint> dataPoints, int i) {
		double max = -1.0;
		for(DataPoint dp : dataPoints){
			double temp = Math.abs(dp.features.get(i));
			if(temp > max){
				max = temp;
			}
		}
		return max;
	}
	
	private static List<Double> multList(List<Double> l1, List<Double> l2){
		List<Double> nl = new ArrayList<Double>(l1.size());
		for(int i=0;i<l1.size();i++){
			nl.add((l1.get(i)*l2.get(i)));
		}
		return nl;
	}

}


class QAClassifier {
	
	private int iterations=100;
	private List<Double> weight;
	
	
	public QAClassifier(int numParams) {
		this.weight = new ArrayList<Double>(numParams);
		for(int i=0;i<numParams;i++){
			weight.add(0.0);
		}
	}
	
	
	public void train(List<DataPoint> dataPoints){
		for(int i=0;i<iterations;i++){
			for(DataPoint dp : dataPoints){
				boolean curans = dot(weight,dp.features) > 0;
				if(curans != dp.answer){
					if(dp.answer){
						weight = addList(weight, dp.features);
					}else{
						weight = addList(weight, negateList(dp.features));
					}
				}
			}
		}
	}
	
	public void classify(List<DataPoint> dataPoints){
		for(DataPoint dp : dataPoints){
			boolean ans = dot(weight,dp.features) > 0;
			if(ans){
				System.out.println(dp.identifier + " +1");
			}else{
				System.out.println(dp.identifier + " -1");
			}
		}
	}
	
	private List<Double> negateList(List<Double> l){
		List<Double> nl = new ArrayList<Double>(l.size());
		for(Double entry : l){
			nl.add((-1*entry));
		}
		return nl;
	}
	
	private List<Double> addList(List<Double> l1, List<Double> l2){
		List<Double> nl = new ArrayList<Double>(l1.size());
		for(int i=0;i<l1.size();i++){
			nl.add((l1.get(i)+l2.get(i)));
		}
		return nl;
	}
	
	private List<Double> multList(List<Double> l1, List<Double> l2){
		List<Double> nl = new ArrayList<Double>(l1.size());
		for(int i=0;i<l1.size();i++){
			nl.add((l1.get(i)*l2.get(i)));
		}
		return nl;
	}
	
	private double dot(List<Double> l1, List<Double> l2){
		List<Double> nl = multList(l1, l2);
		double sum=0;
		for(int i=0;i<nl.size();i++){
			sum = sum + nl.get(i);
		}
		return sum;
	}
	
	
}

class DataPoint {
	
	public String identifier;
	public boolean answer;
	public List<Double> features;
	
	public DataPoint(int numParams) {
		this.features = new ArrayList<Double>(numParams);
	}
	
}
