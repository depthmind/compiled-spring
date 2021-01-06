package com.depthmind.algorithm;

import java.util.Arrays;
import java.util.Stack;

class Solution {
	public boolean findNumberIn2DArray(int[][] matrix, int target) {
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[i].length; j++) {
				if (target == matrix[i][j]) {
					return true;
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {
		int[][] arrs = {{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
//		spiralOrder1(arrs);
//		majorityElement(new int[]{1, 2, 3, 2, 2, 2, 5, 4, 2});
//		twoSum(new int[]{10,26,30,31,47,60}, 40);
//		System.out.println(reverseWords("  hello world!  "));
//		System.out.println(reverseLeftWords("sadfasdfasdf", 6));
//		System.out.println(findNumberIn2DArray1(arrs, 30));
//		System.out.println(replaceSpace(""));
		MinStack minStack = new MinStack();
		minStack.push(1);
	}

	/**
	 * 顺时针打印矩阵
	 * @param matrix
	 * @return
	 */
	public static int[] spiralOrder(int[][] matrix) {
		if(matrix.length == 0) return new int[0];
		int left = 0, right = matrix[0].length - 1, top = 0, bottom = matrix.length - 1, x = 0;
		int[] res = new int[(right + 1) * (bottom + 1)];
		while(true) {
			for(int i = left; i <= right; i++) res[x++] = matrix[top][i];
			if(++top > bottom) break;
			for(int i = top; i <= bottom; i++) res[x++] = matrix[i][right];
			if(left > --right) break;
			for(int i = right; i >= left; i--) res[x++] = matrix[bottom][i];
			if(top > --bottom) break;
			for(int i = bottom; i >= top; i--) res[x++] = matrix[i][left];
			if(++left > right) break;
		}
		return res;
	}

	public static int[] spiralOrder1(int[][] matrix) {
		if (matrix.length == 0) {
			return new int[0];
		}
		int left = 0;
		int right = matrix.length - 1;
		int top = 0;
		int bottom = matrix.length - 1;
		int x = 0;
		int newArr[] = new int[matrix.length * matrix.length];
		while (true) {
			for (int i=left; i<=right; i++) {
				newArr[x++] = matrix[top][i];
			}
			if (++top > bottom) {
				break;
			}
			for (int i=top; i<=bottom; i++) {
				newArr[x++] = matrix[right][i];
			}
			if (--right < left) {
				break;
			}
			for (int i=right; i>=left; i--) {
				newArr[x++] = matrix[bottom][i];
			}
			if (--bottom < top) {
				break;
			}
			for (int i=bottom; i>=top; i--) {
				newArr[x++] = matrix[left][i];
			}
			if (++left > right) {
				break;
			}
		}
		return newArr;
	}

	/**
	 * 摩尔投票
	 * @param nums
	 * @return
	 */
	public static int majorityElement(int[] nums) {
		int count = 1;
		int cur = nums[0];
		for (int i=1; i<nums.length; i++) {
			if (cur == nums[i]) {
				count++;
			} else if (count>0){
				count --;
			} else {
				cur = nums[i];
			}
		}
		return cur;
	}

	/**
	 * 此解法不是最优解法，应该用双指针
	 * 数组中任意两数之和=target
	 * @param nums
	 * @param target
	 * @return
	 */
	public static int[] twoSum(int[] nums, int target) {
		int index = 0;
		int result[] = new int[2];
		while (index < nums.length-1) {
			for (int i=index+1; i<nums.length; i++) {
				if (nums[index] + nums[i] == target) {
					result[0] = nums[index];
					result[1] = nums[i];
					break;
				}
			}
			index ++;
		}
		return result;
	}

	public static String reverseWords(String s) {
		String tmp = s.trim();
		String res = "";
		int start = tmp.length()-1;
		int end = tmp.length()-1;
		while (start >= 0) {
			if (start > 0 && tmp.charAt(start) != ' ') {
				start --;
			} else {
				res += tmp.substring(start, end + 1) + ' ';

				end = start;
				start --;
			}
		}
		return res.trim();
	}

	/**
	 * 将第0-n个字符移动到字符串末尾
	 * @param s
	 * @param n
	 * @return
	 */
	public static String reverseLeftWords(String s, int n) {
		char[] res = new char[s.length()];
		int index = 0;
		for (int i = n; i < s.length(); i++) {
			res[index ++] = s.charAt(i);
		}
		for (int i = 0; i < n; i++) {
			res[index ++] = s.charAt(i);
		}
		return Arrays.toString(res);
	}

	public static boolean findNumberIn2DArray1(int[][] theArr, int target) {
		//找到左下角的数字
		int column = theArr.length-1;
		int row = 0;
		int cur = theArr[column][row];
		while (column >= 0 && row <= theArr.length-1) {
			if (target < cur) {
				cur = theArr[column--][row];
			}
			if (target > cur) {
				cur = theArr[column][row++];
			}
			if (target == cur) {
				return true;
			}
		}
		return false;
	}

	public static String replaceSpace(String s) {
		if (s.length() == 0) {
			return "";
		}
		String tmp = "";
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == ' ') {
				tmp+="%20";
			} else {
				tmp+=s.charAt(i);
			}
		}
		return tmp;
	}

	public int[] reversePrint(ListNode head) {
		/*int[] a = new int[3];
		int i = 2;
		while(head != null) {
			a[i--] = head.val;
			head = head.next;
		}
		return a;*/
		Stack<ListNode> listNodes = new Stack<>();
		while (head != null) {
			listNodes.add(head);
			head = head.next;
		}
		int[] res = new int[listNodes.size()];
		for (int i = 0; i < res.length; i++) {
			res[i] = listNodes.pop().val;
		}
		return res;
	}
}

class ListNode {
	int val;
	ListNode next;
	ListNode(int x) { val = x; }
}


class CQueue {
	Stack<Integer> stack;
	Stack<Integer> stack1;

	public CQueue() {
		stack = new Stack<>();
		stack1 = new Stack<>();
	}

	public void appendTail(int value) {
		stack.push(value);
	}

	public int deleteHead() {
		if (stack1.isEmpty()) {
			while (!stack.isEmpty()) {
				stack1.push(stack.pop());
			}
		}
		if (stack1.isEmpty()) {
			return -1;
		} else {
			return stack1.pop();
		}
	}

	public ListNode deleteNode(ListNode head, int val) {
//		ListNode deletedNode = null;
//		while (head.val != val) {
//			deletedNode = head = head.next;
//		}
//		head.next = head.next.next;
//		return deletedNode;
		if (head == null) {
			return null;
		}
		if (head.val == val) {
			return head.next;
		}
		ListNode post = head;
		ListNode pre = head.next;
		while (pre !=null && pre.val != val) {
			post = pre;
			pre = pre.next;
		}
		if(pre != null) {
			post.next = pre.next;
		}
		return head;
	}

	public ListNode getKthFromEnd(ListNode head, int k) {
		ListNode post = head;
		ListNode pre = head;
		for (int i = 0; i < k; i++) {
			pre = pre.next;
		}
		while (pre != null) {
			pre = pre.next;
			post = post.next;
		}
		return post;
	}

	public ListNode reverseList(ListNode head) {
		Stack<ListNode> stack = new Stack();
		ListNode newHead;
		stack.push(head);
		while (head != null) {
			head = head.next;
			if (head != null) {
				stack.push(head);
			}
		}
		head = newHead = stack.peek();
		while (stack.size() > 0) {
			head.next = stack.pop();
		}
		return newHead;
	}

	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode l1Next = l1.next;
		while (l2 != null) {
			if (l2.val > l1.val) {
				l1.next = l2;
				l2.next = l1Next;
			}
			l2 = l2.next;
		}
		return l1Next;
	}

//	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
//		root.left
//	}
}

class TreeNode {
 	int val;
 	TreeNode left;
 	TreeNode right;
 	TreeNode(int x) { val = x; }
}

class MinStack {
	Stack<Integer> stack;
	Stack<Integer> stack1;

	/** initialize your data structure here. */
	public MinStack() {
		stack = new Stack<>();
		stack1 = new Stack<>();
	}

	public void push(int x) {
		stack.push(x);
		if (stack1.isEmpty() || stack1.peek() > x) {
			stack1.push(x);
		}
	}

	public void pop() {
		if (stack.pop().equals(stack1.peek())) {
			stack1.pop();
		}
	}

	public int top() {
		return stack.peek();
	}

	public int min() {
		return stack1.peek();
	}
}