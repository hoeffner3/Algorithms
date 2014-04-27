package com.kushd.trie;

public class Trie{
	
	public static void main(String[] args) {
		TrieImpl trie = new TrieImpl();
		String[] inputs = {"the", "a", "there", "answer", "any", "bye", "by", "their"};
		for(String input : inputs){
			trie.insert(input);
		}
		
		System.out.println(trie.search("any"));
		System.out.println(trie.search("an"));
		System.out.println(trie.search("ther"));
		System.out.println(trie.search("bye"));
		System.out.println(trie.search("by"));
	}
	
	
}




class TrieImpl {
	
	private TrieNode root;
	private int count;
	
	public TrieImpl(){
		this.root = new TrieNode();
		this.setCount(0);
	}
	
	
	public void insert(String input){
		insertHelper(input, 0, root);
		this.setCount(this.getCount() + 1);
	}
	
	private void insertHelper(String input, int index, TrieNode node){
		if(index == input.length()){
			node.setWord(true);
			return;
		}
		if(node.getChildNode(input.charAt(index)) == null){
			TrieNode cnode = new TrieNode(input.charAt(index));
			(node.getChildNodes()).put(input.charAt(index), cnode);
			insertHelper(input, index+1, cnode);
		}else{
			insertHelper(input, index+1, node.getChildNode(input.charAt(index)));
		}
		
		return;
	}
	
	public boolean search(String input){
		return searchHelper(input, 0, root);
	}
	
	private boolean searchHelper(String input, int index, TrieNode node){
		if(index == input.length()){
			return node.isWord();
		}
		
		if(node.getChildNode(input.charAt(index)) == null){
			return false;
		}
		return searchHelper(input, index+1, node.getChildNode(input.charAt(index)));
	}


	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}


	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}
}
