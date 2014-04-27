package com.kushd.trie;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
	private boolean isWord;
	private char content;
	private Map<Character,TrieNode> childNodes;
	
	public TrieNode(){
		isWord = false;
		content = '$';
		childNodes = new HashMap<Character, TrieNode>();
	}
	
	public TrieNode(char c){
		content = c;
		isWord = false;
		childNodes = new HashMap<Character, TrieNode>();
	}
	
	public TrieNode getChildNode(char c){
		if(childNodes!=null){
			return childNodes.get(c);
		}
		return null;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof TrieNode){
			TrieNode n = (TrieNode)obj;
			return (content == n.getContent());
		}
		return false;
	}
	
	
	
	public boolean isWord(){
		return isWord;
	}
	
	public void setWord(boolean v){
		isWord = v;
	}
	
	public char getContent() {
		return content;
	}
	
	public void setContent(char c){
		content = c;
	}
	
	public Map<Character, TrieNode> getChildNodes() {
		return childNodes;
	}

	public void setChildNodes(Map<Character, TrieNode> childnodes) {
		childNodes = childnodes;
	}

}
