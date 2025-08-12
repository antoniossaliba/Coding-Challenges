package com.antoniossaliba;

public class TreeNode {
	
	private TreeNode leftChild, rightChild;
	private Integer data;
	private Character character;
	
	public TreeNode(Integer data, Character character) {
		this.data = data;
		this.character = character;
		this.leftChild = null;
		this.rightChild = null;
	}
	
	public TreeNode(Integer data, Character character, TreeNode leftChild, TreeNode rightChild) {
		this.data = data;
		this.character = character;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}
	
	public Integer getData() {
		return this.data;
	}
	
	public void setData(Integer data) {
		this.data = data;
	}
	
	public Character getCharacter() {
		return this.character;
	}
	
	public void setCharacter(Character character) {
		this.character = character;
	}
	
	public TreeNode getLeftChild() {
		return this.leftChild;
	}
	
	public void setLeftChild(TreeNode leftChild) {
		this.leftChild = leftChild;
	}
	
	public TreeNode getRightChild() {
		return this.rightChild;
	}
	
	public void setRightChild(TreeNode rightChild) {
		this.rightChild = rightChild;
	}

}
