package com.depthmind.algorithm;

/**
 * @author liuhan
 */
public class RBTree {
	public TreeNode balanceInsertion(TreeNode root, TreeNode x) {
		x.red = true;
		for (TreeNode xp,xpp,xppl,xppr;;) {
			// 如果root为null则x直接成为根节点
			if (root == null) {
				x.red = false;
				return x;
			}
			// 如果x的父节点是root节点
			if ((xp = root).parent == null) {
				xp.next = x;
				return x;
			}

		}
	}

	class TreeNode<K, V>{
		public boolean red;
		public TreeNode next;
		public TreeNode parent;
		public TreeNode left;
		public TreeNode right;
	}
}
