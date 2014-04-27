package com.kushd.leetcode.oj;
import java.util.HashMap;
import java.util.Map;

public class LRUC {
	
	public static void main(String[] args) {
		LRUCache cache = new LRUCache(10);
		cache.set(10,13);
		cache.set(3,17);
		cache.set(6,11);
		cache.set(10,5);
		cache.set(9,10);
		cache.get(13);
		cache.set(2,19);
		cache.get(2);
		cache.get(3);
		cache.set(5,25);
		cache.get(8);
		cache.set(9,22);
		cache.set(5,5);
		cache.set(1,30);
		cache.get(11);
		cache.set(9,12);
		cache.get(7);
		cache.get(5);
		cache.get(8);
		cache.get(9);
		cache.set(4,30);
		cache.set(9,3);
		cache.get(9);
		cache.get(10);
		cache.get(10);
		cache.set(6,14);
		cache.set(3,1);
		cache.get(3);
		cache.set(10,11);
		cache.get(8);
		cache.set(2,14);
		cache.get(1);
		cache.get(5);
		cache.get(4);
		cache.set(11,4);
		cache.set(12,24);
		cache.set(5,18);
		cache.get(13);
		cache.set(7,23);
		cache.get(8);
		cache.get(12);
		cache.set(3,27);
		cache.set(2,12);
		cache.get(5);
		cache.set(2,9);
		cache.set(13,4);
		cache.set(8,18);
		cache.set(1,7);
		cache.get(6);
		
	}
	

}

class LRUCache {
    
    private Map<Integer,NNode> map;
    private NNode head=null;
    private NNode tail=null;
    private int capacity;
    private int size;
    
    public LRUCache(int capacity) {
        map = new HashMap<Integer,NNode>(capacity);
        this.capacity = capacity;
        size=0;
    }
    
    public int get(int key) {
        if(map.get(key) != null){
            NNode node = map.get(key);
            requeue(node);
            return node.value;
        }else{
            return -1;
        }
    }
    
    private void requeue(NNode node){
        NNode right = node.next;
        NNode left = node.prev;
        if(left!=null){
            left.next = right;
            if(right!=null){
                right.prev = left;
            }else{
                tail = left;
            }
            node.prev = null;
            node.next = head;
            head.prev = node;
            head = node;
        }
        
    }
    
    public void set(int key, int value) {
        if(map.get(key) == null){
            if(size == capacity){
                map.remove(tail.key);
                size--;
                tail = tail.prev;
                if(tail != null) { tail.next = null; }
            }
            NNode node = new NNode(key,value,null,head);
            if(head != null){ head.prev = node; }
            head = node;
            if(tail == null){ tail = node; }
            map.put(key,node);
            size++;
        }else{
            NNode node = map.get(key);
            node.value = value;
            requeue(node);
        }
    }
}

class NNode{
    public int key;
    public NNode prev;
    public NNode next;
    public int value;
    
    NNode(int key,int value,NNode prev,NNode next){
        this.value = value;
        this.prev = prev;
        this.next = next;
        this.key = key;
    }
    
}
