package com.kushd.hackerrank;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SegmentTree {
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String strLine = null;
				strLine = br.readLine();
				int nn = Integer.parseInt(strLine.trim());
				Point[] points = new Point[nn+1];
				for(int uu=1;uu<points.length;uu++){
					strLine = br.readLine();
					points[uu] = new Point();
					points[uu].x = Integer.parseInt(strLine.split(" ")[0]);
					points[uu].y = Integer.parseInt(strLine.split(" ")[1]);
				}
				ST st = new ST(points);
				st.init();
				strLine = br.readLine();
				int qq = Integer.parseInt(strLine.trim());
				
				for(int jj=0;jj<qq;jj++){
					strLine = br.readLine();
					String cmd = strLine.split(" ")[0];
					int i = Integer.parseInt(strLine.split(" ")[1]);
					int j = Integer.parseInt(strLine.split(" ")[2]);
					if(cmd.equals("X")){
						st.update(i, j, true);
					}else if(cmd.equals("Y")){
						st.update(i, j, false);
					}else{
						int[] ans = st.query(i, j);
						System.out.println(ans[0]+" "+ans[1]+" "+ans[2]+" "+ans[3]);
					}
				}
				//System.out.println(System.currentTimeMillis());
				//System.out.println(System.currentTimeMillis());
				//for(int jj=0;jj<qq;jj++){
				//	int index = nMap.query(tempArray[jj][0],tempArray[jj][1],tempArray[jj][2]); 
				//}
				//System.out.println(System.currentTimeMillis());
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}


class ST {
	
	private RNode[] nodes;
	private Point[] points;
	
	ST(Point[] points){
		this.points = points;
		int height = (int) Math.ceil(log2(points.length));
		int len = (int) Math.pow(2, height+1);
		this.nodes = new RNode[len];
	}
	
	private double log2(double x) {  
	     return Math.log(x)/Math.log(2.0d);  
	}
	
	public int[] query(int i, int j){
		return queryHelper(1,1,points.length-1,i,j,0,0);
	}
	
	private int[] queryHelper(int index, int start, int end, int i, int j, int xcnt, int ycnt){
		if(i > end || j < start){
			return new int[4];
		}
		if(start>=i&&end<=j){
			int[] toreturn = new int[4];
			toreturn[0] = nodes[index].values[0];
			toreturn[1] = nodes[index].values[1];
			toreturn[2] = nodes[index].values[2];
			toreturn[3] = nodes[index].values[3];
			updateNodeValues(toreturn, xcnt, ycnt);
			return toreturn;
		}
		int[] p1 = queryHelper(2*index, start, (start+end)/2, i, j, xcnt+nodes[index].xcnt, ycnt+nodes[index].ycnt);
		int[] p2 = queryHelper(2*index+1, ((start+end)/2)+1, end, i, j, xcnt+nodes[index].xcnt, ycnt+nodes[index].ycnt);
		int[] plk = new int[4];
		plk[0] = p1[0] + p2[0];
		plk[1] = p1[1] + p2[1];
		plk[2] = p1[2] + p2[2];
		plk[3] = p1[3] + p2[3];
		return plk;
	}
	
	public void init(){
		initialize(1, 1, points.length-1);
	}
	
	public void update(int i, int j, boolean xaxis){
		updateHelper(1,1,points.length-1,i,j,xaxis);
	}
	
	private void updateHelper(int index, int start, int end, int i, int j,boolean xaxis) {
		if(i > end || j < start){
			return;
		}
		if(start>=i&&end<=j){
			if(xaxis){
				nodes[index].xcnt++;
				updateNodeValues(nodes[index].values, 1,0);
			}else{
				nodes[index].ycnt++;
				updateNodeValues(nodes[index].values, 0, 1);
			}
			return;
		}
		updateHelper(2*index, start, (start+end)/2, i, j, xaxis);
		updateHelper(2*index+1, ((start+end)/2)+1, end, i, j, xaxis);
		
		nodes[index].values[0] = nodes[2*index].values[0] + nodes[2*index+1].values[0];
		nodes[index].values[1] = nodes[2*index].values[1] + nodes[2*index+1].values[1];
		nodes[index].values[2] = nodes[2*index].values[2] + nodes[2*index+1].values[2];
		nodes[index].values[3] = nodes[2*index].values[3] + nodes[2*index+1].values[3];
		updateNodeValues(nodes[index].values, nodes[index].xcnt, nodes[index].ycnt);
		
	}
	
	private void updateNodeValues(int[] values,int xcnt,int ycnt){
		xcnt = xcnt%2;
		ycnt = ycnt%2;
		if(xcnt==1&&ycnt==1){
			int temp = values[0];
			values[0] = values[2];
			values[2] = temp;
			temp = values[1];
			values[1] = values[3];
			values[3] = temp;
		}else if(xcnt==1&&ycnt==0){
			int temp = values[0];
			values[0] = values[3];
			values[3] = temp;
			temp = values[1];
			values[1] = values[2];
			values[2] = temp;
		}else if(xcnt==0&&ycnt==1){
			int temp = values[0];
			values[0] = values[1];
			values[1] = temp;
			temp = values[2];
			values[2] = values[3];
			values[3] = temp;
		}
	}

	private void initialize(int index, int start, int end){
		if(start == end){
			nodes[index] = new RNode();
			if(points[start].x > 0 && points[start].y > 0){
				nodes[index].values[0] = 1;
			}else if(points[start].x < 0 && points[start].y > 0){
				nodes[index].values[1] = 1;
			}else if(points[start].x < 0 && points[start].y < 0){
				nodes[index].values[2] = 1;
			}else{
				nodes[index].values[3] = 1;
			}
			return;
		}
		
		initialize(2*index, start, (start+end)/2);
		initialize(2*index+1, ((start+end)/2)+1, end);
		nodes[index] = new RNode();
		nodes[index].values[0] = nodes[2*index].values[0] + nodes[2*index+1].values[0];
		nodes[index].values[1] = nodes[2*index].values[1] + nodes[2*index+1].values[1];
		nodes[index].values[2] = nodes[2*index].values[2] + nodes[2*index+1].values[2];
		nodes[index].values[3] = nodes[2*index].values[3] + nodes[2*index+1].values[3];
	}
	
}

class RNode {
	
	public int[] values = new int[4];
	public int xcnt=0;
	public int ycnt=0;
}


