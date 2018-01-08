
/** **************************************************************************
 *                     The  generic Binary Search Tree class.
 *
 * V.S.Adamchik 2010
 *****************************************************************************/

import java.util.*;

public class BST<T extends Comparable<T>> implements Iterable<T> {
   String src;

    public static int[] kadane(int[] a) {
        //result[0] == maxSum, result[1] == start, result[2] == end;
        int[] result = new int[]{Integer.MIN_VALUE, 0, -1};
        int currentSum = 0;
        int localStart = 0;
        for (int i = 0; i < a.length; i++) {
            currentSum += a[i];
            if (currentSum < 0) {
                  currentSum = 0;
                localStart = i + 1;
              } else if (currentSum > result[0]) {
                result[0] = currentSum;
                result[1] = localStart;
                result[2] = i;
              }
        }
         
        //all numbers in a are negative
        if (result[2] == -1) {
            result[0] = 0;
            for (int i = 0; i < a.length; i++) {
                if (a[i] > result[0]) {
                    result[0] = a[i];
                    result[1] = i;
                    result[2] = i;
                }
            }
        }
         
        return result;
      }

	public static void findMaxSubMatrix(int[][] a) {
		int cols = a[0].length;
		int rows = a.length;
		int[] currentResult;
		int maxSum = Integer.MIN_VALUE;
		int left = 0;
		int top = 0;
		int right = 0;
		int bottom = 0;
		// O(row) space and o(col*col*row) time
		for (int leftCol = 0; leftCol < cols; leftCol++) {
			int[] tmp = new int[rows];

			for (int rightCol = leftCol; rightCol < cols; rightCol++) {

				for (int i = 0; i < rows; i++) {
					tmp[i] += a[i][rightCol];
				}
				currentResult = kadane(tmp);
				if (currentResult[0] > maxSum) {
					maxSum = currentResult[0];
					left = leftCol;
					top = currentResult[1];
					right = rightCol;
					bottom = currentResult[2];
				}
			}
		}
		System.out
				.println("MaxSum: " + maxSum + ", range: [(" + left + ", " + top + ")(" + right + ", " + bottom + ")]");
	}


	static int s; // can be accessed as X.s without object
	final int f = 7; // can't be assigned a different value


	public static int largestPrimeFactor(long number) {
		int i;
		long copyOfInput = number;
		for (i = 2; i <= copyOfInput; i++) {
			if (copyOfInput % i == 0) {
				copyOfInput /= i;
				i--;
			}
		}
		return i;
	}

	// remove char from string without any extra space
	public static String removeRecursive(String word, char ch) {
		int index = word.indexOf(ch);
		if (index == -1) {
			return word;
		}
		return removeRecursive(word.substring(0, index) + word.substring(index + 1, word.length()), ch);
	}
	// LRU
	// Another way of implementing it is using LinkedHashMap. It is an ordered
	// Map. You can specify how you want to order the map, by insertion or by
	// access. Although this is only possible in Java.

	// LinkedHashMap - keeps track of the order in which each entry is added.
	// By default, it removes the oldest entry when reached to a threshold.

	// 1. In constructor - true flag - we are saying that, we want to remove the
	// oldest element based on its access. (the one that was least accessed,
	// should be removed)

	// 2. In overridden method, we are saying that, remove entry only when we
	// have reached cacheSize.
	// or
	// We use two data structures to implement an LRU Cache.
	//
	// Queue which is implemented using a doubly linked list. The maximum size
	// of the queue will be equal to the total number of frames available (cache
	// size).The most recently used pages will be near front end and least
	// recently pages will be near rear end.
	// A Hash with page number as key and address of the corresponding queue
	// node as value.
	// A Splay tree keeps the last recently used data closest to the root the
	// tree and the least recently used data farthest from the root ( a leaf)

	public class LRUCache extends LinkedHashMap<Integer, String> {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private int cacheSize;

		public LRUCache(int size) {
			super(size, 0.75f, true);
			this.cacheSize = size;
		}

		@Override
		protected boolean removeEldestEntry(java.util.Map.Entry<Integer, String> eldest) {
			// remove the oldest element when size limit is reached
			return size() > cacheSize;
		}
	}

	public static void main(String[] args) {
		int ttt = 5;
		ttt = ttt >> 4;
		System.out.println('A' > 'a');

		System.out.println(++ttt / 3 * 4f);

		Integer[] a = { 1, 5, 2, 7, 4 };
		BST<Integer> bst = new BST<Integer>();
		for (Integer n : a)
			bst.insert(n);

		bst.preOrderTraversal();
		System.out.println();

		// testing comparator
		// build a mirror BST with a rule: Left > Parent > Right
		// code for the comparator at the bottom of the file
		bst = new BST<Integer>(new MyComp1());
		for (Integer n : a)
			bst.insert(n);

		bst.preOrderTraversal();
		System.out.println();
		bst.inOrderTraversal();
		System.out.println();

		for (Integer n : bst)
			System.out.print(n);
		System.out.println();

		System.out.println(bst);

		// testing restoring a tree from two given traversals
		bst.restore(new Integer[] { 11, 8, 6, 4, 7, 10, 19, 43, 31, 29, 37, 49 },
				new Integer[] { 4, 6, 7, 8, 10, 11, 19, 29, 31, 37, 43, 49 });
		bst.preOrderTraversal();
		System.out.println();
		bst.inOrderTraversal();
		System.out.println();

		// testing diameter
		System.out.println("diameter = " + bst.diameter());
		// testing width
		System.out.println("width = " + bst.width());
	}

	private Node<T> root;
	private Comparator<T> comparator;

	public BST() {
		root = null;
		comparator = null;
	}

	public BST(Comparator<T> comp) {
		root = null;
		comparator = comp;
	}

	private int compare(T x, T y) {
		if (comparator == null)
			return x.compareTo(y);
		else
			return comparator.compare(x, y);
	}

	/*****************************************************
	 *
	 * INSERT
	 *
	 ******************************************************/
	public void insert(T data) {
		root = insert(root, data);
	}

	private Node<T> insert(Node<T> p, T toInsert) {
		if (p == null)
			return new Node<T>(toInsert);

		if (compare(toInsert, p.data) == 0)
			return p;

		if (compare(toInsert, p.data) < 0)
			p.left = insert(p.left, toInsert);
		else
			p.right = insert(p.right, toInsert);

		return p;
	}

	/*****************************************************
	 *
	 * SEARCH
	 *
	 ******************************************************/
	public boolean search(T toSearch) {
		return search(root, toSearch);
	}

	private boolean search(Node<T> p, T toSearch) {
		if (p == null)
			return false;
		else if (compare(toSearch, p.data) == 0)
			return true;
		else if (compare(toSearch, p.data) < 0)
			return search(p.left, toSearch);
		else
			return search(p.right, toSearch);
	}

	/*****************************************************
	 *
	 * DELETE
	 *
	 ******************************************************/

	public void delete(T toDelete) {
		root = delete(root, toDelete);
	}

	private Node<T> delete(Node<T> p, T toDelete) {
		if (p == null)
			throw new RuntimeException("cannot delete.");
		else if (compare(toDelete, p.data) < 0)
			p.left = delete(p.left, toDelete);
		else if (compare(toDelete, p.data) > 0)
			p.right = delete(p.right, toDelete);
		else {
			if (p.left == null)
				return p.right;
			else if (p.right == null)
				return p.left;
			else {
				/*
				 * To delete a value from a BST:
				 * 
				 * Find the node containing the value to be deleted If it is a
				 * leaf, remove it: make the reference from its parent instead
				 * refer to null If it is an internal node with one child, make
				 * the reference from its parent instead refer directly to its
				 * only child If it is an internal node with two children Find
				 * the node containing either the largest value smaller than it
				 * or the smallest value larger than it (it doesn't matter
				 * which) Remove that node from the tree (it is guaranteed to
				 * have at most one child, so it will be easy to delete) Replace
				 * the value of the original node (the one to delete) with the
				 * value of this node (just deleted)
				 */
				// get data from the rightmost node in the left subtree largest
				// in the smaller number
				// or we can get the leftmost in the right
				p.data = retrieveData(p.left);
				// delete the rightmost node in the left subtree
				p.left = delete(p.left, p.data);
			}
		}
		return p;
	}

	private T retrieveData(Node<T> p) {
		while (p.right != null)
			p = p.right;

		return p.data;
	}

	/*************************************************
	 *
	 * toString
	 *
	 **************************************************/

	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (T data : this)
			sb.append(data.toString());

		return sb.toString();
	}

	/*************************************************
	 *
	 * TRAVERSAL
	 *
	 **************************************************/

	public void preOrderTraversal() {
		preOrderHelper(root);
	}

	private void preOrderHelper(Node r) {
		if (r != null) {
			System.out.print(r + " ");
			preOrderHelper(r.left);
			preOrderHelper(r.right);
		}
	}

	public void inOrderTraversal() {
		inOrderHelper(root);
	}

	private void inOrderHelper(Node r) {
		if (r != null) {
			inOrderHelper(r.left);
			System.out.print(r + " ");
			inOrderHelper(r.right);
		}
	}

	/*************************************************
	 *
	 * CLONE
	 *
	 **************************************************/

	public BST<T> clone() {
		BST<T> twin = null;

		if (comparator == null)
			twin = new BST<T>();
		else
			twin = new BST<T>(comparator);

		twin.root = cloneHelper(root);
		return twin;
	}

	private Node<T> cloneHelper(Node<T> p) {
		if (p == null)
			return null;
		else
			return new Node<T>(p.data, cloneHelper(p.left), cloneHelper(p.right));
	}

	/*************************************************
	 *
	 * MISC
	 *
	 **************************************************/

	public int height() {
		return height(root);
	}

	private int height(Node<T> p) {
		if (p == null)
			return -1;
		else
			return 1 + Math.max(height(p.left), height(p.right));
	}

	public int countLeaves() {
		return countLeaves(root);
	}

	private int countLeaves(Node<T> p) {
		if (p == null)
			return 0;
		else if (p.left == null && p.right == null)
			return 1;
		else
			return countLeaves(p.left) + countLeaves(p.right);
	}

	// This method restores a BST given preorder and inorder traversals
	public void restore(T[] pre, T[] in) {
		root = restore(pre, 0, pre.length - 1, in, 0, in.length - 1);
	}

	private Node<T> restore(T[] pre, int preL, int preR, T[] in, int inL, int inR) {
		if (preL <= preR) {
			int count = 0;
			// find the root in the inorder array
			while (pre[preL] != in[inL + count])
				count++;

			Node<T> tmp = new Node<T>(pre[preL]);
			tmp.left = restore(pre, preL + 1, preL + count, in, inL, inL + count - 1);
			tmp.right = restore(pre, preL + count + 1, preR, in, inL + count + 1, inR);
			return tmp;
		} else
			return null;
	}

	// The width of a binary tree is the maximum number of elements on one level
	// of the tree.
	public int width() {
		int max = 0;
		for (int k = 0; k <= height(); k++) {
			int tmp = width(root, k);
			if (tmp > max)
				max = tmp;
		}
		return max;
	}

	// rerturns the number of node on a given level
	public int width(Node<T> p, int depth) {
		if (p == null)
			return 0;
		else if (depth == 0)
			return 1;
		else
			return width(p.left, depth - 1) + width(p.right, depth - 1);
	}

	// The diameter of a tree is the number of nodes
	// on the longest path between two leaves in the tree.
	public int diameter() {
		return diameter(root);
	}

	private int diameter(Node<T> p) {
		if (p == null)
			return 0;

		// the path goes through the root
		int len1 = height(p.left) + height(p.right) + 3;

		// the path does not pass the root
		int len2 = Math.max(diameter(p.left), diameter(p.right));

		return Math.max(len1, len2);
	}

	/*****************************************************
	 *
	 * TREE ITERATOR
	 *
	 ******************************************************/

	public Iterator<T> iterator() {
		return new MyIterator();
	}

	// pre-order
	private class MyIterator implements Iterator<T> {
		Stack<Node<T>> stk = new Stack<Node<T>>();

		public MyIterator() {
			if (root != null)
				stk.push(root);
		}

		public boolean hasNext() {
			return !stk.isEmpty();
		}

		public T next() {
			Node<T> cur = stk.peek();
			if (cur.left != null) {
				stk.push(cur.left);
			} else {
				Node<T> tmp = stk.pop();
				while (tmp.right == null) {
					if (stk.isEmpty())
						return cur.data;
					tmp = stk.pop();
				}
				stk.push(tmp.right);
			}

			return cur.data;
		}// end of next()

		public void remove() {

		}
	}// end of MyIterator

	/*****************************************************
	 *
	 * the Node class
	 *
	 ******************************************************/

	private class Node<T> {
		private T data;
		private Node<T> left, right;

		public Node(T data, Node<T> l, Node<T> r) {
			left = l;
			right = r;
			this.data = data;
		}

		public Node(T data) {
			this(data, null, null);
		}

		public String toString() {
			return data.toString();
		}
	} // end of Node
}// end of BST

class MyComp1 implements Comparator<Integer> {
	public int compare(Integer x, Integer y) {
		return y - x;
	}
}