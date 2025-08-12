package com.antoniossaliba;

import java.util.Comparator;

public class RemoveMinimumTreeNode implements Comparator<TreeNode> {

	@Override
	public int compare(TreeNode o1, TreeNode o2) {
		return o1.getData() - o2.getData();
	}

}
