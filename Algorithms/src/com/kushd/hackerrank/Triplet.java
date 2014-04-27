package com.kushd.hackerrank;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Triplet {

    public static void main(String[] args) {
        try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String strLine = null;
			
				strLine = br.readLine();
				int nn = Integer.parseInt(strLine.trim());
				int[] numbers = new int[nn];
				strLine = br.readLine();
				String[] values = strLine.split(" ");
				int cnt=0;	
				for(String value : values){
					numbers[cnt] = Integer.parseInt(value);
					cnt++;
				}
				long ans = process(numbers);
				System.out.println(ans);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    private static long process(int[] numbers){
        MMBST tree = new MMBST(true);
        for(int i=numbers.length-1;i>=0;i--){
            tree.insert(numbers[i],i);
        }
        for(int i=0;i<numbers.length;i++){
            tree.delete(numbers[i]);
        }
        tree.isBig = false;
        for(int i=0;i<numbers.length;i++){
            tree.insert(numbers[i],i);
        }
        Map<Integer,Integer> bigmap = tree.bigmap;
        Map<Integer,Integer> smallmap = tree.smallmap;
        Map<Long,Long> dmap = tree.dmap;
        long ans=0;
        for(int i=0;i<numbers.length;i++){
            ans = ans + ((bigmap.get(i))*(smallmap.get(i)));
        }
        Set<Long> keys = dmap.keySet();
        for(Long key : keys){
            ans = ans - (dmap.get(key));
        }
        return ans;
    }
    
    
}



class MMBST {
	
	public int size=0;
	private DummyNode root;
    public Map<Integer,Integer> bigmap = new HashMap<Integer,Integer>();
    public Map<Integer,Integer> smallmap = new HashMap<Integer,Integer>();
    public Map<Long,Long> dmap = new HashMap<Long,Long>();
    public boolean isBig;
    
    MMBST(boolean isBig){
        this.isBig = isBig;
    }
	
	public void insert(long number, int index){
		if(doesExist(root, number)){
			DummyNode node = getNode(root,number);
			if(isBig){
                bigmap.put(index,getNodeReachValue(root, number, 0)+node.rcnt);
                long mult=1;
                if(dmap.containsKey(node.value)){
                    mult = dmap.get(node.value);
                }
                dmap.put(node.value,(long)(node.big*mult));
            }else{
                smallmap.put(index,getNodeReachValue(root, number, 0)+node.lcnt);
                long mult=1;
                if(dmap.containsKey(node.value)){
                    mult = dmap.get(node.value);
                }
                dmap.put(node.value,(long)(node.small*mult));
            }
		}else{
			root = insertHelper(root,number,0,0,index);
		}
	}
	
	private DummyNode getNode(DummyNode node, long number){
		if(node.value == number){
			return node;
		}else if(number > node.value){
			return getNode(node.right,number);
		}else{
			return getNode(node.left,number);
		}
	}
	
	private int getNodeReachValue(DummyNode node, long number, int reach){
		if(node.value == number){
			return reach;
		}else if(number > node.value){
			if(isBig){
				return getNodeReachValue(node.right,number,reach);
			}else{
				return getNodeReachValue(node.right,number,reach+node.lcnt+1);
			}
		}else{
			if(isBig){
				return getNodeReachValue(node.left,number,reach+node.rcnt+1);
			}else{
				return getNodeReachValue(node.left,number,reach);
			}
		}
	}

	private DummyNode insertHelper(DummyNode node,long number,int small,int big,int index) {
		if(node == null){
			node = new DummyNode(number, 0, 0,1);
            if(isBig){
                bigmap.put(index,big);
                node.big = big;
            }else{
                smallmap.put(index,small);
                node.small = small;
            }
			size++;
		}else if(number > node.value){
			node.right = insertHelper(node.right, number,small+node.lcnt+1,big,index);
			node.rcnt++;
			int leftheight = node.left == null?0:node.left.height;
			int rightheight = node.right == null?0:node.right.height;
			node.height = Math.max(leftheight,rightheight)+1;
			if(rightheight - leftheight == 2 ){
				if(number > node.right.value){
					node = rotateWithRight(node);
				}else{
					node = doublerotatewithright(node);
				}
			}
		}else if(number < node.value){
			node.left = insertHelper(node.left, number,small,big+node.rcnt+1,index);
			node.lcnt++;
			int leftheight = node.left == null?0:node.left.height;
			int rightheight = node.right == null?0:node.right.height;
			node.height = Math.max(leftheight,rightheight)+1;
			if(leftheight - rightheight == 2){
				if(number > node.left.value){
					node = doublerotatewithleft(node);
				}else{
					node = rotateWithLeft(node);
				}
			}
        }
		return node;
		
	}
	
	private DummyNode rotateWithLeft(DummyNode cur){
		DummyNode left = cur.left;
		cur.left = left.right;
		left.right = cur;
		
		cur.lcnt = cur.left == null?0:(cur.left.lcnt+cur.left.rcnt+1);
		left.rcnt = cur.lcnt+cur.rcnt+1;
		
		int leftheight = cur.left == null?0:cur.left.height;
		int rightheight = cur.right == null?0:cur.right.height;
		cur.height = Math.max(leftheight, rightheight)+1;
		leftheight = left.left == null?0:left.left.height;
		rightheight = left.right == null?0:left.right.height;
		left.height = Math.max(leftheight, rightheight)+1;
		
		return left;
	}
	
	private DummyNode rotateWithRight(DummyNode cur){
		DummyNode right = cur.right;
		cur.right = right.left;
		right.left = cur;
		
		cur.rcnt = cur.right == null?0:(cur.right.lcnt+cur.right.rcnt+1);
		right.lcnt = cur.lcnt+cur.rcnt+1;
		
		int leftheight = cur.left == null?0:cur.left.height;
		int rightheight = cur.right == null?0:cur.right.height;
		cur.height = Math.max(leftheight, rightheight)+1;
		leftheight = right.left == null?0:right.left.height;
		rightheight = right.right == null?0:right.right.height;
		right.height = Math.max(leftheight, rightheight)+1;
		
		return right;
	}
	
	private DummyNode doublerotatewithleft(DummyNode cur){
	     cur.left = rotateWithRight(cur.left);
	     cur = rotateWithLeft(cur);

	     return cur;
	}
	
	private DummyNode doublerotatewithright(DummyNode cur){
	     cur.right = rotateWithLeft(cur.right);
	     cur = rotateWithRight(cur);

	     return cur;
	}

	
	

	public boolean delete(long number){
		if(!doesExist(root, number)){
			return false; 
		}
		root = deleteHelper(root,number);
		size--;
		return (size>0);
	}
	
	private DummyNode deleteHelper(DummyNode node, long number) {
		if(number > node.value){
			node.right = deleteHelper(node.right, number);
			node.rcnt--;
			int leftheight = node.left == null?0:node.left.height;
			int rightheight = node.right == null?0:node.right.height;
			node.height = Math.max(leftheight,rightheight) + 1;
			if(leftheight - rightheight == 2){
				int leftgcheight = node.left.left==null?0:node.left.left.height;
				int rightgcheight = node.left.right==null?0:node.left.right.height;
				if(leftgcheight >= rightgcheight){
					node = rotateWithLeft(node);
				}else{
					node = doublerotatewithleft(node);
				}
			}
		}else if(number < node.value){
			node.left = deleteHelper(node.left, number);
			node.lcnt--;
			int leftheight = node.left == null?0:node.left.height;
			int rightheight = node.right == null?0:node.right.height;
			node.height = Math.max(leftheight,rightheight) + 1;
			if(rightheight - leftheight == 2){
				int leftgcheight = node.right.left==null?0:node.right.left.height;
				int rightgcheight = node.right.right==null?0:node.right.right.height;
				if(rightgcheight >= leftgcheight){
					node = rotateWithRight(node);
				}else{
					node = doublerotatewithright(node);
				}
			}
		}else{
			if(node.left == null){
				return node.right;
			}else if(node.right == null){
				return node.left;
			}else{
				DummyNode pred = getPred(node.left);
				node.value = pred.value;
				node.left = deleteHelper(node.left, pred.value);
				node.lcnt--;
				int leftheight = node.left == null?0:node.left.height;
				int rightheight = node.right == null?0:node.right.height;
				node.height = Math.max(leftheight,rightheight) + 1;
				if(rightheight - leftheight == 2){
					int leftgcheight = node.right.left==null?0:node.right.left.height;
					int rightgcheight = node.right.right==null?0:node.right.right.height;
					if(rightgcheight >= leftgcheight){
						node = rotateWithRight(node);
					}else{
						node = doublerotatewithright(node);
					}
				}
			}
		}
		return node;
	}

	private DummyNode getPred(DummyNode cur) {
		DummyNode prev = null;
		while(cur!=null){
			prev = cur;
			cur = cur.right;
		}
		return prev;
	}

	private boolean doesExist(DummyNode node, long number){
		if(null == node){
			return false;
		}
		if(number == node.value){
			return true;
		}
		if(number > node.value){
			return doesExist(node.right, number);
		}else{
			return doesExist(node.left, number);
		}
	}
	

}

class DummyNode {
	
	public long value;
	public DummyNode left;
	public DummyNode right;
	public int lcnt;
	public int rcnt;
	public int height;
	public int big;
	public int small;
	
	public DummyNode(long value, int lcnt, int rcnt,int height) {
		this.value = value;
		this.lcnt = lcnt;
		this.rcnt = rcnt;
		this.height = height;
	}
}


