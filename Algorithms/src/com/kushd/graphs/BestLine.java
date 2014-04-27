package com.kushd.graphs;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class BestLine {
	
	public static void main(String[] args) {
		Point p1 = new Point(0,0);
		Point p2 = new Point(1,0);
		Point[] points = {p1,p2};
		int ans = maxPoints(points);
		System.out.println(ans);
	}
	
	public static int maxPoints(Point[] points) {
        Line bestline=null;
        int max=0;
		Map<Line,Integer> lineMap = new HashMap<Line, Integer>();
		for(int i=0;i<points.length-1;i++){
			for(int j=i+1;j<points.length;j++){
				Line line = new Line(points[i], points[j]);
				if(!lineMap.containsKey(line)){
					lineMap.put(line, 0);
				}
				lineMap.put(line, lineMap.get(line)+1);
				if(bestline==null || lineMap.get(line) > lineMap.get(bestline)){
					bestline = line;
					max = lineMap.get(line);
				}
			}
		}
		return max;
        
    }

}


class Line{
	private static double epsilon = 0.0001;
	private double slope;
	private double intercept;
	private boolean isInfiniteSlope;
	public Line(Point p, Point q){
		if(Math.abs(p.x-q.x) > epsilon){
			slope = (p.y-q.y)/(p.x-q.x);
			intercept = p.y-slope*p.x;
		}else{
			isInfiniteSlope=true;
			intercept = p.x;
		}
	}
	
	public boolean isEqual(double a, double b){
		return (Math.abs(a-b) <epsilon);
	}
	
	@Override
	public int hashCode() {
		int sl = (int)(slope*1000);
		int il = (int)(intercept*1000);
		return sl|il;
	}
	
	@Override
	public boolean equals(Object obj) {
		Line l = (Line)obj;
		if(isEqual(intercept, l.intercept) &&
				isEqual(slope, l.slope) &&
				isInfiniteSlope == l.isInfiniteSlope){
			return true;
		}
		return false;
	}
}
