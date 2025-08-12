package com.antoniossaliba;

import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		
		String fileName = "Original.txt";
		FileReaderFileWriter frfw = new FileReaderFileWriter(fileName);
//		String toWrite = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
//		frfw.writeToFile(toWrite);
		String toWrite = frfw.readFromFile();
		TreeNode root = frfw.buildHuffmanTree();
		String newFileName = "EncodedFile.txt";
		frfw.encode(root, toWrite, newFileName);
		frfw.decode(root, newFileName, "DecodedFile.txt");

	}

}
