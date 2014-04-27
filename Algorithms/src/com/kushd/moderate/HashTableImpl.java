package com.kushd.moderate;

public class HashTableImpl {

}


class Hashtbl<K,V>{
	private HashNode<K, V>[] nodes;
	
	@SuppressWarnings("unchecked")
	public Hashtbl(int size){
		nodes = new HashNode[size];
	}
	
	private int getIndex(K key){
		int hash = key.hashCode()%nodes.length;
		if(hash<0){
			hash += nodes.length;
		}
		return hash;
	}
	
	public V insert(K key, V data){
		int hash = getIndex(key);
		for(HashNode<K,V> node = nodes[hash];node!=null;node=node.next){
			if(key.equals(node.key)){
				V oldData = node.data;
				node.data = data;
				return oldData;
			}
		}
		HashNode<K,V> node = new HashNode<K,V>(key, data, nodes[hash]);
		nodes[hash] = node;
		return null;
	}
	
	public boolean remove(K key){
		int hash = getIndex(key);
		HashNode<K,V> prevNode = null;
		for(HashNode<K,V> node = nodes[hash]; node!=null;node=node.next){
			if(key.equals(node.key)){
				if(prevNode!=null){
					prevNode.next = node.next;
					return true;
				}else{
					nodes[hash] = node.next;
					return true;
				}
			}
			prevNode = node;
		}
		return false;
	}
	
	public V get(K key){
		int hash = getIndex(key);
		for(HashNode<K,V> node = nodes[hash];node!=null;node=node.next){
			if(key.equals(node.key)){
				return node.data;
			}
		}
		return null;
	}
}

class HashNode<K,V>{
	public K key;
	V data;
	HashNode<K,V> next;
	
	public HashNode(K k, V v, HashNode<K,V> n){
		key = k;
		data = v;
		next = n;
	}
}
