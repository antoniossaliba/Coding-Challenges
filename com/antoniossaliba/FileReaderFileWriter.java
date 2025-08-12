package com.antoniossaliba;

import java.io.*;
import java.util.*;

public class FileReaderFileWriter {
	
	private Map<Character, Integer> frequencies;
	private String fileName;
	
	public FileReaderFileWriter(String fileName) {
		this.fileName = fileName;
		frequencies = new HashMap<>();
	}
	
	public String getFileName() {
		return this.fileName;
	}
	
	public Map<Character, Integer> getFrequencies() {
		return this.frequencies;
	}
	
	public void writeToFile(String toWrite) throws IOException {
		File currentFile = new File(this.fileName);
		FileWriter fw = new FileWriter(currentFile);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter pw = new PrintWriter(bw);
		fw.write(toWrite);
		fw.close();
		bw.close();
		pw.close();
	}
	
	public String readFromFile() throws IOException {
		StringBuilder sb = new StringBuilder();
		File fileToRead = new File(this.fileName);
		Scanner scanner = new Scanner(fileToRead);
		while(scanner.hasNext()) {
			for(char character : scanner.nextLine().toCharArray()) {
				if(this.frequencies.containsKey(character)) {
					this.frequencies.put(character, this.frequencies.get(character) + 1);
				} else {
					this.frequencies.put(character, 1);
				}
				sb.append(character);
			}
		}
		scanner.close();
		return sb.toString();
	}
	
	public TreeNode buildHuffmanTree() {
		PriorityQueue<TreeNode> pq = new PriorityQueue<>(new RemoveMinimumTreeNode());
		for(Character key : this.frequencies.keySet()) {
			TreeNode newTreeNode = new TreeNode(this.frequencies.get(key), key);
			pq.offer(newTreeNode);
		}
		while(pq.size() > 1) {
			TreeNode leftNode = pq.poll();
			TreeNode rightNode = pq.poll();
			TreeNode rootNode = new TreeNode(rightNode.getData() + leftNode.getData(), null, leftNode, rightNode);
			pq.offer(rootNode);
		}
		return pq.poll();
	}
	
	public void breadthFirstSearchTraversal(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<>();
		if(root == null) return;
		queue.offer(root);
		while(!queue.isEmpty()) {
			TreeNode currentNode = queue.poll();
			if(currentNode.getLeftChild() == null && currentNode.getRightChild() == null) {
				System.out.println("Data = " + currentNode.getData() + ", Character = " + currentNode.getCharacter());
			}
			if(currentNode.getLeftChild() != null) queue.offer(currentNode.getLeftChild());
			if(currentNode.getRightChild() != null) queue.offer(currentNode.getRightChild());
		}
	}
	
	public void collectPaths(TreeNode root, List<Integer> path, List<List<Integer>> paths, List<Character> chars) {
		if(root == null) return;
		if(root.getLeftChild() == null && root.getRightChild() == null) {
			paths.add(new ArrayList<>(path));
			chars.add(root.getCharacter());
		} else {
			if(root.getLeftChild() != null) {
				path.add(0);
			}
			collectPaths(root.getLeftChild(), path, paths, chars);
			if(root.getRightChild() != null) {
				path.add(1);
			}
			collectPaths(root.getRightChild(), path, paths, chars);
		}
		if(!path.isEmpty()) path.remove(path.size() - 1);
	}
	
	public Map<Character, String> runCollectPathsForEncode(TreeNode root) {
		Map<Character, String> codes = new HashMap<>();
		List<Integer> path = new ArrayList<>();
		List<List<Integer>> paths = new ArrayList<>();
		List<Character> chars = new ArrayList<>();
		collectPaths(root, path, paths, chars);
		for(int i = 0; i < paths.size(); i++) {
			StringBuilder sb = new StringBuilder();
			List<Integer> currentPath = paths.get(i);
			for(Integer n : currentPath) {
				sb.append(n);
			}
			codes.put(chars.get(i), sb.toString());
		}
		return codes;
	}
	
	public Map<String, Character> runCollectPathsForDecode(TreeNode root) {
		Map<String, Character> codes = new HashMap<>();
		List<Integer> path = new ArrayList<>();
		List<List<Integer>> paths = new ArrayList<>();
		List<Character> chars = new ArrayList<>();
		collectPaths(root, path, paths, chars);
		for(int i = 0; i < paths.size(); i++) {
			StringBuilder sb = new StringBuilder();
			List<Integer> currentPath = paths.get(i);
			for(Integer n : currentPath) {
				sb.append(n);
			}
			codes.put(sb.toString(), chars.get(i));
		}
		return codes;
	}
	
	public void encode(TreeNode root, String toWrite, String fileName) throws IOException {
		Map<Character, String> codes = this.runCollectPathsForEncode(root);
		StringBuilder sb = new StringBuilder();
		for(char character : toWrite.toCharArray()) {
			sb.append(codes.get(character));
		}
		String s = sb.toString();
		File newFile = new File(fileName);
		FileWriter fw = new FileWriter(newFile);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter pw = new PrintWriter(bw);
		fw.write(s);
		fw.close();
		bw.close();
		pw.close();
	}
	
	public void decode(TreeNode root, String fileName, String newFileName) throws IOException {
		Map<String, Character> codes = this.runCollectPathsForDecode(root);
		StringBuilder sbForLetters = new StringBuilder();
		StringBuilder sbToWrite = new StringBuilder();
		File fileOfInterest = new File(fileName);
		Scanner scanner = new Scanner(fileOfInterest);
		while(scanner.hasNextLine()) {
			for(char character : scanner.nextLine().toCharArray()) {
				if(codes.containsKey(sbForLetters.toString())) {
					sbToWrite.append(codes.get(sbForLetters.toString()));
					sbForLetters.delete(0, sbForLetters.length());
				}
				sbForLetters.append(character);
			}
		}
		if(codes.containsKey(sbForLetters.toString())) {
			sbToWrite.append(codes.get(sbForLetters.toString()));
			sbForLetters.delete(0, sbForLetters.length());
		}
		File decodedFile = new File(newFileName);
		FileWriter fw = new FileWriter(decodedFile);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter pw = new PrintWriter(bw);
		fw.write(sbToWrite.toString());
		fw.close();
		bw.close();
		pw.close();
		scanner.close();
	}

}
